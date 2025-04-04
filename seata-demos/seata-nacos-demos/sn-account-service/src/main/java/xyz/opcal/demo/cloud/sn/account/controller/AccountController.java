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

package xyz.opcal.demo.cloud.sn.account.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import xyz.opcal.demo.cloud.api.dto.AccountDTO;
import xyz.opcal.demo.cloud.sn.account.service.AccountService;

@RestController
public class AccountController {

	private @Autowired AccountService accountService;

	@GetMapping("/account/money")
	public BigDecimal getMoney(String userId) {
		return accountService.getAccountMoney(userId);
	}

	@PostMapping("/account")
	public Boolean updateMoney(String userId, BigDecimal money, Boolean error) {
		accountService.updateUserAccount(userId, money, error);
		return true;
	}

	@GetMapping("/account")
	public AccountDTO getUserAccount(String userId) {
		return accountService.getUserAccount(userId);
	}

}
