import { ElementType, forwardRef, Ref } from "react";
import Image from "next/image";
import { AvatarProps } from "@/types/props";

import { useTheme } from "@emotion/react";
import { css } from "@emotion/css";

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
      {...rest}
    >
      <Image src={image} />
    </Component>
  );
}

export default forwardRef(Avatar) as typeof Avatar;
