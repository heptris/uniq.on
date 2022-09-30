import { ENDPOINT_API } from "@/api/endpoints";
import axios from "axios";
import { NextApiRequest, NextApiResponse } from "next";
import formidable from "formidable";

export const config = {
  api: {
    bodyParser: false
  }
}

export default async (req: NextApiRequest, res: NextApiResponse) => {
  const { method, body, query } = req;



  // form.parse(req, (err, fields, files) => {
  //   console.log(err, fields, files);
  // });

  // console.log("body", body)
  // // console.log("하이", method, body.startupRequestDto)


  // await axios({
  //   method: "post",
  //   url: `${ENDPOINT_API}/invest/regist`,
  //   data: body,
  //   headers: {
  //     'Content-Type': 'multipart/form-data; charset=UTF-8 boundary=6o2knFse3p53ty9dmcQvWAIx1zInP11uCfbm; '
  //   },
  // })
  //   .then((resp) => {
  //     console.log("res", resp)
  //     const { data } = resp;
  //     res.status(200).json(data.data);
  //   })
  //   .catch((err) => {
  //     console.log("err", err)
  //     const { response } = err;
  //     // const { status, data } = resp;
  //     console.log(response);
  //     res.status(404).json("오류 입니다");
  //   });
};
