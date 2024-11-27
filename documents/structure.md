# 1. 패키지
```
band_data
    ├─data
    │  ├─club
    │  │  └─form
    │  └─member
    │      └─form
    ├─event
    │  ├─activity
    │  ├─budget
    │  └─club
    ├─external
    │  └─kafka
    └─simulation
```


# 2. 도메인

## 2-1. ClubData

| 도메인 | 속성 | 타입 | 설명 | 비고 |  
|--------|------|------|------|------|
|ClubData|      |      |      |      |
|        |id|Long|Club Data Id|Primary Key|
|        |clubId|Long|Club Id|Club 추적키, NotNull|
|        |date|Integer|기준년월| NotNull|
|        |memberRegisterCount|Integer|회원 등록 카운터||
|        |memberLeftCount|Integer|회원 탈퇴 카운터||
|        |memberBanCount|Integer|회원 강퇴 카운터||
|        |actCloseCount|Integer|활동 완료 카운터||
|        |actCancelCount|Integer|활동 취소 카운터||
|        |income|Integer|총 수입||
|        |expense|Integer|총 지출||

## 2-2. MemberData

| 도메인 | 속성 | 타입 | 설명 | 비고 |  
|--------|------|------|------|------|
|MemberData|      |      |      |      |
|        |id|Long|Member Data Id|Primary Key|
|        |clubData|ClubData|연관 Club 데이터|Foreginer Key, NotNull|
|        |memberId|Long|Member Id|Member 추적키, NotNull|
|        |date|Integer|기준년월| NotNull|
|        |actAttendCount|Integer|활동 참가 카운터||
|        |actLateAttendCount|Integer|활동 추가 참가 카운터||
|        |actLateNotAttendCount|Integer|활동 추가 불참 카운터||
|        |payCount|Integer|회비 납부 카운터||
|        |unPayCount|Integer|회비 미납 카운터||
|        |latePayCount|Integer|회비 추가 납부 카운터||
|        |payAmount|Integer|총 납부액||
|        |unPayAmount|Integer|총 미납앱||
|        |latePayAmount|Integer|총 추가납부액||


# 3. 주요 컴포넌트

| 컴포넌트 | 설명 | 비고 |  
|----------|------|------|
|KafkaConsumerService|kafka 메시지 소비용 컴포넌트||
|ClubDataController|ClubData 관련 엔드포인트||
|ClubDataService|ClubDatay 관련 비즈니스 로직 수행||
|ClubDataStore|ClubData 관련 DB 접근||
|MemberDataController|MemberData 관련 엔드포인트||
|MemberDataService|MemberData 관련 비즈니스 로직 수행||
|MemberDataStore|MemberData 관련 DB 접근||


# 4. ERD
![xx](https://github.com/user-attachments/assets/3613db86-3d2c-4a66-8410-aa48437b70f0)
