# CS496_week2

### 제목: 

---

### 팀원 : 김덕현, 권효재 

---

# API 목록 :

### 로그인

**주소**

```json
<http://192.249.18.118/login/>
```

**param**

1. Uid → 유저 아이디/ 코드에서 받아오기
2. Upw →  유저 비번/ 임의 설정 가능
3. Uname →  유저/이름
4. Usay → 유저/한줄소개
5. Utype → 유저/ 기술 직군 1.FE // [2.BE](http://2.BE) //  3.FS
6. github → 깃허브 뒤에 아이디
7. lang1 → 첫번쨰로 잘하는 언어
8. lang2 → 두번쨰로 잘하는 언어
9. lang3 → 세번쨰로 잘하는 언어

### Tab1

1. 전체 사용자 불러오는 api(get)

**주소**

```python
<http://192.249.18.118/user/>
```

**result**

```json
[
		{
    "Uid": "exampl111@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 1,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  },
  {
    "Uid": "exampl111@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 1,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  },
  {
    "Uid": "exampl111@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 1,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  }
]
```

1. 특정 사용자 detail 불러오는 api(get)

param → 사용자 id

**주소**

```python
<http://192.249.18.118/user/khj010909>
#이때 khj010909는 사용자의 id이다. 
#즉, 사용자의 id를 가장 마지막에 넣어 url을 만들면 된다. 
```

**result**

```json
[
  {
    "Uid": "khj010909",
    "Upw": "1111",
    "Uname": "Hyojae",
    "Usay": "Hi",
    "Utype": 1,
    "Uideaid": 1,
    "Ustack": "Android studio",
    "github": "hyojaeKwon",
    "Lid": "khj010909",
    "Llang1": "Kotlin",
    "Llang2": "Java",
    "Llang3": "C++"
  }
]
```

1. 같이하기 요청 api(post)

//추가예정

### Tab2

1. idea 게시글 불러오는 api(get)

   **주소**

   ```python
   <http://192.249.18.118/ideas/>
   ```

   **result**

   ```json
   [
     { //Iid는 게시글 id
       "Iid": 61,
       "Ititle": "게시글 제목",
       "Idescription": "게시글 설명",
       "IauthorId": "khj010909"
     },
     {
       "Iid": 60,
       "Ititle": "게시글 제목",
       "Idescription": "게시글 설명",
       "IauthorId": "khj010909"
     },
     {
       "Iid": 59,
       "Ititle": "게시글 제목",
       "Idescription": "게시글 설명",
       "IauthorId": "khj010909"
     }
   ]
   ```

2. idea detail 불러오는 api(get)

   param → 게시글 id

   **주소**

   ```python
   <http://192.249.18.118/ideas/2>
   # 맨 마지막 2는 게시글 아이디입니다.
   ```

   **result**

   ```json
   [
     [
       {
         "Iid": 2,
         "Ititle": "아이디어 재미져2",
         "Idescription": "아이디어 설명2",
         "IauthorId": "khj01090909"
       }
     ],
     [
       {
         "PUid": null
       },
       {
         "PUid": "khj010909"
       },
       {
         "PUid": "khj010909"
       },
       {
         "PUid": "khj010909"
       }
     ],
   	[
       {
         "ISid": 45,
         "IStack": "Java"
       },
       {
         "ISid": 45,
         "IStack": "Python"
       }
     ]
   ]
   ```

3. idea 게시글 등록 api (post)

   param →

   1. key : title → value : “게시글 제목”
   2. key : description → value : “게시글 설명”
   3. key : id→ value : “게시자 id”
   4. key : frontEnd→ value : “사용할 프론트 기술스택”
   5. key : backEnd→ value : “사용할 백 기술스택”
   6. key : etc → value :기타 기술스택

   **주소**

   ```python
   <http://192.249.18.118/ideas/create>
   ```

4. 같이할 사람 신청 api(post)

   param →

   1. key: Iid → value : “게시글 id”
   2. key: participant → value : “같이하자고 신청한 사람 id”

   **주소**

   ```python
   <http://192.249.18.118/ideas/participant>
   ```

### Tab 3

1. 팀원 매칭 api (get)

```json
<http://192.249.18.118/match/khj010909>
#뒤는 user id 입력하는 곳
[
  {
    "Uid": "exampl111@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 1,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  },
  {
    "Uid": "exampl11@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 1,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  },
  {
    "Uid": "exampl1@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 1,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  },
  {
    "Uid": "exampleD2@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 3,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  },
  {
    "Uid": "exampleD@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 3,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  },
  {
    "Uid": "exampleID@naver.com",
    "Uname": "exampleName",
    "Usay": "let's do it well",
    "Utype": 2,
    "github": "example",
    "Llang1": "Java",
    "Llang2": "Python",
    "Llang3": "exc"
  },
  {
    "Uid": "khj010909",
    "Uname": "Hyojae",
    "Usay": "Hi",
    "Utype": 1,
    "github": "hyojaeKwon",
    "Llang1": "Kotlin",
    "Llang2": "Java",
    "Llang3": "C++"
  },
  {
    "Uid": "khj01090909",
    "Uname": "Hyo",
    "Usay": "Hi",
    "Utype": 1,
    "github": "hyojaeKwon",
    "Llang1": "C",
    "Llang2": "Python",
    "Llang3": "C++"
  }
]
```

## 같이 하자고 한 팀원 api(get)

**주소**

```json
<http://192.249.18.118/who-pick/khj010909> #맨 마지막은 id 입력
```

**result**

```json
[
  {
    "PFromid": "example@naver.com"
  },
  {
    "PFromid": "example@naver.com"
  }
]
```

팀원 신청 api (post)

**주소**

```json
<http://192.249.18.118/pick/>
```

param →

1. key: fromId→ value : “같이 하자고 신청한 사람 id”
2. key: toId→ value : “같이할 사람 id”