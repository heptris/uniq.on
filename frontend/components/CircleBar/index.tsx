import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import Text from "../Text";

type CircleProps = {
  total?: number;
  current?: number;
};

export default function CircleBar({ current = 1, total = 3 }: CircleProps) {
  const theme = useTheme();
  const CircleHandler = () => {
    let array = [];
    for (let i = 1; i <= total; i++) {
      if (i <= current) {
        array.push(
          <Circle>
            <Text>{i}</Text>
          </Circle>
        );
      } else {
        array.push(
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
        );
      }
      if (i !== total) array.push(<Line />);
    }
    return array;
  };
  return <CircleWrapper>{CircleHandler()}</CircleWrapper>;
}
const CircleWrapper = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  width: 100%;
  margin: 2rem 0;
`;

const Circle = styled.div`
  width: 3rem;
  height: 3rem;
  display: flex;
  justify-content: center;
  align-items: center;
  border-radius: 50%;
  background-color: ${({ theme }) => theme.color.background.emphasis};
`;

const Line = styled.div`
  width: 5rem;
  height: 0;
  border-top: 1px solid ${({ theme }) => theme.color.text.main};
`;
