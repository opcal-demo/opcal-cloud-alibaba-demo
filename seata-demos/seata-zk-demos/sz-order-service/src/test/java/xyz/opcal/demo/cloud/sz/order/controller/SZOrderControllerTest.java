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

package xyz.opcal.demo.cloud.sz.order.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SZOrderControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void create() {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("userId", "U100001");
		params.add("commodityCode", "C00321");
		params.add("quantity", "10");
		params.add("total", "204.98");
		var response = restTemplate.postForEntity("/order", new HttpEntity<>(params), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		log.info("order: {}", response.getBody());
	}

	@Test
	void mockCreateException() {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("userId", "U100001");
		params.add("commodityCode", "C00321");
		params.add("quantity", "10");
		params.add("total", "204.98");
		params.add("error", "true");
		var response = restTemplate.postForEntity("/order", new HttpEntity<>(params), String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}