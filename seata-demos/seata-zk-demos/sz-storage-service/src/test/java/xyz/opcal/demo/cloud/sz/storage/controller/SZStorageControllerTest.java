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

package xyz.opcal.demo.cloud.sz.storage.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SZStorageControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void getCount() {
		var params = new HashMap<String, Object>();
		params.put("commodityCode", "C00321");
		var response = restTemplate.getForEntity("/storage/count?commodityCode={commodityCode}", Integer.class, params);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		log.info("storage count: {}", response.getBody());

	}

	@Test
	void updateStorage() {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("commodityCode", "C00321");
		params.add("count", "500");
		var response = restTemplate.postForEntity("/storage", new HttpEntity<>(params), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void getStorage() {
		var params = new HashMap<String, Object>();
		params.put("commodityCode", "C00321");
		var response = restTemplate.getForEntity("/storage?commodityCode={commodityCode}", String.class, params);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		log.info("storage info: {}", response.getBody());
	}

	@Test
	void mockUpdateException() {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("commodityCode", "C00321");
		params.add("count", "500");
		params.add("error", "true");
		var response = restTemplate.postForEntity("/storage", new HttpEntity<>(params), String.class);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
	}
}