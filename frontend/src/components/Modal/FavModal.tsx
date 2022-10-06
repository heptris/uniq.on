import { minTabletWidth } from "@/styles/utils";
import { MypageListType } from "@/types/props";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import Link from "next/link";
import Avatar from "../Avatar";
import Button from "../Button";
import ProgressBar from "../ProgressBar";
import Text from "../Text";

type FavModalType = {
  handleModalSubmit: () => void;
  modalContent: MypageListType;
};

export default function FavModal(props: FavModalType) {
  const theme = useTheme();
  const { handleModalSubmit, modalContent } = props;
  return (
    <ModalWrapper>
      <Avatar
        image={modalContent.nftImage}
        css={css`
          width: 6rem;
          height: 6rem;
          margin-bottom: 2rem;

          @media (${minTabletWidth}) {
            width: 10rem;
            height: 10rem;
          }
        `}
      />
      <Text
        role="corp-name"
        css={css`
          font-weight: 600;
        `}
      >
        {modalContent.startupName} #{modalContent.startupId}
      </Text>

      <Text
        css={css`
          color: ${theme.color.text.sub};
          margin-bottom: 1rem;
        `}
      >
        {modalContent.nftDescription}
      </Text>
      <ProgressWrapper>
        <ProgressBar
          css={css`
            margin-right: 5px;
          `}
          progress={
            (modalContent.nftReserveCount / modalContent.nftTargetCount!) * 100
          }
          type={"blue"}
        />
        <Text
          css={css`
            font-size: 0.7rem;
            font-weight: 600;
          `}
        >
          {(modalContent.nftReserveCount / modalContent.nftTargetCount!) * 100}%
        </Text>
      </ProgressWrapper>
      <Link href={`/list/${encodeURIComponent(modalContent.startupId)}`}>
        <Button onClick={handleModalSubmit}>상세페이지 이동</Button>
      </Link>
    </ModalWrapper>
  );
}

const ModalWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
const ProgressWrapper = styled.div`
  display: flex;
  justify-content: center;
  align-items: center;
  margin-bottom: 2rem;
`;
