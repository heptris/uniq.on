import { NFTItem } from "@/types/api_responses";
import { useState } from "react";

export const useNFTModal = () => {
  const [isShowModal, setIsShowModal] = useState(false);
  const [modalContent, setModalContent] = useState<NFTItem>();
  const onSwitchModalHandler = () => {
    setIsShowModal((isShowModal) => !isShowModal);
  };
  const handleModalOpen = (nft: NFTItem) => {
    setModalContent(nft);
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
