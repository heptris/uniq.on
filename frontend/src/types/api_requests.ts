import { Member, NFTItem } from "./api_responses";

export type SignupFormType = Omit<Member, "id" | "profileImage" | "memberType">;
export type StoreNFTParams = Omit<NFTItem, "nftImage" | "tokenId"> & {
  nftImage: Blob | File;
};
export type ApplyFormType = {
  title: string;
  description: string;
  dueDate: string;
  discordUrl: string;
  businessPlanFile: any;
  roadMapFile: any;
  nftTargetCount: number;
  nftPrice: number;
  nftDescription: string;
  nftImageFile: any;
  isChecked: boolean;
};
