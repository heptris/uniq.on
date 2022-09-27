import React, { ElementType, forwardRef, Ref, useState } from "react";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";

import type { SelectTabProps } from "@/types/props";
import type { ColorMap } from "@/types/utils";
import Text from "@/components/Text";
import { cssConvex } from "@/styles/utils";

/**
 * @props
 * @returns
 */
function SelectTab<T extends ElementType = "div">(
  props: SelectTabProps<T>,
  ref: Ref<any>
) {
  const { menus, type = "purple", onSelectHandler, ...rest } = props;
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
    onSelectHandler(current);
  };

  return (
    <>
      <SelectTabContainer colorMap={colorMap} type={type} ref={ref} {...rest}>
        {menus.map((menu, i) => (
          <label key={i}>
            <input
              type="radio"
              value={menu}
              checked={select === menu}
              onChange={handleChange}
            />
            <Selects>
              <Text
                as="p"
                role="select-item"
                css={css`
                  font-size: 1.2rem;
                  font-weight: 700;
                  color: ${theme.color.text.main};
                `}
              >
                {menu}
              </Text>
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
const Hr = styled.hr`
  width: 100%;
  border: 1px solid ${({ theme }) => theme.color.background.card};
  margin-bottom: 1rem;
`;

export default forwardRef(SelectTab) as typeof SelectTab;
