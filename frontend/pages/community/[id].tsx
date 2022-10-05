import { ENDPOINT_API } from "@/api/endpoints";
import Button from "@/components/Button";
import Card from "@/components/Card";
import Text from "@/components/Text";
import { minDesktopWidth, minTabletWidth } from "@/styles/utils";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import axios from "axios";
import { GetServerSideProps } from "next";

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { id } = context.query;
  const InvestmentRequest = await axios
    .get(`${ENDPOINT_API}/invest/community/${id}`)
    .then(({ data }) => data.data);
  return { props: { InvestmentRequest } };
};

type CommunityListProps = {
  title: string;
  nickName: string;
  commentsCount: number;
  createdDate: string;
};
type InvestProps = {
  InvestmentRequest: CommunityListProps[];
};

export default function CommunityList(props: InvestProps) {
  const theme = useTheme();
  const { InvestmentRequest } = props;
  console.log(InvestmentRequest);
  return (
    <CommunityContainer>
      <CommunityHeader>
        <Text
          css={css`
            font-size: 2rem;
          `}
        >
          dunamu
        </Text>
        <Button
          css={css`
            background-color: #5a67ee;
          `}
        >
          디스코드 입장
        </Button>
      </CommunityHeader>
      <Button
        css={css`
          align-self: flex-end;
          margin-bottom: 1rem;
        `}
      >
        글작성
      </Button>
      {InvestmentRequest.map((community) => (
        <Card
          css={css`
            display: flex;
            justify-content: center;
            align-items: center;
            width: 100%;
            height: 6rem;
            margin-bottom: 3vw;
            padding: 0 1rem;
            font-weight: 500;
            @media (${minTabletWidth}) {
              padding: 0;
              margin-bottom: 1vw;
            }
          `}
        >
          <TextContainer>
            <Text
              as="p"
              role="community-title"
              css={css`
                overflow: hidden;
                word-break: keep-all;
                text-overflow: ellipsis;
              `}
            >
              {community.title}
            </Text>
            <Text
              as="p"
              role="community-nickName"
              css={css`
                font-size: 0.8rem;
                color: ${theme.color.text.hover};
              `}
            >
              {community.nickName}
            </Text>
            <Text
              as="p"
              role="community-date"
              css={css`
                font-size: 0.8rem;
                color: ${theme.color.text.hover};
              `}
            >
              {community.createdDate.substring(0, 10)}
            </Text>
          </TextContainer>
          <Text
            role="community-count"
            css={css`
              font-size: 0.8rem;
              color: ${theme.color.text.sub};
            `}
          >
            댓글 {community.commentsCount}
          </Text>
        </Card>
      ))}
    </CommunityContainer>
  );
}

const CommunityContainer = styled.div`
  padding-top: 3rem;
  width: 100%;
  display: flex;
  flex-direction: column;
  @media (${minDesktopWidth}) {
    width: 33.33%;
  }
`;
const CommunityHeader = styled.div`
  display: flex;
  flex-direction: row;
  align-items: center;
  margin: 0rem 1rem 3rem;
  justify-content: space-between;
`;
const TextContainer = styled.div`
  width: 80%;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
