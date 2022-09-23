import { cssConvex } from "@/styles/utils";
import { css, useTheme } from "@emotion/react";
import Button from "../Button";

export default function Video() {
  const theme = useTheme();
  return (
    <div
      css={css`
        position: relative;
        width: 100%;
        height: 100vh;
        overflow: hidden;
        @media screen and (max-width: 768px) {
          width: 100%;
          height: 75vh;
        }
      `}
    >
      <div
        css={css`
          position: absolute;
          width: 100%;
          height: 100vh;
          z-index: 1;
        `}
      >
        <video
          muted
          autoPlay
          loop
          css={css`
            position: absolute;
            display: block;
            width: 100%;
          `}
        >
          <source src="/videos/main1.mp4" type="video/mp4" />
        </video>
      </div>

      <div
        css={css`
          width: 80rem;
          height: 100vh;
          padding: 17rem 2rem;
          position: absolute;
          z-index: 1;

          @media screen and (max-width: 768px) {
            width: 100%;
            height: auto;
            padding: 20px;
            top: 15rem;
          }
        `}
      >
        <h2
          css={css`
            color: ${theme.color.text.main};
            font-size: 6rem;
            line-height: 1.25;
            font-weight: lighter;
            margin-bottom: 3rem;
            @media screen and (max-width: 768px) {
              font-size: 3rem;
            }
          `}
        >
          유니크한 NFT를 구매해보세요.
        </h2>
        <h3
          css={css`
            color: ${theme.color.text.main};
            font-size: 2rem;
            line-height: 1.25;
            margin-bottom: 1.5rem;
            @media screen and (max-width: 768px) {
              font-size: 1.5rem;
            }
          `}
        >
          누구나 쉽게 NFT에 투자하고{" "}
          <br
            css={css`
              display: none;
              @media screen and (max-width: 768px) {
                display: block;
              }
            `}
          />
          투자받는 것이 가능한 곳
        </h3>
        <p
          css={css`
            color: ${theme.color.text.main};
            font-size: 1.5rem;
            line-height: 1.8;
            margin-bottom: 1.5rem;
            @media screen and (max-width: 768px) {
              font-size: 1rem;
            }
          `}
        >
          스타트업과 투자자가 NFT를 기반으로 소통하며 상생하는 플랫폼입니다.
        </p>
        <div>
          <Button
            css={css`
              display: inline-block;
              color: ${theme.color.text.main};
              font-weight: bold;
              padding: 0.8rem 1.3rem;
              margin-right: 1rem;
              border-radius: 2rem;
              background-color: ${theme.color.background.main};
              ${cssConvex}
            `}
          >
            투자받기
          </Button>
          <Button
            css={css`
              display: inline-block;
              color: ${theme.color.text.main};
              font-weight: bold;
              padding: 0.8rem 1.3rem;
              border-radius: 2rem;
              background-color: ${theme.color.background.page};
              border: 1px solid ${theme.color.background.main};
              ${cssConvex}
            `}
          >
            투자하기
          </Button>
        </div>
      </div>
    </div>
  );
}
