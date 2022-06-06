package dev.solocoding.customannotaions;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import dev.solocoding.customannotaions.service.DemoService;

@SpringBootApplication
public class CustomAnnotaionsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomAnnotaionsApplication.class, args);
	}

	@Component
	class StartUp implements CommandLineRunner {
		
		private static final Logger log = LoggerFactory.getLogger(StartUp.class);

		@Autowired
		private ApplicationContext applicationContext;
		@Autowired
		private DemoService demo;


		
		@Override
		public void run(String... args) throws Exception {
			demo.print();
			log.info("""
					Found the custom service witn name: {} 
					""", List.of(applicationContext.getBeanNamesForType(DemoService.class)).stream().collect(Collectors.joining(" , ")));
			
		}

	}
}
