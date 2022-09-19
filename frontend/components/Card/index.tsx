import { ElementType, forwardRef, Ref } from "react";
import Image from "next/image";
import { CardProps } from "@/types/props";

import { useTheme } from "@emotion/react";
import { css } from "@emotion/css";
import { cssConvex } from "@/styles/utils";

import Text from "@/components/Text";

/**
 * @params
 * @return
 */
function Card<T extends ElementType = "div">(
  { as, ...props }: CardProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "div";
  const Component = target;

  const theme = useTheme();

  return (
    <Component
      className={css`
        width: 100%;
        height: 10rem;
        border-radius: 8px;
        padding: 0 2rem;
        background-color: ${theme.color.background.card};

        ${cssConvex}
      `}
      ref={ref}
      {...props}
    >
      <Text as="h2" role="name">
        test
      </Text>
      <Text as="h1" role="title">
        Title test
      </Text>
      <Text as="h3" role="date">
        2022.09.07
      </Text>
      <Image src="" />
    </Component>
  );
}

export default forwardRef(Card) as typeof Card;
