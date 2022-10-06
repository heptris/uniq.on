import axios from "axios";
import { useRouter } from "next/router";
import { useEffect, useState } from "react";
import { useQuery } from "@tanstack/react-query";

import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { uniqonThemes } from "@/styles/theme";

import Text from "@/components/Text";
import Button from "@/components/Button";
import LabelInput from "@/components/LabelInput";
import CircleBar from "@/components/CircleBar";
import FileUpload from "@/components/FileUpload";

import { getUserInfo, useAlert, useAuth, useForm } from "@/hooks";

import { ENDPOINT_API } from "@/api/endpoints";
import { ApplyFormType } from "@/types/api_requests";
import { ROUTES, UNIQON_TOKEN } from "@/constants";
import contracts from "@/contracts/utils";

import { QUERY_KEYS } from "@/api/query_key_schema";
import { Member } from "@/types/api_responses";
import { minDesktopWidth } from "@/styles/utils";

const initialApplyState = {
  title: "",
  dueDate: "",
  description: "",
  discordUrl: "",
  businessPlanFile: null,
  roadMapFile: null,
  nftTargetCount: 1,
  nftPrice: 0,
  nftDescription: "",
  nftImageFile: null,
  isChecked: false,
};

const { LOGIN, HOME } = ROUTES;
const { MY_USER_INFO } = QUERY_KEYS;

export default function apply() {
  const { isLogined } = useAuth();
  const router = useRouter();
  const theme = useTheme();
  const { handleAlertOpen } = useAlert();
  const [current, setCurrent] = useState(1);
  const { form, onChangeForm, setForm } =
    useForm<ApplyFormType>(initialApplyState);
  const { storeNFT } = contracts;

  const { data: member, refetch } = useQuery<Member>(
    [MY_USER_INFO],
    getUserInfo,
    { initialData: {} as Member }
  );

  const { nickname: startupName, id: startupId } = member;

  useEffect(() => {
    isLogined
      ? refetch()
      : (() => {
          handleAlertOpen(2000, "로그인이 필요한 서비스입니다", false);
          router.push(LOGIN);
        })();
  }, []);

  const {
    businessPlanFile,
    description,
    discordUrl,
    dueDate,
    isChecked,
    nftDescription,
    nftImageFile,
    nftPrice,
    nftTargetCount,
    roadMapFile,
    title,
  } = form;

  //1단계 정보

  const onFirstNextHandler = () => {
    title &&
    dueDate &&
    description &&
    discordUrl &&
    businessPlanFile &&
    roadMapFile
      ? setCurrent(current + 1)
      : handleAlertOpen(2000, "모든 칸을 채워주세요.", false);
  };

  const today = new Date().toISOString().slice(0, 10);

  //2단계 정보

  const onSecondNextHandler = () => {
    nftTargetCount && nftPrice && nftDescription && nftImageFile
      ? setCurrent(current + 1)
      : handleAlertOpen(2000, "모든 칸을 채워주세요.", false);
  };

  //3단계 정보

  const onSubmit = async () => {
    // console.log(form, axios.defaults.headers.common);
    if (!isChecked) {
      handleAlertOpen(2000, "개인정보 동의를 체크해주세요", false);
    } else {
      setCurrent(1);

      const formData = new FormData();
      businessPlanFile && formData.append("plan_paper", businessPlanFile);
      nftImageFile && formData.append("nft_image", nftImageFile);
      roadMapFile && formData.append("road_map", roadMapFile);

      const config = {
        headers: {
          "content-type": "multipart/form-data;charset=UTF-8;",
        },
      };

      const token = await storeNFT({
        startupId,
        nftImage: nftImageFile,
        startupName,
        nftPrice,
        nftDescription,
      });

      if (!token) {
        handleAlertOpen(2000, "NFT 업로드 실패", false);
      } else {
        const url = "https://ipfs.io/ipfs/" + token.url.split("://")[1];
        // console.log(dueDate); // 2022-10-07T23:59:59.999Z
        let [date, time] = dueDate.split("T");
        const tmp = Date().split(" ");
        time = tmp[tmp.length - 4];
        const [hh, mm, ss] = time.split(":");
        const newDueDate = date + `T${hh}:${parseInt(mm) + 2}:${ss}.999Z`;

        const data = {
          title,
          dueDate: newDueDate,
          description,
          discordUrl,
          nftTargetCount,
          nftPrice,
          nftDescription,
          tokenURI: url,
        };
        formData.append(
          "startupRequestDto",
          new Blob([JSON.stringify(data)], { type: "application/json" })
        );

        axios
          .post(`${ENDPOINT_API}/invest/regist`, formData, config)
          .then((res) => {
            console.log(res);
            handleAlertOpen(2000, "투자 신청이 완료되었습니다.", true);
            router.push(HOME);
          })
          .catch((err) => {
            console.log(err);
            handleAlertOpen(2000, "투자 신청이 실패했습니다.", false);
            setForm(initialApplyState);
          });
      }
    }
  };

  return (
    <ApplyContainer>
      <Text
        as="h1"
        role="page-header"
        css={css`
          font-size: 2rem;
          font-weight: 700;
          margin-bottom: 2rem;
          align-self: start;
        `}
      >
        투자받기
      </Text>
      <CircleBar current={current} total={3} />
      {current === 1 && (
        <div
          css={css`
            width: 100%;
          `}
        >
          <LabelInput
            css={LabelInputStyle}
            labelText="펀딩 제목"
            name="title"
            onChange={onChangeForm}
          />
          <LabelInput
            css={LabelInputStyle}
            labelText="기업 소개"
            name="description"
            onChange={onChangeForm}
          />
          <LabelInput
            type="date"
            css={LabelInputStyle}
            labelText="펀딩 마감일"
            min={today}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setForm({ ...form, dueDate: e.target.value + "T23:59:59.999Z" })
            }
          />
          <LabelInput
            css={LabelInputStyle}
            labelText="디스코드 주소"
            name="discordUrl"
            onChange={onChangeForm}
          />
          <FileUpload
            type="pdf"
            text="사업소개서 및 프로젝트 소개서 (pdf, 최대 5MB)"
            onFileSelectSuccess={(file: any) =>
              setForm({ ...form, businessPlanFile: file })
            }
            onFileSelectError={({ error }) => alert(error)}
          />
          <FileUpload
            type="pdf"
            text="투자 보상 로드맵 (png, jpg, gif)"
            onFileSelectSuccess={(file: any) =>
              setForm({ ...form, roadMapFile: file })
            }
            onFileSelectError={({ error }) => alert(error)}
          />
          <Button css={ButtonStyle} onClick={onFirstNextHandler}>
            다음단계
          </Button>
        </div>
      )}

      {current === 2 && (
        <div
          css={css`
            width: 100%;
          `}
        >
          {/* <SelectInputTitle>토큰 발행 개수</SelectInputTitle>
          <SelectInput
            id="토큰 발행 개수"
            onChange={(e: React.ChangeEvent<HTMLSelectElement>) =>
              setForm({ ...form, nftTargetCount: +e.target.value })
            }
            value={nftTargetCount}
          >
            <option value="1">1</option>
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
          </SelectInput> */}
          <LabelInput
            css={LabelInputStyle}
            labelText={`최소 발행 토큰 수`}
            type="number"
            min="1"
            max="30"
            name="nftPrice"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setForm({ ...form, nftTargetCount: +e.target.value })
            }
          />
          <LabelInput
            css={LabelInputStyle}
            labelText={`토큰 개당 가격 (${UNIQON_TOKEN})`}
            type="number"
            min="0"
            name="nftPrice"
            onChange={onChangeForm}
          />
          <LabelInput
            css={LabelInputStyle}
            labelText="NFT 소개"
            name="nftDescription"
            onChange={onChangeForm}
          />

          <FileUpload
            type="img"
            text="NFT 이미지 (png, jpg, gif)"
            onFileSelectSuccess={(file: any) =>
              setForm({ ...form, nftImageFile: file })
            }
            onFileSelectError={({ error }) => alert(error)}
          />

          <Text
            css={css`
              align-self: flex-start;
            `}
          >
            <LoadmapLink
              href="https://themetakongz.com/kr.html#sec_roadmap"
              target="_blank"
            >
              투자 보상 로드맵 예시
            </LoadmapLink>
          </Text>
          <Button css={ButtonStyle} onClick={onSecondNextHandler}>
            다음단계
          </Button>
        </div>
      )}

      {current === 3 && (
        <>
          <Text
            css={css`
              align-self: flex-start;
            `}
          >
            개인정보 수집 및 이용 동의
          </Text>
          <InfoAgreeBox>
            <Text
              css={css`
                font-size: 0.8rem;
              `}
            >
              {"  "}uniq.on(이하 유니콘)은 펀딩 정보제공 목적으로 개인정보(성명,
              이메일, 연락처)를 수집하고자 하며, 수집된 개인정보는 수집 및
              이용목적이 달성된 후에는 지체 없이 파기합니다.
              <br />
              {"  "}개인 정보 수집 및 이용에 대하여 동의를 거부할 수 있으나,
              거부할 시 uniq.on 정보제공 신청이 완료되지 않음에 유의하시기
              바랍니다.
            </Text>
          </InfoAgreeBox>
          <AgreeCheckBox>
            <LabelInput
              id="infoAgree"
              type="checkbox"
              name="isChecked"
              onChange={onChangeForm}
            />
            <label
              htmlFor="infoAgree"
              css={css`
                font-size: 0.8rem;
                color: ${theme.color.text.main};
              `}
            >
              개인정보 수집 및 이용에 동의합니다.
            </label>
          </AgreeCheckBox>
          <Button css={ButtonStyle} onClick={onSubmit}>
            제출완료
          </Button>
        </>
      )}
    </ApplyContainer>
  );
}

const ApplyContainer = styled.form`
  padding-top: 5rem;
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  @media (${minDesktopWidth}) {
    width: 33.33%;
  }
`;

const LabelInputStyle = css`
  height: 3rem;
  margin-bottom: 1rem;
  color: ${uniqonThemes.darkTheme.color.text.main};
`;

const ButtonStyle = css`
  margin: 2rem 0 0;
  width: 100%;
  height: 3rem;
`;

const InfoAgreeBox = styled.div`
  width: 100%;
  height: 9rem;
  padding: 0.5rem;
  margin: 1rem 0;
  border-radius: 0.5rem;
  background-color: ${({ theme }) => theme.color.background.item};
`;

const AgreeCheckBox = styled.div`
  width: 100%;
  display: flex;
  align-items: center;
`;

const LoadmapLink = styled.a`
  font-size: 0.8rem;
  color: ${({ theme }) => theme.color.background.emphasis};
  &:hover {
    cursor: pointer;
    color: ${({ theme }) => theme.color.text.hover};
  }
`;

const SelectInputTitle = styled.div`
  display: flex;
  align-self: flex-start;
  color: ${({ theme }) => theme.color.text.main};
  font-size: 12px;
  margin-bottom: 10px;
`;

const SelectInput = styled.select`
  width: 20rem;
  height: 2.5rem;
  border-radius: 4px;
  margin-bottom: 1rem;
  color: ${({ theme }) => theme.color.text.main};
  background-color: ${({ theme }) => theme.color.background.item};
  &:focus {
    outline: 2px solid ${({ theme }) => theme.color.border.main};
  }
`;
