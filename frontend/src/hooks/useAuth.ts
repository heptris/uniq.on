import axios from "axios";
import { useEffect } from "react";
import { useRouter } from "next/router";

import contracts from "@/contracts/utils";
import { _setLogined } from "@/store";
import { ROUTES } from "@/constants";
import { useAppDispatch, useAppSelector } from ".";
import { useAlert } from "./useAlert";

import type { SignupForm } from "@/types/api_requests";

const { SIGNUP, HOME } = ROUTES;

export const useAuth = () => {
  const router = useRouter();
  const { account, checkConnection, connect } = contracts.useAccount();
  const { isLogined } = useAppSelector((state) => state.auth);
  const { handleAlertOpen } = useAlert();
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
      .post("api/login", { walletAddress: account })
      .then((res) => {
        setIsLogined(true);
        router.push(HOME);
      })
      .catch((e) => {
        const { response } = e;
        const { status, data } = response;
        if (status === 404) {
          handleAlertOpen(2000, "회원가입이 필요한 서비스입니다", false);
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
        handleAlertOpen(2000, "회원 가입이 실패했습니다.", false);
      });
  };

  useEffect(() => {
    account && handleLogin();
  }, [account]);

  return { isLogined, handleWallet, handleSignup };
};
