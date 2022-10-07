import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { minDesktopWidth } from "@/styles/utils";

import { useForm, useAuth, useServer } from "@/hooks";

import LabelInput from "@/components/LabelInput";
import Button from "@/components/Button";
import Text from "@/components/Text";

import contracts from "@/contracts/utils";

import { SignupFormType } from "@/types/api_requests";

function SignUp() {
  const { account } = contracts.useAccount();
  const { form, onChangeForm } = useForm<SignupFormType>({
    email: "",
    name: "",
    nickname: "",
    walletAddress: account,
  });
  const { handleNicknameCheck } = useServer();
  const { handleSignup } = useAuth();

  return (
    <SignUpContainer>
      <Text
        as="h1"
        role="page-header"
        css={css`
          font-size: 2rem;
          font-weight: 700;
          margin-bottom: 2rem;
        `}
      >
        회원가입
      </Text>
      <InputContainer>
        <LabelInput
          placeholder="이름"
          labelText="이름"
          value={form.name}
          name={`name`}
          onChange={onChangeForm}
        />
        <div
          css={css`
            position: relative;
          `}
        >
          <LabelInput
            placeholder="닉네임"
            labelText="닉네임"
            value={form.nickname}
            name={`nickname`}
            onChange={onChangeForm}
          />
          <Button
            css={css`
              position: absolute;
              right: 0.2rem;
              bottom: 50%;
              transform: translate(0, 80%);
            `}
            onClick={() => handleNicknameCheck(form.nickname)}
          >
            중복 확인
          </Button>
        </div>
        <LabelInput
          placeholder="이메일"
          labelText="이메일"
          value={form.email}
          name={`email`}
          onChange={onChangeForm}
        />
        <Button
          size={"full"}
          onClick={() => handleSignup(form)}
          css={css`
            margin-top: 2rem;
            height: 3rem;
          `}
        >
          회원 가입
        </Button>
      </InputContainer>
    </SignUpContainer>
  );
}

const SignUpContainer = styled.div`
  width: 100%;
  padding-top: 5rem;

  @media (${minDesktopWidth}) {
    width: 25%;
  }
`;
const InputContainer = styled.div`
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
`;

export default SignUp;
