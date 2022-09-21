import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { css } from "@emotion/css";
import { useTheme } from "@emotion/react";
import { faCopyright } from "@fortawesome/free-solid-svg-icons";

/**
 * @params
 * @return
 */
function Footer() {
  const theme = useTheme();
  return (
    <footer
      className={css`
        width: 100%;
        height: 15rem;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
        color: ${theme.color.text.main};
        .infolist {
          margin: 0 7rem;
        }
        h1 {
          font-size: 1.3rem;
          font-weight: bold;
          margin-bottom: 1rem;
        }
        p {
          font-size: 0.8rem;
          margin-bottom: 0.5rem;
        }
        span {
          color: ${theme.color.text.sub};
        }
        .coinfo {
          font-size: 0.9rem;
        }
        .infolist p {
          &:hover {
            color: ${theme.color.text.hover};
            cursor: pointer;
          }
        }
        @media screen and (max-width: 768px) {
          height: 18rem;
          .infolist {
            margin: 0 1.3rem;
          }
          .coinfo {
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            line-height: 130%;
          }
          .slash {
            visibility: hidden;
          }
        }
      `}
    >
      <div
        className={css`
          width: 100%;
          display: flex;
          flex-direction: row;
          justify-content: center;
          align-items: flex-start;
          margin: 1rem 0 2rem;
        `}
      >
        <div className="infolist">
          <h1>Invest</h1>
          <p>투자리스트</p>
          <p>투자신청</p>
        </div>
        <div className="infolist">
          <h1>My account</h1>
          <p>마이페이지</p>
        </div>
        <div className="infolist">
          <h1>Company</h1>
          <p>자주하는 질문</p>
        </div>
      </div>
      <div
        className={css`
          display: flex;
          flex-direction: column;
          justify-content: center;
          align-items: center;
          margin-bottom: 1rem;
        `}
      >
        <div className="coinfo">
          <span>
            주식회사 유니크온 <span className="slash">|</span>
          </span>
          <span>
            {" "}
            A507 안호진 박상태 신은정 이승호 전한울 한승재{" "}
            <span className="slash">|</span>
          </span>
          <span>
            {" "}
            서울특별시 강남구 테헤란로 212 (역삼동){" "}
            <span className="slash">|</span>
          </span>
          <span> Email hanndrednine@gmail.com</span>
        </div>
        <div
          className={css`
            display: flex;
            flex-direction: row;
            justify-content: center;
            align-items: center;
            color: ${theme.color.text.sub};
            margin-top: 1rem;
          `}
        >
          <p>COPYRIGHT</p>
          <FontAwesomeIcon
            className={css`
              width: 15px;
              margin: 0 0.2rem 0.5rem;
            `}
            icon={faCopyright}
          />
          <p> Uniq.On ALL RIGHTS RESERVED</p>
        </div>
      </div>
    </footer>
  );
}

export default Footer;
