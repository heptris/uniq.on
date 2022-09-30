import styled from "@emotion/styled";
import { css, useTheme } from "@emotion/react";
import { faWallet } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import Avatar from "@/components/Avatar";
import Text from "@/components/Text";
import SelectTab from "@/components/SelectTab";
import Modal from "@/components/Modal";
import Button from "@/components/Button";

import { minTabletWidth } from "@/styles/utils";
import { useNFTModal, useSelectTab } from "@/hooks";
import { Member, NFTItem } from "@/types/api_responses";

import MypageListContainer from "@/container/MypageListContainer";
import { useQueryClient } from "@tanstack/react-query";
import { QUERY_KEYS } from "@/api/query_key_schema";
import { useEffect, useState } from "react";
import { useRouter } from "next/router";

const {
  MY_USER_INFO,
  MY_APPLY_LIST,
  MY_FAVORITE_LIST,
  MY_NFT_LIST,
  MY_RESERVE_LIST,
} = QUERY_KEYS;

function MyPage() {
  const theme = useTheme();
  const client = useQueryClient();
  const router = useRouter();

  const { isShowModal, modalContent, handleModalClose, handleModalOpen } =
    useNFTModal();

  const [nfts, setNfts] = useState<NFTItem[]>([]);

  const menus = ["보유 NFT", "관심목록", "예약내역", "투자신청내역"];
  const { selectedMenu, onSelectHandler } = useSelectTab(menus);

  const member = client.getQueryData<Member>([MY_USER_INFO]);
  if (!member) {
    router.push("/");
    return <Text>Loading...</Text>;
  }

  useEffect(() => {
    switch (selectedMenu) {
      case menus[0]:
        const nftList = client.getQueryData<NFTItem[]>([MY_NFT_LIST]);
        nftList && setNfts(nftList);
        break;
      case menus[1]:
        const favList = client.getQueryData<NFTItem[]>([MY_FAVORITE_LIST]);
        favList && setNfts(favList);
        break;
      case menus[2]:
        const rsrvList = client.getQueryData<NFTItem[]>([MY_RESERVE_LIST]);
        rsrvList && setNfts(rsrvList);
        break;
      case menus[3]:
        const applyList = client.getQueryData<NFTItem[]>([MY_APPLY_LIST]);
        applyList && setNfts(applyList);
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

      <MypageListContainer
        handleModalOpen={handleModalOpen}
        nfts={nfts}
        mypageListType={"FAVOTIRES"}
      />

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
