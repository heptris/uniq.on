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
  memberId?: number;
  startupId: number;
  startupName: string;
  title: string;
  description: string;
  nftTargetCount: number;
  nftReserveCount: number;
  nftPrice: number;
  dueDate: string;
  planPaperImg: string;
  roadmap: string;
  nftImage: string | StaticImageData;
  nftDescription: string;
  isFinished: boolean;
  isGoal: boolean;
  isFav: boolean;
  discordUrl: string;
  enrollStatus?: "PENDING" | "ACCEPT" | "REJECT";
  rejectReason?: string;
};

export type NFTItem = {
  tokenId: number;
  startupId: number;
  nftImage: string | StaticImageData;
  startupName: string;
  nftPrice: number;
};

export type FAVItem = {
  tokenId: number;
  isFav: boolean;
  startupId: number;
  nftImage: string | StaticImageData;
  startupName: string;
  dueDate: string;
  nftDescription: string;
  nftPrice: number;
};

export type RSRVItem = {
  tokenId: number;
  startupId: number;
  nftImage: string | StaticImageData;
  startupName: string;
  nftPrice: number;
  nftReserveCount: number;
};

export type APPLYItem = {
  tokenId: number;
  startupId: number;
  nftImage: string | StaticImageData;
  startupName: string;
  nftPrice: number;
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
// export type MypageItem = Omit<IR> & { planPaper: string };
export type AlarmItem = {
  alarmId: number;
  content: string;
  investCount?: number;
  read: boolean;
  tokenId?: number;
};
