import { css, useTheme } from "@emotion/react";
import { ElementType, forwardRef, Ref } from "react";

import { LabelInputProps } from "@/types/props";
import { cssConcave, cssFontFamily } from "@/styles/utils";

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
        -webkit-appearance: none;
        -webkit-border-radius: 0;
        border: 0;
        height: 3rem;
        padding: 0 1rem;
        background-color: ${color.background.item};
        color: ${color.text.main};
        font-size: 0.9rem;
        font-weight: 700;
        outline: 2px solid transparent;
        transition: outline 0.3s ease 0s;
        border-radius: 8px;
        ${cssFontFamily}

        &:hover {
          outline: 2px solid ${color.hover.main};
        }
        &:focus {
          outline: 2px solid ${color.background.main};
        }
        &::placeholder {
          color: ${color.text.sub};
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
              font-size: 0.9rem;
              font-weight: 600;
              margin-bottom: 8px;
              ${cssFontFamily}
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
