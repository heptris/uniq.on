import { Member, NFTItem } from "./api_responses";

export type SignupForm = Omit<Member, "id" | "profileImage" | "memberType">;
export type StoreNFTParams = Omit<NFTItem, "nftImage" | "tokenId"> & {
  nftImage: Blob | File;
};
