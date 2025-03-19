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

package xyz.opcal.demo.cloud.sn.storage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.opcal.demo.cloud.api.dto.StorageDTO;
import xyz.opcal.demo.cloud.sn.storage.service.StorageService;

@RestController
public class StorageController {

	private @Autowired StorageService storageService;

	@GetMapping("/storage/count")
	public Integer getCount(String commodityCode) {
		return storageService.getCount(commodityCode);
	}

	@PostMapping("/storage")
	public Boolean updateStorage(String commodityCode, Integer count, Boolean error) {
		storageService.updateStorage(commodityCode, count, error);
		return true;
	}

	@GetMapping("/storage")
	public StorageDTO getStorage(String commodityCode) {
		return storageService.getStorage(commodityCode);
	}

}
