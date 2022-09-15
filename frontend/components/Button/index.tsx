import { ElementType, Ref, forwardRef } from "react";
import { ButtonProps } from "@/types/props";

import { css } from "@emotion/react";

import { convex } from "@/styles/index";

/**
 * @params
 * @return
 */
function Button<T extends ElementType = "button">(
  { as, ...props }: ButtonProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "button";
  const Component = target;

  return (
    <Component
      css={css`
        background-color: #2258ed;
        border: 0;
        border-radius: 8px;
        padding: 0.8rem 1.2rem;
        color: #fff;
        font-weight: bold;
        ${convex}

        &:hover {
          cursor: pointer;
          background-color: #1e4cc1;
        }
      `}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Button) as typeof Button;
