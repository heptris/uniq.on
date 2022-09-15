import { ElementType, Ref, forwardRef } from "react";
import { ButtonProps } from "@/types/props";

import { css } from "@emotion/react";

/**
 * @params
 * @return
 */
function Button<T extends ElementType = "button">(
  { as, ...props }: ButtonProps<T>,
  ref: Ref<any>
) {
  const target = as ?? "button";
  const Component = target;

  return (
    <Component
      css={css({
        backgroundColor: "#2258ed",
        border: "0",
        borderRadius: "8px",
        padding: "0.5rem 1rem",
        color: "#fff",
        ":hover": {
          cursor: "pointer",
          backgroundColor: "#1e4cc1",
        },
      })}
      ref={ref}
      {...props}
    />
  );
}

export default forwardRef(Button) as typeof Button;
