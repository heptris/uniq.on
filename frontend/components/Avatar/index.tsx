import { ElementType, forwardRef, Ref } from "react";
import { AvatarProps } from "@/types/props";

/**
 * @params
 * @return
 */
function Avatar<T extends ElementType = "div">(
  { as, ...props }: AvatarProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "div";
  const Component = target;

  return <Component ref={ref} {...props} />;
}

export default forwardRef(Avatar) as typeof Avatar;
