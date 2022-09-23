import Container from "../Container";
import Footer from "../Footer";
import Navbar from "../Navbar";
import { useTheme, css } from "@emotion/react";

export default function Layout({ children }: { children: React.ReactNode }) {
  const theme = useTheme();
  return (
    <Container>
      <Navbar />
      {children}
      <Footer />
    </Container>
  );
}
