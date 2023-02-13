# Uniq.on

- NFT 구매를 통한 소액 투자 서비스

- 주요 기능

  - Metamask 지갑 연결 후 로그인
  - NFT 예약 생성
  - NFT 예약 구매
  - 알림
  - NFT 관리
  - 커뮤니티

## :family: 팀 소개

|                                                한승재                                                |                                                전한울                                                |                                                박상태                                                 |                                                신은정                                                |                                               안호진                                               |                                                이승호                                                 |
| :--------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------: | :---------------------------------------------------------------------------------------------------: |
| <a href="https://github.com/YanZisuka">![](https://avatars.githubusercontent.com/u/83825572?v=4)</a> | <a href="https://github.com/hanwool77">![](https://avatars.githubusercontent.com/u/62233935?v=4)</a> | <a href="https://github.com/sangtae365">![](https://avatars.githubusercontent.com/u/95201136?v=4)</a> | <a href="https://github.com/ejshin2ya">![](https://avatars.githubusercontent.com/u/80582815?v=4)</a> | <a href="https://github.com/HojinAn">![](https://avatars.githubusercontent.com/u/47904304?v=4)</a> | <a href="https://github.com/dltmdgh579">![](https://avatars.githubusercontent.com/u/68692871?v=4)</a> |

<br>

## 🏗️ 기술스택/아키텍처

![](./exec/images/%EA%B8%B0%EC%88%A0%EC%8A%A4%ED%83%9D%EC%95%84%ED%82%A4%ED%85%8D%EC%B2%98.png)

<br>

## 🗃️ ERD

![](./exec/images/ERD.png)

<br>

## 구현 기능 화면

1. 메타마스크 연결 후 회원가입

   ![01. 메타마스크 연결 후 회원가입](./exec/images/01.%20%EB%A9%94%ED%83%80%EB%A7%88%EC%8A%A4%ED%81%AC%20%EC%97%B0%EA%B2%B0%20%ED%9B%84%20%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85.gif)

2. 메타마스크 연결 후 로그인

   ![02. 메타마스크 연결 후 로그인](./exec/images/02.%20%EB%A9%94%ED%83%80%EB%A7%88%EC%8A%A4%ED%81%AC%20%EC%97%B0%EA%B2%B0%20%ED%9B%84%20%EB%A1%9C%EA%B7%B8%EC%9D%B8.gif)

3. 투자 등록 - 렌딩 페이지 투자 신청(스타트업)

   ![03. 투자 등록 - 렌딩 페이지 투자 신청(스타트업)](<./exec/images/03.%20%ED%88%AC%EC%9E%90%20%EB%93%B1%EB%A1%9D%20-%20%EB%A0%8C%EB%94%A9%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%ED%88%AC%EC%9E%90%20%EC%8B%A0%EC%B2%AD(%EC%8A%A4%ED%83%80%ED%8A%B8%EC%97%85).gif>)

4. 메인리스트 렌딩 페이지 투자 하기(스타트업 투자 리스트)

   ![04. 메인리스트 렌딩 페이지 투자 하기(스타트업 투자 리스트)](<./exec/images/04.%20%EB%A9%94%EC%9D%B8%EB%A6%AC%EC%8A%A4%ED%8A%B8%20%EB%A0%8C%EB%94%A9%20%ED%8E%98%EC%9D%B4%EC%A7%80%20%ED%88%AC%EC%9E%90%20%ED%95%98%EA%B8%B0(%EC%8A%A4%ED%83%80%ED%8A%B8%EC%97%85%20%ED%88%AC%EC%9E%90%20%EB%A6%AC%EC%8A%A4%ED%8A%B8).gif>)

5. 투자리스트 상세화면

   1. 즐겨찾기 등록/해제

      ![05.01. 즐겨찾기 등록](./exec/images/05.01.%20%EC%A6%90%EA%B2%A8%EC%B0%BE%EA%B8%B0%20%EB%93%B1%EB%A1%9D.gif)

   2. 투자 예약 하기(by 투자자)

      ![05.02. 투자 예약 하기(by 투자자)](<./exec/images/05.02.%20%ED%88%AC%EC%9E%90%20%EC%98%88%EC%95%BD%20%ED%95%98%EA%B8%B0(by%20%ED%88%AC%EC%9E%90%EC%9E%90).gif>)

6. 알람

   - 성공시

   1. 민팅하라는 알람을 통해 민팅(스타트업)

      ![06.01. 민팅하라는 알람을 통해 민팅(스타트업)](<./exec/images/06.01.%20%EB%AF%BC%ED%8C%85%ED%95%98%EB%9D%BC%EB%8A%94%20%EC%95%8C%EB%9E%8C%EC%9D%84%20%ED%86%B5%ED%95%B4%20%EB%AF%BC%ED%8C%85(%EC%8A%A4%ED%83%80%ED%8A%B8%EC%97%85).gif>)

   2. NFT토큰 구매하라는 알람을 통해 토큰 구매(투자자)

      ![06.02. NFT토큰 구매하라는 알람을 통해 토큰 구매(투자자)](<./exec/images/06.02.%20NFT%ED%86%A0%ED%81%B0%20%EA%B5%AC%EB%A7%A4%ED%95%98%EB%9D%BC%EB%8A%94%20%EC%95%8C%EB%9E%8C%EC%9D%84%20%ED%86%B5%ED%95%B4%20%ED%86%A0%ED%81%B0%20%EA%B5%AC%EB%A7%A4(%ED%88%AC%EC%9E%90%EC%9E%90).gif>)

   - 실패시

   1. 투자/예약 실패했다는 알람 읽기(스타트업, 투자자)

      ![06.03. 투자,예약 실패했다는 알람 읽기(스타트업, 투자자)](<./exec/images/06.03.%20%ED%88%AC%EC%9E%90%2C%EC%98%88%EC%95%BD%20%EC%8B%A4%ED%8C%A8%ED%96%88%EB%8B%A4%EB%8A%94%20%EC%95%8C%EB%9E%8C%20%EC%9D%BD%EA%B8%B0(%EC%8A%A4%ED%83%80%ED%8A%B8%EC%97%85%2C%20%ED%88%AC%EC%9E%90%EC%9E%90).gif>)

7. 프로필

   1. 내 보유 NFT토큰 목록

      ![07.01. 내 보유 NFT토큰 목록](./exec/images/07.01.%20%EB%82%B4%20%EB%B3%B4%EC%9C%A0%20NFT%ED%86%A0%ED%81%B0%20%EB%AA%A9%EB%A1%9D.gif)

   2. 관심목록

      ![07.02. 관심목록](./exec/images/07.02.%20%EA%B4%80%EC%8B%AC%EB%AA%A9%EB%A1%9D.gif)

   3. 투자 예약 목록(투자자 입장)

      ![07.03. 투자 예약 목록(투자자 입장)](<./exec/images/07.03.%20%ED%88%AC%EC%9E%90%20%EC%98%88%EC%95%BD%20%EB%AA%A9%EB%A1%9D(%ED%88%AC%EC%9E%90%EC%9E%90%20%EC%9E%85%EC%9E%A5).gif>)

   4. 투자 등록 목록(스타트업 입장)

      ![07.04. 투자 등록 목록(스타트업 입장)](<./exec/images/07.04.%20%ED%88%AC%EC%9E%90%20%EB%93%B1%EB%A1%9D%20%EB%AA%A9%EB%A1%9D(%EC%8A%A4%ED%83%80%ED%8A%B8%EC%97%85%20%EC%9E%85%EC%9E%A5).gif>)

8. 커뮤니티

   1. 보유 NFT 토큰 커뮤니티 입장

      ![08.01. 보유 NFT 토큰 커뮤니티 입장](./exec/images/08.01.%20%EB%B3%B4%EC%9C%A0%20NFT%20%ED%86%A0%ED%81%B0%20%EC%BB%A4%EB%AE%A4%EB%8B%88%ED%8B%B0%20%EC%9E%85%EC%9E%A5%20%EB%B0%8F%20%EA%B2%8C%EC%8B%9C%ED%8C%90%20%EC%9D%B4%EC%9A%A9.gif)
