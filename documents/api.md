# API

| API | 설명 | DB | 이벤트 |
|-----|------|----|--------|
|[회원수 변화 조회](#회원수-변화-조회)|모임 회원수 변화 조회|||
|[활동수 변화 조회](#활동수-변화-조회)|모임 활동수 변화 조회|||
|[예산 변화 조회](#예산-변화-조회)|모임 예산 변화 조회|||
|[참가율 변화 조회](#참가율-변화-조회)|회원 참가율 변화 조회|||
|[납부율 변화 조회](#납부율-변화-조회)|회원 납부율 변화 조회|||
|[회원 순위 조회](#회원-순위-조회)|최근 회원 점수 순위 조회|||
|[회원 점수 조회](#회원-점수-조회)|최근 회원 점수 상세 조회|||


## ▶회원수 변화 조회 
### GET /data/club/{clubId}/member?fromTime={시작 날짜}

- fromTime 포함 안할시 6개월 이전 부터 조회

```
header: {  
  Authorization: Bearer ${accessToken value}
}
```

### 응답
```
body: {
  list: [
    {
      clubId: Club Id, (Long)
      year: 기준 년, (Integer)
      month: 기준 월, (Integer)
      period: 조회 기준 기간, (Integer)
      trend: 멤버 수 변화, (Integer)
      memberRegisterCount: 가입자 수, (Integer)
      memberLeftCount: 탈퇴자 수, (Integer)
      memberBanCount: 강퇴 수, (Integer)
    },
    ...
  ]
}
```



## ▶활동수 변화 조회 
### GET /data/club/{clubId}/activity?fromTime={시작 날짜}

- fromTime 포함 안할시 6개월 이전 부터 조회

```
header: {  
  Authorization: Bearer ${accessToken value}
}
```

### 응답
```
body: {
  list: [
    {
      clubId: Club Id, (Long)
      year: 기준 년, (Integer)
      month: 기준 월, (Integer)
      trend: 완료 활동 수, (Integer)
      actCloseCount: 완료 활동 수, (Integer)
      actCancelCount: 취소 활동 수, (Integer)
    },
    ...
  ]
}
```


## ▶예산 변화 조회 
### GET /data/club/{clubId}/budget?fromTime={시작 날짜}

- fromTime 포함 안할시 6개월 이전 부터 조회

```
header: {  
  Authorization: Bearer ${accessToken value}
}
```

### 응답
```
body: {
  list: [
    {
      clubId: Club Id, (Long)
      year: 기준 년, (Integer)
      month: 기준 월, (Integer)
      trend: 예산 변화, (Integer)
      income: 총 수입, (Integer)
      expense: 총 지출, (Integer)
    },
    ...
  ]
}
```


## ▶참가율 변화 조회 
### GET /data/member/{clubId}/{memberId}/participant?fromTime={시작 날짜}

- fromTime 포함 안할시 6개월 이전 부터 조회

```
header: {  
  Authorization: Bearer ${accessToken value}
}
```

### 응답
```
body: {
  list: [
    {
      clubId: Club Id, (Long)
      memberid: Member Id, (Long)
      year: 기준 년, (Integer)
      month: 기준 월, (Integer)
      trend: 참가율, (Double)
      attendCount: 참가 수, (Integer)
      notAttendCount: 불참 수, (Integer)
      lateAttendCount: 추가 참가 수, (Integer)
      lateNotAttendCount: 추가 불참 수, (Integer)
    },
    ...
  ]
}
```


## ▶납부율 변화 조회 
### GET /data/member/{clubId}/{memberId}/payMember?fromTime={시작 날짜}}

- fromTime 포함 안할시 6개월 이전 부터 조회

```
header: {  
  Authorization: Bearer ${accessToken value}
}
```

### 응답
```
body: {
  list: [
    {
      clubId: Club Id, (Long)
      memberid: Member Id, (Long)
      year: 기준 년, (Integer)
      month: 기준 월, (Integer)
      trend: 납부율(횟수), (Double)
      payCount: 납부 수, (Integer)
      unPayCount: 미납 수, (Integer)
      latePayCount: 추가 납부 수, (Integer)
      payAmount: 납부액, (Integer)
      unPayAmount: 미납액, (Integer)
      latePayAmount: 추가 납부액, (Integer)
```


## ▶회원 순위 조회 
### GET /data/member/{clubId}/rank?option={옵션 번호}

- 옵션 번호 1: 상위 회원 3명 조회
- 옵션 번호 2: 평균 점수 * 0.3 미만 회원 조회
- 번호 없음: 전체 회원 조회

```
header: {  
  Authorization: Bearer ${accessToken value}
}
```

### 응답

- 3개월 기준 합산 점수 

```
body: {
  list: [
    {
      clubId: Club Id, (Long)
      memberid: Member Id, (Long)
      username: useranme, (String)
      membername: Member 이름, (String)
      role: Member 역할, (String)
      point: 합산 점수, (Integer)
    },
    ...
  ]
}
```


## ▶회원 점수 조회 
### GET /data/member/{clubId}/{memberId}/score

```
header: {  
  Authorization: Bearer ${accessToken value}
}
```

### 응답

- (합산 점수, 참가율, 납부율, 납부액: 3개월 기준) , (unPaidTotal, lastAttendTime, lastPayTime: 전체 기간 기준)
  
```
body: {
  clubId: Club Id, (Long)
  memberid: Member Id, (Long)
  point: 합산 점수, (Integer)
  attendRate: 참가율, (Double)
  payRate: 납부율 (횟수), (Double)
  payAmount: 납부액, (Integer)
  unPaidTotal: 총 미납액, (Integer)
  lastAttendTime: 마지막 활동 참가 시간, (Instnat, ISO 8601)
  lastPayTime: 마지막 회비 납부 시간 (Instnat, ISO 8601)
}
```


