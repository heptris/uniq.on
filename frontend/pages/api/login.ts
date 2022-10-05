import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

import { ENDPOINT_AUTH } from "@/api/endpoints";
import { ACCESS_TOKEN, apiSessionStorage } from "@/api/utils";

import { setCookie } from "cookies-next";

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
    const accessTokenExpiresIn = data.accessTokenExpiresIn;
    const clientData = {
      accessToken,
      accessTokenExpiresIn,
    };
    apiSessionStorage.set(accessToken, refreshToken);
    setCookie(ACCESS_TOKEN, accessToken, {
      req,
      res,
      httpOnly: true,
      secure: true,
      sameSite: "strict",
    });
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
      const accessToken = req.headers.authorization?.split(" ")[1];

      const refreshToken = apiSessionStorage.get(accessToken ?? "");
      refreshToken && apiSessionStorage.delete(accessToken ?? "");

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
