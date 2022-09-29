import Link from "next/link";

import axios from "axios";
import { useQuery } from "@tanstack/react-query";
import { QUERY_KEYS } from "@/api/query_key_schema";

import { css } from "@emotion/react";

import nft1 from "@/assets/nfts/2.png";
import nft2 from "@/assets/nfts/3.png";
import nft3 from "@/assets/nfts/4.png";

import type { Startup } from "@/types/api_responses";
import type { CarouselItem } from "@/types/props";
import Grid from "@/components/Grid";
import CorporationCard from "@/components/Card/CorporationCard";
import Carousel from "@/components/Carousel";
import contracts from "@/contracts/utils";
import SelectTab from "@/components/SelectTab";
import { useSelectTab } from "@/hooks";

export default function InvestmentList() {
  const { account } = contracts.useAccount();
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

  const { isLoading, isError, data } = useQuery(
    [QUERY_KEYS.INVEST_LIST],
    async () => {
      const data: Startup[] = await (
        await axios.get("/api/invest")
      ).data.content;
      return data;
    }
  );
  if (isLoading) return;
  if (isError) return;
  const startups = data;

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
        {startups.map((startup) => (
          <Link href={`/list/${encodeURIComponent(startup.startupId)}`}>
            <CorporationCard
              key={startup.startupId}
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

// const startups: Startup[] = [
//   {
//     startupId: 1,
//     startupName: "Samsung NEXT",
//     profileImage: startup,
//     title: "SNKRZ",
//     dueDate: "2022.09.07",
//     nftTargetCount: 33,
//     nftReserveCount: 1,
//   },
//   {
//     startupId: 2,
//     startupName: "Samsung NEXT",
//     profileImage: startup,
//     title: "SNKRZ",
//     dueDate: "2022.09.07",
//     nftTargetCount: 33,
//     nftReserveCount: 10,
//   },
//   {
//     startupId: 3,
//     startupName: "Samsung NEXT",
//     profileImage: startup,
//     title: "SNKRZ",
//     dueDate: "2022.09.07",
//     nftTargetCount: 33,
//     nftReserveCount: 20,
//   },
//   {
//     startupId: 4,
//     startupName: "Samsung NEXT",
//     profileImage: startup,
//     title: "SNKRZ",
//     dueDate: "2022.09.07",
//     nftTargetCount: 33,
//     nftReserveCount: 0,
//   },
// ];
