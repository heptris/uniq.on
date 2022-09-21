import { ElementType, Ref, forwardRef } from "react";
import { ButtonProps } from "@/types/props";

import { css, useTheme } from "@emotion/react";
import { cssFontFamily, cssConvex } from "@/styles/utils";

/**
 * @params
 * `size`: `fit` | `full`
 *
 * `type`: `purple` | `blue`
 *
 * `disabled`: `boolean`
 * @return `HTMLButtonElement`
 */
function Button<T extends ElementType = "button">(
  props: ButtonProps<T>,
  ref: Ref<any>
) {
  const {
    size = "fit",
    type = "purple",
    disabled = false,
    as,
    ...rest
  } = props;
  const target = as ?? "button";
  const Component = target;

  const theme = useTheme();
  const colorMap = {
    background: {
      purple: theme.color.background.main,
      blue: theme.color.background.emphasis,
      disabled: theme.color.status.disabled,
    },
    hover: {
      purple: theme.color.hover.main,
      blue: theme.color.hover.emphasis,
    },
  };

  return (
    <Component
      css={css`
        background-color: ${disabled === false
          ? colorMap.background[type]
          : colorMap.background.disabled};
        border: 0;
        border-radius: 8px;
        padding: 0.8rem 1.2rem;
        color: ${theme.color.text.main};
        font-weight: 500;
        font-size: 0.875rem;
        width: ${size === "full" ? "100%" : "fit-content"};
        transition: background-color 0.3s ease 0s, color 0.3s ease 0s,
          transform 0.3s ease 0s, box-shadow 0.3s ease 0s;
        ${cssFontFamily}
        ${cssConvex}

        &:not([disabled]):hover,
        &:not([disabled]):active {
          background-color: ${colorMap.hover[type]};
        }
        &:not([disabled]):hover {
          cursor: pointer;
        }
      `}
      ref={ref}
      {...rest}
    />
  );
}

export default forwardRef(Button) as typeof Button;
