import { ENDPOINT_API } from "@/api/endpoints";
import {
  commentCreateMutation,
  commentDeleteMutation,
  commentUpdateMutation,
  useAlert,
} from "@/hooks";
import { CommentListProps } from "@/pages/community/detail/[communityId]/[startupId]";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { useQueryClient } from "@tanstack/react-query";
import axios from "axios";
import { useState } from "react";
import Button from "../Button";
import LabelInput from "../LabelInput";
import Text from "../Text";
import { QUERY_KEYS } from "@/api/query_key_schema";
import InputComment from "./InputComment";
const { MY_COMMUNITY_LIST } = QUERY_KEYS;

type CommentProps = {
  comment: CommentListProps;
  profileNickname: string;
  communityId: number;
  type: string;
};
export default function Comment(props: CommentProps) {
  const theme = useTheme();
  const client = useQueryClient();
  const { handleAlertOpen } = useAlert();
  const { comment, profileNickname, type, communityId } = props;
  const { mutate: mutateDeleteComment } = commentDeleteMutation();
  const { mutate: mutateUpdateComment } = commentUpdateMutation();
  const { mutate: mutateCreateComment } = commentCreateMutation();
  const [openWrite, setOpenWrite] = useState(false);
  const [openUpdate, setOpenUpdate] = useState(false);
  const [updateContent, setUpdateContent] = useState(comment.content);
  const [content, setContent] = useState("");
  const openWriteHandler = () => {
    setOpenWrite((openWrite) => !openWrite);
  };
  const openUpdateHandler = () => {
    setOpenUpdate((openUpdate) => !openUpdate);
  };

  const onDeleteHandler = () => {
    const commentId = comment.commentId;
    mutateDeleteComment(
      { commentId, communityId },
      {
        onSuccess: () =>
          handleAlertOpen(2000, "댓글 삭제가 완료되었습니다", true),
        onError: () =>
          handleAlertOpen(2000, "댓글 삭제가 실패했습니다.", false),
        onSettled: () => client.invalidateQueries([MY_COMMUNITY_LIST]),
      }
    );
  };
  const onSubmitWrite = () => {
    const data = { content, parentId: comment.commentId };
    mutateCreateComment(
      { communityId, data },
      {
        onSuccess: () => {
          handleAlertOpen(2000, "댓글이 등록되었습니다", true);
          openWriteHandler();
          setContent("");
        },
        onError: () =>
          handleAlertOpen(2000, "댓글 등록이 실패했습니다.", false),
        onSettled: () => client.invalidateQueries([MY_COMMUNITY_LIST]),
      }
    );
  };

  const onSubmitUpdate = () => {
    const commentId = comment.commentId;
    const data = { content: updateContent };
    mutateUpdateComment(
      { commentId, communityId, data },
      {
        onSuccess: () => {
          handleAlertOpen(2000, "댓글 수정이 완료되었습니다", true);
          openUpdateHandler();
        },
        onError: () =>
          handleAlertOpen(2000, "댓글 수정이 실패했습니다.", false),
        onSettled: () => client.invalidateQueries([MY_COMMUNITY_LIST]),
      }
    );
  };
  return (
    <ParentComment>
      <div
        css={css`
          width: 100%;
          display: flex;
          flex-direction: row;
          justify-content: space-between;
          align-items: center;
          padding: 0 2rem;
          box-sizing: border-box;
        `}
      >
        <div
          css={css`
            display: flex;
            flex-direction: column;
            ${type === "child" ? `padding-left:5rem` : `padding-left:0rem`}
          `}
        >
          <Text
            as="h1"
            css={css`
              font-size: 1.5rem;
            `}
          >
            {comment.nickName}
          </Text>
          <Text
            as="p"
            css={css`
              font-size: 0.8rem;
              color: ${theme.color.text.hover};
            `}
          >
            {comment.updateDate.substring(0, 10)}
          </Text>
        </div>

        <Text>{comment.content}</Text>
        <Text
          onClick={openWriteHandler}
          css={css`
            color: ${theme.color.text.sub};
            &:hover {
              color: ${theme.color.text.hover};
              cursor: pointer;
            }
          `}
        >
          {!openWrite && type === "parent" && !openUpdate && "댓글작성"}
        </Text>
        {comment.nickName === profileNickname && !openWrite && !openUpdate && (
          <div>
            <Button
              onClick={openUpdateHandler}
              css={css`
                margin-right: 0.5rem;
              `}
            >
              수정
            </Button>
            <Button onClick={onDeleteHandler}>삭제</Button>
          </div>
        )}
      </div>
      <hr
        css={css`
          color: ${theme.color.text.hover};
          width: 100%;
        `}
      />
      {openWrite && (
        <InputComment
          type={type}
          content={content}
          onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
            setContent(e.target.value)
          }
          onSubmitHandler={onSubmitWrite}
          openHandler={openWriteHandler}
        />
      )}
      {openUpdate && (
        <InputComment
          type={type}
          update="수정"
          content={updateContent}
          onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
            setUpdateContent(e.target.value)
          }
          onSubmitHandler={onSubmitUpdate}
          openHandler={openUpdateHandler}
        />
      )}
    </ParentComment>
  );
}

const ParentComment = styled.div``;
