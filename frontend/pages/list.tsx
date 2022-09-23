import Head from "next/head";

import { css } from "@emotion/react";
import styled from "@emotion/styled";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faArrowDownWideShort } from "@fortawesome/free-solid-svg-icons";

import corp from "@/assets/corps/1.png";
import nft from "@/assets/nfts/1.png";
import nft1 from "assets/nfts/2.png";
import nft2 from "assets/nfts/3.png";
import nft3 from "assets/nfts/animals/3.png";

import type { NFTItem, Corp } from "@/types/api_responses";
import type { CarouselItem } from "@/types/props";
import Button from "@/components/Button";
import Text from "@/components/Text";
import Grid from "@/components/Grid";
import Navbar from "@/components/Navbar";
import CorporationCard from "@/components/Card/CorporationCard";
import Layout from "@/components/Layout";
import Carousel from "@/components/Carousel";
import contracts from "contracts/utils";

export default function Home() {
  const { account, setAccount } = contracts.useAccount();
  const carouselItems: CarouselItem[] = [
    {
      image: nft1,
    },
    {
      image: nft2,
    },
    {
      image: nft3,
    },
  ];
  const corps: Corp[] = [
    {
      corpName: "Samsung NEXT",
      corpAvatar: corp,
      title: "SNKRZ",
      date: "2022.09.07",
      progress: 0,
    },
    {
      corpName: "Samsung NEXT",
      corpAvatar: corp,
      title: "SNKRZ",
      date: "2022.09.07",
      progress: 33,
    },
  ];

  return (
    <Layout css={css``}>
      <Head>
        <title>Uniq.on</title>
        <link rel="icon" href="/favicon.ico" />
      </Head>

      <Navbar />
      <Carousel items={carouselItems} />
      <PageHeader>
        <Text
          css={css`
            font-size: 20px;
            font-weight: 600;
          `}
          as="h1"
        >
          등록된 펀딩 목록
        </Text>
        <Button
          onClick={() => console.log(account)}
          css={css`
            width: 6rem;
            height: 3rem;
            display: flex;
            justify-content: center;
            align-items: center;
          `}
          type={"blue"}
        >
          <FontAwesomeIcon
            css={css`
              height: 100%;
              margin-right: 0.5rem;
            `}
            icon={faArrowDownWideShort}
          />
          필터
        </Button>
      </PageHeader>
      <Hr />

      <Grid>
        {corps.map((corp, i) => (
          <CorporationCard
            key={i}
            corpName={corp.corpName}
            corpAvatar={corp.corpAvatar}
            title={corp.title}
            date={corp.date}
            progress={corp.progress}
          />
        ))}
      </Grid>
    </Layout>
  );
}

const PageHeader = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 2rem 0 0 0;
`;
const Hr = styled.hr`
  width: 100%;
  border: 1px solid ${({ theme }) => theme.color.background.card};
  margin-bottom: 1rem;
`;
