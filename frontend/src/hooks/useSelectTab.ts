import { useState } from "react";

export const useSelectTab = (menus: string[]) => {
  const [selectedMenu, setSelectedMenu] = useState(menus[0]);
  const onSelectHandler = (selectedMenu: string) => {
    setSelectedMenu(selectedMenu);
  };

  return { selectedMenu, onSelectHandler };
};
