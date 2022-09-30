import { ENDPOINT_API } from "@/api/endpoints";
import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, query } = req;

  // console.log(url, query, method);

  if (method !== "GET") res.status(404).json("잘못된 요청입니다.");

  const { listId } = query;

  return axios
    .get(`${ENDPOINT_API}/member/mypage/${listId}`)
    .then((response) => {
      const { status, data } = response;
      console.log(data);
      res.status(status).json(data.data);
    })
    .catch((err) => {
      const { response } = err;
      const { status, data } = response;
      res.status(status).json(data);
    });

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
