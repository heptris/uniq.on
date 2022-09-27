import LabelInput from "@/components/LabelInput";
import contracts from "@/contracts/utils";
import { SignupForm } from "@/types/api_requests";
import { ChangeEvent, useState } from "react";

function SignUp() {
  const { account } = contracts.useAccount();
  const [form, setForm] = useState<SignupForm>({
    name: "",
    nickname: "",
    email: "",
    walletAddress: account,
  });
  const onChangeForm = (e: ChangeEvent<HTMLInputElement>) => {
    const { name, value } = e.target;
    setForm({
      ...form,
      [name]: value,
    });
  };
  return (
    <>
      <LabelInput
        placeholder="이름"
        labelText="이름"
        value={form.name}
        name={`name`}
        onChange={onChangeForm}
      />
      <LabelInput
        placeholder="닉네임"
        labelText="닉네임"
        value={form.nickname}
        name={`nickname`}
        onChange={onChangeForm}
      />
      <LabelInput
        placeholder="이메일"
        labelText="이메일"
        value={form.email}
        name={`email`}
        onChange={onChangeForm}
      />
    </>
  );
}

export default SignUp;
