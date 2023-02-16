package edu.uha.miage;
/*

https://start.spring.io/#!type=maven-project&language=java&platformVersion=3.0.2&packaging=jar&jvmVersion=17&groupId=edu.uha.miage&artifactId=DateTime&name=Date%20Time&description=Ma%20premi%C3%A8re%20application%20web%20avec%20Spring&packageName=edu.uha.miage&dependencies=devtools,web,thymeleaf,validation

*/
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DateTimeApplication {

	public static void main(String[] args) {
		SpringApplication.run(DateTimeApplication.class, args);
	}

}
