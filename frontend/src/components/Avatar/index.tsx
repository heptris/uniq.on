import { ElementType, forwardRef, Ref } from "react";
import Image from "next/image";
import { AvatarProps } from "@/types/props";

import { css, useTheme } from "@emotion/react";

/**
 * @params
 * `image`: `string` | `StaticImageData`
 *
 * `outline`: `boolean`
 * @return `ReactElement`
 */
function Avatar<T extends ElementType = "div">(
  props: AvatarProps<T>,
  ref: Ref<any>
) {
  const { image, outline = true, as, ...rest } = props;
  const target = as ?? "div";
  const Component = target;

  const theme = useTheme();

  return (
    <Component
      css={css`
        border-radius: 8px;
        width: 5rem;
        height: 5rem;
        overflow: hidden;
        box-shadow: ${outline
          ? `0 0 0 6px ${theme.color.border.main}`
          : `0 0 0 1px ${theme.color.border.main}`};
        filter: drop-shadow(0 0 10px ${theme.color.background.item});
      `}
      ref={ref}
      {...rest}
    >
      <Image src={image} />
    </Component>
  );
}

export default forwardRef(Avatar) as typeof Avatar;
