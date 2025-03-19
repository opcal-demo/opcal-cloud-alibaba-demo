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

package xyz.opcal.demo.cloud.sz.account.service.impl;

import java.math.BigDecimal;
import java.util.Objects;

import org.apache.dubbo.config.annotation.DubboService;
import org.apache.seata.core.context.RootContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import xyz.opcal.demo.cloud.api.dto.AccountDTO;
import xyz.opcal.demo.cloud.api.exception.BusinessException;
import xyz.opcal.demo.cloud.api.service.AccountApiService;
import xyz.opcal.demo.cloud.sz.account.entity.Account;
import xyz.opcal.demo.cloud.sz.account.mapper.AccountMapper;
import xyz.opcal.demo.cloud.sz.account.repository.AccountRepository;
import xyz.opcal.demo.cloud.sz.account.service.AccountService;

@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
@DubboService(interfaceClass = AccountApiService.class)
public class AccountServiceImpl implements AccountService {

	private @Autowired AccountRepository accountRepository;

	@Override
	public Account findUserAccount(String userId) {
		return accountRepository.findByUserId(userId);
	}

	@Override
	public AccountDTO getUserAccount(String userId) {
		return AccountMapper.MAPPER.toDto(findUserAccount(userId));
	}

	@Override
	public BigDecimal getAccountMoney(String userId) {
		Account account = findUserAccount(userId);

		if (Objects.nonNull(account)) {
			return account.getMoney();
		}
		return BigDecimal.ZERO;
	}

	@Override
	public void updateUserAccount(String userId, BigDecimal money, Boolean error) {
		log.info("xid {}", RootContext.getXID());
		Account account = findUserAccount(userId);
		if (Objects.nonNull(account)) {
			account.setMoney(money);
			accountRepository.save(account);
		}
		if (Boolean.TRUE.equals(error)) {
			throw new BusinessException("account update error");
		}
	}
}
