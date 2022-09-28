import { useAppSelector } from "@/hooks";
import Alert from "../Alert";
import Container from "../Container";
import Footer from "../Footer";
import Navbar from "../Navbar";

export default function Layout({ children }: { children: React.ReactNode }) {
  const { isAlertOn, isSuccess, message } = useAppSelector(
    (state) => state.alert
  );
  return (
    <Container>
      <Navbar />
      {isAlertOn ? <Alert isSuccess={isSuccess} message={message} /> : null}
      {children}
      <Footer />
    </Container>
  );
}
