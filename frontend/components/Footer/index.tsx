import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCopyright } from "@fortawesome/free-solid-svg-icons";

import Text from "@/components/Text";
import Link from "next/link";

/**
 * @params
 * @return
 */
function Footer() {
  const theme = useTheme();

  return (
    <FooterContainer>
      <Text
        as="h1"
        role="logo"
        css={css`
          font-size: 2rem;
          font-weight: 700;
        `}
      >
        uniq.on
      </Text>

      <InfoContainer>
        <InfoItem>
          <Text as="h1" role="info-header">
            Investment
          </Text>
          <ul>
            <Link href="/list">
              <Text as="li">투자리스트</Text>
            </Link>
            <Link href="/apply">
              <Text as="li">투자신청</Text>
            </Link>
          </ul>
        </InfoItem>
        <InfoItem>
          <Text as="h1" role="info-header">
            My account
          </Text>
          <ul>
            <Text as="li">마이페이지</Text>
          </ul>
        </InfoItem>
        <InfoItem>
          <Text as="h1" role="info-header">
            Company
          </Text>
          <ul>
            <Text as="li">자주하는 질문</Text>
          </ul>
        </InfoItem>
      </InfoContainer>

      <Developers>
        <Text as="h1">A507</Text>
        <Text role="developers">안호진 박상태 신은정 이승호 전한울 한승재</Text>
        <Text as="h2">asdv94@naver.com</Text>
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
  width: 100%;
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
  display: flex;
  justify-content: space-around;
  margin-top: 3rem;
`;
const InfoItem = styled.div`
  h1 {
    font-size: 1.2rem;
    font-weight: 500;
    margin-bottom: 0.5rem;
  }
  ul {
    li {
      color: ${({ theme }) => theme.color.text.sub};

      &:hover {
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
  span,
  h2,
  h3 {
    color: ${({ theme }) => theme.color.text.sub};
  }
  h1 {
    font-size: 0.8rem;
  }
  span {
    font-size: 0.9rem;
  }
  h2,
  h3 {
    font-size: 0.8rem;
  }
`;
const Copyright = styled.div`
  display: flex;
  align-items: center;
  margin-top: 5rem;
  color: ${({ theme }) => theme.color.text.sub};

  span {
    color: ${({ theme }) => theme.color.text.sub};
  }
`;

export default Footer;
