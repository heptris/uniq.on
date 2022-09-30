import axios from "axios";
import Text from "@/components/Text";
import { css, useTheme } from "@emotion/react";
import styled from "@emotion/styled";
import { useState } from "react";
import Button from "@/components/Button";
import LabelInput from "@/components/LabelInput";
import CircleBar from "@/components/CircleBar";
import { uniqonThemes } from "@/styles/theme";
import FileUpload from "@/components/FileUpload";
import { useRouter } from "next/router";
import { useAlert } from "@/hooks";
import { ENDPOINT_API } from "@/api/endpoints";

export default function apply() {
  const theme = useTheme();
  const router = useRouter();
  const { handleAlertOpen } = useAlert();
  const [current, setCurrent] = useState(1);

  //1단계 정보
  const [title, setTitle] = useState("");
  const [dueDate, setDueDate] = useState("");
  const [description, setDescription] = useState("");
  const [discordUrl, setDiscordUrl] = useState("");
  const [businessPlanFile, setBusinessPlanFile] = useState(null);
  const [roadMapFile, setRoadMapFile] = useState(null);

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
  const [nftTargetCount, setNftTargetCount] = useState(10);
  const [nftPrice, setNftPrice] = useState<Number>();
  const [nftDescription, setNftDescription] = useState("");
  const [nftImageFile, setNftImageFile] = useState(null);

  const onSecondNextHandler = () => {
    nftTargetCount && nftPrice && nftDescription && nftImageFile
      ? setCurrent(current + 1)
      : handleAlertOpen(2000, "모든 칸을 채워주세요.", false);
  };

  //3단계 정보
  const [isChecked, setIsChecked] = useState(false); //개인정보 동의 체크여부

  const onSubmit = () => {
    if (!isChecked) {
      handleAlertOpen(2000, "개인정보 동의를 체크해주세요", false);
    } else {
      setCurrent(1);
      setIsChecked(false);

      const formData = new FormData();
      businessPlanFile && formData.append("plan_paper", businessPlanFile);
      nftImageFile && formData.append("nft_image", nftImageFile);
      roadMapFile && formData.append("road_map", roadMapFile);
      const data = {
        title,
        dueDate,
        description,
        discordUrl,
        nftTargetCount,
        nftPrice,
        nftDescription,
      };
      formData.append(
        "startupRequestDto",
        new Blob([JSON.stringify(data)], { type: "application/json" })
      );
      console.log(data);
      console.log(businessPlanFile);
      console.log(nftImageFile);
      console.log(roadMapFile);
      formData && console.log(formData);
      const config = {
        headers: {
          "content-type": "multipart/form-data;charset=UTF-8;",
        },
      };
      // axios({
      //   method: "post",
      //   url: "api/apply",
      //   data: formData,
      //   headers: {
      //     "Content-Type": "multipart/form-data;charset=UTF-8;",
      //   },
      // })
      //   .then((res) => {
      //     console.log(res);
      //     handleAlertOpen(2000, "투자 신청이 완료되었습니다.", true);
      //   })
      //   .catch((err) => {
      //     console.log(err);
      //     handleAlertOpen(2000, "투자 신청이 실패했습니다.", false);
      //   });

      axios
        .post(`${ENDPOINT_API}/invest/regist`, formData, config)
        .then((res) => {
          console.log(res);
          handleAlertOpen(2000, "투자 신청이 완료되었습니다.", true);
        })
        .catch((err) => {
          console.log(err);
          handleAlertOpen(2000, "투자 신청이 실패했습니다.", false);
        });

      // router.push("/");
    }
  };
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
    //개인정보 동의 체크여부 확인 함수
    e.target.checked ? setIsChecked(true) : setIsChecked(false);
  };

  return (
    <ContainWrapper>
      <Text
        as="h1"
        css={css`
          font-size: 1.5rem;
          margin: 5rem 0 1rem;
        `}
      >
        투자받기
      </Text>
      <CircleBar current={current} total={3} />
      {current === 1 && (
        <>
          <LabelInput
            css={LabelInputStyle}
            labelText="투자자 모집 제목"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setTitle(e.target.value)
            }
          />
          <LabelInput
            css={LabelInputStyle}
            labelText="회사 소개글"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setDescription(e.target.value)
            }
          />
          <LabelInput
            type="date"
            css={LabelInputStyle}
            labelText="투자 마감일"
            min={today}
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setDueDate(e.target.value + "T23:59:59.999Z")
            }
          />
          <LabelInput
            css={LabelInputStyle}
            labelText="디스코드 주소"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setDiscordUrl(e.target.value)
            }
          />
          <FileUpload
            type="pdf"
            text="사업소개서 및 프로젝트 소개서(파일용량 5MB까지)"
            onFileSelectSuccess={(file: any) => setBusinessPlanFile(file)}
            onFileSelectError={({ error }) => alert(error)}
          />
          <FileUpload
            type="pdf"
            text="NFT 투자혜택 로드맵을 제출해주세요."
            onFileSelectSuccess={(file: any) => setRoadMapFile(file)}
            onFileSelectError={({ error }) => alert(error)}
          />
          <Button css={ButtonStyle} onClick={onFirstNextHandler}>
            다음단계
          </Button>
        </>
      )}

      {current === 2 && (
        <>
          <SelectInputTitle>토큰 발행 개수</SelectInputTitle>
          <SelectInput
            id="토큰 발행 개수"
            onChange={(e: React.ChangeEvent<HTMLSelectElement>) =>
              setNftTargetCount(Number(e.target.value))
            }
            value={nftTargetCount}
          >
            <option value="10">10</option>
            <option value="20">20</option>
            <option value="30">30</option>
          </SelectInput>
          <LabelInput
            css={LabelInputStyle}
            labelText="토큰 개당 가격(SSH)"
            type="number"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setNftPrice(Number(e.target.value))
            }
          />
          <LabelInput
            css={LabelInputStyle}
            labelText="토큰 정보"
            onChange={(e: React.ChangeEvent<HTMLInputElement>) =>
              setNftDescription(e.target.value)
            }
          />

          <FileUpload
            type="img"
            text="NFT 이미지 파일을 첨부해주세요."
            onFileSelectSuccess={(file: any) => setNftImageFile(file)}
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
              NFT 투자혜택 로드맵 예시 링크(메타콩즈)
            </LoadmapLink>
          </Text>
          <Button css={ButtonStyle} onClick={onSecondNextHandler}>
            다음단계
          </Button>
        </>
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
            <LabelInput id="infoAgree" type="checkbox" onChange={onChange} />
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
    </ContainWrapper>
  );
}

const ContainWrapper = styled.form`
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: ${({ theme }) => theme.color.text.main};
`;

const LabelInputStyle = css`
  width: 20rem;
  height: 2.5rem;
  margin-bottom: 1rem;
  color: ${uniqonThemes.darkTheme.color.text.main};
`;

const ButtonStyle = css`
  margin: 2rem 0 0;
  width: 20rem;
`;

const InfoAgreeBox = styled.div`
  width: 20rem;
  height: 9rem;
  padding: 0.5rem;
  margin: 1rem 0;
  border-radius: 0.5rem;
  background-color: ${({ theme }) => theme.color.background.item};
`;

const AgreeCheckBox = styled.div`
  width: 20rem;
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
