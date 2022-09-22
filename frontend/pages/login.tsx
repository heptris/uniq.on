import Button from "@/components/Button";
import Layout from "@/components/Layout";
import { css } from "@emotion/css";
import { useTheme } from "@emotion/react";

export default function login() {
  const theme = useTheme();
  return (
    <Layout>
      <div
        className={css`
          margin: 4rem 0rem 5rem;
          font-weight: bold;
          font-size: 40px;
        `}
      >
        <span
          className={css`
            color: ${theme.color.background.main};
          `}
        >
          uniq
        </span>
        .on
      </div>
      <div
        className={css`
          margin-bottom: 5rem;
          border: solid 1px ${theme.color.text.main};
          padding: 1rem;
          border-radius: 1rem;
        `}
      >
        input 들어갈곳(지갑을 연결해주세요)
      </div>
      <Button>지갑 연결하기</Button>
    </Layout>
  );
}
