import { FileUploadProps } from "@/types/props";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { faUpload } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { ElementType, forwardRef, Ref } from "react";
import LabelInput from "../LabelInput";
import Text from "../Text";

function FileUpload<T extends ElementType = "div">(
  props: FileUploadProps<T>,
  ref: Ref<any>
) {
  const { text, ...rest } = props;
  const theme = useTheme();
  return (
    <FileUploadWrapper ref={ref} {...rest}>
      <LabelInput css={FileUploadStyle} labelText={text} disabled />
      <FileUploadBtn>
        <FontAwesomeIcon
          icon={faUpload}
          css={css`
            width: 1.5rem;
            color: ${theme.color.text.main};
            margin-right: 2rem;
          `}
        />
        <Text
          css={css`
            font-size: 0.5rem;
          `}
        >
          파일올리기
        </Text>
      </FileUploadBtn>
    </FileUploadWrapper>
  );
}

const FileUploadWrapper = styled.div`
  position: relative;
  margin-bottom: 1rem;
`;

const FileUploadBtn = styled.div`
  display: flex;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  left: 6rem;
  top: 2.2rem;
  position: absolute;
  &:hover {
    cursor: pointer;
    color: ${({ theme }) => theme.color.text.hover};
  }
`;

const FileUploadStyle = css`
  width: 20rem;
  height: 3rem;
`;

export default forwardRef(FileUpload) as typeof FileUpload;
