/*
 * Copyright 2020-2025 Opcal
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.opcal.demo.cloud.nd.b.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NdBCalculateControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void calculate() {
		var x = 5.0D;
		var y = 2.0D;
		var z = 9.0D;
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("x", x);
		params.add("y", y);
		params.add("z", z);
		var responseEntity = restTemplate.postForEntity("/original-api/calculate", new HttpEntity<MultiValueMap<String, Object>>(params), Double.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		log.info("f({},{}, {}) = {}", x, y, z, responseEntity.getBody());
	}

}