import { Member } from "./api_responses";

export type SignupForm = Omit<Member, "id" | "profileImage" | "memberType">;
