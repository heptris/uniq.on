import axios from "axios";

import { ENDPOINT_API } from "@/api/endpoints";
import { QUERY_KEYS } from "@/api/query_key_schema";

import { useMutation, useQuery } from "@tanstack/react-query";
import { CommunityDetailProps } from "@/pages/community/detail/[communityId]/[startupId]";

const { MY_COMMUNITY_LIST } = QUERY_KEYS;

const getCommunityList = async (communityId: number) =>
  await axios
    .get(`${ENDPOINT_API}/invest/community/detail/${communityId}`)
    .then(({ data }) => data.data);

const useCommunity = ({
  communityList,
  communityId,
}: {
  communityList: CommunityDetailProps;
  communityId: number;
}) =>
  useQuery([MY_COMMUNITY_LIST], () => getCommunityList(communityId), {
    initialData: communityList,
  });

const commentDeleteMutation = () =>
  useMutation(
    ({ communityId, commentId }: { communityId: number; commentId: number }) =>
      axios.delete(
        `${ENDPOINT_API}/invest/community-comments/${communityId}/${commentId}`
      )
  );
const commentCreateMutation = () =>
  useMutation(({ communityId, data }: { communityId: number; data: any }) =>
    axios.post(
      `${ENDPOINT_API}/invest/community-comments/${communityId}/comment`,
      data
    )
  );
const commentUpdateMutation = () =>
  useMutation(
    ({
      communityId,
      commentId,
      data,
    }: {
      communityId: number;
      commentId: number;
      data: any;
    }) =>
      axios.put(
        `${ENDPOINT_API}/invest/community-comments/${communityId}/${commentId}`,
        data
      )
  );

export {
  useCommunity,
  getCommunityList,
  commentDeleteMutation,
  commentCreateMutation,
  commentUpdateMutation,
};
