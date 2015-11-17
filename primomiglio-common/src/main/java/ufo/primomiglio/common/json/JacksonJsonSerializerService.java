//formatter:off
/* ----------------------------------------------------------------------------
 *     PROJECT : EURES
 *
 *     PACKAGE : eu.europa.ec.empl.eures.cvo.service.serializer.impl
 *        FILE : JacksonJsonSerializerService.java
 *
 *  CREATED BY : ARHS Developments
 *          ON : 5 Jun 2012
 *
 * MODIFIED BY : ARHS Developments
 *          ON : $LastChangedDate
 *     VERSION : $LastChangedRevision
 *
 * ----------------------------------------------------------------------------
 * Copyright (c) 2011 European Commission - DG EMPL
 * ----------------------------------------------------------------------------
 */
//formatter:on
package ufo.primomiglio.common.json;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


/**
 * <class_description>
 * <p>
 * <b>notes</b>:
 * <p>
 * ON : 5 Jun 2012
 *
 * @author ARHS Developments - cinafr
 * @version $Revision
 */
@Component
public class JacksonJsonSerializerService implements JsonSerializerService {

	private final ObjectMapper mapper;

	public JacksonJsonSerializerService() {
		this(false, false);
	}

	public JacksonJsonSerializerService(final boolean failOnUnknownProperties) {
		this(failOnUnknownProperties, true);
	}

	public JacksonJsonSerializerService(final boolean failOnUnknownProperties, final boolean failOnEmptyBeans) {
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, failOnUnknownProperties);
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, failOnEmptyBeans);
	}

	@Override
	public String toJson(final Object object) {
		try {
			return mapper.writeValueAsString(object);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String toJson(final Object object, final boolean prettyPrinted) {
		try {
			return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public <T> T fromJson(final Class<T> clazz, final String json) {
		return fromJson(json, clazz);
	}

	@Override
	public <T> T fromJson(final String json, final Class<T> clazz, final Class<?>... genericsArgs) {
		try {
			if (genericsArgs.length > 0) {
				JavaType type = mapper.getTypeFactory().constructParametrizedType(clazz, clazz, genericsArgs);
				return mapper.readValue(json.toString(), type);
			}
			return mapper.readValue(json.toString(), clazz);
		} catch (final Exception e) {
			throw new RuntimeException(e);
		}
	}

}
