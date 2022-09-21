import React, {
  ComponentPropsWithoutRef,
  forwardRef,
  Ref,
  useState,
} from "react";
import Image from "next/image";
import logo from "assets/logo.png";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBars,
  faBell,
  faCircle,
  faTimes,
  faUser,
  faWallet,
} from "@fortawesome/free-solid-svg-icons";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import Link from "next/link";

/**
 * @params
 * @return `HTMLHeaderElement`
 */
function Navbar(
  { ...props }: ComponentPropsWithoutRef<"header">,
  ref: Ref<any>
) {
  const [isLogin, setIsLogin] = useState(false);
  const [active, setActive] = useState(false);
  const toggleHandler = () => {
    setActive(!active);
  };

  const theme = useTheme();
  return (
    <Header theme={theme} ref={ref} {...props}>
      <div className="container">
        <div className="nav-icon">
          <Link href="/">
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
          </Link>

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
          <nav
            className={active ? "main-navigation active" : "main-navigation"}
          >
            {isLogin ? (
              <Link href="/mypage">마이페이지</Link>
            ) : (
              <Link href="/login">로그인</Link>
            )}
            <a href="#">투자리스트</a>
            <a href="#">투자신청</a>
            <a href="#">자주하는질문</a>
          </nav>
          {isLogin ? (
            <div
              className="nav-icon"
              css={css`
                padding-left: 50px;
              `}
            >
              <Link href="/alarm">
                <FontAwesomeIcon
                  icon={faBell}
                  css={css`
                    width: 19px;
                    color: ${theme.color.text.main};
                    &:hover {
                      color: ${theme.color.text.hover};
                    }
                  `}
                />
              </Link>
              <Link href="/alarm">
                <FontAwesomeIcon
                  icon={faCircle}
                  css={css`
                    width: 7px;
                    color: ${theme.color.status.fail};
                    transform: translate(-6px, -12px);
                  `}
                />
              </Link>
            </div>
          ) : (
            <div
              css={css`
                display: flex;
                justify-content: center;
                &:hover {
                  color: ${theme.color.text.hover};
                  cursor: pointer;
                }
              `}
            >
              <Link href="/login">
                <FontAwesomeIcon
                  icon={faWallet}
                  css={css`
                    width: 19px;
                    margin-left: 2rem;
                  `}
                />
              </Link>
            </div>
          )}
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
  );
}

const Header = styled.header`
  background-color: ${({ theme }) => theme.color.background.page};
  color: ${({ theme }) => theme.color.text.main};
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 80px;
  padding: 20px 10px;
  z-index: 9960;
  border-bottom: 1px solid ${({ theme }) => theme.color.background.item};

  .container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    height: 100%;
    padding: 0 20px;
    position: relative;
  }

  .nav-icon {
    display: flex;
    align-items: flex-end;
    margin: 0;
    z-index: 9960;
    &:hover {
      cursor: pointer;
      color: ${({ theme }) => theme.color.text.hover};
    }
  }

  a {
    font-size: 18px;
    text-decoration: none;
    font-weight: 500;
    &:hover {
      color: ${({ theme }) => theme.color.text.hover};
    }
  }

  .main-navigation {
    float: right;
  }

  .main-navigation a {
    margin-left: 50px;
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
      z-index: 9960;
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
      &:hover {
        color: ${({ theme }) => theme.color.text.hover};
      }
    }
  }

  @media screen and(max-width:480px) {
  }
`;

export default forwardRef(Navbar) as typeof Navbar;
