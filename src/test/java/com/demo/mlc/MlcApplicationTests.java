package com.demo.mlc;

import static org.junit.jupiter.api.Assertions.assertNull;

import com.demo.mlc.dto.ErrorCodeDTO;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MlcApplicationTests {

	@Test
	void contextLoads() {
		var myClass = new ErrorCodeDTO();
		assertNull(myClass.getCodeError()); // JUnit assertion
	}

}
