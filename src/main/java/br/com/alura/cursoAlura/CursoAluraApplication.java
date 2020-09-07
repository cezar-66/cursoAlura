package br.com.alura.cursoAlura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication
@EnableSpringDataWebSupport // Habilita Paginacao
@EnableCaching // Habilita Cache
public class CursoAluraApplication {
	public static void main(String[] args) {
		SpringApplication.run(CursoAluraApplication.class, args);
	}
}
