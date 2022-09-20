import { ElementType, Ref, forwardRef } from "react";
import { TextProps } from "@/types/props";

import { css, useTheme } from "@emotion/react";
import { cssFontFamily } from "@/styles/utils";

/**
 * @props
 * @return
 */
function Text<T extends ElementType = "span">(
  { typography = "content", as, ...props }: TextProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "span";
  const Component = target;

  const theme = useTheme();

  return (
    <Component
      css={css`
        color: ${theme.color.text.main};
        line-height: ${typography === "content" && `150%`};
        overflow: hidden;
        text-overflow: ellipsis;

        ${cssFontFamily}
      `}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Text) as typeof Text;
