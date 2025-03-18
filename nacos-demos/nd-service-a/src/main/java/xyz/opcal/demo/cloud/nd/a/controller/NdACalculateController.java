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

package xyz.opcal.demo.cloud.nd.a.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/original-api")
public class NdACalculateController {

	@Value("${nd.a.calculator.x-factor}")
	private double xFactor;

	@PostMapping("/calculate")
	public double calculate(@RequestParam Double x, @RequestParam Double y) {

		double result = Math.log1p(x) + Math.pow(x, xFactor) * Math.sin(y);
		log.info("log1p({}) + {}^{} * sin({}) = {}", x, x, xFactor, y, result);
		return result;
	}
}
