import { ElementType, forwardRef, Ref } from "react";

import { css } from "@emotion/react";

import { AlertProps } from "@/types/props";
import { useTheme } from "@emotion/react";
import Text from "../Text";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faInfoCircle, faWarning } from "@fortawesome/free-solid-svg-icons";
import { HEADER_HEIGHT } from "@/constants";

/**
 * @param isSuccess `boolean`
 * @param message `string`
 * @returns ReactElement
 */
function Alert<T extends ElementType = "div">(
  { as, isSuccess = false, message, ...props }: AlertProps<T>,
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
        bottom: ${HEADER_HEIGHT};
        width: fit-content;
        min-height: 3.5rem;
        padding: 1rem 1rem;
        border: 1px solid transparent;
        border-radius: 8px;
        background-color: ${isSuccess
          ? color.status.success
          : color.status.fail};
        color: ${color.text.main};
        z-index: 9990;
        transition: all 0.3s ease 0s;
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
        <div
          css={css`
            display: flex;
          `}
        >
          <FontAwesomeIcon
            icon={isSuccess ? faInfoCircle : faWarning}
            css={css`
              height: 1.3rem;
              margin-right: 0.5rem;
            `}
          />
          <Text>{message}</Text>
        </div>
      </div>
    </Component>
  );
}

export default forwardRef(Alert) as typeof Alert;
