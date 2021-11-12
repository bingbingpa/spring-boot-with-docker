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
  
### flyway docker 적용해보기

- sql 스크립트 작성
  - V를 prefix 로 붙이면 버전관리 대상 스크립트이다. migration 이 된 후에 해당 버전보다 낮은 버전의 스크립트가 추가 되어도 실행되지 않는다.
  - R를 prefix 로 붙이면 migration 이 실행될 때 중복 실행될 수 있는 스크립트이다. 
    - V를 붙인 스크립트는 내용을 수정하면 checksum 이 다르다는 오류가 발생하지만,
    R를 붙인 스크립트는 내용이 바뀌지 않으면 다시 실행되지 않지만, 내용이 바뀌면 다시 실행된다.
- conf 파일 작성하기
  - db 접속정보와 sql 스크립트 위치를 적어준다. sql 스크립트 위치는 여러개 지정 할 수 있다.
  - 여기서는 버전관리 대상 스크립트와 seed 대상 스크립트를 분리하였다.
- docker-compose
  - migration 대상 스크립트 경로와 conf 파일 위치를 volume 으로 잡아주고 실행!