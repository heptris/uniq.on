import { ElementType, forwardRef, Ref } from "react";
import Image, { StaticImageData } from "next/image";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { faEthereum } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import type { Combine } from "@/types/utils";
import type { CardProps } from "@/types/props";
import type { RSRVItem } from "@/types/api_responses";
import Card from "@/components/Card";
import Text from "@/components/Text";
import ProgressBar from "@/components/ProgressBar";
import { UNIQON_TOKEN } from "@/constants";

type RSRVItemCardProps =
  | {
      nftReserveCount?: number;
    }
  | RSRVItem;

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
function RSRVItemCard<T extends ElementType = "div">(
  props: Combine<RSRVItemCardProps, CardProps<T>>,
  ref: Ref<any>
) {
  const {
    startupId,
    nftDescription,
    nftImage,
    startupName,
    nftPrice,
    nftReserveCount,
    ...rest
  } = props;

  const theme = useTheme();

  return (
    <Card style={{ height: "fit-content" }} ref={ref} {...rest}>
      <ImageContainer>
        <Image
          src={nftImage}
          layout={"fill"}
          objectFit={"cover"}
          css={css`
            z-index: -2;
          `}
        />
      </ImageContainer>
      <InfoContainer>
        <Text
          role="token-name"
          css={css`
            font-weight: 600;
          `}
        >
          {startupName} #{nftReserveCount}
        </Text>
        <Text
          role="corp-name"
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
          {nftReserveCount !== undefined && (
            <>
              <ProgressBar
                css={css`
                  margin-right: 5px;
                `}
                progress={nftReserveCount}
                type={"blue"}
              />
              <Text
                css={css`
                  font-size: 0.7rem;
                  font-weight: 600;
                `}
              >
                {nftReserveCount}%
              </Text>
            </>
          )}
        </Text>
      </InfoContainer>
    </Card>
  );
}

const InfoContainer = styled.div`
  padding: 0.5rem 1rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
const ImageContainer = styled.div`
  position: relative;
  display: block;
  aspect-ratio: 1 / 1;
  overflow: hidden;
`;

export default forwardRef(RSRVItemCard) as typeof RSRVItemCard;
