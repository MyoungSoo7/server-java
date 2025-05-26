package kr.hhplus.be.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

    // 서버는 백엔드의 꽃이다. (성능,안정성, 확장성,데이터보안)
	//TDD를 통해 더욱 견고하고 안정적인 코드를 작성
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
