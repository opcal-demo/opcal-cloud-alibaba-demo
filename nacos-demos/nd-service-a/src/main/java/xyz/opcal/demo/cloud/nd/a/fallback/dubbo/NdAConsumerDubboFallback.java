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

package xyz.opcal.demo.cloud.nd.a.fallback.dubbo;

import org.apache.dubbo.rpc.AsyncRpcResult;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;

import com.alibaba.csp.sentinel.adapter.dubbo3.fallback.DubboFallback;
import com.alibaba.csp.sentinel.slots.block.BlockException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NdAConsumerDubboFallback implements DubboFallback {

	@Override
	public Result handle(Invoker<?> invoker, Invocation invocation, BlockException ex) {
		log.error("invoker [{}] invocation [{}] ex: ", invoker, invocation, ex);
		return AsyncRpcResult.newDefaultAsyncResult(Double.NaN, invocation);

	}
}
