import { ENDPOINT_API } from "@/api/endpoints";
import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body, query } = req;
  if (method !== "GET") res.status(400).end();

  const page = typeof query.page === "string" ? query.page : 0;

  await axios({
    method: "get",
    url: `${ENDPOINT_API}/invest`,
    params: {
      size: 10,
      page: page,
    },
  })
    .then((resp) => {
      const { data, headers } = resp;
      res.status(200).json(data.data);
    })
    .catch((e) => {
      const { resp } = e;
      const { status, data } = resp;
      console.log(data);
      res.status(status).json(data);
    });
};
