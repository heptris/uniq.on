import { css, useTheme } from "@emotion/react";
import { ElementType, forwardRef, Ref } from "react";

import { LabelInputProps } from "@/types/props";

/**
 * @params
 *
 * @return ReactElement
 */
function LabelInput<T extends ElementType = "input">(
  { as, placeholder, labelText, value, onChange, ...props }: LabelInputProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "input";
  const Component = target;

  const { color } = useTheme();

  const content = (
    <Component
      ref={ref}
      css={css`
        border-radius: 4px;
        background-color: ${color.background.item};
        &:focus {
          outline: 2px solid ${color.border.main};
        }
      `}
      placeholder={placeholder}
      id={labelText}
      value={value}
      onChange={onChange}
      {...props}
    />
  );

  return (
    <>
      {labelText !== undefined ? (
        <div
          css={css`
            display: flex;
            flex-direction: column;
          `}
        >
          <label
            htmlFor={labelText}
            css={css`
              color: ${color.text.main};
              font-size: 12px;
              margin-bottom: 10px;
            `}
          >
            {labelText}
          </label>
          {content}
        </div>
      ) : (
        <>{content}</>
      )}
    </>
  );
}

export default forwardRef(LabelInput) as typeof LabelInput;
