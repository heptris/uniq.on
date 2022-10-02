import { MypageListType } from "@/types/props";
import { useState } from "react";

export const useNFTModal = () => {
  const [isShowModal, setIsShowModal] = useState(false);
  const [modalContent, setModalContent] = useState<MypageListType>();
  const onSwitchModalHandler = () => {
    setIsShowModal((isShowModal) => !isShowModal);
  };
  const handleModalOpen = (type: MypageListType) => {
    setModalContent(type);
    onSwitchModalHandler();
  };
  const handleModalClose = () => onSwitchModalHandler();
  return {
    isShowModal,
    handleModalClose,
    handleModalOpen,
    modalContent,
    setModalContent,
  };
};
