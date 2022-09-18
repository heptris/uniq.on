import { ElementType, Ref, forwardRef } from "react";
import { ButtonProps } from "@/types/props";

import { css, useTheme } from "@emotion/react";
import { cssFontFamily, cssConvex } from "@/styles/utils";

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
        font-weight: 500;
        font-size: 0.875rem;
        width: ${size === "full" ? "100%" : "fit-content"};
        transition: background-color 0.3s ease 0s, color 0.3s ease 0s,
          transform 0.3s ease 0s, box-shadow 0.3s ease 0s;
        ${cssFontFamily}
        ${cssConvex}

        &:hover {
          cursor: pointer;
          background-color: ${type === "purple"
            ? "var(--purple500)"
            : "var(--blue900)"};
        }
        &:active {
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
