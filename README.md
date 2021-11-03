# spring-boot-with-docker
스프링 애플리케이션을 도커로 실행하기

### springBoot application 을 도커 이미지로 빌드 후 docker-compose 로 서비스 하기 

- springBoot 2.3 부터 gradle task 로 도커 이미지를 만들 수 있다. 
- 프로젝트 최상위 경로에서 실행 
  ~~~ shell
  ./gradlew bootBuildImage
  ~~~
- docker-compose 로 여러 컨테이너를 기동시에 db 가 기동되기 전에 app 이 기동되어 connection 이 되지 않는다..
- dockerize 로 db 가 기동전까지 hang 을 걸고 싶은데 gradle task 로 만든 이미지를 커스텀하게 쓸 수 없어서 Dockerfile 을 따로 만들었다. 
- docker image build
  ~~~ shell
  .\gradlew.bat clean build
  docker build -t app-admin admin/.
  docker build -t app-user user/.
  ~~~
- docker-compose-dev 실행
  ~~~ shell
  docker-compose -f docker-compose-dev.yml up -d
  ~~~
- docker-compose 종료
- ~~~ shell
  docker-compose -f docker-compose-dev.yml down
  ~~~