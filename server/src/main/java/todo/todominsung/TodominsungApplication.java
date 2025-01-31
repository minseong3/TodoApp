package todo.todominsung;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("todo.todominsung.module.mapper")  // Mapper 스캔 경로 설정
public class TodominsungApplication {
	public static void main(String[] args) {
		SpringApplication.run(TodominsungApplication.class, args);
	}

}
