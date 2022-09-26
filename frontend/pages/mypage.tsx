import contracts from "@/contracts/utils";

import styled from "@emotion/styled";
import { css, useTheme } from "@emotion/react";
import { faWallet } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import img from "@/assets/nfts/animals/1.png";
import nft1 from "@/assets/nfts/1.png";
import nft2 from "@/assets/nfts/2.png";
import nft3 from "@/assets/nfts/3.png";

import { useNFTModal } from "@/hooks";

import Avatar from "@/components/Avatar";
import Text from "@/components/Text";
import Grid from "@/components/Grid";
import NFTItemCard from "@/components/Card/NFTItemCard";
import SelectTab from "@/components/SelectTab";
import Modal from "@/components/Modal";
import Button from "@/components/Button";

import { minTabletWidth } from "@/styles/utils";

import type { Member, NFTItem } from "@/types/api_responses";

function MyPage() {
  const theme = useTheme();
  const { account, setAccount } = contracts.useAccount();
  const { isShowModal, modalContent, handleModalClose, handleModalOpen } =
    useNFTModal();
  const member: Member = {
    id: 1,
    name: "tester",
    profileImage: img,
    nickname: "Anonymous",
    walletAddress: account,
    email: "test@gmail.com",
    memberType: "USER",
  };
  const nfts: NFTItem[] = [
    {
      companyId: 1,
      nftImage: nft1,
      tokenId: 1243,
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
    {
      companyId: 2,
      nftImage: nft2,
      tokenId: 1243,
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
    {
      companyId: 3,
      nftImage: nft3,
      tokenId: 1243,
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
  ];

  const handleModalSubmit = () => {
    // 보유 NFT 목록일 경우
    // discordUrl로 이동
    // 예약 목록일 경우
    // 해당 /list/:companyId로 이동
    handleModalClose();
  };

  return (
    <>
      <Background />
      <ProfileContainer>
        <Avatar
          image={member.profileImage}
          css={css`
            margin-left: 1vw;
            width: 6rem;
            height: 6rem;

            @media (${minTabletWidth}) {
              width: 10rem;
              height: 10rem;
            }
          `}
        />
        <Text
          as="h1"
          role="member-nickname"
          css={css`
            margin-top: 2rem;
            font-size: 2rem;
            font-weight: 700;
          `}
        >
          {member.nickname}
        </Text>
        <Text
          as="h2"
          role="member-walletAddress"
          css={css`
            color: ${theme.color.text.sub};
            display: flex;
          `}
        >
          <FontAwesomeIcon
            icon={faWallet}
            css={css`
              width: 1rem;
              margin-right: 0.5rem;
            `}
          />
          {member.walletAddress.substring(0, 5) +
            "..." +
            member.walletAddress.substring(member.walletAddress.length - 4)}
        </Text>
      </ProfileContainer>

      <SelectTab
        menus={["보유 NFT", "관심목록", "구매내역"]}
        css={css`
          margin-top: 2rem;
        `}
      />

      <Grid column="double">
        {nfts.map((nft1: NFTItem) => {
          const { companyId, corpName, nftImage, price, progress, tokenId } =
            nft1;
          return (
            <NFTItemCard
              key={companyId}
              nftImage={nftImage}
              tokenId={tokenId}
              corpName={corpName}
              price={price}
              progress={progress}
              onClick={() => handleModalOpen(nft1)}
            />
          );
        })}
      </Grid>
      <Modal
        isOpen={isShowModal}
        onCancel={handleModalClose}
        onSubmit={handleModalSubmit}
      >
        <div>
          {modalContent?.corpName}
          <Button onClick={handleModalSubmit}>어디로 이동하는 버튼</Button>
        </div>
      </Modal>
    </>
  );
}

const ProfileContainer = styled.div`
  position: relative;
  width: 100%;
  margin-top: 11rem;

  @media (${minTabletWidth}) {
    margin-top: 9rem;
  }
`;
const Background = styled.div`
  position: absolute;
  width: 100vw;
  height: 15rem;
  background-color: ${({ theme }) => theme.color.background.item};
  opacity: 50%;
`;

export default MyPage;
