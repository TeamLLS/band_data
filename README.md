# API

| API | 설명 |
|-----|------|
|[클럽 생성](#클럽-생성)|내 클럽 생성|
|[클럽 정보 조회](#클럽-정보-조회)|
|[회원 등록](#회원-등록)|Club 내 회원 등록|
|[클럽 회원 조회](#클럽-회원-조회)|
|[활동 생성](#활동-생성)|Club 내 활동 생성|
|[활동 조회](#활동-조회)|대상 활동 정보 반환|
|[참가자 등록](#참가자-등록)|Activity 참가자 등록|
|[참가자 조회](#참가자-조회)|대상 활동의 참가자 조회|
|[장부 생성](#장부-생성)|Club 내 장부 생성|
|[장부 조회](#장부-조회)|해당 장부 조회|  
|[납부자 등록](#납부자-등록)|PayBook 납부자 등록|
|[납부 대상 목록 조회](#납부-대상-목록-조회)|해당 장부의 납부 대상 목록 조회|



## ▶클럽 생성
### POST /club
```
header: {  
  Authorization: Bearer ${accessToken value},
}

form-data: {
  name: 모임 이름, (String)
  description: 모임 설명, (String)
  image: 모임 이미지, (MulitPartFile)
  contactInfo: 컨택 정보 (String)
} 
```

### 응답
```
body: {  
  clubId: 생성 클럽 ID (Long)
}
```


## ▶클럽 정보 조회
### GET /club/{club Id}
```
header: {  
  Authorization: Bearer ${accessToken value},
}
```

### 응답
```
body: {
  clubId: 클럽 ID, (Long)
  name: 클럽 이름, (String)
  description: 클럽 설명, (String)
   image: 이미지 리소스 url,
   contactInfo: 컨택 정보, (String)
   status: 클럽 상태, (String)
   memberNum: 회원 수 (Integer)
}
```



## ▶회원 등록
### POST /club/dummy/member
```
haeder: {
  * username: Club 생성시 사용했던 username (String) 
}

body: {
  * clubId: 생성한 Club ID, (Long) 
  username: 회원 구분자, (String)
  name: 회원 이름, (Stirng)
  role: 회원 역할, (String, MANAGER or REGULAR) 
  birthYear: 생년월일, (Integer)
  gender: 성별 (String, male or female)
} 
```

### 응답
```
body: {  
  id: 생성된 Member Id (Long)
}
```

## ▶클럽 회원 조회
### GET /member/{클럽 ID}/list?pageNo={페이지 번호}
```
header: {  
  Authorization: Bearer ${accessToken value},
}
```

### 응답
```
body: {
    list: [
        {
            memberId: 회원 ID, (Long)
            clubId: 클럽 ID, (Long)
            username: username, (String)
            roleName: 회원 권한, (String)
            name: 회원 이름, (Stirng)
            age: 나이, (Integer)
            gender: 성별, (String)
            statusName: 회원 상태 (String)
        },
        ...
    ]
}
```

## ▶활동 생성
### POST /activity/dummy
```
body: {
  * clubId: 생성한 Club ID, (Long)
  * username: Club 생성시 사용했던 username, (String)
  name: 활동 이름, (Stirng)
  location: 활동 장소 (String) 
  description: 활동 상세, (String)
  startTime: 활동 시작 시간, (Instnat, ISO 8601)
  endTime: 활동 종료 시간, (Instnat, ISO 8601)
  deadline: 참가자 모집 마감 시간, (Instnat, ISO 8601)
  time: 해당 활동 생성 시간 (Instnat, ISO 8601)  -> 기준 시간1
} 
```

### 응답
```
body: {  
  id: 생성된 Activity Id (Long)
}
```

## ▶활동 조회
### Get /activity/{activityId}
```
header: {  
  Authorization: Bearer ${accessToken value}
}
```

### 응답
```
body: {
  id: Activity Id, (Long)
  clubId: Club Id, (Long)
  name: 활동 이름,  (String)
  image: 이미지 url,  (String)
  location: 활동 장소, (String)
  description: 설명, (String)
  startTime: 시작 시간, (Instnat, ISO 8601)
  endTime: 종료 시간, (Instnat, ISO 8601)
  deadline: 모집 종료 시간  (Instnat, ISO 8601)
  status: 상태,  (String)
  participantNum: 참가자 수 (Integer)
}
```

## ▶참가자 등록
### POST /activity/dummy/participant
```
body: {
  * clubId: 생성한 Club ID, (Long)
  * username: Club 생성시 사용했던 username, (String)
  * activityId: 생성한 Activity ID, (Long)
  * memberId: 해당 Member ID, (Long)
  * memberName: 해당 Member 이름, (Stirng)
  * profileName: 해당 Member username, (String)
  status: 참가 상태 (String, ATTEND or NOT_ATTEND or ADDITIONAL_ATTEND or ADDITIONAL_NOT_ATTEND) 
  time: 신청 시간, (Instnat, ISO 8601)
} 
```

### 응답
```
```
## ▶참가자 조회
### Get /participant/{activityId}/list?pageNo={page 번호}
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
      id: Participant Id, (Long)
      activityId: Activity Id, (Long)
      username: username,  (String)
      memberId: Member Id, (Long)
      memberName: Member 이름, (String)
      status: 참가 상태 (String)
      changedAt: 신청 시간 (Instnat, ISO 8601)
    },
    ...
  ],
  attend: 내 참가 여부 (Boolean)
}
```


## ▶장부 생성
### POST /budget/dummy/paybook
```
body: {
  * clubId: 생성한 Club ID, (Long)
  * username: Club 생성시 사용했던 username, (String)
  * memberName: Club 생성시 사용했던 User의 이름, (String) 
  name: 장부 이름, (Stirng)
  description: 장부 설명, (String)
  deadline: 납부 마감 시간, (Instnat, ISO 8601)
  time: 해당 장부 생성 시간 (Instnat, ISO 8601)  -> 기준 시간2
} 
```

### 응답
```
body: {  
  id: 생성된 PayBook Id (Long)
}
```

## ▶장부 조회
### GET /paybook/{장부 Id}

```
header: {  
  Authorization: Bearer ${accessToken value},
} 
```

### 응답
```
body: {
  id: 장부 id, (Long)
  clubId: 클럽 Id, (Long)
  createdBy: 생성자 이름, (String)
  name: 이름, (String)
  description: 설명, (String)
  status: 상태, (String)
  amount: 납부 금액, (Integer)
  createdAt: 생성 시간, (Instant)
  closedAt: 만료 시간, (Instant)
  deadline: 모금 종료 시간 (Instnat, ISO 8601)
}
```



## ▶납부자 등록
### POST /budget/dummy/paymember
```
body: {
  * clubId: 생성한 Club ID, (Long)
  * username: Club 생성시 사용했던 username, (String)
  * payBookId: 생성한 PayBook ID, (Long)
  * memberId: 해당 Member ID, (Long)
  * memberName: 해당 Member 이름, (Stirng)
  * profileName: 해당 Member username, (String)
  status: 납부 상태 (String, PAID or UNPAID or LATE_PAID) 
  time: 납부 시간, (Instnat, ISO 8601)
} 
```

### 응답
```
```

## ▶납부 대상 목록 조회
### GET /paymember/{장부 Id}/list?unPay={true|false}&pageNo={페이지 번호}

unPay=true일 경우, 미납자만 조회 (포함하지 않을 경우 자동 false)

```
header: {  
  Authorization: Bearer ${accessToken value},
} 
```

### 응답
```
body: {
  list: [
    {
      id: 납부 대상 Id, (Long)
      payBookId: 장부 Id, (Long)
      username: username, (String)
      memberId: 회원 Id, (Long)
      memberName: 회원 이름, (String)
      status: 상태, (String)
      paidAt: 납부 일자 (Instnat, ISO 8601)
    },
    ...
  ]
}
```


