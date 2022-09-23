import { css, useTheme } from "@emotion/react";

export default function Video() {
  const theme = useTheme();
  return (
    <div
      css={css`
        position: relative;
        width: 100%;
        height: 100vh;
        overflow: hidden;
      `}
    >
      <div
        css={css`
          position: absolute;
          width: 100%;
          height: 100vh;
          z-index: 1;
          @media screen and (max-width: 768px) {
            width: 300%;
          }
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
          height: 100%;
        `}
      >
        <div
          css={css`
            width: 520px;
            height: 100vh;
            padding: 240px 20px;
            position: absolute;
            right: 0;
            top: 0;
            z-index: 1;
          `}
        >
          <h2
            css={css`
              color: ${theme.color.text.main};
              font-size: 64px;
              line-height: 1.25;
              font-weight: lighter;
              margin-bottom: 40px;
            `}
          >
            유니크한 NFT를 구매해보세요.
          </h2>
          <h3
            css={css`
              color: ${theme.color.text.main};
              font-size: 24px;
              line-height: 1.25;
              margin-bottom: 20px;
            `}
          >
            누구나 쉽게 NFT 투자하고 투자받는 것이 가능한 곳
          </h3>
          <p
            css={css`
              color: ${theme.color.text.main};
              font-size: 16px;
              line-height: 1.8;
              margin-bottom: 20px;
            `}
          >
            스타트업과 투자자가 NFT를 기반으로 소통하며 상생하는 플랫폼입니다.
          </p>
          <div>
            <a
              css={css`
                display: inline-block;
                color: ${theme.color.text.main};
                font-weight: bold;
                padding: 12px 20px;
                border-radius: 24px;
                background-color: ${theme.color.background.main};
              `}
              href="#"
            >
              투자받기
            </a>
            <a
              css={css`
                background-color: ${theme.color.background.page};
                border: 1px solid ${theme.color.background.main};
              `}
              href="#"
            >
              투자하기
            </a>
          </div>
        </div>
      </div>
    </div>
  );
}
