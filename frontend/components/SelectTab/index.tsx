import React, { ElementType, useState } from "react";
import { useTheme } from "@emotion/react";
import { css } from "@emotion/css";
import styled from "@emotion/styled";
import { cssConvex } from "@/styles/utils";

type SelectTabProps = {
  color?: string;
  menus?: string[];
};

/**
 * @params
 * @return
 */

function SelectTab<T extends ElementType = "div">({
  color = "purple",
  menus = ["보유중인NFT", "구매예약", "관심목록", "투자신청내역"],
}: SelectTabProps) {
  const [select, setSelect] = useState("");
  const theme = useTheme();
  const handleChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    setSelect(e.target.value);
  };
  return (
    <SelectWapper theme={theme} color={color}>
      {menus.map((menu) => (
        <label>
          <input
            type="radio"
            value={menu}
            checked={select === menu}
            onChange={handleChange}
          />
          <div className="box">
            <span>{menu}</span>
          </div>
        </label>
      ))}
    </SelectWapper>
  );
}

const SelectWapper = styled.div`
  width: 100%;
  text-align: center;
  input[type="radio"] {
    display: none;
    &:checked {
      + .box {
        background-color: ${({ theme, color }) =>
          color === "purple"
            ? theme.color.background.main
            : theme.color.background.emphasis};
        ${cssConvex}
      }
    }
  }
  .box {
    width: 20%;
    height: 2.5rem;
    border-radius: 2.5rem;
    transition: all 250ms ease;
    will-change: transition;
    display: inline-block;
    text-align: center;
    cursor: pointer;
    position: relative;
    &:active {
      transform: translateY(5px);
    }
    span {
      position: absolute;
      transform: translate(0, 110%);
      left: 0;
      right: 0;
      font-size: 0.8rem;
      user-select: none;
      color: ${({ theme }) => theme.color.text.main};
    }
  }
`;

export default SelectTab;
