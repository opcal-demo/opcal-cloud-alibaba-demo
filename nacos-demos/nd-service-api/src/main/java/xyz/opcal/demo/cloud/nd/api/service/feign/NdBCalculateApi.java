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

package xyz.opcal.demo.cloud.nd.api.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import xyz.opcal.demo.cloud.nd.api.service.feign.configuration.ServiceBFeignConfiguration;
import xyz.opcal.demo.cloud.nd.api.service.feign.fallback.factory.NdBCalculateApiFallbackFactory;

// @formatter:off
@FeignClient(name = "nd-service-b", path = "/original-api",
		configuration = ServiceBFeignConfiguration.class,
		fallbackFactory = NdBCalculateApiFallbackFactory.class)
// @formatter:on
public interface NdBCalculateApi {

	@PostMapping("/calculate")
	double calculate(@RequestParam Double x, @RequestParam Double y, @RequestParam Double z);

}
