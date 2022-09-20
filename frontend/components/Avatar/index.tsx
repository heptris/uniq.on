import { ElementType, forwardRef, Ref } from "react";
import Image from "next/image";
import { AvatarProps } from "@/types/props";

import { useTheme } from "@emotion/react";
import { css } from "@emotion/css";

/**
 * @params
 * `imagePath`: `string`
 *
 * `outline`: `boolean`
 * @return `ReactElement`
 */
function Avatar<T extends ElementType = "div">(
  { imagePath, outline = true, as, ...props }: AvatarProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "div";
  const Component = target;

  const theme = useTheme();

  return (
    <Component
      className={css`
        width: 5rem;
        height: 5rem;
        border-radius: 8px;
        overflow: hidden;
        outline: ${outline
          ? `6px solid ${theme.color.border.main}`
          : `1px solid ${theme.color.border.main}`};
        filter: drop-shadow(0 0 10px ${theme.color.background.item});
      `}
      ref={ref}
      {...props}
    >
      <Image src={imagePath} />
    </Component>
  );
}

export default forwardRef(Avatar) as typeof Avatar;
