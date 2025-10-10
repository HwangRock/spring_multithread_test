# spring_multithread_test

spring에서 스레드풀 관련 파라미터를 바꿔가며  
특정 알고리즘을 수행하는 API의 메트릭을  
Jmeter로 부하테스트를 가하며 측정합니다.

---

### 실험 보고서

https://velog.io/@hwangrock1220/%EC%84%9C%EB%B2%84%EC%84%B1%EB%8A%A5-spring%EC%97%90%EC%84%9C-JMeter%EB%A1%9C-%EB%A9%80%ED%8B%B0-%EC%8A%A4%EB%A0%88%EB%93%9C-%EC%8B%A4%ED%97%98


---

### 실험 설계

##### 실험한 API의 알고리즘
https://www.acmicpc.net/problem/1431

##### API 명세서

| **엔드포인트** | **HTTP 메서드** | **설명** |
|----------------|------------------|-----------|
| `/test`        | GET              | DB에 있는 데이터를 불러와 알고리즘을 수행합니다. |
| `/delete`      | DELETE           | DB의 모든 데이터를 삭제합니다. |
| `/random`      | POST             | 500개의 무작위 문자열 데이터를 생성하여 DB에 저장합니다. |

##### 구조

![](./presentation/testing.png)