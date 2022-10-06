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
  isReserved: boolean;
};

export type NFTItem = {
  nftReserveCount: number;
  nftDescription: string;
  startupId: number;
  nftImage: string | StaticImageData;
  startupName: string;
  nftPrice: number;
};

export type FAVItem = {
  nftReserveCount: number;
  isFav: boolean;
  startupId: number;
  nftImage: string | StaticImageData;
  startupName: string;
  dueDate: string;
  nftDescription: string;
  nftPrice: number;
};

export type RSRVItem = {
  startupId: number;
  nftDescription: string;
  nftImage: string | StaticImageData;
  startupName: string;
  nftPrice: number;
  nftReserveCount: number;
};

export type APPLYItem = {
  nftReserveCount: number;
  nftDescription: string;
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
  tokenURI?: string;
  investCount?: number;
  nftPrice?: number;
  read: boolean;
  tokenId?: number;
};
