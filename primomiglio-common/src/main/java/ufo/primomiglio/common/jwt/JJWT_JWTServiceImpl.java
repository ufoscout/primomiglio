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
package ufo.primomiglio.common.jwt;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import ufo.primomiglio.common.json.JsonSerializerService;

@Component
public class JJWT_JWTServiceImpl implements JWTService {

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS512;
    private static final String ALGORITHM = SIGNATURE_ALGORITHM.getValue();
    private final Key key;
    private final JsonSerializerService jsonSerializerService;

    @Autowired
    public JJWT_JWTServiceImpl(JWTConfig config, JsonSerializerService jsonSerializerService) {
        this.jsonSerializerService = jsonSerializerService;
        key = new SecretKeySpec(config.getSecret().getBytes(), ALGORITHM);
    }

    @Override
    public <T> String generate(T payload) {
        return Jwts.builder().setSubject(jsonSerializerService.toJson(payload)).signWith(SIGNATURE_ALGORITHM, key).compact();
    }

    @Override
    public String parse(String jsonString) {
        return Jwts.parser().setSigningKey(key).parseClaimsJws(jsonString).getBody().getSubject();
    }

    @Override
    public <T> T parse(String jsonString, Class<T> payloadClass) {
        return jsonSerializerService.fromJson(payloadClass, parse(jsonString));
    }
}
