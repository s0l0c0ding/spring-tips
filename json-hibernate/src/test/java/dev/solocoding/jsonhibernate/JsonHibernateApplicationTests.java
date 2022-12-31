package dev.solocoding.jsonhibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.jdbc.Sql;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = {JsonHibernateApplication.class, TestContainerConfig.class})
class JsonHibernateApplicationTests {



	Logger logger = Logger.getLogger(JsonHibernateApplicationTests.class.getName());

	@Autowired
	private TestRestTemplate restTemplate;
	@Autowired
	private ObjectMapper mapper;
	


	@Test
	@Sql("init-db.sql")
	void whenGetPostThenSuccess() {
		Postdto actual = restTemplate.getForObject("/{id}", Postdto.class, 1);
		
		assertEquals("post1", actual.post());
		assertFalse(actual.comment().get("key").isMissingNode());
		logger.info("comment body: " + actual.comment().toString());
		assertEquals("bodyComment", actual.comment().path("key").asText());
	}

	@Test
	@Sql("init-db.sql")
	void whenCreatPostThenSuccess() {
		var postBody = "bodyPost";
		var commentBody = "commentPost";
		var req = new Postdto(postBody, mapper.createObjectNode().put("key", commentBody));
		
		Postdto actual = restTemplate.postForObject("/", req, Postdto.class);
		
		assertEquals(postBody, actual.post());
		assertFalse(actual.comment().get("key").isMissingNode());
		logger.info("comment body: " + actual.comment().toString());
		assertEquals(commentBody, actual.comment().path("key").asText());
	}

	@Test
	@Sql("init-db.sql")
	void whenGetPostByCommentThenSuccess() {
		Postdto actual = restTemplate.getForObject("/comments/{body}", Postdto.class, "bodyComment222");
		
		assertEquals("post2", actual.post());
		assertFalse(actual.comment().get("key").isMissingNode());
		logger.info("comment body: " + actual.comment().toString());
		assertEquals("bodyComment222", actual.comment().path("key").asText());
	}

}
