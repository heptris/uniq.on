import { css } from "@emotion/react";
import Button from "../Button";
import LabelInput from "../LabelInput";
type InputProps = {
  type: string;
  update?: string;
  content: string;
  onChange: (e: React.ChangeEvent<HTMLInputElement>) => void;
  onSubmitHandler: () => void;
  openHandler: () => void;
};
export default function InputComment(props: InputProps) {
  const { type, update, content, onChange, onSubmitHandler, openHandler } =
    props;
  return (
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
        onChange={onChange}
      />
      <span
        css={css`
          margin-top: 0.5rem;
          align-self: flex-end;
        `}
      >
        <Button
          onClick={onSubmitHandler}
          type="blue"
          css={css`
            margin-right: 0.5rem;
          `}
        >
          {update ? "수정" : "등록"}
        </Button>
        <Button type="blue" onClick={openHandler}>
          취소
        </Button>
      </span>
    </div>
  );
}
