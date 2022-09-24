import contracts from "@/contracts/utils";

import styled from "@emotion/styled";
import { css, useTheme } from "@emotion/react";
import { faWallet } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import img from "@/assets/nfts/animals/1.png";
import nft1 from "@/assets/nfts/1.png";
import nft2 from "@/assets/nfts/2.png";
import nft3 from "@/assets/nfts/3.png";

import type { Member } from "@/types/api_responses";
import Layout from "@/components/Layout";
import Avatar from "@/components/Avatar";
import Text from "@/components/Text";
import Grid from "@/components/Grid";
import NFTItemCard from "@/components/Card/NFTItemCard";
import SelectTab from "@/components/SelectTab";
import { minTabletWidth } from "@/styles/utils";

function MyPage() {
  const theme = useTheme();
  const { account, setAccount } = contracts.useAccount();
  const member: Member = {
    id: 1,
    name: "tester",
    profileImage: img,
    nickname: "Anonymous",
    walletAddress: account,
    email: "test@gmail.com",
    memberType: "USER",
  };
  const nfts = [
    {
      nftImage: nft1,
      tokenId: 1243,
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
    {
      nftImage: nft2,
      tokenId: 1243,
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
    {
      nftImage: nft3,
      tokenId: 1243,
      corpName: "test",
      price: 0.99,
      progress: 60,
    },
  ];

  return (
    <Layout>
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
        {nfts.map((nft1, i) => (
          <NFTItemCard
            key={i}
            nftImage={nft1.nftImage}
            tokenId={nft1.tokenId}
            corpName={nft1.corpName}
            price={nft1.price}
            progress={nft1.progress}
          />
        ))}
      </Grid>
    </Layout>
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
