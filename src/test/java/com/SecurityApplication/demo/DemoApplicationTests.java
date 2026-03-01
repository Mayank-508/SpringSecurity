package com.SecurityApplication.demo;

import com.SecurityApplication.demo.entity.User;
import com.SecurityApplication.demo.service.JwtService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private JwtService jwtService;
	@Test
	void contextLoads() {
	}

	@Test
	void testJwt()
	{

		User user=new User(4L,"mayank", "mayank@gmail.com","1234");
		String token= jwtService.generateAccessToken(user);

		System.out.println("this is the Jwt token ->>>>>  "+token);

		Long id= jwtService.getUserIdFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI0IiwiZW1haWwiOiJtYXlhbmtAZ21haWwuY29tIiwicm9sZXMiOlsiVVNFUiIsIkFETUlOIl0sImlhdCI6MTc3MjAyMDY2MywiZXhwIjoxNzcyMDIwNzIzfQ.ZOYVFGhWe6-KJ3y8UPUvz7SbVOU4Sgp9ZZNm1OICg1uuneUpwPX6cstWanizEoaDNk4sTIFXBcm401_snTWS-A");


		System.out.println("the id is this ->>>"+id);
	}

}
