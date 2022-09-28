import { ENDPOINT_AUTH } from "@/api/endpoints";
import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body, url, query } = req;

  // console.log(url, query);
  const nickname =
    typeof query.nickname == "string" ? decodeURI(query.nickname) : "";
  switch (method) {
    case "GET":
      axios
        .get(`${ENDPOINT_AUTH}/${encodeURI(nickname)}/check`)
        .then((response) => {
          const {
            data: { status, data },
          } = response;
          // console.log(response);
          res.status(status).json(data);
        })
        .catch((error) => {
          const { response } = error;
          const {
            data: { status, message },
          } = response;
          res.status(status).json({ data: message });
        });
      break;
  }
};
