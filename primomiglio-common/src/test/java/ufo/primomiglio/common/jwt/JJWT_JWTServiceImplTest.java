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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.jsonwebtoken.SignatureException;
import ufo.primomiglio.common.BaseAbstractTest;

public class JJWT_JWTServiceImplTest extends BaseAbstractTest {

    @Autowired
    private JWTService jwtService;

    @Test
    public void should_generate_and_parse_custom_beans() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.from = "from-" + UUID.randomUUID();
        message.subject = "sub-" + UUID.randomUUID();
        message.sentDate = new Date();

        String JWT = jwtService.generate(message);
        getLogger().info("Generated JWT:\n{}", JWT);

        String parsed = jwtService.parse(JWT);
        getLogger().info("Parsed JWT:\n{}", parsed);
        assertNotNull(parsed);
        assertFalse(parsed.isEmpty());

        SimpleMailMessage parsedMessage = jwtService.parse(JWT, SimpleMailMessage.class);
        assertNotNull(parsedMessage);
        assertEquals( message.from, parsedMessage.from );
        assertEquals( message.subject, parsedMessage.subject );
        assertEquals( message.sentDate, parsedMessage.sentDate );
    }

    @Test(expected=SignatureException.class)
    public void should_fail_parsion_tampered_jwt() {
        SimpleMailMessage message = new SimpleMailMessage();
        message.from = "from-" + UUID.randomUUID();
        message.subject = "sub-" + UUID.randomUUID();
        message.sentDate = new Date();

        String JWT = jwtService.generate(message);
        getLogger().info("Generated JWT:\n{}", JWT);

        jwtService.parse(JWT + 1);
    }

    public static class SimpleMailMessage {

        public String from;
        public String replyTo;
        public String[] to;
        public String[] cc;
        public String[] bcc;
        public Date sentDate;
        public String subject;
        public String text;

    }
}
