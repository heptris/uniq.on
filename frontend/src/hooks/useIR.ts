import { useMutation, useQuery } from "@tanstack/react-query";
import axios from "axios";

import { ENDPOINT_API } from "@/api/endpoints";
import { IR } from "@/types/api_responses";
import { QUERY_KEYS } from "@/api/query_key_schema";

const { IR_DETAIL } = QUERY_KEYS;

const useIR = (id: string | string[] | undefined, initialData: IR) =>
  useQuery<IR>([IR_DETAIL], () => getIR(id), { initialData });

const getIR = async (startupId: string | string[] | undefined) =>
  await axios
    .get(`${ENDPOINT_API}/invest/${startupId}`)
    .then(({ data }) => data.data);

const useNftReserveMutation = () =>
  useMutation(({ startupId }: { startupId: string | string[] | undefined }) =>
    axios.get(`${ENDPOINT_API}/invest/reserve/${startupId}`)
  );

const useNftFavMutation = () =>
  useMutation(({ startupId }: { startupId: string | string[] | undefined }) =>
    axios.get(`${ENDPOINT_API}/invest/${startupId}/favorite`)
  );

export { getIR, useIR, useNftReserveMutation, useNftFavMutation };
