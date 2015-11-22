/*******************************************************************************
 * Copyright 2015 Francesco Cina'
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package ufo.primomiglio.backend.web.util;

import org.springframework.web.context.request.async.DeferredResult;

import reactor.rx.Promise;

public class DeferredResultUtil {

    public static <T> DeferredResult<T> toDeferredResult(Promise<T> promise) {
        DeferredResult<T> result = new DeferredResult<>();
        promise.onSuccess(t -> result.setResult(t))
        .onError(ex -> result.setErrorResult(ex));
        return result;
    }

}
