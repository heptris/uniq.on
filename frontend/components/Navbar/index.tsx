/**
 * @params
 * @return
 */
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBars,
  faBell,
  faCircle,
  faTimes,
  faUser,
} from "@fortawesome/free-solid-svg-icons";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import Image from "next/image";
import logo from "assets/logo.png";
import React, { useState } from "react";

function Navbar({ children }: { children: React.ReactNode }) {
  const [isLogin, setIsLogin] = useState(true);
  const [active, setActive] = useState(false);
  const toggleHandler = () => {
    setActive(!active);
  };

  const theme = useTheme();
  return (
    <>
      <Header
        theme={theme}
        css={css`
          background-color: ${theme.color.background.card};
          color: ${theme.color.text.main};
        `}
      >
        <div className="container">
          <div className="navicon">
            <div
              css={css`
                margin-bottom: 5px;
                font-weight: bold;
                font-size: 27px;
                position: absolute;
              `}
            >
              <span
                css={css`
                  color: ${theme.color.background.main};
                `}
              >
                uniq
              </span>
              .on
            </div>
            <div
              css={css`
                transform: translate(300%, -5px);
              `}
            >
              <Image src={logo} alt="logo" width={30} height={40} />
            </div>
          </div>
          <div
            css={css`
              display: flex;
              align-items: center;
            `}
          >
            {isLogin ? (
              <div className="navicon">
                <FontAwesomeIcon
                  icon={faBell}
                  css={css`
                    width: 19px;
                    color: ${theme.color.text.main};
                  `}
                />
                <FontAwesomeIcon
                  icon={faCircle}
                  css={css`
                    width: 7px;
                    color: ${theme.color.status.fail};
                    transform: translate(-6px, -12px);
                  `}
                />
              </div>
            ) : (
              <div>
                <FontAwesomeIcon icon={faUser} />
              </div>
            )}
            <nav
              className={active ? "main-navigation active" : "main-navigation"}
            >
              <a href="#">{isLogin ? "마이페이지" : "로그인"}</a>
              <a href="#">투자리스트</a>
              <a href="#">투자신청</a>
              <a href="#">자주하는질문</a>
            </nav>
            <button
              className="more-btn"
              onClick={toggleHandler}
              css={css`
                margin-left: 1rem;
              `}
            >
              <FontAwesomeIcon
                className="icon"
                icon={!active ? faBars : faTimes}
                css={css`
                  width: 24px;
                  color: ${theme.color.text.main};
                  &:hover {
                    cursor: pointer;
                  }
                `}
              />
            </button>
          </div>
        </div>
      </Header>
      <main>{children}</main>
    </>
  );
}

const Header = styled.header`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 80px;
  padding: 20px 0;
  z-index: 3;

  .container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    height: 100%;
    padding: 0 20px;
    position: relative;
  }

  .navicon {
    display: flex;
    align-items: flex-end;
    margin: 0;
    z-index: 3;
    &:hover {
      cursor: pointer;
    }
  }

  a {
    font-size: 16px;
    text-decoration: none;
    &:hover {
      color: ${(props) => props.theme.color.text.sub};
    }
  }

  .main-navigation {
    float: right;
  }

  .main-navigation a {
    margin-left: 20px;
  }

  .main-navigation a.active {
    font-weight: bold;
    border-bottom: 2px solid #004fff;
  }

  .more-btn {
    display: none;
  }

  @media screen and (max-width: 768px) {
    padding: 0;
    height: 80px;

    h1 {
      display: flex;
    }
    h1 img {
      top: 20px;
      height: 16px;
    }
    .main-navigation {
      position: fixed;
      top: 0;
      right: 0;
      padding: 80px 20px;
      width: 100%;
      height: 100vh;
      background-color: ${(props) => props.theme.color.background.card};
      opacity: 0;
      visibility: hidden;
      transform: translateX(100%);
      transition: 0.3s;
    }
    .main-navigation.active {
      opacity: 1;
      visibility: visible;
      transform: translateX(0);
      transition: 0.3s;
    }

    .main-navigation a {
      display: block;
      margin: auto;
      margin-bottom: 8px;
      padding: 12px;
    }
    .main-navigation a.btn {
      text-align: center;
    }

    .more-btn {
      display: block;
      z-index: 3;
      right: 12px;
      top: 8px;
      width: 40px;
      height: 40px;
      border-radius: 8px;
      border: none;
      background-color: transparent;
    }

    .more-btn .icon {
      display: block;
      width: 24px;
      height: 24px;
      margin: 0 auto;
    }
  }

  @media screen and(max-width:480px) {
  }
`;
export default Navbar;
