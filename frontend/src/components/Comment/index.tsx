import { ENDPOINT_API } from "@/api/endpoints";
import { useAlert } from "@/hooks";
import { CommentListProps } from "@/pages/community/detail/[communityId]/[startupId]";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import axios from "axios";
import { useState } from "react";
import Button from "../Button";
import LabelInput from "../LabelInput";
import Text from "../Text";

type CommentProps = {
  comment: CommentListProps;
  profileNickname: string;
  communityId: number;
  type: string;
};
export default function Comment(props: CommentProps) {
  const { comment, profileNickname, type, communityId } = props;
  console.log(comment);
  const [open, setOpen] = useState(false);
  const [content, setContent] = useState(comment.content);
  const openToggleHandler = () => {
    setOpen((open) => !open);
  };
  const theme = useTheme();
  const { handleAlertOpen } = useAlert();
  const onDeleteHandler = () => {
    axios
      .delete(
        `${ENDPOINT_API}/invest/community-comments/${communityId}/${comment.commentId}`
      )
      .then((res) => {
        console.log(res);
        handleAlertOpen(2000, "댓글 삭제가 완료되었습니다", true);
      })
      .catch((err) => {
        console.log(err);
        handleAlertOpen(2000, "댓글 삭제가 실패했습니다", false);
      });
  };
  const openCreateHandler = () => {
    const data = { content, parentId: comment.commentId };
    console.log(data);
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
          onClick={openToggleHandler}
          css={css`
            color: ${theme.color.text.sub};
            &:hover {
              color: ${theme.color.text.hover};
              cursor: pointer;
            }
          `}
        >
          {!open && type === "parent" && "댓글작성"}
        </Text>
        {comment.nickName === profileNickname && (
          <div>
            <Button
              onClick={openToggleHandler}
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
      {open && (
        <div
          css={css`
            width: 100%;
            display: flex;
            flex-direction: column;
            ${type === "child" ? `padding-left:6rem` : `padding-left:0rem`}
          `}
        >
          <LabelInput
            value={content}
            css={css`
              width: 100%;
              height: 5rem;
            `}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setContent(e.target.value)
            }
          />
          <span
            css={css`
              margin-top: 0.5rem;
              align-self: flex-end;
            `}
          >
            <Button
              onClick={openCreateHandler}
              type="blue"
              css={css`
                margin-right: 0.5rem;
              `}
            >
              등록
            </Button>
            <Button type="blue" onClick={openToggleHandler}>
              취소
            </Button>
          </span>
        </div>
      )}
    </ParentComment>
  );
}

const ParentComment = styled.div``;
