import Button from "@/components/Button";
import Container from "@/components/Container";
import Footer from "@/components/Footer";
import Navbar from "@/components/Navbar";
import { css } from "@emotion/css";
import { useTheme } from "@emotion/react";

export default function login() {
  const theme = useTheme();
  return (
    <Container>
      <Navbar />
      <div
        className={css`
          width: 100%;
          padding: 2rem 0;
          flex: 1 1;
          display: flex;
          flex-direction: column;
          justify-content: flex-start;
          align-items: center;
          color: ${theme.color.text.main};
        `}
      >
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
      </div>
      <Footer />
    </Container>
  );
}
