import { ElementType, forwardRef, Ref } from "react";
import { CardProps } from "@/types/props";

import { css, useTheme } from "@emotion/react";
import { cssConvex } from "@/styles/utils";

/**
 * @params
 * @returns `ReactElement`
 */
function Card<T extends ElementType = "div">(
  props: CardProps<T>,
  ref: Ref<any>
) {
  const { as, children, clickable = false, ...rest } = props;
  const target = as ?? "div";
  const Component = target;

  const theme = useTheme();

  return (
    <Component
      css={css`
        width: 100%;
        height: fit-content;
        border-radius: 8px;
        position: relative;
        overflow: hidden;
        background-color: ${theme.color.background.card};
        z-index: 0;

        ${clickable &&
        `
          &:hover {
            cursor: pointer;
            filter: brightness(80%);
          }
        `}
      `}
      ref={ref}
      {...rest}
    >
      <div
        css={css`
          width: 100%;
          height: 100%;
          border-radius: inherit;
          background-color: transparent;
          position: absolute;
          z-index: -1;

          ${cssConvex}
        `}
      />
      {children}
    </Component>
  );
}

export default forwardRef(Card) as typeof Card;
