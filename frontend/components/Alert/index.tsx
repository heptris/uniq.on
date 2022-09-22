import { ElementType, forwardRef, Ref } from "react";

import { css } from "@emotion/css";

import { AlertProps } from "@/types/props";
import { useTheme } from "@emotion/react";

/**
 * @params
 * isSuccess : boolean
 *
 * message : string
 * @return ReactElement
 */
function Alert<T extends ElementType = "div">(
  {
    as,
    isSuccess,
    message = "404 에러 잘못된 요청입니다.",
    ...props
  }: AlertProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "div";
  const Component = target;

  const { color } = useTheme();

  return (
    <Component
      ref={ref}
      className={css`
        background-color: ${isSuccess
          ? color.status.success
          : color.status.fail};
      `}
      {...props}
    >
      {message}
    </Component>
  );
}

export default forwardRef(Alert) as typeof Alert;
