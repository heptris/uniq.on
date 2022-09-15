import { ElementType, Ref, forwardRef } from "react";
import { TextProps } from "@/types/props";

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

  return <Component ref={ref} {...props} />;
}

export default forwardRef(Text) as typeof Text;
