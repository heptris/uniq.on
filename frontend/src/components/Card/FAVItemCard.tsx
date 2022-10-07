import { ElementType, forwardRef, Ref } from "react";
import Image from "next/image";
import { useQueryClient } from "@tanstack/react-query";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { faEthereum } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faHeart } from "@fortawesome/free-solid-svg-icons";

import Card from "@/components/Card";
import Text from "@/components/Text";
import ProgressBar from "@/components/ProgressBar";

import { useAlert, useNftFavMutation } from "@/hooks";
import { UNIQON_TOKEN } from "@/constants";

import { QUERY_KEYS } from "@/api/query_key_schema";

import type { FAVItemCardProps } from "@/types/props";

const { MY_FAVORITE_LIST } = QUERY_KEYS;

/**
 * @params
 * `nftImage`: `string` | `StaticImageData`
 *
 * `tokenId`: `string`
 *
 * `startupName`: `string`
 *
 * `price`: `number`
 *
 * `status`: `string`
 *
 * `progress`: `number`
 * @returns `ReactElement`
 */
function FAVItemCard<T extends ElementType>(
  props: FAVItemCardProps<T>,
  ref: Ref<any>
) {
  const {
    nftTargetCount,
    nftReserveCount,
    startupId,
    nftImage,
    nftDescription,
    dueDate,
    startupName,
    nftPrice,
    progress,
    favItem,
    isFav,
    handleModalOpen,
    ...rest
  } = props;

  const theme = useTheme();
  const { handleAlertOpen } = useAlert();

  const client = useQueryClient();

  const { mutate: mutateNftFav } = useNftFavMutation();
  const handleNftFav = () => {
    mutateNftFav(
      { startupId },
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
        onSettled: () => client.invalidateQueries([MY_FAVORITE_LIST]),
      }
    );
  };

  return (
    <Card ref={ref} {...rest}>
      <ImageContainer onClick={() => handleModalOpen(favItem)}>
        <Image
          src={nftImage}
          layout={"fill"}
          objectFit={"cover"}
          css={css`
            z-index: -2;
          `}
        />
      </ImageContainer>
      {/* <FavInfoContainer> */}
      <InfoContainer onClick={() => handleModalOpen(favItem)}>
        <Text
          role="corp-name"
          css={css`
            font-weight: 600;
          `}
        >
          {startupName} #{startupId}
        </Text>
        <Text
          as="h1"
          role="token-name"
          css={css`
            color: ${theme.color.text.sub};
            font-size: 0.7rem;
            margin-bottom: 0.5rem;
          `}
        >
          {startupName}
        </Text>
        <Text
          as="div"
          role="price"
          css={css`
            display: flex;
            align-items: center;
            font-weight: 500;
            font-size: 0.8rem;
            margin-bottom: 0.5rem;
          `}
        >
          <FontAwesomeIcon
            css={css`
              width: 0.6rem;
              margin-right: 0.5rem;
            `}
            icon={faEthereum}
          />
          {nftPrice} {UNIQON_TOKEN}
        </Text>
        <Text
          role="status"
          css={css`
            font-size: 0.8rem;
            display: flex;
            justify-content: start;
            align-items: center;
          `}
        >
          {progress !== undefined && (
            <>
              <ProgressBar
                css={css`
                  margin-right: 5px;
                `}
                progress={progress}
                type={"blue"}
              />
              <Text
                css={css`
                  font-size: 0.7rem;
                  font-weight: 600;
                `}
              >
                {progress}%
              </Text>
            </>
          )}
        </Text>
      </InfoContainer>
      {/* <FavContainer onClick={handleNftFav}>
          <FontAwesomeIcon
            css={css`
              width: 1.5rem;
              margin-right: 0.5rem;
              color: ${theme.color.background.main};
              &:hover {
                color: ${theme.color.text.main};
              }
            `}
            icon={faHeart}
          />
        </FavContainer> */}
      {/* </FavInfoContainer> */}
    </Card>
  );
}
const FavInfoContainer = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: space-between;
`;
const InfoContainer = styled.div`
  padding: 0.5rem 1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
const FavContainer = styled.div`
  display: flex;
  justify-content: center;
`;
const ImageContainer = styled.div`
  position: relative;
  display: block;
  aspect-ratio: 1 / 1;
  overflow: hidden;
`;

export default forwardRef(FAVItemCard) as typeof FAVItemCard;
