import Container from "../Container";
import Footer from "../Footer";
import Navbar from "../Navbar";
import { useTheme, css } from "@emotion/react";

export default function Layout({ children }: { children: React.ReactNode }) {
  const theme = useTheme();
  return (
    <Container
      css={css`
        padding: 0;
      `}
    >
      <Navbar />
      <div
        css={css`
          width: 100%;
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
