import { setAlertState, setIsAlertOn } from "@/store";
import { useAppDispatch, useAppSelector } from ".";

export const useAlert = () => {
  const dispatch = useAppDispatch();

  const handleAlertOpen = (
    time = 2000,
    message = "404 에러 잘못된 요청입니다",
    isSuccess = false
  ) => {
    dispatch(setAlertState({ message, isSuccess }));
    dispatch(setIsAlertOn(true));
    setTimeout(() => {
      dispatch(setIsAlertOn(false));
    }, time);
  };

  return { handleAlertOpen };
};
