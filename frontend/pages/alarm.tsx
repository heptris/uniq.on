import Image from "next/image";

import Container from "@/components/Container";
import Navbar from "@/components/Navbar";
import SelectTab from "@/components/SelectTab";

import { useEffect, useState } from "react";
import { css } from "@emotion/css";
import { useTheme } from "@emotion/react";
import Button from "@/components/Button";
import Footer from "@/components/Footer";
import Layout from "@/components/Layout";

const dummyData = {
  checked: [
    {
      text: "NFT가 발급되었어요! 마이페이지에서 확인해주세요.",
      date: "1일 전",
    },
    {
      text: "예약한 펀딩이 성공 했어요! 확인 버튼을 눌러 NFT 구매를 진행해주세요",
      date: "14일 전",
    },
  ],
  unchecked: [
    {
      text: "A 기업 Q&A에 댓글이 달렸어요! 확인 버튼을 눌러 해당 페이지로 이동하세요",
      date: "10일 전",
    },
    {
      text: "회원가입이 완료되었습니다. 가입 축하드립니다.",
      date: "30일 전",
    },
  ],
};

export default function alarm() {
  const theme = useTheme();
  const menus = ["확인한 알림", "읽지 않은 알림"];
  const [select, setSelect] = useState(menus[0]);
  const [checked, setChecked] = useState(true);

  const selectHandler = (val: string) => {
    setSelect(val);
  };

  useEffect(() => {
    select === "확인한 알림" ? setChecked(true) : setChecked(false);
  }, [select]);

  return (
    <Layout>
      <SelectTab func={selectHandler} menus={menus} />
      {(checked ? dummyData.checked : dummyData.unchecked).map(
        (alarm: { text: any; date: any }) => (
          <div
            className={css`
              display: flex;
              flex-direction: row;
              justify-content: center;
              align-items: center;
              width: 100%;
              height: 6rem;
              margin-top: 2rem;
              padding: 0 0.5rem;
              border-radius: 10px;
              background-color: ${theme.color.background.item};
              color: ${theme.color.text.main};
            `}
          >
            <div
              className={css`
                display: flex;
                flex-direction: column;
                justify-content: center;
                width: 80%;
              `}
            >
              <p
                className={css`
                  margin-bottom: 0.5rem;
                  overflow: hidden;
                  text-overflow: ellipsis;
                  line-height: 1.2em;
                `}
              >
                {alarm.text}
              </p>
              <p
                className={css`
                  font-size: 0.8rem;
                  color: ${theme.color.text.hover};
                `}
              >
                {alarm.date}
              </p>
            </div>
            <Button>확인</Button>
          </div>
        )
      )}
    </Layout>
  );
}
