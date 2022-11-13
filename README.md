# 현재 배포된 주소
- 146.56.151.151

# 게시판 기능 설명
- 유저 회원가입, 회원정보 수정
- 게시글 생성, 수정, 조회, 삭제
- 댓글 생성, 삭제

# 강조하고 싶은 내용
- 도커와, 젠킨스를 이용한 CI/CD
- controller, service 유닛 테스트 Coverage 100%
- JPA의 lazy loading session을 고려한 DTO 사용
- Spring의 DI를 이용해 느슨한 결합을 가지도록 코드 작성
- try, catch가 필요한 예외처리를 AOP를 이용해 비지니스 로직을 깨끗하게 유지
- Spring Security의 세션을 이용한 로그인 처리

# Folder structure
- config: Spring Security를 위한 설정파일이 있는 패키지
- controller: 컨트롤러 클래스를 관리함
- service: 서비스 클래스를 관리함
- dto: dto 클래스를 관리함
- handler: Exception을 처리하는 클래스를 관리함
- model: entity들을 관리함
- repository: JPA repository를 관리함

# Backend architecture
- 클래스들 관계도(Security 포함)
![스크린샷, 2022-11-13 14-23-13](https://user-images.githubusercontent.com/47857304/201508203-42717222-38eb-43a7-a188-3e704c4bfad5.png)
- DB 스키마
  ![스크린샷, 2022-11-13 14-55-38](https://user-images.githubusercontent.com/47857304/201508232-4a8c9d4b-ca7c-4157-aeb2-056220c38509.png)