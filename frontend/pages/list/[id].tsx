import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";

import nft1 from "@/assets/nfts/1.png";

import type { IR } from "@/types/api_responses";
import Grid from "@/components/Grid";
import Card from "@/components/Card";
import NFTItemCard from "@/components/Card/NFTItemCard";
import Text from "@/components/Text";
import Button from "@/components/Button";
import LabelInput from "@/components/LabelInput";
import { cssFontFamily, minDesktopWidth } from "@/styles/utils";

function InvestmentDetail() {
  const menus = [];
  const InvestmentRequest: IR = {
    id: 1,
    corpName: "Samsung NEXT",
    planPaper: `Lorem ipsum dolor sit amet, consectetur adipiscing elit. Fusce
    semper porttitor facilisis. Sed facilisis diam a nisl venenatis
    suscipit. Nam vitae consequat dui. Praesent in odio iaculis nibh
    rhoncus tempus a id ligula. Nullam dignissim neque eu lacinia
    finibus. In magna nunc, vulputate sed urna vel, dignissim suscipit
    eros. Duis at sagittis erat. Ut sed eros venenatis, blandit odio ac,
    ornare felis. Ut odio sapien, fermentum et lacus sed, pharetra
    elementum velit. Suspendisse dui velit, maximus quis nibh non,
    rhoncus egestas turpis. Proin eget urna vitae dui tincidunt
    facilisis. Nulla facilisi. Suspendisse ullamcorper tellus et
    facilisis eleifend. Vivamus posuere libero suscipit dui dignissim
    lobortis. Etiam vitae consectetur lacus. Vivamus viverra varius eros
    dapibus vestibulum. Ut porta, lectus a pretium faucibus, lectus
    felis sodales metus, ac gravida urna lectus sed neque. Fusce
    facilisis at nibh et viverra. Sed iaculis varius metus, non
    consequat lorem pulvinar iaculis. Sed at diam justo. Etiam leo ex,
    maximus non enim vel, pellentesque pretium nibh. Class aptent taciti
    sociosqu ad litora torquent per conubia nostra, per inceptos
    himenaeos. Nullam gravida imperdiet euismod. Maecenas dapibus, dolor
    vel gravida maximus, arcu nibh cursus sapien, a mollis neque nisi ac
    urna. Nam sit amet placerat lacus. Fusce aliquet lectus vitae
    placerat dignissim. Sed imperdiet sagittis metus at fringilla.
    Aenean semper tellus non sem congue, nec iaculis nunc tincidunt.
    Donec vel facilisis ipsum. Donec mi sem, ornare sed eros id, aliquam
    mattis lectus. Donec fermentum nisl elit. Integer libero eros,
    condimentum id sem ac, accumsan dapibus ipsum. Morbi non tellus
    ullamcorper, pellentesque massa vehicula, efficitur odio. Nulla
    finibus nibh non efficitur faucibus. Maecenas velit nulla, sodales
    in neque ut, fermentum feugiat sem. Maecenas non metus a diam congue
    fringilla. In id elit orci. Praesent rhoncus quis nisl non
    vulputate. Aenean molestie et sem id gravida. Vivamus dignissim
    rhoncus mauris, eu finibus lorem elementum nec. Donec tincidunt
    lorem vitae molestie hendrerit. Morbi eu scelerisque metus. Vivamus
    in risus id lorem viverra laoreet. Fusce malesuada nec ipsum a
    placerat. Aliquam erat volutpat. Aliquam sit amet lacinia ligula.
    Nullam vestibulum interdum neque at iaculis.`,
    roadmap: "",
    title: "SNKRZ",
    dueDate: "2022.09.26",
    targetAmount: 1000,
    curTotalAmount: 216,
    nftImage: nft1,
    nftTypeCount: 1,
    nftTokenCount: 217,
    nftPrice: 0.99,
    isClosed: false,
    isAchieved: false,
    discordUrl: "",
    enrollStatus: "ACCEPT",
    rejectReason: "",
    description: `이건 이쁜 NFT`,
    memberId: 1,
  };

  return (
    <Grid
      css={css`
        padding-top: 2rem;

        @media (${minDesktopWidth}) {
          grid-template-columns: repeat(2, 1fr);
        }
      `}
    >
      <NFTInfo>
        <NFTItemCard
          nftImage={InvestmentRequest.nftImage}
          tokenId={InvestmentRequest.nftTokenCount}
          corpName={InvestmentRequest.corpName}
          price={InvestmentRequest.nftPrice}
        />
        <Card
          css={css`
            margin-top: 0.5rem;
          `}
        >
          <CardContent>
            <div
              css={css`
                display: flex;
                justify-content: space-between;
                align-items: center;
              `}
            >
              <Text as="h1">NFT 소개</Text>
              <FavoriteButton>
                <FontAwesomeIcon icon={faHeart} width={"1.8rem"} />
              </FavoriteButton>
            </div>
            <Text as="p">{InvestmentRequest.description}</Text>

            <Text as="h1">상세정보</Text>
            <InlineInfo>
              <Text as="h2">Blockchain</Text>
              <Text as="p">Ethereum</Text>
            </InlineInfo>
            <InlineInfo>
              <Text as="h2">발행 토큰 수</Text>
              <Text as="p">{InvestmentRequest.targetAmount}</Text>
            </InlineInfo>
            <InlineInfo>
              <Text as="h2">Token Standard</Text>
              <Text as="p">ERC-721</Text>
            </InlineInfo>
            <InlineInfo>
              <Text as="h2">펀딩 마감일</Text>
              <Text as="p">{InvestmentRequest.dueDate}</Text>
            </InlineInfo>
            <InlineInfo>
              <Text as="h2">펀딩 진행률</Text>
              <Text as="p">
                {(InvestmentRequest.curTotalAmount /
                  InvestmentRequest.targetAmount) *
                  100}
                %
              </Text>
            </InlineInfo>

            <Button
              size="full"
              css={css`
                height: 3rem;
                margin-top: 3rem;
              `}
            >
              구매예약
            </Button>
          </CardContent>
        </Card>
      </NFTInfo>

      <Card>
        <CardContent>
          <Text as="h1">기업 소개</Text>
          <Text>{InvestmentRequest.planPaper}</Text>

          <Text as="h1">Q&A</Text>

          <LabelInput placeholder={"NFT 구매 전, 질문을 할 수 있어요."} />
        </CardContent>
      </Card>
    </Grid>
  );
}

const NFTInfo = styled.div``;
const CardContent = styled.div`
  display: flex;
  flex-direction: column;
  padding: 2rem 1rem;

  h1 {
    font-size: 1.5rem;
    font-weight: 700;
    padding: 1rem 0;
  }
  h2 {
    display: inline;
    color: ${({ theme }) => theme.color.text.sub};
    font-weight: 600;
  }
  p {
    font-weight: 500;
  }
`;
const FavoriteButton = styled.button`
  background-color: transparent;
  width: fit-content;
  border: 0;
  color: ${({ theme }) => theme.color.text.sub};
  font-weight: 700;
  display: flex;
  gap: 0.5em;
  align-items: center;
  transition: color 0.3s ease 0s;
  opacity: 60%;
  ${cssFontFamily}

  &:hover {
    cursor: pointer;
    color: ${({ theme }) => theme.color.hover.main};
  }
`;
const InlineInfo = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 1rem;
`;

export default InvestmentDetail;
