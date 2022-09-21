import { ElementType, forwardRef, Ref } from "react";

import { useTheme } from "@emotion/react";
import styled from "@emotion/styled";

import type { ProgressBarProps } from "@/types/props";
import { cssConcave, cssConvex } from "@/styles/utils";

type ColorMap = {
  background: {
    purple: string;
    blue: string;
  };
};

/**
 * @props
 * `progress`: `number`
 *
 * `maxProgress`: `number`
 *
 * `type`: `purple` | `blue`
 *
 * @return `HTMLProgressElement`
 */
function ProgressBar<T extends ElementType = "progress">(
  props: ProgressBarProps<T>,
  ref: Ref<any>
) {
  const { progress, maxProgress = 100, type = "purple", ...rest } = props;

  const theme = useTheme();
  const colorMap: ColorMap = {
    background: {
      purple: theme.color.background.main,
      blue: theme.color.background.emphasis,
    },
  };

  return (
    <Progress
      theme={theme}
      colorMap={colorMap}
      type={type}
      value={progress}
      max={maxProgress}
      ref={ref}
      {...rest}
    />
  );
}

type ProgressProps = {
  colorMap: ColorMap;
  type: keyof ColorMap["background"];
};

const Progress = styled.progress<ProgressProps>`
  border: 0;
  height: 0.3rem;

  ::-webkit-progress-bar {
    border-radius: 8px;
    background-color: ${({ theme }) => theme.color.background.item};
    ${cssConcave}
  }
  ::-webkit-progress-value {
    border-radius: 8px;
    background-color: ${({ colorMap, type }) => colorMap.background[type]};
    ${cssConvex}
  }
`;

export default forwardRef(ProgressBar) as typeof ProgressBar;
