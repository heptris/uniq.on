import { ENDPOINT_API } from "@/api/endpoints";
import Button from "@/components/Button";
import Card from "@/components/Card";
import Text from "@/components/Text";
import { minDesktopWidth, minTabletWidth } from "@/styles/utils";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import axios from "axios";
import { GetServerSideProps } from "next";
import Link from "next/link";

export const getServerSideProps: GetServerSideProps = async (context) => {
  const { startupId, startupName } = context.query;
  const CommunityRequest = await axios
    .get(`${ENDPOINT_API}/invest/community/${startupId}`)
    .then(({ data }) => data.data);
  return { props: { CommunityRequest, startupId, startupName } };
};

type CommunityListProps = {
  title: string;
  nickName: string;
  hit: number;
  communityId: number;
  startupName: string;
  commentsCount: number;
  createdDate: string;
};
type InvestProps = {
  CommunityRequest: CommunityListProps[];
  startupId: number;
  startupName: string;
};

export default function CommunityList(props: InvestProps) {
  const theme = useTheme();
  const { CommunityRequest, startupId, startupName } = props;
  return (
    <CommunityContainer>
      <CommunityHeader>
        <Text
          css={css`
            font-size: 2rem;
            font-weight: 600;
          `}
        >
          {startupName}
        </Text>
        <Button
          css={css`
            background-color: #5a67ee;
          `}
        >
          디스코드 입장
        </Button>
      </CommunityHeader>
      <Link href={`/community/write/${startupId}/${startupName}`}>
        <Button
          css={css`
            align-self: flex-end;
            margin-bottom: 1rem;
          `}
        >
          글작성
        </Button>
      </Link>
      {CommunityRequest.length == 0 && "첫 게시글을 작성해주세요"}
      {CommunityRequest.map((community, i) => (
        <Link href={`/community/detail/${community.communityId}/${startupId}`}>
          <Card
            key={i}
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
              &:hover {
                cursor: pointer;
                background-color: ${theme.color.background.item};
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
            <div
              css={css`
                width: 35%;
              `}
            >
              <Text
                role="community-count"
                css={css`
                  font-size: 0.8rem;
                  margin-right: 0.8rem;
                  color: ${theme.color.text.sub};
                `}
              >
                조회수 {community.hit}
              </Text>
              <Text
                role="community-count"
                css={css`
                  font-size: 0.8rem;
                  color: ${theme.color.text.sub};
                `}
              >
                댓글 {community.commentsCount}
              </Text>
            </div>
          </Card>
        </Link>
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
  margin-left: 1rem;
  flex-direction: column;
  justify-content: center;
`;
