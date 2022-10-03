import { GetServerSideProps } from "next";
import axios from "axios";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { minDesktopWidth, minTabletWidth } from "@/styles/utils";

import SelectTab from "@/components/SelectTab";
import Button from "@/components/Button";
import Card from "@/components/Card";
import Text from "@/components/Text";

import { useSelectTab } from "@/hooks";
import { ENDPOINT_API } from "@/api/endpoints";

import { AlarmItem } from "@/types/api_responses";
type AlarmProps = {
  unreadAlarmList: AlarmItem[];
  readAlarmList: AlarmItem[];
};

export const getServerSideProps: GetServerSideProps = async () => {
  const alarmList = await axios
    .get(`${ENDPOINT_API}/alarm/alarmList`)
    .then(({ data }) => data.data);
  const unreadAlarmList: AlarmItem[] = [];
  const readAlarmList: AlarmItem[] = [];
  alarmList.forEach((alarm: AlarmItem) =>
    alarm.read ? readAlarmList.push(alarm) : unreadAlarmList.push(alarm)
  );
  return { props: { unreadAlarmList, readAlarmList } };
};

export default function alarm(props: AlarmProps) {
  const { readAlarmList, unreadAlarmList } = props;
  const theme = useTheme();
  const menus = ["읽지 않은 알림", "확인한 알림"];
  const { selectedMenu, onSelectHandler } = useSelectTab(menus);

  return (
    <AlarmContainer>
      <SelectTab menus={menus} onSelectHandler={onSelectHandler} />
      {(selectedMenu === "읽지 않은 알림"
        ? unreadAlarmList
        : readAlarmList
      ).map((alarm: AlarmItem) => (
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
              {alarm.content}
            </Text>
            <Text
              as="p"
              role="alarm-date"
              css={css`
                font-size: 0.8rem;
                color: ${theme.color.text.hover};
              `}
            >
              {alarm.read ? "읽음" : "안읽음"}
            </Text>
          </TextContainer>
          {selectedMenu === "읽지 않은 알림" ? <Button>확인</Button> : <></>}
        </Card>
      ))}
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
