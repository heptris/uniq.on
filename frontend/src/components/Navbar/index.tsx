import React, {
  ComponentPropsWithoutRef,
  forwardRef,
  Ref,
  useEffect,
  useState,
} from "react";
import Image from "next/image";
import logo from "@/assets/logo.png";

import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faBars,
  faBell,
  faCircle,
  faTimes,
  faCircleUser,
  faWallet,
} from "@fortawesome/free-solid-svg-icons";
import { css, Theme, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import Link from "next/link";
import contracts from "@/contracts/utils";
import Text from "../Text";
import { useAuth } from "@/hooks";
import { HEADER_HEIGHT } from "@/constants";

/**
 * @params
 * @return `HTMLHeaderElement`
 */
function Navbar(
  { ...props }: ComponentPropsWithoutRef<"header">,
  ref: Ref<any>
) {
  const { isLogined } = useAuth();
  const [active, setActive] = useState(false);
  const activeHandler = () => {
    setActive(!active);
  };

  const theme = useTheme();

  return (
    <HeaderWrapper theme={theme} ref={ref} {...props}>
      <HeaderContainer>
        <HeaderIcon>
          <Link href="/">
<<<<<<< frontend/src/components/Navbar/index.tsx
            <Text css={LogoText} onClick={() => setActive(false)}>
=======
          
>>>>>>> frontend/src/components/Navbar/index.tsx
              <Text
                css={css`
                  color: ${theme.color.background.main};
                `}
              >
                uniq
              </Text>
              .on
            </Text>
          </Link>
          <LogoImage onClick={() => setActive(false)}>
            <Image src={logo} alt="logo" width={30} height={40} />
          </LogoImage>
        </HeaderIcon>
        <HeaderList>
          <nav
            className={active ? "main-navigation active" : "main-navigation"}
          >
            <Link href="/list">
              <Text css={ListTextStyle({ theme })} onClick={activeHandler}>
                투자리스트
              </Text>
            </Link>
            <Link href="/apply">
              <Text css={ListTextStyle({ theme })} onClick={activeHandler}>
                투자신청
              </Text>
            </Link>
            <Link href="/question">
              <Text css={ListTextStyle({ theme })} onClick={activeHandler}>
                자주하는질문
              </Text>
            </Link>
          </nav>
          {isLogined ? (
            <HeaderIcon
              css={css`
                padding-left: 50px;
              `}
            >
              <Link href="/mypage">
                <FontAwesomeIcon
                  onClick={() => setActive(false)}
                  icon={faCircleUser}
                  css={css`
                    width: 1.5rem;
                    color: ${theme.color.text.main};

                    &:hover {
                      color: ${theme.color.text.hover};
                    }
                  `}
                />
              </Link>
              <Link href="/alarm">
                <FontAwesomeIcon
                  onClick={() => setActive(false)}
                  icon={faBell}
                  css={css`
                    width: 1.3rem;
                    color: ${theme.color.text.main};
                    &:hover {
                      color: ${theme.color.text.hover};
                    }
                  `}
                />
              </Link>
              <Link href="/alarm">
                <FontAwesomeIcon
                  onClick={() => setActive(false)}
                  icon={faCircle}
                  css={css`
                    width: 7px;
                    color: ${theme.color.status.fail};
                    transform: translate(-6px, -12px);
                  `}
                />
              </Link>
            </HeaderIcon>
          ) : (
            <HeaderIcon>
              <Link href="/login">
                <FontAwesomeIcon
                  onClick={() => setActive(false)}
                  icon={faWallet}
                  css={css`
                    width: 19px;
                    margin-left: 2rem;
                  `}
                />
              </Link>
            </HeaderIcon>
          )}
          <MoreBtn
            className="more-btn"
            onClick={activeHandler}
            css={css`
              margin-left: 1rem;
            `}
          >
            <FontAwesomeIcon
              icon={!active ? faBars : faTimes}
              css={css`
                display: block;
                width: 24px;
                height: 24px;
                margin: 0 auto;
                color: ${theme.color.text.main};
                &:hover {
                  color: ${theme.color.text.hover};
                  cursor: pointer;
                }
              `}
            />
          </MoreBtn>
        </HeaderList>
      </HeaderContainer>
    </HeaderWrapper>
  );
}

const HeaderWrapper = styled.header`
  background-color: ${({ theme }) => theme.color.background.page};
  color: ${({ theme }) => theme.color.text.main};
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: ${HEADER_HEIGHT};
  padding: 20px 10px;
  z-index: 9960;
  border-bottom: 1px solid ${({ theme }) => theme.color.background.item};

  .main-navigation {
    float: right;
  }

  .main-navigation span {
    margin-left: 50px;
  }

  @media screen and (max-width: 768px) {
    padding: 0;
    height: 80px;

    .main-navigation {
      position: fixed;
      top: 0;
      right: -100%;
      padding: 80px 20px;
      width: 100%;
      height: 100vh;
      background-color: ${(props) => props.theme.color.background.card};
      opacity: 0;
      visibility: hidden;
      transform: translateX(0%);
      transition: 0.3s;
    }
    .main-navigation.active {
      opacity: 1;
      visibility: visible;
      transform: translateX(-100%);
      transition: 0.3s;
    }

    .main-navigation span {
      display: block;
      margin: auto;
      padding: 12px;
    }
  }

  @media screen and(max-width:480px) {
  }
`;

const HeaderContainer = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  height: 100%;
  padding: 0 20px;
  position: relative;
`;

const HeaderIcon = styled.div`
  display: flex;
  align-items: flex-end;
  margin: 0;
  z-index: 9960;

  &:hover {
    cursor: pointer;
    color: ${({ theme }) => theme.color.text.hover};
  }
`;

const LogoText = css`
  font-weight: bold;
  font-size: 27px;
  position: absolute;
`;

const LogoImage = styled.div`
  transform: translate(300%, -5px);
`;

const HeaderList = styled.div`
  display: flex;
  align-items: center;
`;

const ListTextStyle = ({ theme }: { theme: Theme }) => css`
  font-size: 18px;
  text-decoration: none;
  font-weight: 500;
  &:hover {
    color: ${theme.color.text.hover};
    cursor: pointer;
  }
  @media screen and (max-width: 768px) {
  }
`;

const MoreBtn = styled.button`
  display: none;
  @media screen and (max-width: 768px) {
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
`;
export default forwardRef(Navbar) as typeof Navbar;
