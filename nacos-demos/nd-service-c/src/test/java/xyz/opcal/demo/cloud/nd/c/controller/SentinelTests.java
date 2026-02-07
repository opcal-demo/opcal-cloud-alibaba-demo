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

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.boot.resttestclient.autoconfigure.AutoConfigureTestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestInstance(Lifecycle.PER_METHOD)
@AutoConfigureTestRestTemplate
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SentinelTests {

	@Autowired
	TestRestTemplate testRestTemplate;

	private ExecutorService executorService;
	private AtomicLong atomicLong;

	@BeforeEach
	void init() {
		executorService = Executors.newVirtualThreadPerTaskExecutor();
		atomicLong = new AtomicLong();

	}

	@AfterEach
	void stop() throws InterruptedException {

		log.info("NaN counts {}", atomicLong.get());
	}

	@ParameterizedTest
	@ValueSource(strings = { "/a/calculate", "/b/calculate", "/dubbo/calculate" })
	void calculate(String api) throws InterruptedException {
		Random random = new Random();
		for (int i = 0; i < 1000; i++) {
			executorService.submit(() -> {
				double x = random.nextDouble() + random.nextInt(100);
				var params = new LinkedMultiValueMap<String, Object>();
				params.add("x", x);
				var response = testRestTemplate.postForEntity(api, new HttpEntity<MultiValueMap<String, Object>>(params), Double.class);
				if (Double.isNaN(response.getBody())) {
					atomicLong.incrementAndGet();
				}
			});
		}
		var count = 0;
		executorService.shutdown();
		while (!executorService.awaitTermination(1, TimeUnit.MINUTES)) {
			System.out.println("Waiting " + count);
			count++;
		}

	}

}
