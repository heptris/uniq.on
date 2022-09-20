import Navbar from "@/components/Navbar";
import SelectTab from "@/components/SelectTab";
import { css } from "@emotion/css";

import { useTheme } from "@emotion/react";
import { useState } from "react";

export default function alarm() {
  const theme = useTheme();
  const menus = ["확인한 알림", "읽지 않은 알림"];
  const [select, setSelect] = useState(menus[0]);
  const selectHandler = (val: string) => {
    setSelect(val);
    console.log(select);
  };
  return (
    <>
      <main
        className={css`
          background-color: ${theme.color.background.page};
        `}
      >
        <Navbar />
        <SelectTab func={selectHandler} menus={menus} />
      </main>
    </>
  );
}
