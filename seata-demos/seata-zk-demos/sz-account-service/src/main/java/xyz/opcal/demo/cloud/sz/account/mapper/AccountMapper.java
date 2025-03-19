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

package xyz.opcal.demo.cloud.sz.account.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import xyz.opcal.demo.cloud.api.dto.AccountDTO;
import xyz.opcal.demo.cloud.sz.account.entity.Account;

@Mapper
public interface AccountMapper {

	AccountMapper MAPPER = Mappers.getMapper(AccountMapper.class);

	AccountDTO toDto(Account entity);

	Account toEntity(AccountDTO dto);
}
