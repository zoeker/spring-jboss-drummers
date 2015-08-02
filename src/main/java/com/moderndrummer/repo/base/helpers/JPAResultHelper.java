package com.moderndrummer.repo.base.helpers;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;


/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class JPAResultHelper {
    public static <T> T getMaxResult(final Query query, final int size) {
        query.setMaxResults(size);
        try {
            return (T) query.getResultList();

        } catch (final NoResultException e) {
            return null;
        }

    }

    public static <T> T getMaxResult1(final Query query) {
        query.setMaxResults(1);
        try {
            return (T) query.getSingleResult();

        } catch (final NoResultException e) {
            return null;
        }

    }

    public static <T> T getSingleResult(final Query query) {
        query.setMaxResults(1);
        final List<?> list = query.getResultList();
        if (list == null || list.size() == 0) {
            return null;
        }
        return (T) list.get(0);
    }

    public static <T> T getSingleResult2(final Query query)
            throws NoResultException {
        query.setMaxResults(1);
        final List<?> list = query.getResultList();
        if (list == null || list.size() == 0) {
            throw new NoResultException();
        }
        return (T) list.get(0);
    }

    public static Object getSingleResultOrNull(final Query query) {
        final List results = query.getResultList();
        if (results.isEmpty()) {
            return null;
        } else if (results.size() == 1) {
            return results.get(0);
        }
        throw new NonUniqueResultException();
    }
    
    public static Integer getResultInteger(final Query q) throws NoResultException {
        final Integer result = (Integer) JPAResultHelper.getMaxResult1(q);
        if (result == null) {
            throw new NoResultException();
        }

        return result.intValue();
    }

}
