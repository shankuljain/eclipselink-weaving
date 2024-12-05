package com.shanks.weaving;

import com.shanks.weaving.repository.PostRepository;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@SpringBootTest(classes = {WeavingApplication.class})
class WeavingApplicationTests {

	@Autowired
	private PostRepository projectRepository;

	@Test
	void contextLoads() {
		Assertions.assertTrue(true);
	}

	@Test
	void countQueries(){
		QueryCountHolder.clear();
		this.projectRepository.findAllById(List.of(1,2));
		var count = QueryCountHolder.getGrandTotal().getSelect();
		Assertions.assertEquals(3, count);
	}
}
