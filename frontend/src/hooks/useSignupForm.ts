import contracts from "@/contracts/utils";
import { SignupForm } from "@/types/api_requests";
import axios from "axios";
import { ChangeEvent, useState } from "react";
import { useAlert } from "./useAlert";

export const useSignupForm = () => {
  const { account } = contracts.useAccount();
  const { handleAlertOpen } = useAlert();
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
  const handleNicknameCheck = async () => {
    console.log(axios.defaults.headers);
    return await axios
      .get(`api/signup/${form.nickname}`)
      .then((res) => {
        const { status } = res;
        console.log(res);
        handleAlertOpen(1000, res.data, true);
        return status === 200;
      })
      .catch((err) => {
        console.log(err);
        const { response } = err;
        handleAlertOpen(1000, response.data.data, false);
      });
  };

  return { form, onChangeForm, handleNicknameCheck };
};
