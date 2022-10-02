import Link from "next/link";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";

import Button from "@/components/Button";
import Text from "@/components/Text";
import { minDesktopWidth } from "@/styles/utils";
import { HEADER_HEIGHT } from "@/constants";

export default function Video() {
  const theme = useTheme();

  return (
    <VideoContainer>
      <video
        muted
        autoPlay
        loop
        playsInline
        css={css`
          position: absolute;
          width: 100%;
          height: 100%;
          object-fit: cover;
        `}
      >
        <source src="/videos/main1.mp4" type="video/mp4" />
      </video>

      <Content>
        <Text
          as="h1"
          css={css`
            font-size: 2rem;
            font-weight: 500;
            word-break: keep-all;
            margin-bottom: 2rem;

            @media (${minDesktopWidth}) {
              margin-bottom: 3rem;
              font-size: 3rem;
            }
          `}
        >
          유니크한 NFT를 구매해보세요.
        </Text>
        <Text
          as="p"
          css={css`
            font-size: 1rem;
            font-weight: 300;
            margin-bottom: 1.5rem;

            @media (${minDesktopWidth}) {
              font-size: 1.3rem;
            }
          `}
        >
          누구나 쉽게 유니콘 기업에 투자하는 것이 가능한 곳
          <br />
          스타트업과 투자자가 NFT를 통해 새로운 기회를 만듭니다.
        </Text>
        <ButtonContainer>
          <Link href="/list">
            <Button
              css={css`
                display: inline-block;
                font-weight: 700;
                padding: 1rem 1.5rem;
                margin-right: 1rem;
                background-color: ${theme.color.background.main};
              `}
            >
              투자하기
            </Button>
          </Link>
          <Link href="/apply">
            <Button
              css={css`
                display: inline-block;
                font-weight: 700;
                padding: 1rem 1.5rem;
                background-color: ${theme.color.background.page};
                border: 1px solid ${theme.color.background.main};
              `}
            >
              투자신청
            </Button>
          </Link>
        </ButtonContainer>
      </Content>
    </VideoContainer>
  );
}

const VideoContainer = styled.div`
  position: relative;
  width: 100vw;
  height: calc(100vh - ${HEADER_HEIGHT});
  overflow: hidden;
`;
const Content = styled.div`
  width: 100vw;
  height: calc(100vh - ${HEADER_HEIGHT});
  position: absolute;
  padding: 0 6vw;
  display: flex;
  flex-direction: column;
  justify-content: center;
`;
const ButtonContainer = styled.div``;
