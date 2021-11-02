# spring-boot-with-docker
스프링 애플리케이션을 도커로 실행하기

### springBoot application 을 도커 이미지로 빌드 후 docker-compose 로 서비스 하기 

- springBoot 2.3 부터 gradle task 로 도커 이미지를 만들 수 있다. 
- 프로젝트 최상위 경로 해서 실행 
  ~~~ shell
  ./gradlew bootBuildImage
  ~~~
