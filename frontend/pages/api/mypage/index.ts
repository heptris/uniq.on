import axios from "axios";

import { ENDPOINT_API } from "@/api/endpoints";
import { NextApiRequest, NextApiResponse } from "next";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body, url, query } = req;

  switch (method) {
    case "GET":
      return axios
        .get(`${ENDPOINT_API}/member`)
        .then((response) => {
          const { status, data } = response;
          console.log("사용자 정보 조회", data.data);
          res
            .status(status)
            .json({ ...data.data, nickname: data.data.nickName });
        })
        .catch((err) => {
          const { response } = err;
          const { status, data } = response;
          res.status(status).json(data);
        });
  }

  // switch (method) {
  //   case "POST":
  //     await axios
  //       .post(`${ENDPOINT_API}/signup`, {
  //         name,
  //         nickName: nickname,
  //         email,
  //         walletAddress,
  //       })
  //       .then((response) => {
  //         console.log(response);

  //         const { data } = response;

  //         res.status(200).json(data.data);
  //       })
  //       .catch((e) => {
  //         const { response } = e;
  //         const { status, data } = response;
  //         console.log(data);
  //         res.status(status).json(data);
  //       });
  //     break;
  //   case "GET":
  //     console.log("여기 걸리나");
  //     res.status(404).json({ data: "닉네임을 입력해주세요" });
  //     break;
  // }
};
