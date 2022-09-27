import { useEffect } from "react";

import { useTheme } from "@emotion/react";

import Container from "../Container";
import Footer from "../Footer";
import Navbar from "../Navbar";

export default function Layout({ children }: { children: React.ReactNode }) {
  const theme = useTheme();
  useEffect(() => {
    document.body.style.backgroundColor = theme.color.background.page;
  }, []);

  return (
    <Container>
      <Navbar />
      {children}
      <Footer />
    </Container>
  );
}
