import { GetServerSideProps } from "next";
import Link from "next/link";
import axios from "axios";

import { css } from "@emotion/react";

import nft1 from "@/assets/nfts/2.png";
import nft2 from "@/assets/nfts/3.png";
import nft3 from "@/assets/nfts/4.png";

import { useSelectTab } from "@/hooks";

import Grid from "@/components/Grid";
import CorporationCard from "@/components/Card/CorporationCard";
import Carousel from "@/components/Carousel";
import SelectTab from "@/components/SelectTab";

import { ENDPOINT_API } from "@/api/endpoints";

import { Startup } from "@/types/api_responses";
import type { CarouselItem } from "@/types/props";
type InvestListProps = {
  startups: Startup[];
};

export const getServerSideProps: GetServerSideProps = async () => {
  const { content: startups, ...rest } = await axios({
    method: "get",
    url: `${ENDPOINT_API}/invest`,
    params: {
      size: 10,
      page: 0,
    },
  }).then(({ data }) => data.data);
  return { props: { startups, ...rest } };
};

export default function InvestmentList(props: InvestListProps) {
  const { startups, ...rest } = props;
  const menus = ["전체 목록", "Top 10"];
  const { selectedMenu, onSelectHandler } = useSelectTab(menus);

  const carouselItems: CarouselItem[] = [
    {
      startupName: "RENGA",
      image: nft1,
    },
    {
      startupName: "DigiDaigaku",
      image: nft2,
    },
    {
      startupName: "Hume Genesis",
      image: nft3,
    },
  ];

  return (
    <>
      <Carousel items={carouselItems} />
      <SelectTab
        menus={menus}
        onSelectHandler={onSelectHandler}
        type={"purple"}
        css={css`
          margin-top: 2rem;
        `}
      />

      <Grid>
        {startups?.map((startup) => (
          <Link
            href={`/list/${encodeURIComponent(startup.startupId)}`}
            key={startup.startupId}
          >
            <CorporationCard
              startupName={startup.startupName}
              profileImage={startup.profileImage}
              title={startup.title}
              dueDate={startup.dueDate}
              progress={parseInt(
                (
                  (startup.nftReserveCount / startup.nftTargetCount) *
                  100
                ).toFixed(0)
              )}
              clickable={true}
            />
          </Link>
        ))}
      </Grid>
    </>
  );
}
