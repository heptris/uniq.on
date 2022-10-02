import { ElementType, forwardRef, Ref } from "react";
import Image, { StaticImageData } from "next/image";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { faEthereum } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import type { Combine } from "@/types/utils";
import type { CardProps, MypageListType } from "@/types/props";
import type { FAVItem } from "@/types/api_responses";
import Card from "@/components/Card";
import Text from "@/components/Text";
import ProgressBar from "@/components/ProgressBar";
import { faHeart } from "@fortawesome/free-solid-svg-icons";
import { useAlert } from "@/hooks";
import axios from "axios";

type FAVItemCardProps =
  | {
      progress?: number;
      favItem: FAVItem;
      handleModalOpen: (type: MypageListType) => void;
    }
  | FAVItem;

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
function FAVItemCard<T extends ElementType = "div">(
  props: Combine<FAVItemCardProps, CardProps<T>>,
  ref: Ref<any>
) {
  const {
    tokenId,
    startupId,
    nftImage,
    nftDescription,
    dueDate,
    startupName,
    nftPrice,
    progress,
    favItem,
    handleModalOpen,
    ...rest
  } = props;

  const theme = useTheme();
  const { handleAlertOpen } = useAlert();

  const handleFavorite = () => {
    //관심목록 해제
    axios
      .get(`/api/invest/${startupId}/favorite`)
      .then((response) => {
        const { status } = response;
        if (status === 200) {
          handleAlertOpen(2000, "즐겨찾기가 해제되었습니다.", true);
        } else {
          handleAlertOpen(2000, "즐겨찾기가 해제가 실패했습니다.", false);
        }
      })
      .catch((err) => {
        const { response } = err;
        const { status, data } = response;
        handleAlertOpen(2000, `${status}에러가 발생했습니다.`, false);
      });
  };

  return (
    <Card style={{ height: "fit-content" }} ref={ref} {...rest}>
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
      <FavInfoContainer>
        <InfoContainer onClick={() => handleModalOpen(favItem)}>
          <Text
            role="corp-name"
            css={css`
              font-weight: 600;
            `}
          >
            {startupName} #{tokenId}
          </Text>
          <Text
            role="token-name"
            css={css`
              color: ${theme.color.text.sub};
              font-size: 0.7rem;
              margin-bottom: 0.5rem;
            `}
          >
            {nftDescription}
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
            {nftPrice} ETH
          </Text>
          <Text
            role="due-date"
            css={css`
              color: ${theme.color.text.sub};
              font-weight: 600;
              font-size: 0.8rem;
              margin-bottom: 0.5rem;
            `}
          >
            마감기한 : {dueDate}
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
        <FavContainer
          onClick={() => {
            handleFavorite();
          }}
        >
          <FontAwesomeIcon
            css={css`
              width: 1.5rem;
              margin-right: 0.5rem;
              color: ${theme.color.text.main};
            `}
            icon={faHeart}
          />
        </FavContainer>
      </FavInfoContainer>
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
