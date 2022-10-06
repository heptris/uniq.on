/* 백엔드 응답 타입 */

import { StaticImageData } from "next/image";

export type Member = {
  id: number;
  name: string;
  walletAddress: string;
  nickname: string;
  profileImage: string | StaticImageData;
  email: string;
  memberType: "ADMIN" | "USER";
};
/** Investment Request */
export type IR = {
  id: number;
  corpName: string;
  planPaper: string;
  roadmap: string;
  title: string;
  dueDate: string;
  targetAmount: number;
  curTotalAmount: number;
  nftImage: string | StaticImageData;
  nftTypeCount: number;
  nftTokenCount: number;
  nftPrice: number;
  isClosed: boolean;
  isAchieved: boolean;
  discordUrl: string;
  enrollStatus: "PENDING" | "ACCEPT" | "REJECT";
  rejectReason: string;
  description: string;
  memberId: number;
};
export type NFTItem = {
  companyId: number;
  nftImage: string | StaticImageData;
  tokenId: number;
  corpName: string;
  price: number;
  progress: number;
  discordUrl?: string;
};
export type Corp = {
  corpName: string;
  corpAvatar: string | StaticImageData;
  title: string;
  date: string;
  progress: number;
};
