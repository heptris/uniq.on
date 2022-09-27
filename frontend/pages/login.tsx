import Image from "next/image";

import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { minDesktopWidth } from "@/styles/utils";

import metamask from "@/assets/wallet-icons/metamask.png";

import contracts from "@/contracts/utils";
import Text from "@/components/Text";
import Card from "@/components/Card";

export default function Login() {
  const { account, setAccount } = contracts.useAccount();
  const wallets = [
    {
      id: 1,
      logo: metamask,
      name: "MetaMask",
    },
  ];

  return (
    <LoginContainer>
      <Text
        as="h1"
        role="guide"
        css={css`
          font-size: 2rem;
          font-weight: 700;
        `}
      >
        지갑을 연결하세요.
      </Text>
      <Card
        role="wallet-menu"
        css={css`
          margin-top: 2rem;
        `}
      >
        {wallets.map((wallet) => (
          <MenuItem key={wallet.id} onClick={() => console.log(account)}>
            <ImageContainer>
              <Image src={wallet.logo} />
            </ImageContainer>
            <Text
              role="menu-item"
              css={css`
                font-weight: 700;
                font-size: 1.2rem;
              `}
            >
              {wallet.name}
            </Text>
          </MenuItem>
        ))}
      </Card>
    </LoginContainer>
  );
}

const LoginContainer = styled.div`
  padding-top: 5rem;
  width: 100%;

  @media (${minDesktopWidth}) {
    width: 50%;
  }
`;
const MenuItem = styled.div`
  position: relative;
  display: flex;
  align-items: center;
  padding: 1.2rem 0.5rem;
  z-index: 1;

  &:hover {
    cursor: pointer;
    background-color: ${({ theme }) => theme.color.hover.emphasis};
  }
`;
const ImageContainer = styled.div`
  width: 3rem;
`;
