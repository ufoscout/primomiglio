/* ----------------------------------------------------------------------------
 *     PROJECT : EURES
 *
 *     PACKAGE : eu.europa.ec.empl.eures.basement.core.dao
 *        FILE : WriteDao.java
 *
 *  CREATED BY : ARHS Developments
 *          ON : Jun 21, 2013
 *
 * MODIFIED BY : ARHS Developments
 *          ON : $LastChangedDate
 *     VERSION : $LastChangedRevision
 *
 * ----------------------------------------------------------------------------
 * Copyright (c) 2011 European Commission - DG EMPL
 * ----------------------------------------------------------------------------
 */
package ufo.primomiglio.common.jdbc;

import com.jporm.rm.session.Session;

/**
 * <class_description>
 * <p><b>notes</b>:
 * <p>ON : Jun 21, 2013
 *
 * @author ARHS Developments - Francesco Cina
 * @version $Revision
 */
public interface WriteDao<T> {

    default int remove(final Session session, final T object) {
        return session.delete(object);
    }

    default T save(final Session session, final T object) {
        return session.save(object);
    }

    default T update(final Session session, final T object) {
        return session.update(object);
    }

    default T saveOrUpdate(final Session session, final T object) {
        return session.saveOrUpdate(object);
    }
}
