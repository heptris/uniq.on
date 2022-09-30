import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";

import SelectTab from "@/components/SelectTab";
import Button from "@/components/Button";
import Card from "@/components/Card";
import Text from "@/components/Text";
import { minDesktopWidth } from "@/styles/utils";
import { useSelectTab } from "@/hooks";

const dummyData = {
  checked: [
    {
      text: "A 기업 Q&A에 댓글이 달렸어요! 확인 버튼을 눌러 해당 페이지로 이동하세요",
      date: "14일 전",
    },
    {
      text: "회원가입이 완료되었습니다. 가입 축하드립니다.",
      date: "30일 전",
    },
  ],
  unchecked: [
    {
      text: "NFT가 발급되었어요! 마이페이지에서 확인해주세요.",
      date: "1일 전",
    },
    {
      text: "예약한 펀딩이 성공 했어요! 확인 버튼을 눌러 NFT 구매를 진행해주세요",
      date: "10일 전",
    },
  ],
};

export default function alarm() {
  const theme = useTheme();
  const menus = ["읽지 않은 알림", "확인한 알림"];
  const { selectedMenu, onSelectHandler } = useSelectTab(menus);

  return (
    <AlarmContainer>
      <SelectTab menus={menus} onSelectHandler={onSelectHandler} />
      {(selectedMenu === "읽지 않은 알림"
        ? dummyData.checked
        : dummyData.unchecked
      ).map((alarm: { text: string; date: string }) => (
        <Card
          css={css`
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 6rem;
            margin-bottom: 0.5rem;
            padding: 0 1rem;
            font-weight: 600;

            @media (${minDesktopWidth}) {
              padding: 0;
              margin-bottom: 1rem;
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
              {alarm.text}
            </Text>
            <Text
              as="p"
              role="alarm-date"
              css={css`
                font-size: 0.8rem;
                color: ${theme.color.text.hover};
              `}
            >
              {alarm.date}
            </Text>
          </TextContainer>
          <Button>확인</Button>
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
    width: 50%;
  }
`;
const TextContainer = styled.div`
  width: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
