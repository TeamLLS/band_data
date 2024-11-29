# API

| API | 설명 |
|-----|------|
|[회원 등록](#회원-등록)|Club 내 회원 등록|
|[활동 생성](#활동-생성)|Club 내 활동 생성|
|[참가자 등록](#참가자-등록)|Activity 참가자 등록|
|[장부 생성](#장부-생성)|Club 내 장부 생성|
|[참가자 등록](#참가자-등록)|PayBook 납부자 등록|



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


## ▶참가자 등록
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



