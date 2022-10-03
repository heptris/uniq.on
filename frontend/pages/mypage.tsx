import { useEffect, useState } from "react";
import { GetServerSideProps } from "next";
import axios from "axios";

import styled from "@emotion/styled";
import { css, useTheme } from "@emotion/react";
import { faWallet } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { minTabletWidth } from "@/styles/utils";

import { ENDPOINT_API } from "@/api/endpoints";

import { useNFTModal, useSelectTab } from "@/hooks";

import Avatar from "@/components/Avatar";
import Text from "@/components/Text";
import SelectTab from "@/components/SelectTab";
import Modal from "@/components/Modal";
import Button from "@/components/Button";
import MypageListContainer from "@/container/MypageListContainer";

import { APPLYItem, FAVItem, NFTItem, RSRVItem } from "@/types/api_responses";
type MyPageProps = {
  member: any;
  applyList: any;
  favoriteList: any;
  reserveList: any;
};

export const getServerSideProps: GetServerSideProps = async () => {
  const member = await axios.get(`${ENDPOINT_API}/member`).then(({ data }) => {
    return { ...data.data, nickname: data.data.nickName };
  });
  const applyList = await axios
    .get(`${ENDPOINT_API}/member/mypage/startup`)
    .then(({ data }) => data.data);
  const favoriteList = await axios
    .get(`${ENDPOINT_API}/member/mypage/favstartup`)
    .then(({ data }) => data.data);
  const reserveList = await axios
    .get(`${ENDPOINT_API}/member/mypage/invest`)
    .then(({ data }) => data.data);

  return { props: { member, applyList, favoriteList, reserveList } };
};

function MyPage(props: MyPageProps) {
  const theme = useTheme();
  const { isShowModal, modalContent, handleModalClose, handleModalOpen } =
    useNFTModal();
  const [nfts, setNfts] = useState<NFTItem[]>([]);
  const [favs, setFavs] = useState<FAVItem[]>([]);
  const [rsrvs, setRsrvs] = useState<RSRVItem[]>([]);
  const [applys, setApplys] = useState<APPLYItem[]>([]);

  const { member, applyList, favoriteList, reserveList } = props;

  const menus = ["보유 NFT", "관심목록", "예약내역", "투자신청내역"];
  const { selectedMenu, onSelectHandler } = useSelectTab(menus);

  useEffect(() => {
    switch (selectedMenu) {
      case menus[0]:
        // nftList && setNfts(nftList);
        break;
      case menus[1]:
        setFavs(favoriteList);
        break;
      case menus[2]:
        setRsrvs(reserveList);
        break;
      case menus[3]:
        setApplys(applyList);
        break;
    }
  }, [selectedMenu]);

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
        menus={menus}
        onSelectHandler={onSelectHandler}
        css={css`
          margin-top: 2rem;
        `}
      />

      {selectedMenu === menus[0] && (
        <MypageListContainer handleModalOpen={handleModalOpen} nfts={nfts} />
      )}
      {selectedMenu === menus[1] && (
        <MypageListContainer handleModalOpen={handleModalOpen} favs={favs} />
      )}
      {selectedMenu === menus[2] && (
        <MypageListContainer handleModalOpen={handleModalOpen} rsrvs={rsrvs} />
      )}
      {selectedMenu === menus[3] && (
        <MypageListContainer
          handleModalOpen={handleModalOpen}
          applys={applys}
        />
      )}

      <Modal
        isOpen={isShowModal}
        onCancel={handleModalClose}
        onSubmit={handleModalSubmit}
      >
        <div>
          {modalContent?.startupName}
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
