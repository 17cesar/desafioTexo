package com.texo.integracao;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.texo.VH.ProducerWinVH;

@SpringBootTest(webEnvironment =  SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class ProducerControllerIT {

	@Autowired
	private TestRestTemplate  testRestTemplate;
	
	@LocalServerPort
	private int port;
	
	@Test
	void listarGanhadoresPremio() {
		ResponseEntity<ProducerWinVH> response = testRestTemplate.getForEntity("/api/producer/winners-breaks", ProducerWinVH.class);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}
	
}
