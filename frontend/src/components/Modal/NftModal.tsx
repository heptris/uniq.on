import { minTabletWidth } from "@/styles/utils";
import { MypageListType } from "@/types/props";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import Link from "next/link";
import Avatar from "../Avatar";
import Button from "../Button";
import ProgressBar from "../ProgressBar";
import Text from "../Text";

type NftModalType = {
  handleModalSubmit: () => void;
  modalContent: MypageListType;
};

export default function NftModal(props: NftModalType) {
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
          margin-bottom: 2rem;
        `}
      >
        {modalContent.nftDescription}
      </Text>
      <ButtonWrapper>
        <Link
          href={`/community/${modalContent.startupId}/${modalContent.startupName}`}
        >
          <Button
            type="blue"
            onClick={handleModalSubmit}
            css={css`
              margin-right: 1rem;
            `}
          >
            커뮤니티 입장
          </Button>
        </Link>
        <Link href={`/list/${encodeURIComponent(modalContent.startupId)}`}>
          <Button type="blue" onClick={handleModalSubmit}>
            거래 상세내역
          </Button>
        </Link>
      </ButtonWrapper>
    </ModalWrapper>
  );
}

const ModalWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;
const ButtonWrapper = styled.div`
  width: 100%;
  display: flex;
  flex-direction: row;
  justify-content: center;
`;
