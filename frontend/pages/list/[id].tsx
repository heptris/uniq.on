import { useEffect } from "react";
import { GetServerSideProps } from "next";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { cssFontFamily, minDesktopWidth } from "@/styles/utils";

import Grid from "@/components/Grid";
import Card from "@/components/Card";
import NFTItemCard from "@/components/Card/NFTItemCard";
import Text from "@/components/Text";
import Button from "@/components/Button";

import {
  getIR,
  useIR,
  useNftFavMutation,
  useNftReserveMutation,
  useAlert,
} from "@/hooks";

import { IR } from "@/types/api_responses";

type IDProps = {
  irInfo: IR;
  id: string | string[] | undefined;
};

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { id } = context.query;
  const irInfo = await getIR(id);
  return { props: { irInfo, id } };
};

function InvestmentDetail(props: IDProps) {
  const { irInfo, id } = props;
  const { color } = useTheme();
  const { handleAlertOpen } = useAlert();
  const { data: InvestmentRequest, refetch } = useIR(id, irInfo);
  const {
    isReserved,
    isFav,
    dueDate,
    nftDescription,
    nftImage,
    nftPrice,
    nftReserveCount,
    nftTargetCount,
    planPaperImg,
    startupId,
    startupName,
  } = InvestmentRequest;

  useEffect(() => {
    refetch();
  }, []);

  const { mutate: mutateNftReserve } = useNftReserveMutation();
  const { mutate: mutateNftFav } = useNftFavMutation();

  const handleNftReserve = () => {
    mutateNftReserve(
      { startupId: id },
      {
        onSettled: () => refetch(),
      }
    );
  };
  const handleNftFav = () => {
    mutateNftFav(
      { startupId: id },
      {
        onSuccess: () => {
          handleAlertOpen(
            2000,
            isFav ? "즐겨찾기가 해제되었습니다." : "즐겨찾기가 등록되었습니다.",
            true
          );
        },
        onError() {
          handleAlertOpen(2000, `즐겨찾기 처리 중 에러가 발생했습니다.`, false);
        },
        onSettled: () => refetch(),
      }
    );
  };

  return (
    <Grid
      css={css`
        padding-top: 2rem;
        width: 100%;

        @media (${minDesktopWidth}) {
          width: 70%;
          grid-template-columns: repeat(2, 1fr);
        }
      `}
    >
      <NFTInfo>
        <NFTItemCard
          nftDescription={InvestmentRequest.nftDescription}
          nftReserveCount={InvestmentRequest.nftReserveCount}
          startupId={startupId}
          nftImage={nftImage}
          startupName={startupName}
          nftPrice={nftPrice}
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
              <FavoriteButton onClick={handleNftFav}>
                {isFav ? (
                  <FontAwesomeIcon
                    icon={faHeart}
                    width={"1.8rem"}
                    css={css`
                      color: ${color.background.main};
                      &:hover {
                        color: ${color.text.main};
                      }
                    `}
                  />
                ) : (
                  <FontAwesomeIcon icon={faHeart} width={"1.8rem"} />
                )}
              </FavoriteButton>
            </div>
            <Text as="p">{nftDescription}</Text>

            <Text as="h1">상세정보</Text>
            <InlineInfo>
              <Text as="h2">블록체인</Text>
              <Text as="p">Ethereum</Text>
            </InlineInfo>
            <InlineInfo>
              <Text as="h2">발행 토큰 수</Text>
              <Text as="p">{nftTargetCount}</Text>
            </InlineInfo>
            <InlineInfo>
              <Text as="h2">토큰 표준</Text>
              <Text as="p">ERC-721</Text>
            </InlineInfo>
            <InlineInfo>
              <Text as="h2">펀딩 마감일</Text>
              <Text as="p">{dueDate}</Text>
            </InlineInfo>
            <InlineInfo>
              <Text as="h2">펀딩 진행률</Text>
              <Text as="p">{(nftReserveCount / nftTargetCount) * 100}%</Text>
            </InlineInfo>
            {isReserved ? (
              <Button
                size="full"
                css={css`
                  height: 3rem;
                  margin-top: 3rem;
                `}
                disabled={true}
              >
                예약완료
              </Button>
            ) : (
              <Button
                size="full"
                css={css`
                  height: 3rem;
                  margin-top: 3rem;
                `}
                onClick={handleNftReserve}
              >
                구매예약
              </Button>
            )}
          </CardContent>
        </Card>
      </NFTInfo>

      <Card>
        <CardContent>
          <Text as="h1">기업 소개</Text>
          <ImageContainer>
            <img src={planPaperImg} width={"100%"} />
          </ImageContainer>

          {/* <Text as="h1">Q&A</Text>

          <LabelInput placeholder={"NFT 구매 전, 질문을 할 수 있어요."} /> */}
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
const ImageContainer = styled.div`
  position: relative;
  width: 100%;
  height: fit-content;
  overflow: hidden;
`;

export default InvestmentDetail;
