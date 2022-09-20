import { ElementType, forwardRef, Ref } from "react";
import { StaticImageData } from "next/image";

import { useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { css } from "@emotion/css";

import { Combine } from "@/types/utils";
import { CardProps } from "@/types/props";
import Card from "@/components/Card";
import Text from "@/components/Text";
import Avatar from "@/components/Avatar";

type CorporationCardProps = {
  corpName: string;
  corpAvatar: string | StaticImageData;
  title: string;
  date: string;
  progress: number;
};

/**
 * @props
 * `corpName`: `string`
 *
 * `corpAvatar`: `string` | `StaticImageData`
 *
 * `title`: `string`
 *
 * `date`: `string`
 *
 * `progress`: `number`
 * @return `ReactElement`
 */
function CorporationCard<T extends ElementType = "div">(
  props: Combine<CorporationCardProps, CardProps<T>>,
  ref: Ref<any>
) {
  const { corpName, corpAvatar, title, date, progress, ...rest } = props;

  const theme = useTheme();

  return (
    <Card
      style={{
        height: "8rem",
        display: "flex",
        justifyContent: "space-between",
        alignItems: "center",
      }}
      ref={ref}
      {...rest}
    >
      <InfoContainer>
        <Text
          as="h2"
          role="corp-name"
          className={css`
            color: ${theme.color.text.sub};
            font-size: 0.7rem;
          `}
        >
          {corpName}
        </Text>
        <Text
          as="h1"
          role="title"
          className={css`
            font-size: 1.2rem;
            font-weight: 600;
          `}
        >
          {title}
        </Text>
        <Text
          as="h3"
          role="date"
          className={css`
            color: ${theme.color.text.sub};
            font-size: 0.7rem;
          `}
        >
          {date}
        </Text>
        <Text
          role="status"
          className={css`
            font-size: 0.8rem;
          `}
        >
          {progress}%
        </Text>
      </InfoContainer>
      <Avatar
        style={{ marginRight: "2rem" }}
        role="corp-avatar"
        image={corpAvatar}
        outline={false}
      />
    </Card>
  );
}

const InfoContainer = styled.div`
  height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  padding: 0 2rem;
`;

export default forwardRef(CorporationCard) as typeof CorporationCard;
