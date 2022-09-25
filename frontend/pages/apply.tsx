import Text from "@/components/Text";
import { css } from "@emotion/react";
import CircleBar from "@/components/CircleBar";
import styled from "@emotion/styled";
import { useState } from "react";
import Button from "@/components/Button";
import LabelInput from "@/components/LabelInput";

export default function apply() {
  const [current, setCurrent] = useState(1);
  const currentUpHandler = () => {
    setCurrent(current + 1);
  };
  const submitHandler = () => {
    setCurrent(1);
  };
  return (
    <ContainWrapper>
      <Text
        as="h1"
        css={css`
          font-size: 1.5rem;
          margin: 3rem 0 1rem;
        `}
      >
        투자받기
      </Text>
      <CircleBar current={current} total={3} />
      {current === 1 && (
        <>
          <InputBox labelText="기업명" />
          <InputBox labelText="담당자 이름/직함" />
          <InputBox labelText="담당자 이메일" />
          <InputBox labelText="담당자 이메일" />
          <ButtonBox onClick={currentUpHandler}>다음단계</ButtonBox>
        </>
      )}
      {current === 2 && (
        <>
          <InputBox labelText="희망 모집 금액(SSH)" />
          <InputBox labelText="투자 마감일" />
          <InputBox labelText="토큰 발행 개수" />
          <InputBox labelText="디스코드 주소" />
          <InputBox labelText="회사 소개글" />
          <InputBox labelText="투자자 모집 제목" />
          <ButtonBox onClick={currentUpHandler}>다음단계</ButtonBox>
        </>
      )}
      {current === 3 && (
        <>
          <Text>개인정보 수집 및 이용 동의</Text>
          <ButtonBox onClick={submitHandler}>제출완료</ButtonBox>
        </>
      )}
    </ContainWrapper>
  );
}

const ContainWrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
`;

const InputBox = styled(LabelInput)`
  width: 20rem;
  height: 2rem;
  margin-bottom: 1rem;
`;

const ButtonBox = styled(Button)`
  margin: 3rem 0;
  width: 20rem;
`;
