import { ElementType, Ref, forwardRef } from "react";
import { ButtonProps } from "@/types/props";

import { css, useTheme } from "@emotion/react";
import { convex } from "@/styles/utils";

/**
 * @params
 * `size`: `fit` | `full`
 *
 * `type`: `purple` | `blue`
 * @return `HTMLButtonElement`
 */
function Button<T extends ElementType = "button">(
  { size = "fit", type = "purple", as, ...props }: ButtonProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "button";
  const Component = target;

  const theme = useTheme();

  return (
    <Component
      css={css`
        background-color: ${type === "purple"
          ? theme.colors.mainColor
          : theme.colors.emphasisColor};
        border: 0;
        border-radius: 8px;
        padding: 0.8rem 1.2rem;
        color: ${theme.colors.txtMainColor};
        font-weight: bold;
        width: ${size === "full" ? "100%" : "fit-content"};
        ${convex}

        &:hover {
          cursor: pointer;
          background-color: ${type === "purple"
            ? "var(--purple500)"
            : "var(--blue900)"};
        }
      `}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Button) as typeof Button;
