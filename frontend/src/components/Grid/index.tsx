import { ElementType, forwardRef, Ref } from "react";
import { GridProps } from "@/types/props";

import { css } from "@emotion/react";
import { minDesktopWidth, minTabletWidth } from "@/styles/utils";

/**
 * @params
 * `column`: `mono` | `double`
 * @return `HTMLDivElement`
 */
function Grid<T extends ElementType = "div">(
  { column = "mono", as, ...props }: GridProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "div";
  const Component = target;

  return (
    <Component
      css={css`
        width: 100%;
        display: grid;
        grid-template-columns: repeat(1, 1fr);
        gap: 3vw;
        ${column === "double" &&
        `
          grid-template-columns: repeat(2, 1fr);
        `}

        @media (${minTabletWidth}) {
          grid-template-columns: repeat(2, 1fr);
          gap: 1vw;
        }
        @media (${minDesktopWidth}) {
          grid-template-columns: repeat(3, 1fr);
          ${column === "double" &&
          `
          grid-template-columns: repeat(4, 1fr);
          `}
        }
      `}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Grid) as typeof Grid;
