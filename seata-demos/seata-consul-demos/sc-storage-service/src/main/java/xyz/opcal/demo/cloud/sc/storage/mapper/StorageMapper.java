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

package xyz.opcal.demo.cloud.sc.storage.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import xyz.opcal.demo.cloud.api.dto.StorageDTO;
import xyz.opcal.demo.cloud.sc.storage.entity.Storage;

@Mapper
public interface StorageMapper {

	StorageMapper MAPPER = Mappers.getMapper(StorageMapper.class);

	StorageDTO toDto(Storage entity);

	Storage toEntity(StorageDTO dto);
}
