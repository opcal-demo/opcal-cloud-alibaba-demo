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

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.opcal.demo.cloud.nd.api.service.CalculatorService;
import xyz.opcal.demo.cloud.nd.api.service.feign.NdACalculateApi;
import xyz.opcal.demo.cloud.nd.api.service.feign.NdBCalculateApi;

@RestController
public class NdCCrossCalculateController {

	@DubboReference(mock = "true")
	private CalculatorService calculatorService;

	@Autowired
	private NdBCalculateApi ndBCalculateApi;

	@Autowired
	private NdACalculateApi ndACalculateApi;

	@PostMapping("/dubbo/calculate")
	public Double dubboCalculate(@RequestParam Double x) {
		return calculatorService.calculate(x, x, x);
	}

	@PostMapping("/a/calculate")
	public Double aCalculate(@RequestParam Double x) {
		return ndACalculateApi.calculate(x, x);
	}

	@PostMapping("/b/calculate")
	public Double bCalculate(@RequestParam Double x) {
		return ndBCalculateApi.calculate(x, x, x);
	}
}
