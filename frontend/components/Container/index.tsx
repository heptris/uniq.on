import { ElementType, forwardRef, Ref } from "react";
import { ContainerProps } from "@/types/props";

import { css, useTheme } from "@emotion/react";
import { minTabletWidth } from "@/styles/utils";

/**
 * @params
 * @return
 */
function Container<T extends ElementType = "section">(
  { as, ...props }: ContainerProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "section";
  const Component = target;

  const theme = useTheme();

  return (
    <Component
      css={css`
        background-color: ${theme.color.background.page};
        min-height: 100vh;
        margin-top: 80px;
        padding: 0 2vw;
        display: flex;
        flex-direction: column;
        align-items: center;
        overflow: hidden;

        @media (${minTabletWidth}) {
          padding: 0 4vw;
        }
      `}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Container) as typeof Container;
