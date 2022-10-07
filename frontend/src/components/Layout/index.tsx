import { useEffect } from "react";
import { useTheme } from "@emotion/react";

import { useAppSelector, useAuth, useServer } from "@/hooks";

import Alert from "../Alert";
import Container from "../Container";
import Footer from "../Footer";
import Navbar from "../Navbar";
import { useRouter } from "next/router";

export default function Layout({ children }: { children: React.ReactNode }) {
  const { isAlertOn, isSuccess, message } = useAppSelector(
    (state) => state.alert
  );
  const { silentRefresh, isLogined } = useAuth();
  const theme = useTheme();
  const { handleUnreadAlarm } = useServer();
  const router = useRouter();

  useEffect(() => {
    document.body.style.backgroundColor = theme.color.background.page;
    document.body.style.color = theme.color.text.main;
    silentRefresh();
  }, []);

  useEffect(() => {
    isLogined && handleUnreadAlarm();
    console.log("페이지 이동 체크");
  }, [router.asPath]);

  return (
    <Container>
      <Navbar />
      {isAlertOn ? <Alert isSuccess={isSuccess} message={message} /> : null}
      {children}
      <Footer />
    </Container>
  );
}
