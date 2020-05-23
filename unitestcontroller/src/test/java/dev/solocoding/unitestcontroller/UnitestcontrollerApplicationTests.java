package dev.solocoding.unitestcontroller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import dev.solocoding.unitestcontroller.controller.PostController;
import dev.solocoding.unitestcontroller.dto.Post;
import dev.solocoding.unitestcontroller.service.PostService;

// @ExtendWith(SpringExtension.class)
class UnitestcontrollerApplicationTests {

	private static MockMvc mockMvc;
	private static PostService service = mock(PostService.class);

	@BeforeAll
	static void init () {
		mockMvc = MockMvcBuilders.standaloneSetup(new PostController(service)).build();
	}

	@Test
	void testController() throws Exception {
		when(service.getAPost(1)).thenReturn(new Post("postTitle", "body"));
		mockMvc.perform(get("/post/1")).andDo(print()).andExpect(status().isOk());
	}
	
	@Test
	void shouldGive405ForPost() throws Exception {
		when(service.getAPost(1)).thenReturn(new Post("postTitle", "body"));
		mockMvc.perform(post("/post/1")).andDo(print()).andExpect(status().isMethodNotAllowed());
	}

}
