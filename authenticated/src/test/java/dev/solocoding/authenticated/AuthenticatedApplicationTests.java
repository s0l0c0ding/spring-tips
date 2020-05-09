package dev.solocoding.authenticated;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import dev.solocoding.authenticated.controller.UserController;

@WebMvcTest(controllers = UserController.class)

class AuthenticatedApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	 private final String  parUrl = "/user/par";
	 private final String contexturl = "/user/context";

	@Test
	void shouldGive401WithoutUser() throws Exception {
		mockMvc.perform(get(parUrl)).andExpect(status().is(HttpStatus.UNAUTHORIZED.value()));
	}
	@Test
	@WithMockUser(username = "userMock", password = "pwd", roles = "USER")
	void getCurrentUserTest() throws Exception {
		var res = mockMvc.perform(get(parUrl)).andExpect(status().isOk()).andReturn();
		assertEquals("userMock", res.getResponse().getContentAsString());
	}

	@Test
	@WithMockUser(username = "userMock", password = "pwd", roles = "USER")
	void getCurrentUserContextTest() throws Exception {
		var res = mockMvc.perform(get(contexturl)).andExpect(status().isOk()).andReturn();
		assertEquals("userMock", res.getResponse().getContentAsString());
	}

}
