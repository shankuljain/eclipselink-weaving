package com.shanks.weaving;

import com.shanks.weaving.entities.Post;
import com.shanks.weaving.repository.PostRepository;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManagerFactory;
import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = {WeavingApplication.class})
class WeavingApplicationTests {

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
	}

	@Test
	void countQueries(){
		entityManagerFactory.getCache().evictAll();
		QueryCountHolder.clear();
		this.postRepository.findAllById(List.of(1,2));
		var count = QueryCountHolder.getGrandTotal().getSelect();
		QueryCountHolder.clear();
		Assertions.assertEquals(1, count);
	}

	static class Wrapper {
		Post getPost(Integer id) {
			return null;
		}
	}
}
