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

package xyz.opcal.demo.cloud.sz.storage.service.impl;

import java.util.Objects;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import xyz.opcal.demo.cloud.api.dto.StorageDTO;
import xyz.opcal.demo.cloud.api.exception.BusinessException;
import xyz.opcal.demo.cloud.api.service.StorageApiService;
import xyz.opcal.demo.cloud.sz.storage.entity.Storage;
import xyz.opcal.demo.cloud.sz.storage.mapper.StorageMapper;
import xyz.opcal.demo.cloud.sz.storage.repository.StorageRepository;
import xyz.opcal.demo.cloud.sz.storage.service.StorageService;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@DubboService(interfaceClass = StorageApiService.class)
public class StorageServiceImpl implements StorageService {

	private @Autowired StorageRepository storageRepository;

	@Override
	public Storage findStorage(String commodityCode) {
		return storageRepository.findByCommodityCode(commodityCode);
	}

	@Override
	public StorageDTO getStorage(String commodityCode) {
		return StorageMapper.MAPPER.toDto(findStorage(commodityCode));
	}

	@Override
	public Integer getCount(String commodityCode) {
		Storage storage = findStorage(commodityCode);
		if (Objects.nonNull(storage)) {
			return storage.getCount();
		}
		return 0;
	}

	@Override
	public void updateStorage(String commodityCode, int count, Boolean error) {
		log.info("xid {}", RootContext.getXID());
		Storage storage = findStorage(commodityCode);
		if (Objects.nonNull(storage)) {
			storage.setCount(count);
			storageRepository.save(storage);
		}
		if (Boolean.TRUE.equals(error)) {
			throw new BusinessException("update storage error");
		}
	}

}
