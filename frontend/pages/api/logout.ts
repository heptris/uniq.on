import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

import { ENDPOINT_AUTH } from "@/api/endpoints";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method } = req;

  if (method !== "GET") {
    res.status(404).end();
  }

  console.log("axios 헤더 확인", axios.defaults.headers.common);

  await axios
    .get(`${ENDPOINT_AUTH}/logout`)
    .then((response) => {
      const {
        data: { status, message },
      } = response;
      axios.defaults.headers.common.Authorization = ``;
      axios.defaults.data = "";
      res.status(status).json(message);
    })
    .catch((e) => {
      console.log("로그아웃 처리 오류 확인 로그", e);
      res.status(500).json("로그아웃 처리 중 서버 에러");
    });
};
