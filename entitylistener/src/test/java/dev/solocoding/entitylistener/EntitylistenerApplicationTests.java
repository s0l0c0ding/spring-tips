package dev.solocoding.entitylistener;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import dev.solocoding.entitylistener.entities.Post;
import dev.solocoding.entitylistener.repo.PostBckRepo;
import dev.solocoding.entitylistener.repo.PostRepo;

@DataJpaTest
class EntitylistenerApplicationTests {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private PostBckRepo bckRepo;

	@Test
	void shouldBckOnPresist() {
		Post toSave = new Post();
		toSave.setBody("body");
		toSave.setTitle("title");
		postRepo.save(toSave);
		postRepo.count(); // to cause flushing
		assertEquals(1, bckRepo.count());
	}

	@Test
	void shouldBckonUpdate(){
		Post post = new Post();
		post.setTitle("title");
		post.setBody("body");
		postRepo.saveAndFlush(post);

		post.setTitle("title updated");
		postRepo.save(post);

		postRepo.count();
		assertEquals(2, bckRepo.count());// when run at its own otherwise 3

	}

}
