import { useEffect } from "react";
import axios from "axios";

import { useAlert } from "./useAlert";
import { useAppDispatch, useAppSelector } from ".";

import { ENDPOINT_API } from "@/api/endpoints";

import { _setHasUnreadAlarm } from "@/store";

export const useServer = () => {
  const {
    alarm: { hasUnreadAlarm },
    auth: { isLogined },
  } = useAppSelector(({ alarm, auth }) => {
    return { alarm, auth };
  });
  const dispatch = useAppDispatch();
  const setHasUnreadAlarm = (state: boolean) => {
    if (isLogined) dispatch(_setHasUnreadAlarm(state));
  };

  const { handleAlertOpen } = useAlert();

  const handleNicknameCheck = async (nickname: string) => {
    return await axios
      .get(`api/signup/${nickname}`)
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

  const handleUnreadAlarm = async () => {
    await axios
      .get(`${ENDPOINT_API}/alarm/unReadAlarmList`)
      .then(({ data }) => {
        console.log(data.data);
        data.data.length > 0
          ? setHasUnreadAlarm(true)
          : setHasUnreadAlarm(false);
      });
  };

  useEffect(() => {
    isLogined && handleUnreadAlarm();
  }, [isLogined]);

  return { hasUnreadAlarm, handleNicknameCheck, handleUnreadAlarm };
};
