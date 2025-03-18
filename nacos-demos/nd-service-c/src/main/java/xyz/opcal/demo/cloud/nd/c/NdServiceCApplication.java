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

package xyz.opcal.demo.cloud.nd.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

import com.alibaba.csp.sentinel.adapter.dubbo3.config.DubboAdapterGlobalConfig;

import xyz.opcal.demo.cloud.nd.api.service.feign.NdACalculateApi;
import xyz.opcal.demo.cloud.nd.api.service.feign.NdBCalculateApi;
import xyz.opcal.demo.cloud.nd.api.service.feign.configuration.ServiceBFeignConfiguration;
import xyz.opcal.demo.cloud.nd.c.fallback.dubbo.NdCConsumerDubboFallback;

@EnableDiscoveryClient
@Import(ServiceBFeignConfiguration.class)
@EnableFeignClients(clients = { NdACalculateApi.class, NdBCalculateApi.class })
@SpringBootApplication
public class NdServiceCApplication {

	static {
		DubboAdapterGlobalConfig.setConsumerFallback(new NdCConsumerDubboFallback());
	}

	public static void main(String[] args) {
		SpringApplication.run(NdServiceCApplication.class, args);
	}

}
