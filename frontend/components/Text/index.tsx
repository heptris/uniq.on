import { ElementType, Ref, forwardRef } from "react";
import { TextProps } from "@/types/props";

import { css, useTheme } from "@emotion/react";

/**
 * @params
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
      `}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Text) as typeof Text;
