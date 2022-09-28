import { useAppSelector } from "@/hooks";
import Alert from "../Alert";
import { useEffect } from "react";

import { useTheme } from "@emotion/react";


import Container from "../Container";
import Footer from "../Footer";
import Navbar from "../Navbar";

export default function Layout({ children }: { children: React.ReactNode }) {

  const { isAlertOn, isSuccess, message } = useAppSelector(
    (state) => state.alert
  );

  const theme = useTheme();
  useEffect(() => {
    document.body.style.backgroundColor = theme.color.background.page;
  }, []);


  return (
    <Container>
      <Navbar />
      {isAlertOn ? <Alert isSuccess={isSuccess} message={message} /> : null}
      {children}
      <Footer />
    </Container>
  );
}
