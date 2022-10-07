import { AlertBaseProps } from "@/types/props";
import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";

const authSlice = createSlice({
  name: "login",
  initialState: {
    isLogined: false,
    walletAddress: "",
  },
  reducers: {
    _setLogined(state, action: PayloadAction<boolean>) {
      state.isLogined = action.payload;
    },
    _setAccount(state, action: PayloadAction<string>) {
      state.walletAddress = action.payload;
    },
  },
});
const alertSlice = createSlice({
  name: "alert",
  initialState: {
    isAlertOn: false,
    message: "",
    isSuccess: false,
  },
  reducers: {
    setIsAlertOn(state, action: PayloadAction<boolean>) {
      state.isAlertOn = action.payload;
    },
    setAlertState(state, action: PayloadAction<AlertBaseProps>) {
      state.message = action.payload.message;
      state.isSuccess = action.payload.isSuccess;
    },
  },
});
const alarmSlice = createSlice({
  name: "alarm",
  initialState: {
    hasUnreadAlarm: false,
  },
  reducers: {
    _setHasUnreadAlarm(state, action: PayloadAction<boolean>) {
      state.hasUnreadAlarm = action.payload;
    },
  },
});

const store = configureStore({
  reducer: {
    auth: authSlice.reducer,
    alert: alertSlice.reducer,
    alarm: alarmSlice.reducer,
  },
});

export const { _setLogined, _setAccount } = authSlice.actions;
export const { setIsAlertOn, setAlertState } = alertSlice.actions;
export const { _setHasUnreadAlarm } = alarmSlice.actions;
export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
