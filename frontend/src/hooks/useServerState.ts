import { QUERY_KEYS } from "@/api/query_key_schema";
import { useQueries } from "@tanstack/react-query";
import axios from "axios";

const {
  MY_USER_INFO,
  MY_APPLY_LIST,
  MY_FAVORITE_LIST,
  MY_RESERVE_LIST,
  INVEST_LIST,
} = QUERY_KEYS;

export const useServerState = () => {
  useQueries({
    queries: [
      {
        queryKey: [MY_USER_INFO],
        queryFn: () => axios.get(`api/mypage`).then(({ data }) => data),
      },
      {
        queryKey: [MY_RESERVE_LIST],
        queryFn: () => axios.get(`api/mypage/invest`).then(({ data }) => data),
      },
      {
        queryKey: [MY_FAVORITE_LIST],
        queryFn: () =>
          axios.get(`api/mypage/favstartup`).then(({ data }) => data),
      },
      {
        queryKey: [MY_APPLY_LIST],
        queryFn: () => axios.get(`api/mypage/startup`).then(({ data }) => data),
      },
      {
        queryKey: [INVEST_LIST],
        queryFn: () =>
          axios.get("/api/invest").then(({ data }) => data.content),
      },
    ],
  });
};
