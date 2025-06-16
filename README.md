## 프로젝트 목표1
사용자가 게시물을 조회하는 시점에, 팔로우하는 사람의 게시물을 조회하는 건 합리적인 생각이다. 이 전략은 팔로우하는 사용자가 몇 명 없을 때는 문제 되지 않는다. \
하지만 팔로우하는 사용자가 늘어난다면, 시간복잡도가 늘어날 수밖에 없는 한계점을 갖고 있다. 왜 그런지 아래의 시간복잡도를 살펴보자.
```
log(Follow 전체 레코드) + 해당회원의 Following * log(Post 전체 레코드)
```
위에서 살펴봤듯이 팔로우하는 사람이 증가할수록 탐색 시간이 길어지는 구조를 갖는다.
즉 팔로우하는 사람이 늘어나면 늘어날수록 SQL 조건이 or 가 늘어나는 구조이다.
이걸 어떻게 해결하면 좋을지 고민해보고 베스트 프렉티스는 무엇인지 자료조사를 해보니, X, instagram 은 Fan-out-on-write 전략을 사용한다.
Fan-out-on-write 전략은 특정 사용자가 게시물을 작성하는 시점에 팔로워들에게 해당 컨텐츠를 Timeline 테이블에 넣어주는 개념이다.
그랬을 때 팔로잉하는 사용자는 게시물을 조회할 때 일일이 팔로우하는 사용자들의 아이디를 조건절에 넣을 필요 없이 자신의 Timeline 을 기반으로 조회하게 되고, 이는 마치 인덱스를 활용하게 해주는 것과 유사한 효과를 보여준다.
즉, 기존의 조회 시점에서 발생했던 부하를 쓰기 시점의 부하로 바꿔버린 셈이다.

![img.png](ref/img.png)

## 프로젝트에 사용된 S/W
- Docker
- PostgreSQL
- MongoDB
- Kafka, Zookeeper
- Kafka-ui

위 S/W 는 도커 컨테이너로 기동하여 프로젝트를 진행했다. `compose.yaml` 을 참고하자.
```bash
# 도커 컨테이너 기동
docker compose up -d
```

>참고로 postgresql 은 docker-entrypoint 에 필요한 셋팅을 해두었으나, MongoDB 는 아래 가이드를 참고. 
## MongoDB 초기 셋팅 관련
```bash
# intellij 로 접속할 경우 아래와 같이 함.
# mongodb://localhost:27017/papascar?authSource=admin

# 이외 도커 컨테이너로 직접 진입하여 아래 초기화 작업 진행. docker entry script 는.. 추후에 알아보겠음
docker exec -it mongodb mongosh -u admin -p 1234 --authenticationDatabase admin

use papascar;

db.createCollection("timelines")

db.timelines.createIndex({ "followerUserId": -1, "createdAt": -1 })

db.timelines.createIndex({ "carId": -1 } )

db.timelines.getIndexes()
```



