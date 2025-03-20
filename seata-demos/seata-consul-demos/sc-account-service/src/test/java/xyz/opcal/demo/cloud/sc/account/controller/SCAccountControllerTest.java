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

package xyz.opcal.demo.cloud.sc.account.controller;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SCAccountControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void getMoney() {
		var params = new HashMap<String, Object>();
		params.put("userId", "U100001");
		var response = restTemplate.getForEntity("/account/money?userId={userId}", String.class, params);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		log.info("money: {}", response.getBody());
	}

	@Test
	void updateMoney() {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("userId", "U100001");
		params.add("money", "9000");
		var response = restTemplate.postForEntity("/account", new HttpEntity<>(params),String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void getUserAccount() {
		var params = new HashMap<String, Object>();
		params.put("userId", "U100001");
		var response = restTemplate.getForEntity("/account?userId={userId}", String.class, params);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		log.info("userAccount: {}", response.getBody());
	}

	@Test
	void mockUpdateException() {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("userId", "U100001");
		params.add("money", "9000");
		params.add("error", "true");
		var response = restTemplate.postForEntity("/account", new HttpEntity<>(params),String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}