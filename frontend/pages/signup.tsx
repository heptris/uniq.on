import { css } from "@emotion/react";

import { useSignupForm, useAuth } from "@/hooks";
import LabelInput from "@/components/LabelInput";
import Card from "@/components/Card";
import Button from "@/components/Button";

function SignUp() {
  const { form, onChangeForm, handleNicknameCheck } = useSignupForm();
  const { handleSignup } = useAuth();

  return (
    <Card>
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
            bottom: 0.17rem;
          `}
          onClick={handleNicknameCheck}
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
      <Button onClick={() => handleSignup(form)}>회원 가입</Button>
    </Card>
  );
}

export default SignUp;
