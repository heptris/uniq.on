import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

import { ENDPOINT_AUTH } from "@/api/endpoints";
import { ACCESS_TOKEN, REFRESH_TOKEN } from "@/api/utils";

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

    setCookie(ACCESS_TOKEN, accessToken, {
      req,
      res,
      httpOnly: true,
      secure: true,
      sameSite: "strict",
    });
    setCookie(REFRESH_TOKEN, refreshToken, {
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
      const arr: [string, string][] | undefined = req.headers.cookie
        ?.split(";")
        .map((el) => {
          const tmp = el.trim().split("=");
          return [tmp[0], tmp[1]];
        });

      const cookieMap = new Map<string, unknown>(arr);
      const accessToken = req.headers.authorization?.split(" ")[1];
      const refreshToken = cookieMap.get(REFRESH_TOKEN);
      console.log(cookieMap);
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
          console.log(status, data);
          res.status(status).json(data);
        });
      break;
  }
};
