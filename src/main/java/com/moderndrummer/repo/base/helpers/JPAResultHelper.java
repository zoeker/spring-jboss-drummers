package com.moderndrummer.repo.base.helpers;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class JPAResultHelper<E> {
	
	protected static final Logger LOGGER = LoggerFactory.getLogger("drumchops-errorslog");

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

	public static <E> E getSingleResult(final Query query) {
		try{
			
			query.setMaxResults(1);
			final List<?> list = query.getResultList();
			if (list == null || list.size() == 0) {
				return null;
			}
			return (E) list.get(0);
			
		}catch(NoResultException ex){
			LOGGER.error(ex.getMessage(),ex);
			//return createNewObjectByClassReference(e.getClass()); 
			throw new NoResultException();
		}
		
	}

	public static <T> T getSingleResult2(final Query query) throws NoResultException {
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
	
	
	private static  <E> E createNewObjectByClassReference(Class clazz) {
		E e = null;
		try {
			Constructor<?> constructor = clazz.getConstructors()[0];
			e = (E) constructor.newInstance();
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException
				| IllegalArgumentException e1) {
			LOGGER.error(e1.getMessage());
		}
		return e;
	}

	public <T> T instanceOf(Class clazz, Object value) {
		// return clazz.newInstance();
		Constructor ct;
		try {
			ct = clazz.getConstructor();
			return (T) ct.newInstance(value);

		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException ei) {
			LOGGER.error(ei.getMessage());
		}
		return null;

	}

}
