import { ENDPOINT_API } from "@/api/endpoints";
import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body, query } = req;
  if (method !== "GET") res.status(400).end();

  const startupId =
    typeof query.startupId === "string"
      ? decodeURIComponent(query.startupId)
      : "";
  if (startupId === "") res.status(400).end();

  await axios
    .get(`${ENDPOINT_API}/invest/${encodeURIComponent(startupId)}`)
    .then((resp) => {
      const { data, headers } = resp;
      console.log(data);
      res.status(200).json(data.data);
    })
    .catch((e) => {
      const { resp } = e;
      const { status, data } = resp;
      console.log(data);
      res.status(status).json(data);
    });
};
