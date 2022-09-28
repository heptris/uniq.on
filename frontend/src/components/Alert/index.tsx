import { ElementType, forwardRef, Ref } from "react";

import { css } from "@emotion/react";

import { AlertProps } from "@/types/props";
import { useTheme } from "@emotion/react";
import Text from "../Text";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faInfoCircle, faWarning } from "@fortawesome/free-solid-svg-icons";
import { HEADER_HEIGHT } from "@/constants";

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
    isSuccess = false,
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
      css={css`
        position: fixed;
        top: ${HEADER_HEIGHT};
        width: 100vw;
        min-height: 3.5rem;
        padding: 1rem 1rem;
        border: 1px solid transparent;
        border-radius: 0.25rem;
        background-color: ${isSuccess
          ? color.status.success
          : color.status.fail};
        color: ${color.text.main};
        z-index: 1000;
      `}
      {...props}
    >
      <div
        css={css`
          display: flex;
          align-items: center;
          justify-content: center;
          height: 100%;
          text-align: center;
        `}
      >
        <FontAwesomeIcon
          icon={isSuccess ? faInfoCircle : faWarning}
          css={css`
            height: 2rem;
          `}
        />
        <Text
          css={css`
            width: 70%;
          `}
        >
          {message}
        </Text>
      </div>
    </Component>
  );
}

export default forwardRef(Alert) as typeof Alert;
