import { ElementType, forwardRef, Ref } from "react";
import Image, { StaticImageData } from "next/image";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { faEthereum } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";

import { Combine } from "@/types/utils";
import { CardProps } from "@/types/props";
import Card from "@/components/Card";
import Text from "@/components/Text";

type NFTItemCardProps = {
  nftImage: string | StaticImageData;
  tokenName: string;
  corpName: string;
  price?: number;
  status?: string;
  progress?: number;
};

/**
 * @props
 * `nftImage`: `string` | `StaticImageData`
 *
 * `tokenName`: `string`
 *
 * `corpName`: `string`
 *
 * `price`: `number`
 *
 * `status`: `string`
 *
 * `progress`: `number`
 * @return `ReactElement`
 */
function NFTItemCard<T extends ElementType = "div">(
  props: Combine<NFTItemCardProps, CardProps<T>>,
  ref: Ref<any>
) {
  const { nftImage, tokenName, corpName, price, status, progress, ...rest } =
    props;

  const theme = useTheme();

  return (
    <Card style={{ height: "20rem" }} ref={ref} {...rest}>
      <Image src={nftImage} style={{ width: "100%", height: "width" }} />
      <InfoContainer>
        <Text
          role="token-name"
          css={css`
            font-weight: 600;
          `}
        >
          {tokenName}
        </Text>
        <Text
          role="corp-name"
          css={css`
            color: ${theme.color.text.sub};
            font-size: 0.7rem;
            margin-bottom: 0.5rem;
          `}
        >
          {corpName}
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
          {price} ETH
        </Text>
        <Text
          role="status"
          css={css`
            font-size: 0.8rem;
          `}
        >
          {progress && (
            <ProgressBar
              css={css`
                margin-right: 10px;
              `}
              progress={progress}
              type={"blue"}
            />
          )}
          {progress}%
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

export default forwardRef(NFTItemCard) as typeof NFTItemCard;
