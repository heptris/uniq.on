import axios from "axios";
import { useMutation, useQuery } from "@tanstack/react-query";

import { ENDPOINT_API } from "@/api/endpoints";
import { QUERY_KEYS } from "@/api/query_key_schema";

import { AlarmProps } from "@/types/props";
import { AlarmItem } from "@/types/api_responses";

const { ALARM_LIST } = QUERY_KEYS;

const getAlarmList = async () =>
  await axios
    .get(`${ENDPOINT_API}/alarm/alarmList`)
    .then<AlarmItem[]>(({ data }) => data.data);

const useAlarm = () =>
  // { alarmList }: AlarmProps
  useQuery(
    [ALARM_LIST],
    getAlarmList
    // { initialData: alarmList }
  );

const useAlarmMutation = () =>
  useMutation(({ alarmId }: { alarmId: number }) =>
    axios.post(`${ENDPOINT_API}/alarm/readAlarm/${alarmId}`)
  );

export { useAlarm, getAlarmList, useAlarmMutation };
