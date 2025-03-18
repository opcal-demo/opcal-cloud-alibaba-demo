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

package xyz.opcal.demo.cloud.nd.api.service.feign.fallback.factory;

import org.springframework.cloud.openfeign.FallbackFactory;

import lombok.extern.slf4j.Slf4j;
import xyz.opcal.demo.cloud.nd.api.service.feign.NdBCalculateApi;

@Slf4j
public class NdBCalculateApiFallbackFactory implements FallbackFactory<NdBCalculateApi> {

	@Override
	public NdBCalculateApi create(Throwable cause) {
		log.error("fallback error exception[{}], cause[{}], message[{}] ", cause.getClass(), cause.getCause(), cause.getMessage());
		return new NdBCalculateApi() {
			@Override
			public double calculate(Double x, Double y, Double z) {
				return Double.NaN;
			}
		};
	}

}
