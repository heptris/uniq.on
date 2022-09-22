import Container from "../Container";
import Footer from "../Footer";
import Navbar from "../Navbar";
import { css } from "@emotion/css";
import { useTheme } from "@emotion/react";

export default function Layout({ children }: { children: React.ReactNode }) {
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
        {children}
      </div>
      <Footer />
    </Container>
  );
}
