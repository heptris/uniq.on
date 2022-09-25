import { useState } from "react";

export const useModal = () => {
  const [isShowModal, setIsShowModal] = useState(false);
  const onSwitchModalHandler = () => {
    setIsShowModal((isShowModal) => !isShowModal);
  };
  return { isShowModal, onSwitchModalHandler };
};
