import { QUERY_KEYS } from "@/api/query_key_schema";
import NFTItemCard from "@/components/Card/NFTItemCard";
import Grid from "@/components/Grid";
import { NFTItem } from "@/types/api_responses";
import { MypageListType } from "@/types/props";
import React from "react";

const { MY_APPLY_LIST, MY_FAVORITE_LIST, MY_NFT_LIST, MY_RESERVE_LIST } =
  QUERY_KEYS;

const MypageListContainer = ({
  nfts,
  handleModalOpen,
  mypageListType,
}: {
  nfts: NFTItem[];
  handleModalOpen: (nft: NFTItem) => void;
  mypageListType: MypageListType;
}) => {
  return (
    <Grid column="double">
      {nfts.map((nft: NFTItem) => (
        <NFTItemCard
          key={nft.startupId}
          nftImage={nft.nftImage}
          tokenId={nft.tokenId}
          startupName={nft.startupName}
          price={nft.price}
          onClick={() => handleModalOpen(nft)}
          clickable={true}
        />
      ))}
    </Grid>
  );
};

export default MypageListContainer;
