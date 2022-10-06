import { ENDPOINT_API } from "@/api/endpoints";
import { ACCESS_TOKEN } from "@/api/utils";
import Button from "@/components/Button";
import Card from "@/components/Card";
import Comment from "@/components/Comment";
import Grid from "@/components/Grid";
import LabelInput from "@/components/LabelInput";
import Text from "@/components/Text";
import { useAlert } from "@/hooks";
import { minDesktopWidth } from "@/styles/utils";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { faCommentDots, faEye } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { useMutation } from "@tanstack/react-query";
import axios from "axios";
import { GetServerSideProps } from "next";
import Link from "next/link";
import { useRouter } from "next/router";
import { useState } from "react";

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { communityId, startupId } = context.query;
  const config = {
    headers: { authorization: `Bearer ${context.req.cookies[ACCESS_TOKEN]}` },
  };
  const CommentRequest = await axios
    .get(`${ENDPOINT_API}/invest/community/detail/${communityId}`, config)
    .then(({ data }) => data.data);
  const ProfileRequest = await axios
    .get(`${ENDPOINT_API}/member`, config)
    .then(({ data }) => data.data);
  return {
    props: {
      CommentRequest,
      communityId,
      startupId,
      ProfileRequest,
    },
  };
};

// export type ChildrenProps = {
//   commentId: number;
//   content: string;
//   myComment: boolean;
//   nickName: string;
//   parentId: number;
//   updateDate: string;
// };

export type CommentListProps = {
  children?: CommentListProps[];
  commentId: number;
  content: string;
  myComment: boolean;
  nickName: string;
  parentId: number;
  updateDate: string;
};

type ProfileProps = {
  walletAddress: string;
  nickName: string;
  email: string;
  profileImage: string;
  memberType: string;
};
type CommunityDetailProps = {
  commentList: CommentListProps[];
  commentsCount: number;
  content: string;
  createdDate: string;
  hit: number;
  nickName: string;
  title: string;
};

type DetailProps = {
  CommentRequest: CommunityDetailProps;
  ProfileRequest: ProfileProps;
  communityId: number;
  startupId: number;
};

export default function detail(props: DetailProps) {
  const router = useRouter();
  const theme = useTheme();
  const { handleAlertOpen } = useAlert();
  const { CommentRequest, ProfileRequest, communityId, startupId } = props;
  const [content, setContent] = useState("");
  console.log(CommentRequest);
  console.log(ProfileRequest);
  const onDeleteHandler = async () => {
    await axios
      .delete(`${ENDPOINT_API}/invest/community/${startupId}/${communityId}`)
      .then((res) => {
        console.log(res);
        handleAlertOpen(2000, "게시글 삭제가 완료되었습니다", true);
        // router.push(`/community/${startupId}`); //startupName 필요
      })
      .catch((err) => {
        console.log(err);
        handleAlertOpen(2000, "게시글 삭제가 실패했습니다", false);
      });
  };
  const data = {
    content,
  };
  // const addComment = useMutation((data) =>
  //   axios.post(
  //     `${ENDPOINT_API}/invest/community-comments/${communityId}/comment`,
  //     data
  //   )
  // );
  const onSubmitComment = () => {
    if (!content) {
      handleAlertOpen(2000, "빈칸을 작성해주세요.", false);
    } else {
      axios
        .post(
          `${ENDPOINT_API}/invest/community-comments/${communityId}/comment`,
          data
        )
        .then((res) => {
          console.log(res);
          handleAlertOpen(2000, "댓글이 등록되었습니다", true);
        })
        .catch((err) => {
          console.log(err);
          handleAlertOpen(2000, "댓글 등록이 실패했습니다.", false);
        });
    }
  };

  return (
    <Grid
      css={css`
        display: flex;
        flex-direction: column;
        justify-content: center;
        width: 100%;
        margin-top: 3rem;

        @media (${minDesktopWidth}) {
          width: 70%;
          grid-template-columns: repeat(2, 1fr);
        }
      `}
    >
      <Card
        css={css`
          margin-top: 0.5rem;
        `}
      >
        <CardContent>
          <div
            css={css`
              display: flex;
              flex-direction: column;
              justify-content: space-between;
            `}
          >
            <Text
              css={css`
                align-self: flex-start;
                font-size: 2rem;
                font-weight: 600;
                margin-bottom: 1rem;
              `}
            >
              {CommentRequest.title}
            </Text>
            <div
              css={css`
                display: flex;
                width: 100%;
                justify-content: space-between;
                align-items: center;
              `}
            >
              <div
                css={css`
                  display: flex;
                  flex-direction: column;
                `}
              >
                <Text
                  as="p"
                  css={css`
                    font-size: 1.5rem;
                  `}
                >
                  {CommentRequest.nickName}
                </Text>
                <Text
                  as="h2"
                  css={css`
                    font-size: 0.8rem;
                  `}
                >
                  {CommentRequest.createdDate.substring(0, 10) +
                    " " +
                    CommentRequest.createdDate.substring(11, 19)}
                </Text>
              </div>
              <Text
                css={css`
                  display: flex;
                  align-items: center;
                  color: ${theme.color.text.sub};
                `}
              >
                <FontAwesomeIcon
                  icon={faEye}
                  css={css`
                    width: 1rem;
                    color: ${theme.color.text.sub};
                    margin-right: 0.5rem;
                  `}
                />
                조회수 {CommentRequest.hit}
              </Text>
            </div>
          </div>
          <hr
            css={css`
              width: 100%;
              color: ${theme.color.text.main};
              margin: 2rem 0;
            `}
          />
          <Text>{CommentRequest.content}</Text>
        </CardContent>
      </Card>
      {ProfileRequest.nickName === CommentRequest.nickName && (
        <div
          css={css`
            align-self: flex-end;
          `}
        >
          <Link href={`/community/update/${startupId}/${communityId}`}>
            <Button
              css={css`
                margin-right: 1rem;
              `}
            >
              수정
            </Button>
          </Link>

          <Button onClick={onDeleteHandler}>삭제</Button>
        </div>
      )}
      <CommentWriteWrapper>
        <Text
          as="h1"
          css={css`
            display: flex;
            align-items: center;
            margin-bottom: 1rem;
            color: ${theme.color.text.sub};
          `}
        >
          <FontAwesomeIcon
            icon={faCommentDots}
            css={css`
              width: 1rem;
              color: ${theme.color.text.sub};
              margin-right: 0.5rem;
            `}
          />
          댓글 {CommentRequest.commentsCount}
        </Text>
        <LabelInput
          css={css`
            width: 100%;
            height: 10rem;
            margin-bottom: 1rem;
          `}
          onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
            setContent(e.target.value)
          }
        />
        <Button
          onClick={onSubmitComment}
          css={css`
            align-self: flex-end;
          `}
        >
          댓글등록
        </Button>
      </CommentWriteWrapper>
      <hr
        css={css`
          color: ${theme.color.text.hover};
          width: 100%;
        `}
      />
      {CommentRequest.commentList.map((comment: CommentListProps) => (
        <>
          <Comment
            comment={comment}
            profileNickname={ProfileRequest.nickName}
            communityId={communityId}
            key={comment.commentId}
            type="parent"
          />
          {comment.children?.map((child: CommentListProps) => (
            <>
              <Comment
                comment={child}
                communityId={communityId}
                profileNickname={ProfileRequest.nickName}
                key={child.commentId}
                type="child"
              />
            </>
          ))}
        </>
      ))}
    </Grid>
  );
}
const CardContent = styled.div`
  display: flex;
  flex-direction: column;
  padding: 2rem;

  h1 {
    font-size: 1.5rem;
    font-weight: 700;
    padding: 1rem 0;
  }
  h2 {
    display: inline;
    color: ${({ theme }) => theme.color.text.sub};
    font-weight: 600;
  }
  p {
    font-weight: 500;
  }
`;

const CommentWriteWrapper = styled.div`
  display: flex;
  width: 100%;
  flex-direction: column;
  padding-top: 3rem;
`;

const ParentComment = styled.div``;
