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

#### Weaving
포인트컷으로 지정한 핵심 관심 메소드가 호출 될 때, 어드바이스에 해당하는 횡단 메소드가 삽이되는 과정.


#### Aspect / Advisor
PointCut 과 Advice 의 결합

