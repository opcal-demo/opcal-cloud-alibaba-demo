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

package xyz.opcal.demo.cloud.sc.order.service.impl;

import java.math.BigDecimal;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import xyz.opcal.demo.cloud.api.dto.OrderDTO;
import xyz.opcal.demo.cloud.api.exception.BusinessException;
import xyz.opcal.demo.cloud.api.service.OrderApiService;
import xyz.opcal.demo.cloud.sc.order.entity.Order;
import xyz.opcal.demo.cloud.sc.order.mapper.OrderMapper;
import xyz.opcal.demo.cloud.sc.order.repository.OrderRepository;
import xyz.opcal.demo.cloud.sc.order.service.OrderService;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@DubboService(interfaceClass = OrderApiService.class)
public class OrderServiceImpl implements OrderService {

	private @Autowired OrderRepository orderRepository;

	@Override
	public Order findOrder(Long id) {
		return orderRepository.findById(id).orElse(null);
	}

	@Override
	public OrderDTO getOrder(Long id) {
		return OrderMapper.MAPPER.toDto(findOrder(id));
	}

	@Override
	public OrderDTO createOrder(String userId, String commodityCode, Integer quantity, BigDecimal total, Boolean error) {
		log.info("xid {}", RootContext.getXID());
		Order order = new Order();
		order.setUserId(userId);
		order.setCommodityCode(commodityCode);
		order.setCount(quantity);
		order.setMoney(total);
		orderRepository.save(order);
		if (Boolean.TRUE.equals(error)) {
			throw new BusinessException("crate order error");
		}
		return OrderMapper.MAPPER.toDto(order);
	}
}
