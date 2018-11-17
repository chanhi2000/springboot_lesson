## Day01

교육참조 사이트:  [https://cafe.naver.com/kndjang][https://cafe.naver.com/kndjang]
jamesol@paran.com


### 프로젝트에 사용 될 기초 구성요소
- JAVA
	- JAVA_HOME : 
	echo %JAVA_HOME% 없으면 환경 변수 적용
	setx 
	- PATH: 
	echo %PATH%
	append ;%JAVA_HOME%\bin

- SPRING BOOT CLI
	- SPRING_HOME :
	echo %SPRING_HOME% 없으면 환경 변수 적용
	- PATH:
	appedn %SPRING_HOME%\bin

- Maven
	- MAVEN_HOME :
	echo %MAVEN_HOME% 없으면 환경 변수 적용
	- PATH:
	appedn %MAVEN_HOME%\bin	

## Spring Boot 프로젝트 구성
```cmd 
D:\....\> spring init --build gradle myapp
D:\....\> spring init -dweb,data-jpa,h2,thymeleaf, --build maven myapp --force
```

## Maven을 이용한 Spring Boot App ㄱ컴파일 & 실행
```cmd
D:\....\> mvn spring-boot:run
```
or
```cmd
D:\....\...\com\example\myapp\>spring run *.java
```

## Spring 기본정리
- OOP: 
- TEST: 
	- Junit
	- 


## Oracle Database Tabel 생성
- TableName: `User`

|column|type|설정|
|:-----|:---|:---|
|u_id|`varchar2(10)`|`pk`|
|name|`varchar2(10)`|`not null`|
|password|`varchar2(10)`|`not null`|


## iOC: 	제어에 역전
## DI:		의존관계의 주입
## AOP(Aspect Oriented Programming):		관점지향 프로그램
 - 기존 소스에 수정 없이 부가기능을 추가.
 - 컴포넌트 개발에 가장 중요한 두 가지 원칙
 	- 낮은 결합도
 	- 높은 응집도


Loggging: crossscutting
business Logic: Core Concern
excpetion: crossscutting
transaction: crossscutting


#### JoinPoint
클ㄹ라이언트가 호출하는 모든 비즈니스 메소드, *Impl클래스의 모든 메소드

#### PointCut
필터링 된 조인포인트를 의미
트랜잭션 처리하는 공통 기능이 있으면 등록/수정/삭제 기능에는 동작을 해야 한다.
단 조회 기능에는 동작할 필요가 없다.

|리턴타입|패키지 경로|클래스명|메소드|
|:-----:|:---------|:-------|:----|
| * | com.sist.aop.advice.. | *Impl | *(..) |
| * | com.sist.aop.advice.. | *Impl | *Tx(..) |

#### Advice
어드바이스는 횡단 관심에 해당하는 공통 기능코드

|에노테이션|역할|
|:-------:|:--:|
| Before | 비즈니스 메소드 실행전 |
| After | 비즈니스 메소드 실행후 무조건 실행 (try~cdatch~finally) finally에 해당
| After_returning | 비즈니스 메소드 성공적으로 리턴되면 동작 |
| After_throwing | 비즈니스 메소드 실행중 예외가 발생하면 동작 |
| around | 메소드 호출 자체를 가로채 비즈니스 메소드 실행 전후에 처리할 로직 | 

#### Weaving
포인트컷으로 지정한 핵심 관심 메소드가 호출 될 때, 어드바이스에 해당하는 횡단 메소드가 삽이되는 과정.


#### Aspect / Advisor
PointCut 과 Advice 의 결합

#### `*` 포인트컷 표현식: 
>> 예 : `* com.sist.hr..*Dao.*(..)

####### 1. 리턴타입

| 표현식 | 설명 |
|:-----:|:-----|
| `*` | 모든 리턴타입 |
| `void` | 리턴타입이 void인 메소드 선택 |
| `!void`| 리턴타입이 void가 아닌 메소드 선택 | 


###### 2. 페키지 지정
| 표현식 | 설명 |
|:-----:|:-----|
| `com.sist.hr` | `com.sist.hr` 만 선택 |
| `com.sist.hr..` | `com.sist.hr`패키지로 시작하는 모든  패키지 | 
| `com.sis.hr..Impl` | `com.sis.hr`패키지로 시작하고 마지막 패키지 이름이 Impl로 끝나느 패키지 

###### 3. 클래스
| 표현식 | 설명 |
|:-----:|:-----|
| `BoardServiceImpl` | `BoardServiceImpl`만 선택 |
| `*Impl` | 클래스 명칭이 `Impl`로 끝나는 클래스 |
| `BoardService+` | 해당 클래스로 부터 파생된 모든 자식 클래스 |


###### 4. 메소드
| 표현식 | 설명 |
|:-----:|:-----|
| `*(..)` | 모든 메소드 |
| `*Tx(..)` | 명칭이 `Tx`로 끝나는 모든 메소드 선택  |


###### 5. 매개 변수
| 표현식 | 설명 |
|:-----:|:-----|
| `(..)` | 모든 매개변수 |
| `(*)` | 1개의 매개변수를 가지는 |
| `(com.sist.hr.UserVO)` | `UserVO`만 가지고 있는 |  
| `(!com.sist.hr.UserVO)` | `UserVO`가 아닌 |  



## 태이블 속성 수정
|column|type|설정|
|:-----|:---|:---|
|u_id|`varchar2(10)`|`pk`|
|name|`varchar2(10)`|`not null`|
|password|`varchar2(10)`|`not null`|
|h_Level|`number(2)`|`not null`|
|login|`number(7)`|`not null`|
|recommend|`number(7)`|`not null`|
|email|`varchar2(200)`|`not null`|
|reg_dt|`date`|`not null`|

>> 사용자의 레벨: 
>> - Basic:  사용자 처음 로그인 하면 
>> - Silver: 가입 후 로그인 횟수 50번 이상
>> - Gold: 	Silver에서 추천을 30회 이상 받으면



