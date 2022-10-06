import { ENDPOINT_API } from "@/api/endpoints";
import { ACCESS_TOKEN } from "@/api/utils";
import Button from "@/components/Button";
import LabelInput from "@/components/LabelInput";
import Text from "@/components/Text";
import { useAlert, useForm } from "@/hooks";
import { minDesktopWidth } from "@/styles/utils";
import { css } from "@emotion/react";
import styled from "@emotion/styled";
import axios from "axios";
import { GetServerSideProps } from "next";
import { useRouter } from "next/router";
import { useState } from "react";
import { CommentListProps } from "../../detail/[communityId]/[startupId]";

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { startupId, communityId } = context.query;
  const config = {
    headers: { authorization: `Bearer ${context.req.cookies[ACCESS_TOKEN]}` },
  };
  const CommentRequest = await axios
    .get(`${ENDPOINT_API}/invest/community/detail/${communityId}`, config)
    .then(({ data }) => data.data);
  return { props: { CommentRequest, startupId, communityId } };
};

type CommunityDetailProps = {
  commentList: CommentListProps[];
  content: string;
  createdDate: string;
  hit: number;
  nickName: string;
  title: string;
};
type UpdateProps = {
  startupId: number;
  communityId: number;
  CommentRequest: CommunityDetailProps;
};

export default function update(props: UpdateProps) {
  const router = useRouter();
  const { startupId, communityId, CommentRequest } = props;
  console.log(CommentRequest);
  const { handleAlertOpen } = useAlert();
  const [title, setTitle] = useState(CommentRequest.title);
  const [content, setContent] = useState(CommentRequest.content);

  const onSubmit = () => {
    if (title && content) {
      const data = {
        title,
        content,
      };

      const config = {
        headers: {
          "content-type": "application/json;charset=UTF-8;",
        },
      };
      axios
        .put(
          `${ENDPOINT_API}/invest/community/${startupId}/${communityId}`,
          data,
          config
        )
        .then((res) => {
          console.log(res);
          handleAlertOpen(2000, "게시글 수정이 완료되었습니다.", true);
          setTitle("");
          setContent("");
          router.push(`/community/detail/${communityId}/${startupId}`);
        })
        .catch((err) => {
          console.log(err);
          handleAlertOpen(2000, "게시글 수정이 실패했습니다.", false);
        });
    } else {
      handleAlertOpen(2000, "모든 칸을 채워주세요.", false);
    }
  };
  return (
    <WriteContainer>
      <Text
        css={css`
          align-self: flex-start;
          font-size: 2rem;
          font-weight: 600;
          margin-bottom: 5rem;
        `}
      >
        글작성
      </Text>
      <LabelInput
        labelText="제목"
        value={title}
        onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
          setTitle(e.target.value)
        }
      />
      <br />
      <LabelInput
        labelText="내용"
        value={content}
        css={css`
          height: 10rem;
        `}
        onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
          setContent(e.target.value)
        }
      />
      <Button
        onClick={onSubmit}
        css={css`
          margin-top: 1rem;
          align-self: flex-end;
        `}
      >
        수정
      </Button>
    </WriteContainer>
  );
}

const WriteContainer = styled.div`
  padding-top: 3rem;
  width: 100%;
  display: flex;
  flex-direction: column;
  @media (${minDesktopWidth}) {
    width: 33.33%;
  }
`;
