export * from "@/hooks/useNFTModal";
export * from "@/hooks/useInterval";
export * from "@/hooks/useSelectTab";
export * from "@/hooks/useAuth";
export * from "@/hooks/useAlert";
export * from "@/hooks/useServer";
export * from "@/hooks/useForm";

import { useDispatch, useSelector } from "react-redux";
import type { TypedUseSelectorHook } from "react-redux";
import type { RootState, AppDispatch } from "@/store";

// Use throughout your app instead of plain `useDispatch` and `useSelector`
export const useAppDispatch: () => AppDispatch = useDispatch;
export const useAppSelector: TypedUseSelectorHook<RootState> = useSelector;
