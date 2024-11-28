# API

| API | 설명 | DB | 이벤트 |
|-----|------|----|--------|
|[회원수 변화 조회](#회원수-변화-조회)|모임 회원수 변화 조회|||
|[활동수 변화 조회](#활동수-변화-조회)|모임 활동수 변화 조회|||
|[예산 변화 조회](#예산-변화-조회)|모임 예산 변화 조회|||
|[참가율 변화 조회](#참가율-변화-조회)|회원 참가율 변화 조회|||
|[납부율 변화 조회](#납부율-변화-조회)|회원 납부율 변화 조회|||


## ▶회원수 변화 조회 
### GET /data/club/{clubId}/member?period={시간 간격}&pageNo={페이지 번호}

- period은 선택사항이며, 포함하지 않을시 1개월 기준으로 조회 (1~12)

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
### GET /data/club/{clubId}/activity?period={시간 간격}&pageNo={페이지 번호}

- period은 선택사항이며, 포함하지 않을시 1개월 기준으로 조회 (1~12)

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
      trend: 완료 활동 수, (Integer)
      actCloseCount: 완료 활동 수, (Integer)
      actCancelCount: 취소 활동 수, (Integer)
    },
    ...
  ]
}
```


## ▶예산 변화 조회 
### GET /data/club/{clubId}/budget?period={시간 간격}&pageNo={페이지 번호}

- period은 선택사항이며, 포함하지 않을시 1개월 기준으로 조회 (1~12)

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
      trend: 예산 변화, (Integer)
      income: 총 수입, (Integer)
      expense: 총 지출, (Integer)
    },
    ...
  ]
}
```


## ▶참가율 변화 조회 
### GET /data/member/{memberId}/participant?period={시간 간격}&pageNo={페이지 번호}

- period은 선택사항이며, 포함하지 않을시 1개월 기준으로 조회 (1~12)

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
      period: 조회 기준 기간, (Integer)
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
### GET /data/member/{memberId}/payMember?period={시간 간격}&pageNo={페이지 번호}

- period은 선택사항이며, 포함하지 않을시 1개월 기준으로 조회 (1~12)

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
      period: 조회 기준 기간, (Integer)
      countTrend: 납부율(횟수), (Double)
      amountTrend: 납부율(액수), (Double)
      payCount: 참가 수, (Integer)
      unPayCount: 불참 수, (Integer)
      latePayCount: 추가 참가 수, (Integer)
      payAmount: 추가 참가 수, (Integer)
      unPayAmount: 추가 참가 수, (Integer)
      latePayAmount: 추가 불참 수, (Integer)
    },
    ...
  ]
}
```
