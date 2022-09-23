import { ElementType, forwardRef, Ref } from "react";
import { GridProps } from "@/types/props";

import { css } from "@emotion/react";

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
        display: flex;
        align-items: center;
        justify-content: center;
        flex-wrap: wrap;
        row-gap: 0.5rem;

        max-width: 800px;

        @media (max-width: 600px) {
          width: 100%;
          flex-direction: column;
          ${column === "double" &&
          `
            display: grid;
            grid-template-columns: repeat(2, 50%);
            column-gap: 0.5rem;
          `}
        }
      `}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Grid) as typeof Grid;
