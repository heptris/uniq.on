import { ENDPOINT_API } from "@/api/endpoints";
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

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { startupId, startupName } = context.query;
  return { props: { startupId, startupName } };
};

type CommunityProps = {
  startupId: number;
  startupName: string;
};

export default function write(props: CommunityProps) {
  const router = useRouter();
  const { startupId, startupName } = props;
  const { handleAlertOpen } = useAlert();
  const [title, setTitle] = useState("");
  const [content, setContent] = useState("");

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
        .post(`${ENDPOINT_API}/invest/community/${startupId}`, data, config)
        .then((res) => {
          console.log(res);
          handleAlertOpen(2000, "게시글 등록이 완료되었습니다..", true);
        })
        .catch((err) => {
          console.log(err);
          handleAlertOpen(2000, "게시글 등록이 실패했습니다.", false);
        });
      setTitle("");
      setContent("");
      router.push(`/community/${startupId}/${startupName}`);
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
        onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
          setTitle(e.target.value)
        }
      />
      <br />
      <LabelInput
        labelText="내용"
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
          width: 100%;
        `}
      >
        등록
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
