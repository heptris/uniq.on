import React, { ElementType, forwardRef, Ref, useState } from "react";
import { useTheme } from "@emotion/react";
import styled from "@emotion/styled";

import type { SelectTabProps } from "@/types/props";
import type { ColorMap } from "@/types/utils";
import { cssConvex } from "@/styles/utils";

/**
 * @props
 * @returns
 */
function SelectTab<T extends ElementType = "div">(
  props: SelectTabProps<T>,
  ref: Ref<any>
) {
  const { menus, type = "purple", ...rest } = props;
  const theme = useTheme();
  const colorMap: ColorMap = {
    background: {
      purple: theme.color.background.main,
      blue: theme.color.background.emphasis,
    },
  };
  const [select, setSelect] = useState(menus[0]);
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    const current = e.target.value;
    setSelect(current);
  };

  return (
    <>
      <SelectTabContainer colorMap={colorMap} type={type} ref={ref} {...rest}>
        {menus.map((menu) => (
          <label>
            <input
              type="radio"
              value={menu}
              checked={select === menu}
              onChange={handleChange}
            />
            <Selects>
              <SelectItem>{menu}</SelectItem>
            </Selects>
          </label>
        ))}
      </SelectTabContainer>
      <Hr />
    </>
  );
}

type SCProps = {
  colorMap: ColorMap;
  type: keyof ColorMap["background"];
};

const SelectTabContainer = styled.div<SCProps>`
  width: 100%;
  display: flex;

  input[type="radio"] {
    display: none;

    &:checked {
      + div {
        background-color: ${({ colorMap, type }) => colorMap.background[type]};
        ${cssConvex}
      }
    }
  }
`;
const Selects = styled.div`
  height: 2.5rem;
  padding: 0 1rem;
  border-radius: 9999px;
  margin-right: 1rem;
  display: flex;
  justify-content: center;
  align-items: center;
  transition: all 0.3s ease 0s;

  &:hover {
    cursor: pointer;
  }
  &:active {
    transform: translateY(5px);
  }
`;
const SelectItem = styled.p`
  font-size: 1.2rem;
  font-weight: 700;
  color: ${({ theme }) => theme.color.text.main};
`;
const Hr = styled.hr`
  width: 100%;
  border: 1px solid ${({ theme }) => theme.color.background.card};
  margin-bottom: 1rem;
`;

export default forwardRef(SelectTab) as typeof SelectTab;
