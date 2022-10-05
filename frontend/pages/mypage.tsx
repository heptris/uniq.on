import { useEffect, useState } from "react";
import { GetServerSideProps } from "next";

import styled from "@emotion/styled";
import { css, useTheme } from "@emotion/react";
import { faWallet } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { minTabletWidth } from "@/styles/utils";

import {
  useNFTModal,
  useSelectTab,
  getApplyList,
  getFavList,
  getReserveList,
  getUserInfo,
  useUserQueries,
} from "@/hooks";

import Avatar from "@/components/Avatar";
import Text from "@/components/Text";
import SelectTab from "@/components/SelectTab";
import Modal from "@/components/Modal";
import MypageListContainer from "@/container/MypageListContainer";

import { MyPageProps } from "@/types/props";
import { useRouter } from "next/router";
import FavModal from "@/components/Modal/FavModal";
import NftModal from "@/components/Modal/NftModal";
import { ROUTES } from "@/constants";

import { NFTItem } from "@/types/api_responses";
import contracts from "@/contracts/utils";
import axios from "axios";

const { HOME } = ROUTES;

// export const getServerSideProps: GetServerSideProps = async () => {
//   return { props: { myNftList } };
// };

function MyPage() {
  // props: { myNftList: string[] }
  // props: MyPageProps
  const theme = useTheme();
  const { isShowModal, modalContent, handleModalClose, handleModalOpen } =
    useNFTModal();
  const { useWeb3, useAccount } = contracts;
  const { mintUniqonNFTContract } = useWeb3();
  const { account } = useAccount();
  const [nfts, setNfts] = useState<string[]>([]);
  // const { myNftList: nfts } = props;
  const handleNftList = async () => {
    const myNftList: string[] = await mintUniqonNFTContract?.methods
      .getOwnedTokens(account)
      .call();
    // console.log(account, myNftList);
    setNfts(myNftList);
    // const file = await ipfs.get(myNftList[0]);

    // console.log(file);
    axios
      .get(myNftList[0])
      .then((res) => {
        console.log(res);
      })
      .catch((err) => console.error(err));
  };
  useEffect(() => {
    mintUniqonNFTContract && handleNftList();
  }, [mintUniqonNFTContract]);

  const [
    { data: member, isLoading: isMemberLoading },
    { data: applyList, isLoading: isApplyLoading },
    { data: favoriteList, isLoading: isFavLoading },
    { data: reserveList, isLoading: isReserveLoading },
  ] = useUserQueries();
  const menus = ["보유 NFT", "관심목록", "예약내역", "투자신청내역"];
  const { selectedMenu, onSelectHandler } = useSelectTab(menus);

  // const router = useRouter();

  if (isMemberLoading || isApplyLoading || isFavLoading || isReserveLoading) {
    return <div>Loading...</div>;
  }

  const { email, id, memberType, name, nickname, profileImage, walletAddress } =
    member!;

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
          image={profileImage}
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
          {nickname}
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
          {walletAddress.substring(0, 5) +
            "..." +
            walletAddress.substring(walletAddress.length - 4)}
        </Text>
      </ProfileContainer>

      <SelectTab
        menus={menus}
        onSelectHandler={onSelectHandler}
        css={css`
          margin-top: 2rem;
        `}
      />

      {nfts.map((el, i) => (
        <div key={i}>{el}</div>
      ))}
      {/* {selectedMenu === menus[0] &&
        // <MypageListContainer handleModalOpen={handleModalOpen} nfts={nfts} />
      {selectedMenu === menus[1] && (
        <MypageListContainer
          handleModalOpen={handleModalOpen}
          favs={favoriteList}
        />
      )}
      {selectedMenu === menus[2] && (
        <MypageListContainer
          handleModalOpen={handleModalOpen}
          rsrvs={reserveList}
        />
      )}
      {selectedMenu === menus[3] && (
        <MypageListContainer
          handleModalOpen={handleModalOpen}
          applys={applyList}
        />
      )} */}

      <Modal
        isOpen={isShowModal}
        onCancel={handleModalClose}
        onSubmit={handleModalSubmit}
      >
        {selectedMenu === menus[1] && modalContent && (
          <FavModal
            handleModalSubmit={handleModalSubmit}
            modalContent={modalContent}
          />
        )}
        {selectedMenu === menus[2] && modalContent && (
          <NftModal
            handleModalSubmit={handleModalSubmit}
            modalContent={modalContent}
          />
        )}
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
