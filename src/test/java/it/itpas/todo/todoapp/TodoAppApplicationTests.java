package it.itpas.todo.todoapp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class TodoAppApplicationTests {

	@Test
	protected void contextLoads() {
		assertEquals(1,1);
	}

}
