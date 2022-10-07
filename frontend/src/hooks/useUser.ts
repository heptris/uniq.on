import axios from "axios";

import { ENDPOINT_API } from "@/api/endpoints";
import { QUERY_KEYS } from "@/api/query_key_schema";
import {
  APPLYItem,
  FAVItem,
  Member,
  NFTItem,
  RSRVItem,
} from "@/types/api_responses";
import { MyPageProps } from "@/types/props";
import { useQueries } from "@tanstack/react-query";

const {
  MY_APPLY_LIST,
  MY_FAVORITE_LIST,
  MY_NFT_LIST,
  MY_RESERVE_LIST,
  MY_USER_INFO,
} = QUERY_KEYS;

const getUserInfo = async (config?: object) =>
  await axios.get(`${ENDPOINT_API}/member`, config).then<Member>(({ data }) => {
    const { nickName: nickname, memberId: id, ...rest } = data.data;
    return {
      nickname,
      id,
      ...rest,
    };
  });
const getApplyList = async (config?: object) =>
  await axios
    .get(`${ENDPOINT_API}/member/mypage/startup`, config)
    .then<APPLYItem[]>(({ data }) => data.data);
const getFavList = async (config?: object) =>
  await axios
    .get(`${ENDPOINT_API}/member/mypage/favstartup`, config)
    .then<FAVItem[]>(({ data }) => data.data);
const getReserveList = async (config?: object) =>
  await axios
    .get(`${ENDPOINT_API}/member/mypage/invest`, config)
    .then<RSRVItem[]>(({ data }) => data.data);
const getNftList = async (config?: object) =>
  await axios
    .get(`${ENDPOINT_API}/member/mypage/own-nft`, config)
    .then<NFTItem[]>(({ data }) =>
      data.data.map((item: { [x: string]: unknown; price: number }) => {
        const { price: nftPrice, ...rest } = item;
        return {
          nftPrice,
          ...rest,
        };
      })
    );

const useUserQueries = ({
  member,
  applyList,
  favoriteList,
  reserveList,
  nftList,
}: MyPageProps) =>
  useQueries({
    queries: [
      {
        queryKey: [MY_USER_INFO],
        queryFn: getUserInfo,
        initialData: member,
      },
      {
        queryKey: [MY_APPLY_LIST],
        queryFn: getApplyList,
        initialData: applyList,
      },
      {
        queryKey: [MY_FAVORITE_LIST],
        queryFn: getFavList,
        initialData: favoriteList,
      },
      {
        queryKey: [MY_RESERVE_LIST],
        queryFn: getReserveList,
        initialData: reserveList,
      },
      {
        queryKey: [MY_NFT_LIST],
        queryFn: getNftList,
        initialData: nftList,
      },
    ],
  });

export {
  useUserQueries,
  getApplyList,
  getFavList,
  getReserveList,
  getUserInfo,
  getNftList,
};
