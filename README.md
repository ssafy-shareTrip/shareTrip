<div align="center">
  <br />
  <img src="./readme_assets/shareTripLogo.jpg" alt="ShareTrip" width="600px" height="200px" />
  <br />
  <h1> 쉐어 트립 (ShareTrip)</h1>
  <h3>🛫 관광지 여행 경로 생성 및 공유 서비스</h3>
  
</div>

<div align="right">

[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https%3A%2F%2Fgithub.com%2Fssafy-shareTrip%2FshareTrip&count_bg=%2382C0D9&title_bg=%23555555&icon=&icon_color=%23E7E7E7&title=hits&edge_flat=false)](https://hits.seeyoufarm.com)

</div>

## 목차

1. [**웹 서비스 소개**](#1)
1. [**버전 기록**](#2)
1. [**기술 스택**](#3)
1. [**주요 기능**](#4)
1. [**프로젝트 구성도**](#5)
1. [**서비스 화면**](#6)
1. [**개발 팀 소개**](#7)
1. [**개발 기간**](#8)

<br />

<div id="1"></div>

## 💁 웹 서비스 소개

**쉐어트립(ShareTrip)** 은 여러명이서 함께 여행경로를 공유할 수 있는 여행 서비스입니다.

쉐어트립은 시도/ 구군/ 여행 컨텐츠/ 키워드에 따라 관광지를 검색할 수 있으며 지도 상에서 바로바로 관광지를 확인할 수 있습니다.

특정 관광지를 선택하여 경로를 짠 뒤 맞팔한 사람들과 함께 경로를 공유할 수 있으며, 원하는 경우 공개적으로 다른 사람들과 함께 경로를 공유할 수 있습니다.

여행 경로 게시판은 공개 경로/ 그룹 경로로 나뉘어져 있어 선택에 따라 다른 사람의 여행 경로를 조회하거나 같은 그룹이 만든 경로를 확인할 수 있습니다.

관광지 참조, 좋아요, 북마크 등 여러 기준에 따라 인기있는 관광지 통계를 확인할 수 있습니다.

<br />

[**🔗 웹 서비스로 바로가기**](https://ssafymate.site) (서비스 종료)

<br />

<div id="2"></div>

## 👷 버전 기록

|   버전   | <div align="center">업데이트 내용</div> | 업데이트 날짜 |
| :------: | :-------------------------------------- | :-----------: |
| `v1.1.0` | - 쉐어트립 서비스 오픈                  |   23.12.24.   |

<br />

<div id="3"></div>

## 🛠 기술 스택

### **Front-end**

| <img src="https://profilinator.rishav.dev/skills-assets/html5-original-wordmark.svg" alt="HTML5" width="100px" height="100px" /> | <img src="https://profilinator.rishav.dev/skills-assets/css3-original-wordmark.svg" alt="CSS3" width="100px" height="100px" /> | <img src="https://profilinator.rishav.dev/skills-assets/javascript-original.svg" alt="JavaScript" width="100px" height="100px" /> |
| :------------------------------------------------------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------------------------------------------------: |
|                                                              HTML5                                                               |                                                              CSS3                                                              |                                                            JavaScript                                                             |

| <img src="https://cdn.vuetifyjs.com/docs/images/brand-kit/v-logo-atom.svg" alt="Vuetify3" width="100px" height="100px" /> | <img src="https://profilinator.rishav.dev/skills-assets/vuejs-original-wordmark.svg" alt="Vuejs" width="100px" height="100px" /> |
| :-----------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------: |
|                                                         Vuetify3                                                          |                                                              Vuejs                                                               |

### **Back-end**

| <img src="https://profilinator.rishav.dev/skills-assets/java-original-wordmark.svg" alt="Java" width="100px" height="100px" /> | <img src="https://www.seekpng.com/png/full/8-80775_spring-logo-png-transparent-spring-java.png" alt="Spring-Boot" width="100px" height="100px" /> | <img src="https://profilinator.rishav.dev/skills-assets/mysql-original-wordmark.svg" alt="MySQL" width="100px" height="100px" /> |
| :----------------------------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------------------------------: |
|                                                              Java                                                              |                                                                    Spring-Boot                                                                    |                                                              MySQL                                                               |

### **DevOps**

| <img src="https://profilinator.rishav.dev/skills-assets/git-scm-icon.svg" alt="Git" width="100px" height="100px" /> | <img src="https://pbs.twimg.com/profile_images/1351702967561252865/aXfcETIt_400x400.jpg" alt="aws" width="100px" height="100px" /> |
| :-----------------------------------------------------------------------------------------------------------------: | :--------------------------------------------------------------------------------------------------------------------------------: |
|                                                         Git                                                         |                                                                aws                                                                 |

<br />

<div id="4"></div>

## 💡 주요 기능

  <table >
    <tr>
      <th width="150">기능</th>
      <th>내용</th>
    </tr>
    <tr>
      <td>관광지 조회 </td>
      <td>시도/ 구군/ 여행 컨텐츠/ 키워드로 검색한 관광지를 지도 상에서 조회할 수 있습니다.</td>
    </tr>
    <tr>
      <td>관광지 상세 조회</td>
      <td>특정 관광지의 정보를 조회하거나 좋아요, 북마크를 클릭하여 댓글을 작성할 수 있습니다.</td>
    </tr>
     <tr>
      <td>여행 경로 공유</td>
      <td>특정 관광지들을 따로 저장하여 나만의 경로를 생성 및 공유할 수 있습니다. 또한 서로 맞팔한 사람을 초대하여 그룹 단위로 여행 경로를 공유할 수 있습니다. </td>
    </tr>
     <tr>
      <td>여행 전체 표시</td>
      <td>여행 경로를 저장할 때 공유버튼을 클릭하면 여행 경로 게시판에서 내가 만든 여행경로를 다른 사람들과 공유할 수 있습니다.</td>
    </tr>
     <tr>
      <td> 여행 경로 게시판(공개 경로)</td>
      <td> 공개 경로에서 다른 사람들이 공유한 여행경로를 확인할 수 있습니다.   </td>
    </tr>
     <tr>
      <td>여행 경로 게시판(그룹 경로)</td>
      <td>그룹 경로에서 그룹끼리 만든 여행경로를 확인할 수 있습니다.</td>
    </tr>
     <tr>
      <td> 관광지 통계 </td>
      <td>관광지 참조, 좋아요, 북마크, 경로 포함, 성별, 연령대별로 통계를 확인하여 인기있는 관광지 1,2,3등 확인할 수 있습니다.   </td>
    </tr>
  </table>

<br />

<div id="5"></div>

## 📂 프로젝트 구성도

|                              개체-관계 모델(ERD)                              |                                  클래스 다이어그램                                   |
| :---------------------------------------------------------------------------: | :----------------------------------------------------------------------------------: |
| <img src="./readme_assets/erd.png" alt="개체-관계 모델(ERD)" width="600px" /> | <img src="./readme_assets/classDiagram.jpg" alt="클래스 다이어그램" width="800px" /> |

<br />

<br />

<div id="6"></div>

## 🖥️ 서비스 화면

<div align="center">
  <h3>회원가입</h3>
  <img src="./readme_assets/회원가입.gif" alt="회원가입" />
</div>
<hr/>
<div align="center">
  <h3>검색</h3>
 <img src="./readme_assets/검색.gif" alt="검색" />
</div>
<hr/>
<div align="center">
  <h3>지도 위치기반 관광지조회</h3>
 <img src="./readme_assets/위치기반_관광지조회.gif" alt="위치기반_관광지조회" />
</div>
<hr/>
<div align="center">
  <h3>관광지 상세조회</h3>
 <img src="./readme_assets/관광지_상세조회.gif" alt="관광지_상세조회" />
</div>
<hr/>
<div align="center">
  <h3>경로 생성</h3>
 <img src="./readme_assets/경로생성.gif" alt="경로생성" />
</div>
<hr/>
<div align="center">
  <h3>그룹경로 조회 및 그룹원 추가</h3>
 <img src="./readme_assets/그룹경로_조회_그룹추가.gif" alt="그룹경로_조회_그룹추가" />
</div>
<hr/>
<div align="center">
  <h3>경로 가져오기 및 타이틀 수정</h3>
 <img src="./readme_assets/경로_가져오기_타이틀수정.gif" alt="경로_가져오기_타이틀수정" />
</div>
<hr/>
<div align="center">
  <h3>마이페이지 경로 조회 및 삭제</h3>
 <img src="./readme_assets/마이페이지_경로조회_삭제.gif" alt="마이페이지_경로조회_삭제" />
</div>
<hr/>
<div align="center">
  <h3>즐겨찾기한 관광지조회</h3>
 <img src="./readme_assets/즐겨찾기_관광지조회.gif" alt="즐겨찾기_관광지조회" />
</div>
<hr/>
<div align="center">
  <h3>팔로우</h3>
 <img src="./readme_assets/팔로우.gif" alt="팔로우" />
</div>
<hr/>

<br />

<br />

<div id="7"></div>

## 👪 개발 팀 소개

<table align="center">
  <tr>
    <td align="center" width="150px">
      <a href="https://github.com/ChaNyeok1225" target="_blank">
        <img src="https://avatars.githubusercontent.com/ChaNyeok1225" alt="전찬혁 프로필" />
      </a>
    </td>
    <td align="center" width="150px">
      <a href="https://github.com/Ryujy" target="_blank">
        <img src="https://avatars.githubusercontent.com/Ryujy" alt="류지윤 프로필" />
      </a>
    </td>
    <td align="center" width="150px">
      <a href="https://github.com/person003333" target="_blank">
        <img src="https://avatars.githubusercontent.com/eunalove" alt="오은아 프로필" />
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">
      <a href="https://github.com/ChaNyeok1225" target="_blank">
        전찬혁<br />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/Ryujy" target="_blank">
        류지윤<br />
      </a>
    </td>
    <td align="center">
      <a href="https://github.com/eunalove" target="_blank">
        오은아<br />
      </a>
    </td>
  </tr>
</table>

<br />

<br />

<div id="8"></div>

## 📅 개발 기간

23.11.16. ~ 23.11.24

<br />

<br />

<div id='11'></div>
