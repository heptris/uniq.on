import { ENDPOINT_AUTH } from "@/api/endpoints";
import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body, url } = req;

  console.log(body);

  const { name, nickname, email, walletAddress } = body;

  switch (method) {
    case "POST":
      await axios
        .post(`${ENDPOINT_AUTH}/signup`, {
          name,
          nickName: nickname,
          email,
          walletAddress,
        })
        .then((response) => {
          console.log(response);

          const { data } = response;

          res.status(200).json(data.data);
        })
        .catch((e) => {
          const { response } = e;
          const { status, data } = response;
          console.log(data);
          res.status(status).json(data);
        });
      break;
    case "GET":
      console.log("여기 걸리나");
      res.status(404).json({ data: "닉네임을 입력해주세요" });
      break;
  }
};
