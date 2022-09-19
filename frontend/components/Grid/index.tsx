import { ElementType, forwardRef, Ref } from "react";
import { GridProps } from "@/types/props";
import { css } from "@emotion/react";

/**
 * @params
 * @return
 */
function Grid<T extends ElementType = "div">(
  { as, ...props }: GridProps<T>,
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

        max-width: 800px;
        margin-top: 3rem;

        @media (max-width: 600px) {
          width: 100%;
          flex-direction: column;
        }
      `}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Grid) as typeof Grid;
