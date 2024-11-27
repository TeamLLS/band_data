# API

| API | 설명 | DB | 이벤트 |
|-----|------|----|--------|
|[회원수 변화 조회](#회원수-변화-조회)||||
|[](#)||||
|[](#)||||
|[](#)||||
|[](#)||||


## ▶회원수 변화 조회 
### POST /data/club/{clubId}/member?period={시간 간격}&pageNo={페이지 번호}

- period은 선택사항이며, 포함하지 않을시 1개월 기준으로 조회 

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
      trend: 멤버수 변화, (Integer)
      memberRegisterCount: 가입자 수, (Integer)
      memberLeftCount: 탈퇴자 수, (Integer)
      memberBanCount: 강퇴 수, (Integer)
    },
    ...
  ]
}
```
