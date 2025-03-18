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

package xyz.opcal.demo.cloud.nd.a.service;

import org.apache.dubbo.config.annotation.DubboService;

import lombok.extern.slf4j.Slf4j;
import xyz.opcal.demo.cloud.nd.api.service.CalculatorService;

@Slf4j
@DubboService
public class CalculatorServiceImpl implements CalculatorService {

	@Override
	public double calculate(double x, double y, double z) {
		log.info("|{}| - {}^{} * {}", x, y, z, Math.E);
		return Math.abs(x) - Math.pow(y, z) * Math.E;
	}
}
