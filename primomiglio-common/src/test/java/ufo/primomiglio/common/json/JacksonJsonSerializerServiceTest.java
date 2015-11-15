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
package ufo.primomiglio.common.json;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.junit.Test;

import ufo.primomiglio.common.BaseAbstractTest;

public class JacksonJsonSerializerServiceTest extends BaseAbstractTest {

    @Resource
    private JsonSerializerService jsonSerializerService;

    @Test
    public void testJson() {
        String from = UUID.randomUUID().toString();
        SimpleMailMessage message = new SimpleMailMessage();
        message.from = from;

        String json = jsonSerializerService.toJson(message);
        assertNotNull(json);
        assertTrue(json.contains( from ));

        System.out.println("JSON content: /n" + json);

        SimpleMailMessage languageFromJson = jsonSerializerService.fromJson(SimpleMailMessage.class, json);
        assertNotNull(languageFromJson);
        assertEquals( from,  message.from );

    }

    @Test
    public void testGenerics() {
        String from = UUID.randomUUID().toString();
        SimpleMailMessage message = new SimpleMailMessage();
        message.from = from;

        List<SimpleMailMessage> messages = new ArrayList<SimpleMailMessage>();
        messages.add(message);

        String json = jsonSerializerService.toJson(messages);
        assertNotNull(json);
        assertTrue(json.contains(from));

        List<SimpleMailMessage> messagesFromJson = jsonSerializerService.fromJson(json, List.class, SimpleMailMessage.class);
        assertNotNull(messagesFromJson);
        assertFalse(messagesFromJson.isEmpty());
        Object entry = messagesFromJson.get(0);
        getLogger().info(entry.getClass().getName());
        assertTrue(entry instanceof SimpleMailMessage);

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
