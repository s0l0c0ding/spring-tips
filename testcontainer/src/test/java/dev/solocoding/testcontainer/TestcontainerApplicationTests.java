package dev.solocoding.testcontainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootTest(classes = {TestcontainerApplication.class, TestContainerConfig.class})
class TestcontainerApplicationTests {

	
	@Autowired
	private JdbcTemplate jdbc;


	 @Test
	 void shouldRead(){
		 int count = jdbc.queryForObject("SELECT COUNT(*) FROM POST", Integer.class);
		assertEquals(2, count);
	 }

	 @Test
	 void shoulModify(){
		var update = jdbc.update("UPDATE POST SET title='first' WHERE id=1");
		assertEquals(1, update);
	 }


}