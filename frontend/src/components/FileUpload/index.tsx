import { FileUploadProps } from "@/types/props";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { faUpload } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { ElementType, forwardRef, Ref, useRef, useState } from "react";
import LabelInput from "../LabelInput";
import Text from "../Text";
const IMAGE_FILE_EXTENSION = ".png,.jpg,.jpeg,.gif";
const PDF_FILE_EXTENSION = ".pdf";

function FileUpload<T extends ElementType = "div">(
  props: FileUploadProps<T>,
  ref: Ref<any>
) {
  const { text, type, onFileSelectSuccess, onFileSelectError, ...rest } = props;
  const theme = useTheme();
  const fileInput = useRef<HTMLInputElement>(null);
  const [fileName, setFileName] = useState("");
  const [imageSrc, setImageSrc] = useState("");

  const handleFileInput = (e: React.ChangeEvent<HTMLInputElement>) => {
    if (e.target.files !== null) {
      const file = e.target.files[0];
      if (file.size > 1024 * 1024 * 50)
        onFileSelectError({ error: "파일 크기가 50MB를 초과했습니다." });
      else {
        onFileSelectSuccess(file);
        setFileName(file.name);
        if (type === "img") {
          setImageSrc(URL.createObjectURL(file));
        }
      }
    }
  };

  return (
    <FileUploadWrapper ref={ref} {...rest}>
      <LabelInput
        type="file"
        css={FileUploadStyle}
        ref={fileInput}
        accept={
          type === "pdf" ? `${PDF_FILE_EXTENSION}` : `${IMAGE_FILE_EXTENSION}`
        }
        labelText={text}
        onChange={handleFileInput}
      />
      {imageSrc && (
        <ImageWrapper>
          <Image alt="sample" src={imageSrc} />
        </ImageWrapper>
      )}
      <FileUploadBtn
        onClick={(e) => {
          e.preventDefault();
          fileInput.current?.click();
        }}
      >
        {fileName ? (
          <Text>{fileName}</Text>
        ) : (
          <>
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
                font-size: 0.8rem;
              `}
            >
              파일올리기
            </Text>
          </>
        )}
      </FileUploadBtn>
    </FileUploadWrapper>
  );
}

const FileUploadWrapper = styled.div`
  display: flex;
  flex-direction: column;
  align-self: flex-start;
  margin-bottom: 1rem;
`;

const FileUploadBtn = styled.button`
  display: flex;
  width: 20rem;
  height: 3rem;
  flex-direction: row;
  justify-content: center;
  align-items: center;
  border-radius: 4px;
  background-color: ${({ theme }) => theme.color.background.item};
  &:hover {
    cursor: pointer;
    color: ${({ theme }) => theme.color.text.hover};
  }
`;

const FileUploadStyle = css`
  display: none;
`;

const ImageWrapper = styled.div`
  display: flex;
  justify-content: center;
`;

const Image = styled.img`
  width: 20rem;
  height: 20rem;
  padding: 1rem;
`;

export default forwardRef(FileUpload) as typeof FileUpload;
