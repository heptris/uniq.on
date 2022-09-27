import contracts from "@/contracts/utils";
import { _setLogined } from "@/store";
import { ROUTES } from "@/constants";
import axios from "axios";
import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from ".";
import { useRouter } from "next/router";

import type { SignupForm } from "@/types/api_requests";

const { SIGNUP, HOME } = ROUTES;

export const useAuth = () => {
  const router = useRouter();
  const { account, checkConnection, connect } = contracts.useAccount();
  const { isLogined } = useAppSelector((state) => state.auth);
  const dispatch = useAppDispatch();
  const setIsLogined = (state: boolean) => {
    if (account) dispatch(_setLogined(state));
  };

  const handleWallet = async () => {
    const isConnected = await checkConnection();
    if (!isConnected) {
      if (!(await connect())) {
        alert("지갑을 연결해주세요!");
        return;
      }
    }
  };

  const handleLogin = async () => {
    // 지갑 연결 이후
    // 주소를 서버에 보내고 로그인 과정 처리
    await axios
      .post("api/login", { userAccount: account })
      .then((res) => {
        console.log(axios.defaults.headers);
        setIsLogined(true);
        router.push(HOME);
      })
      .catch((e) => {
        const { response } = e;
        const { status, data } = response;
        if (status === 404) {
          router.push(SIGNUP);
        }
      });
  };

  const handleSignup = async (signupForm: SignupForm) => {
    await axios
      .post("api/signup", signupForm)
      .then(() => handleLogin())
      .catch((e) => {
        const { response } = e;
        const { status, data } = response;
        alert("회원 가입이 실패했습니다.");
      });
  };

  useEffect(() => {
    account && handleLogin();
  }, [account]);

  // useEffect(() => {
  //   if (!isLogined) {
  //     handleWallet();
  //   }
  // }, []);

  return { isLogined, handleWallet, handleSignup };
};
