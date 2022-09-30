import { useAppSelector, useServerState } from "@/hooks";
import Video from "@/components/Video";

export default function Home() {
  const { isLogined } = useAppSelector((state) => state.auth);

  isLogined && useServerState();

  return <Video />;
}
