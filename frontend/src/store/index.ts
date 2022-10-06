import { configureStore, createSlice, PayloadAction } from "@reduxjs/toolkit";

type AuthStateType = {};

const authSlice = createSlice({
  name: "login",
  initialState: {
    isLogined: false,
  },
  reducers: {
    _setLogined(state, action: PayloadAction<boolean>) {
      state.isLogined = action.payload;
    },
  },
});

const store = configureStore({
  reducer: {
    auth: authSlice.reducer,
  },
});

export const { _setLogined } = authSlice.actions;
export default store;
export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
