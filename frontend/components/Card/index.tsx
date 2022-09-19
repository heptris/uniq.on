import { ElementType, forwardRef, Ref } from "react";
import { CardProps } from "@/types/props";

/**
 * @params
 * @return
 */
function Card<T extends ElementType = "div">(
  { as, ...props }: CardProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "div";
  const Component = target;

  return <Component ref={ref} {...props} />;
}

export default forwardRef(Card) as typeof Card;
