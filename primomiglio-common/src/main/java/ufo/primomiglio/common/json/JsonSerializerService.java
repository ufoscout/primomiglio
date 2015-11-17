//formatter:off
/* ----------------------------------------------------------------------------
 *     PROJECT : EURES
 *
 *     PACKAGE : eu.europa.ec.empl.eures.cvo.service.serializer
 *        FILE : JsonSerializerService.java
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

/**
 * <class_description>
 * <p><b>notes</b>:
 * <p>ON : 5 Jun 2012
 *
 * @author ARHS Developments - cinafr
 * @version $Revision
 */

public interface JsonSerializerService {

	/**
	 * Return the json representation of the Bean
	 * @param object
	 * @return
	 */
	String toJson(Object object);

	/**
	 * Return the json representation of the Bean
	 * @param object
	 * @param prettyPrinted if true it returns a formatted json string. WARN: it is slower than the other method! TO BE USED ONLY FOR TEST PURPOSES
	 * @return
	 */
	String toJson(Object object, boolean prettyPrinted);

	<T> T fromJson(Class<T> clazz, String json);

	<T> T fromJson(String json, Class<T> clazz, Class<?>... genericsArgs);

}
