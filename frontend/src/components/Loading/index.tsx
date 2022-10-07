import styled from "@emotion/styled";

function Loading() {
  return (
    <Wrapper>
      <Spinner />
    </Wrapper>
  );
}

const Wrapper = styled.div`
  background-size: cover;
  background-color: rgba(0, 0, 0, 0.5);
  position: fixed;
  top: 0;
  right: 0;
  bottom: 0;
  left: 0;
  z-index: 9995;
  display: flex;
  justify-content: center;
  align-items: center;
`;
const Spinner = styled.div`
  width: 4rem;
  height: 4rem;
  transform: translate(-50%, -50%);
  border: 10px solid ${({ theme }) => theme.color.background.item};
  border-top: 10px solid ${({ theme }) => theme.color.background.emphasis};
  border-radius: 50%;
  animation: spinner 0.4s linear infinite;
  box-sizing: border-box;

  @keyframes spinner {
    0% {
      transform: rotate(0deg);
    }
    100% {
      transform: rotate(360deg);
    }
  }
`;
export default Loading;
