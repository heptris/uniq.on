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
  startupId: number;
  startupName: string;
  description: string;
  planPaperImg: string;
  roadmap: string;
  title: string;
  dueDate: string;
  nftTargetCount: number;
  nftImage: string | StaticImageData;
  nftReserveCount: number;
  nftPrice: number;
  nftDescription: string;
  isFinished: boolean;
  isGoal: boolean;
  discordUrl: string;
  enrollStatus?: "PENDING" | "ACCEPT" | "REJECT";
  rejectReason?: string;
  memberId?: number;
};
export type NFTItem = {
  tokenId: number;
  startupId: number;
  nftImage: string | StaticImageData;
  startupName: string;
  price: number;
};
export type Startup = {
  startupId: number;
  startupName: string;
  title: string;
  dueDate: string;
  nftTargetCount: number;
  nftReserveCount: number;
  profileImage: string | StaticImageData;
};
