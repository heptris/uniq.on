import Link from "next/link";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCopyright } from "@fortawesome/free-solid-svg-icons";

import Text from "@/components/Text";
import { minDesktopWidth, minTabletWidth } from "@/styles/utils";

/**
 * @params
 * @return
 */
function Footer() {
  const theme = useTheme();

  return (
    <FooterContainer>
      <Link href="/">
        <Text
          as="h1"
          role="logo"
          css={css`
            font-size: 2rem;
            font-weight: 700;
            color: ${theme.color.text.hover};

            &:hover {
              cursor: pointer;
            }
          `}
        >
          uniq.on
        </Text>
      </Link>

      <InfoContainer>
        <InfoItem>
          <Text as="h1" role="info-header">
            Investment
          </Text>
          <ul>
            <Link href="/list">
              <Text as="li">투자리스트</Text>
            </Link>
            <Text as="li">투자신청</Text>
          </ul>
        </InfoItem>
        <InfoItem>
          <Text as="h1" role="info-header">
            Account
          </Text>
          <ul>
            <Link href="/mypage">
              <Text as="li">마이페이지</Text>
            </Link>
          </ul>
        </InfoItem>
        <InfoItem>
          <Text as="h1" role="info-header">
            Q&A
          </Text>
          <ul>
            <Text as="li">자주하는 질문</Text>
          </ul>
        </InfoItem>
        <InfoItem>
          <Text as="h1" role="info-header">
            Contact
          </Text>
          <ul>
            <Text as="li">asdv94@naver.com</Text>
          </ul>
        </InfoItem>
      </InfoContainer>

      <Developers>
        <Text as="h1">A507</Text>
        <Text as="h2" role="developers">
          안호진 박상태 신은정 이승호 전한울 한승재
        </Text>
        <Text as="h3">서울특별시 강남구 테헤란로 212 (역삼동) </Text>
      </Developers>

      <Copyright>
        <Text>COPYRIGHT</Text>
        <FontAwesomeIcon
          css={css`
            width: 15px;
            margin: 0 0.2rem 0.5rem;
          `}
          icon={faCopyright}
        />
        <Text>uniq.on ALL RIGHTS RESERVED</Text>
      </Copyright>
    </FooterContainer>
  );
}

const FooterContainer = styled.footer`
  border: 0;
  margin: 3rem 0 0 0;
  width: 100vw;
  height: 50rem;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: ${({ theme }) => theme.color.text.main};
  background-color: ${({ theme }) => theme.color.background.card};
`;
const InfoContainer = styled.div`
  width: 100%;
  grid-template-columns: repeat(2, 1fr);
  text-align: center;
  display: grid;
  margin-top: 5rem;
  row-gap: 5rem;

  @media (${minTabletWidth}) {
    grid-template-columns: repeat(3, 1fr);
  }
  @media (${minDesktopWidth}) {
    width: 50%;
    grid-template-columns: repeat(4, 1fr);
  }
`;
const InfoItem = styled.div`
  h1 {
    font-size: 1.2rem;
    font-weight: 500;
    margin-bottom: 0.5rem;
    color: ${({ theme }) => theme.color.text.hover};
  }
  ul {
    li {
      color: ${({ theme }) => theme.color.text.sub};

      &:hover {
        cursor: pointer;
        text-decoration: underline;
      }
    }
  }
`;
const Developers = styled.div`
  display: flex;
  flex-direction: column;
  margin-top: 5rem;

  h1,
  h2,
  h3 {
    color: ${({ theme }) => theme.color.text.sub};
  }
  h1,
  h2 {
    font-size: 0.8rem;
  }
  h3 {
    font-size: 0.7rem;
  }
`;
const Copyright = styled.div`
  display: flex;
  align-items: center;
  margin-top: 5rem;
  color: ${({ theme }) => theme.color.text.sub};
  font-size: 0.8rem;

  span {
    color: ${({ theme }) => theme.color.text.sub};
  }
`;

export default Footer;
