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
|------|----|----|
|u_id|`varchar2(10)`|`pk`|
|name|`varchar2(10)`|`not null`|
|password|`varchar2(10)`|`not null`|


## iOC: 	제어에 역전
## DI:		의존관계의 주입
## AOP:		관점지향 프로그램