package com.bnppf.kata.developmentbooks;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(properties = {
		"spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration"
})

class DevelopmentBooksApplicationTests {

	@MockBean
	private com.bnppf.kata.developmentbooks.mapper.BookMapper bookMapper;

	@Test
	void contextLoads() {
	}

}
