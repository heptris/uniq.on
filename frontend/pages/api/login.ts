import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

import { ENDPOINT_AUTH } from "@/api/endpoints";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body } = req;
  if (method !== "POST" && method !== "GET") {
    res.status(404).end();
  }

  const onLoginProcess = (data: {
    accessToken: string;
    refreshToken: string;
    accessTokenExpiresIn: number;
  }) => {
    const accessToken = data.accessToken;
    const refreshToken = data.refreshToken;
    console.log("로그인 하고서", accessToken, refreshToken);
    const accessTokenExpiresIn = data.accessTokenExpiresIn;
    axios.defaults.headers.common.Authorization = `Bearer ${accessToken}`;
    axios.defaults.data = refreshToken;
    const clientData = {
      accessToken,
      accessTokenExpiresIn,
    };
    res.status(200).json(clientData);
  };

  switch (method) {
    // 로그인 케이스
    case "POST":
      await axios
        .post(`${ENDPOINT_AUTH}/login`, body)
        .then((response) => {
          const {
            data: { data: resData },
          } = response;
          onLoginProcess(resData);
        })
        .catch((e) => {
          const { response } = e;
          const { status, data } = response;
          console.log(data);
          res.status(status).json(data);
        });
      break;
    // 토큰 재발행 케이스
    case "GET":
      const accessToken =
        `${axios.defaults.headers.common.Authorization}`.slice(7);
      const refreshToken = axios.defaults.data;
      await axios
        .post(`${ENDPOINT_AUTH}/reissue`, {
          accessToken,
          refreshToken,
        })
        .then((response) => {
          const {
            data: { data: resData },
          } = response;
          console.log("재발행이후", resData);
          onLoginProcess(resData);
        })
        .catch((e) => {
          const { response } = e;
          const { status, data } = response;
          console.log(data);
          res.status(status).json(data);
        });
      break;
  }
};
