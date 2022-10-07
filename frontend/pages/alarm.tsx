import { GetServerSideProps } from "next";
import axios from "axios";
import contracts from "@/contracts/utils";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { minDesktopWidth, minTabletWidth } from "@/styles/utils";

import SelectTab from "@/components/SelectTab";
import Button from "@/components/Button";
import Card from "@/components/Card";
import Text from "@/components/Text";

import {
  getAlarmList,
  useAlarm,
  useAlarmMutation,
  useAlert,
  useSelectTab,
  useServer,
} from "@/hooks";

import { AlarmItem } from "@/types/api_responses";
import { AlarmProps } from "@/types/props";
import { ACCESS_TOKEN } from "@/api/utils";
import { ENDPOINT_API } from "@/api/endpoints";

export const getServerSideProps: GetServerSideProps = async (context) => {
  const config = {
    headers: { authorization: `Bearer ${context.req.cookies[ACCESS_TOKEN]}` },
  };
  const alarmList = await getAlarmList(config);
  return { props: { alarmList } };
};

export default function alarm(props: AlarmProps) {
  const { data: alarmList, refetch } = useAlarm(props);
  const { mutate: mutateAlarmRead } = useAlarmMutation();
  const { handleUnreadAlarm } = useServer();

  const unreadAlarmList: AlarmItem[] = [];
  const readAlarmList: AlarmItem[] = [];
  alarmList.forEach((alarm) =>
    alarm.read ? readAlarmList.push(alarm) : unreadAlarmList.push(alarm)
  );

  const theme = useTheme();
  const menus = ["읽지 않은 알림", "확인한 알림"];
  const { selectedMenu, onSelectHandler } = useSelectTab(menus);
  const { handleAlertOpen } = useAlert();

  const { mintUniqonNFTContract } = contracts.useWeb3();
  const { mintToken, buyToken } = contracts.useContract();
  const getAlarmType = (alarm: AlarmItem) => {
    if (alarm.investCount) return "MINT";
    if (alarm.tokenId) return "BUY";
    return "TEXT";
  };
  const handleAlarmRead = async (alarm: AlarmItem) => {
    const { alarmId } = alarm;
    const alarmType = getAlarmType(alarm);
    // nft 구매 로직 or
    // nft 발행 로직
    if (alarmType === "MINT") {
      const res = await mintToken({
        tokenURI: alarm.tokenURI!, // 추가해야할 부분
        totalAmount: alarm.investCount!,
        price: alarm.nftPrice!,
      });
      const lastTokenId: number = await mintUniqonNFTContract?.methods
        .current()
        .call();
      console.log([`mintToken result: ${res}`, `lastTokenId: ${lastTokenId}`]);

      const data = { lastTokenId };
      axios
        .post(ENDPOINT_API + `/alarm/mintSuccess/${alarmId}`, data)
        .then((res) => {
          console.log(`axios result: ${res}`);
        })
        .catch((err) => console.error(err));
    } else if (alarmType === "BUY") {
      const res = await buyToken(alarm.tokenId!);
      console.log(`buyToken result: ${res}`);

      if (!res) {
        await axios.post(ENDPOINT_API + `/alarm/investFail/${alarmId}`);
        return;
      }

      axios
        .post(ENDPOINT_API + `/alarm/investSuccess/${alarmId}`)
        .then((res) => {
          console.log(`axios result: ${res}`);
        })
        .catch((err) => console.error(err));
    }

    mutateAlarmRead(
      { alarmId },
      {
        onSuccess: () => handleAlertOpen(2000, "읽음 처리 완료", true),
        onSettled: () => (refetch(), handleUnreadAlarm()),
      }
    );
  };

  return (
    <AlarmContainer>
      <SelectTab menus={menus} onSelectHandler={onSelectHandler} />
      {(selectedMenu === "읽지 않은 알림"
        ? unreadAlarmList
        : readAlarmList
      ).map((alarm: AlarmItem) => {
        const { read, content, alarmId } = alarm;
        return (
          <Card
            css={css`
              display: flex;
              justify-content: center;
              align-items: center;
              width: 100%;
              height: 6rem;
              margin-bottom: 3vw;
              padding: 0 1rem;
              font-weight: 500;

              @media (${minTabletWidth}) {
                padding: 0;
                margin-bottom: 1vw;
              }
            `}
          >
            <TextContainer>
              <Text
                as="p"
                role="alarm-text"
                css={css`
                  overflow: hidden;
                  word-break: keep-all;
                  text-overflow: ellipsis;
                `}
              >
                {content}
              </Text>
              <Text
                as="p"
                role="alarm-date"
                css={css`
                  font-size: 0.8rem;
                  color: ${theme.color.text.hover};
                `}
              ></Text>
            </TextContainer>
            <Button
              disabled={read}
              onClick={() => {
                read || handleAlarmRead(alarm);
              }}
            >
              {read ? "읽음" : "확인"}
            </Button>
          </Card>
        );
      })}
    </AlarmContainer>
  );
}

const AlarmContainer = styled.div`
  padding-top: 5rem;
  width: 100%;
  display: flex;
  flex-direction: column;

  @media (${minDesktopWidth}) {
    width: 33.33%;
  }
`;
const TextContainer = styled.div`
  width: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
