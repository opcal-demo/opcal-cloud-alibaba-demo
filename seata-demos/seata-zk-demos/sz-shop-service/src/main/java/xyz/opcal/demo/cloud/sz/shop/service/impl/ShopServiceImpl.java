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

package xyz.opcal.demo.cloud.sz.shop.service.impl;

import java.math.BigDecimal;

import org.apache.seata.core.context.RootContext;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import xyz.opcal.demo.cloud.sz.shop.common.ErrorType;
import xyz.opcal.demo.cloud.sz.shop.service.ShopService;
import xyz.opcal.demo.cloud.sz.shop.service.client.AccountServiceClient;
import xyz.opcal.demo.cloud.sz.shop.service.client.OrderServiceClient;
import xyz.opcal.demo.cloud.sz.shop.service.client.StorageServiceClient;

@Slf4j
@Service
public class ShopServiceImpl implements ShopService {

	private @Autowired AccountServiceClient accountServiceClient;

	private @Autowired StorageServiceClient storageServiceClient;

	private @Autowired OrderServiceClient orderServiceClient;

	@GlobalTransactional(timeoutMills = 300000, name = "opcal-cloud-alibaba-demo-tx")
	@Override
	public void submit(String userId, String commodityCode, Integer quantity, ErrorType error) {
		log.info("xid {}", RootContext.getXID());
		Integer count = storageServiceClient.getCount(commodityCode);

		BigDecimal money = accountServiceClient.getMoney(userId);

		BigDecimal total = new BigDecimal(10).multiply(new BigDecimal(quantity));

		BigDecimal left = money.subtract(total);

		orderServiceClient.createOrder(userId, commodityCode, quantity, total, ErrorType.isError(ErrorType.ORDER, error));

		accountServiceClient.updateMoney(userId, left, ErrorType.isError(ErrorType.ACCOUNT, error));

		storageServiceClient.updateStorage(commodityCode, count - quantity, ErrorType.isError(ErrorType.STORAGE, error));
	}

}
