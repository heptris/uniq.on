import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

import { ENDPOINT_AUTH } from "@/api/endpoints";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body } = req;

  if (method !== "POST") {
    res.status(404).end();
  }

  await axios
    .post(`${ENDPOINT_AUTH}/auth/login`, body)
    .then((response) => {
      const { data, headers } = response;

      const accessToken = data.data.accessToken;
      const refreshToken = data.data.accessToken;
      axios.defaults.headers.common.Authorization = `Bearer ${accessToken}`;
      console.log(data.data, headers);
      console.log(axios.defaults.headers.common);
      res.status(200).json(data);
    })
    .catch((e) => {
      const { response } = e;
      const { status, data } = response;
      console.log(data);
      res.status(status).json(data);
    });
};
