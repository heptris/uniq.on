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

export default function apply() {
  const theme = useTheme();
  const router = useRouter();
  const [current, setCurrent] = useState(1);
  const [isChecked, setIsChecked] = useState(false);
  const currentUpHandler = () => {
    setCurrent(current + 1);
  };
  const submitHandler = () => {
    if (!isChecked) {
      alert("개인정보 동의를 체크해주세요");
    } else {
      alert("신청이 완료되었습니다.");
      setCurrent(1);
      setIsChecked(false);
      router.push("/");
    }
  };
  const onChange = (e: React.ChangeEvent<HTMLInputElement>) => {
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
          <LabelInput css={LabelInputStyle} labelText="기업명" />
          <LabelInput css={LabelInputStyle} labelText="담당자 이름/직함" />
          <LabelInput css={LabelInputStyle} labelText="담당자 이메일" />
          <LabelInput css={LabelInputStyle} labelText="담당자 연락처" />
          <Button css={ButtonStyle} onClick={currentUpHandler}>
            다음단계
          </Button>
        </>
      )}
      {current === 2 && (
        <>
          <LabelInput css={LabelInputStyle} labelText="희망 모집 금액(SSH)" />
          <LabelInput
            type="date"
            css={LabelInputStyle}
            labelText="투자 마감일"
          />
          <SelectInputTitle>토큰 발행 개수</SelectInputTitle>
          <SelectInput id="토큰 발행 개수">
            <option>10</option>
            <option>20</option>
            <option>30</option>
          </SelectInput>

          <LabelInput css={LabelInputStyle} labelText="디스코드 주소" />
          <LabelInput css={LabelInputStyle} labelText="회사 소개글" />
          <LabelInput css={LabelInputStyle} labelText="투자자 모집 제목" />
          <FileUpload text="사업소개서 및 프로젝트 소개서(파일용량 50MB까지)" />
          <FileUpload text="NFT 이미지 파일을 첨부해주세요." />
          <FileUpload text="NFT 투자혜택 로드맵을 제출해주세요." />

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

          <Button css={ButtonStyle} onClick={currentUpHandler}>
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
                font-size: 0.5rem;
              `}
            >
              uniq.on(이하 유니콘)은 펀딩 정보제공 목적으로 개인정보(성명,
              이메일, 연락처)를 수집하고자 하며, 수집된 개인정보는 수집 및
              이용목적이 달성된 후에는 지체 없이 파기합니다.
              <br /> 개인 정보 수집 및 이용에 대하여 동의를 거부할 수 있으나,
              거부할 시 uniq.on 정보제공 신청이 완료되지 않음에 유의하시기
              바랍니다.
            </Text>
          </InfoAgreeBox>
          <AgreeCheckBox>
            <LabelInput id="infoAgree" type="checkbox" onChange={onChange} />
            <label
              htmlFor="infoAgree"
              css={css`
                font-size: 0.5rem;
                color: ${theme.color.text.main};
              `}
            >
              개인정보 수집 및 이용에 동의합니다.
            </label>
          </AgreeCheckBox>
          <Button css={ButtonStyle} onClick={submitHandler}>
            제출완료
          </Button>
        </>
      )}
    </ContainWrapper>
  );
}

const ContainWrapper = styled.div`
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
  height: 7rem;
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
  font-size: 0.5rem;
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
