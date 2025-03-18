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

package xyz.opcal.demo.cloud.nd.c.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NdCCrossCalculateControllerTest {

	@Autowired
	TestRestTemplate restTemplate;

	@ParameterizedTest
	@ValueSource(strings = { "/a/calculate", "/b/calculate", "/dubbo/calculate" })
	void calculate(String api) throws InterruptedException {
		var params = new LinkedMultiValueMap<String, Object>();
		params.add("x", 3D);
		var responseEntity = restTemplate.postForEntity(api, new HttpEntity<MultiValueMap<String, Object>>(params), Double.class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		log.info("{} result: {}", api, responseEntity.getBody());
		Thread.sleep(100000);
	}

}