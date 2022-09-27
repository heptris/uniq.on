import { CircleBarProps } from "@/types/props";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { ElementType, forwardRef, Ref } from "react";
import Text from "../Text";

function CircleBar<T extends ElementType = "div">(
  props: CircleBarProps<T>,
  ref: Ref<any>
) {
  const {
    total = 3,
    current = 1,
    menus = ["기업정보", "개인정보", "투자정보"],
    ...rest
  } = props;
  const theme = useTheme();
  const CircleHandler = () => {
    let array = [];
    for (let i = 1; i <= total; i++) {
      if (i <= current) {
        array.push(
          <CircleBox>
            <Circle>
              <Text>{i}</Text>
            </Circle>
            <Text css={TextStyle}>{menus[i - 1]}</Text>
          </CircleBox>
        );
      } else {
        array.push(
          <CircleBox>
            <Circle
              css={css`
                background-color: ${theme.color.text.main};
              `}
            >
              <Text
                css={css`
                  color: ${theme.color.background.page};
                `}
              >
                {i}
              </Text>
            </Circle>
            <Text css={TextStyle}>{menus[i - 1]}</Text>
          </CircleBox>
        );
      }
      if (i !== total) array.push(<Line />);
    }
    return array;
  };
  return (
    <CircleWrapper ref={ref} {...rest}>
      {CircleHandler()}
    </CircleWrapper>
  );
}
const CircleWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin: 2rem 0;
`;

const CircleBox = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  margin-bottom: 2rem;
`;

const Circle = styled.div`
  width: 3rem;
  height: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  margin-bottom: 0.5rem;
  background-color: ${({ theme }) => theme.color.background.emphasis};
`;

const Line = styled.div`
  width: 5rem;
  height: 0;
  border-top: 1px solid ${({ theme }) => theme.color.text.main};
  margin-bottom: 3rem;
`;

const TextStyle = css`
  font-size: 0.5rem;
`;

export default forwardRef(CircleBar) as typeof CircleBar;
