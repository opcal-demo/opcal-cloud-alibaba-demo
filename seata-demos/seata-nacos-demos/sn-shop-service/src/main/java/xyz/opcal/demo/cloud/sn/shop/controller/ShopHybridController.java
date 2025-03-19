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

package xyz.opcal.demo.cloud.sn.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.opcal.demo.cloud.sn.shop.common.ErrorType;
import xyz.opcal.demo.cloud.sn.shop.service.ShopHybridService;

@RestController
public class ShopHybridController {

	private @Autowired ShopHybridService shopHybridService;

	@PostMapping("/hybrid/submit")
	public Boolean submit(String userId, String commodityCode, Integer quantity, ErrorType error) {
		shopHybridService.submit(userId, commodityCode, quantity, error);
		return true;
	}

}
