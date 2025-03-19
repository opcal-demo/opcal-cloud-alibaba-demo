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

package xyz.opcal.demo.cloud.sz.shop.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SZShopHybridControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void submit() {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("userId", "U100001");
		params.add("commodityCode", "C00321");
		params.add("quantity", "10");
		var response = restTemplate.postForEntity("/hybrid/submit", new HttpEntity<>(params), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@ParameterizedTest
	@ValueSource(strings = { "ACCOUNT", "STORAGE", "ORDER" })
	void mockSubmitException(String error) {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("userId", "U100001");
		params.add("commodityCode", "C00321");
		params.add("quantity", "10");
		params.add("error", error);
		var response = restTemplate.postForEntity("/hybrid/submit", new HttpEntity<>(params), String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}