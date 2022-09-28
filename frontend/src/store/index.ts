import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";

type AuthStateType = {};

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
    setAlertState(
      state,
      action: PayloadAction<{ message: string; isSuccess: boolean }>
    ) {
      state.message = action.payload.message;
      state.isSuccess = action.payload.isSuccess;
    },
  },
});

const store = configureStore({
  reducer: {
    auth: authSlice.reducer,
    alert: alertSlice.reducer,
  },
});

export const { _setLogined, _setAccount } = authSlice.actions;
export const { setIsAlertOn, setAlertState } = alertSlice.actions;
export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
