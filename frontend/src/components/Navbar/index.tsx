import Link from "next/link";
import { ComponentPropsWithoutRef, forwardRef, Ref, useState } from "react";
import Image from "next/image";

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

import logo from "@/assets/logo.png";

import Text from "../Text";
import { useAuth, useServer } from "@/hooks";
import { HEADER_HEIGHT, ROUTES } from "@/constants";

const { ALARM, APPLY, HOME, LIST, LOGIN, MYPAGE } = ROUTES;

/**
 * @params
 * @return `HTMLHeaderElement`
 */
function Navbar(
  { ...props }: ComponentPropsWithoutRef<"header">,
  ref: Ref<any>
) {
  const { isLogined } = useAuth();
  const theme = useTheme();

  const { hasUnreadAlarm } = useServer();

  const [active, setActive] = useState(false);
  const activeHandler = () => {
    setActive(!active);
  };

  return (
    <HeaderWrapper theme={theme} ref={ref} {...props}>
      <HeaderContainer>
        <Link href={HOME}>
          <HeaderIcon>
            <Text css={LogoText} onClick={() => setActive(false)}>
              <Text
                css={css`
                  color: ${theme.color.background.main};
                `}
              >
                uniq
              </Text>
              .on
            </Text>
            <LogoImage onClick={() => setActive(false)}>
              <Image src={logo} alt="logo" width={30} height={40} />
            </LogoImage>
          </HeaderIcon>
        </Link>
        <HeaderList>
          <nav
            className={active ? "main-navigation active" : "main-navigation"}
          >
            <Link href={LIST}>
              <Text css={ListTextStyle({ theme })} onClick={activeHandler}>
                투자리스트
              </Text>
            </Link>
            {isLogined ? (
              <Link href={APPLY}>
                <Text css={ListTextStyle({ theme })} onClick={activeHandler}>
                  투자신청
                </Text>
              </Link>
            ) : (
              <></>
            )}
          </nav>
          {isLogined ? (
            <HeaderIcon
              css={css`
                padding-left: 50px;
              `}
            >
              <Link href={MYPAGE}>
                <FontAwesomeIcon
                  onClick={() => setActive(false)}
                  icon={faCircleUser}
                  css={css`
                    width: 1.5rem;
                    color: ${theme.color.text.main};
                    margin-right: 26px;
                    &:hover {
                      color: ${theme.color.text.hover};
                    }
                  `}
                  size={"2xl"}
                />
              </Link>
              <Link href={ALARM}>
                <HeaderIcon
                  onClick={() => setActive(false)}
                  css={css`
                    position: relative;
                  `}
                >
                  <FontAwesomeIcon
                    icon={faBell}
                    css={css`
                      width: 1.3rem;
                      color: ${theme.color.text.main};
                      &:hover {
                        color: ${theme.color.text.hover};
                      }
                    `}
                    size={"2xl"}
                  />
                  {hasUnreadAlarm ? (
                    <FontAwesomeIcon
                      icon={faCircle}
                      css={css`
                        position: absolute;
                        right: -0.4rem;
                        width: 7px;
                        color: ${theme.color.status.fail};
                        transform: translate(-6px, -12px);
                      `}
                    />
                  ) : (
                    <></>
                  )}
                </HeaderIcon>
              </Link>
            </HeaderIcon>
          ) : (
            <Link href={LOGIN}>
              <HeaderIcon>
                <FontAwesomeIcon
                  onClick={() => setActive(false)}
                  icon={faWallet}
                  css={css`
                    width: 19px;
                    margin-left: 2rem;
                  `}
                />
              </HeaderIcon>
            </Link>
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
