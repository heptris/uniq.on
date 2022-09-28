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
        border-radius: 8px;
        border: 0;
        height: 3rem;
        padding: 0 1rem;
        background-color: ${color.background.item};
        color: ${color.text.main};
        font-size: 0.9rem;
        font-weight: 700;
        box-shadow: 0 0 0 2px transparent;
        transition: box-shadow 0.3s ease 0s;
        ${cssFontFamily}

        &:hover {
          box-shadow: 0 0 0 2px ${color.hover.main};
        }
        &:focus {
          outline: none;
          box-shadow: 0 0 0 2px ${color.background.main};
        }
        &::placeholder {
          color: ${color.text.sub};
        }
        &[type="checkbox"] {
          height: fit-content;
          -webkit-appearance: checkbox;

          &:hover,
          &:focus {
            box-shadow: none;
          }
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
