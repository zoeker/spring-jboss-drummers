package com.moderndrummer.repo.base;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.moderndrummer.entity.exceptions.ModernDrummerJPAException;
import com.moderndrummer.messages.ModernDrummerMessages;
import com.moderndrummer.repo.base.helpers.JPAResultHelper;
import com.moderndrummer.util.ObjectUtil;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public abstract class BaseJPQLDao<E> {

	protected static final Logger LOGGER = LoggerFactory.getLogger("drumchops-errorslog");

	private static final Class<Object> E = null;

	// @PersistenceContext(unitName = "DrumChopsPU")
	@Autowired
	// @Qualifier(value = "em")
	protected EntityManager em;

	

	protected void log(final Exception e) {
		LOGGER.error(e.getMessage());
		LOGGER.error("Exception", e);
	}

	protected void log(String message, final Exception e) {
		LOGGER.error(message);
		LOGGER.error("Exception", e);
	}

	/**
	 * Method throwException.
	 * 
	 * @param e
	 *            Exception
	 * @throws DiveMasterTransactionalJPAException
	 */
	protected void throwException(final Exception e) throws ModernDrummerJPAException {

		try {
			log(e);

			verifyByException(e, "Entry", e.getMessage());
		} catch (final IllegalStateException | SecurityException e1) {
			log(e1);
			throw new ModernDrummerJPAException(e.getMessage());
		}

	}

	protected void log(final String message) {
		LOGGER.error(message);

	}

	protected void logInfo(final String message) {
		LOGGER.info(message);
	}

	protected void throwExceptionWithMessage(final Exception e, String entry, String message)
			throws ModernDrummerJPAException {
		if (e.getMessage().contains(entry) && e.getMessage().contains("Duplicate")) {
			throw new ModernDrummerJPAException(message);
		}
	}

	protected void verifyByException(final Exception e, String entry, String message) throws ModernDrummerJPAException {
		if (e instanceof PersistenceException) {
			throwExceptionWithMessage(e, entry, message);
			throw new ModernDrummerJPAException(e.getMessage());
		}
		if (e instanceof PersistenceException) {
			throwExceptionWithMessage(e, entry, message);
			throw new ModernDrummerJPAException(e.getMessage());
		} else {
			throw new ModernDrummerJPAException(e.getMessage());
		}
	}

	private List<E> executeAndGetResultList(String sqlQuery) {
		List<E> result;
		final Query q = em.createQuery(sqlQuery);
		result = q.getResultList();
		return result;
	}

	protected void rollBackTransaction(final Exception e) throws ModernDrummerJPAException {
		try {
			LOGGER.error(e.getMessage(), e);

			throw new ModernDrummerJPAException(ModernDrummerMessages.TRANSACTION_ROLLBACK + " " + e.getMessage());

		} catch (final IllegalStateException | SecurityException e1) {
			LOGGER.error(e1.getMessage(), e);
			throw new ModernDrummerJPAException("Transaction rolledback " + e.getMessage());

		}

	}

	protected Object doRollBack(Object object, boolean activatedTransaction) throws ModernDrummerJPAException {
		try {

			Object mergedObject = em.merge(object);
			return mergedObject;

		} catch (final Exception e) {
			LOGGER.error(e.getMessage(), e);
			rollBackTransaction(e);

			throw new ModernDrummerJPAException(e.getMessage());
		}
	}

	// testme
	protected void delete(Object object, Class clazz) throws ModernDrummerJPAException {

		try {

			em.remove(em.merge(clazz.cast(object)));

		} catch (final Exception e) {
			log(e.getMessage(), e);
			doRollBack(e);
		}
	}

	// testme
	protected <E> E insert(Object object, Class clazz) throws ModernDrummerJPAException {
		E persistentObject;
		try {

			persistentObject = (E) clazz.cast(object);
			em.persist(persistentObject);
			em.flush();
			em.refresh(persistentObject);

			return persistentObject;

		} catch (final Exception ex) {
			LOGGER.error(ex.getMessage(), ex);
			rollBackTransaction(ex);
			throw new ModernDrummerJPAException(ex.getMessage());
		}
	}

	// testme
	protected void delete(Set<Object> objects, Class clazz) throws ModernDrummerJPAException {

		try {

			for (Object o : objects) {
				em.remove(em.merge(clazz.cast(o)));
			}

		} catch (final Exception e) {
			log(e.getMessage(), e);
			doRollBack(e);
		}
	}

	// testme
	protected E update(final Object object, boolean activatedTransaction, Class clazz) throws SystemException,
			NotSupportedException, RollbackException, HeuristicMixedException, HeuristicRollbackException {
		try {

			final E updatedEquipment = (E) em.merge(clazz.cast(object));

			return updatedEquipment;

		} catch (Exception e) {
			throw new ModernDrummerJPAException(e.getMessage());
		}

		// return instanceOf(clazz,new Object());
	}

	private E createNewObjectByClassReference(Class clazz) {
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

	protected List<E> extracted(final Query q) {
		return q.getResultList();
	}

	protected void doRollBack() throws ModernDrummerJPAException {

		try {

			throw new ModernDrummerJPAException("Transaction rolled back");

		} catch (final IllegalStateException | SecurityException e1) {
			LOGGER.error(e1.getMessage(), e1);
			throw new ModernDrummerJPAException(e1.getMessage());
		}

	}

	protected void doRollBack(final Exception e) throws ModernDrummerJPAException {

		try {

			throw new ModernDrummerJPAException(e.getMessage());

		} catch (final IllegalStateException | SecurityException e1) {
			LOGGER.error(e1.getMessage(), e1);
			throw new ModernDrummerJPAException(e1.getMessage());
		}

	}

	protected void handleTransactionExceptions(final Exception e, String entries, String message)
			throws ModernDrummerJPAException {
		String newMessage = " rollback transaction " + e.getMessage();
		if (e.getCause().getMessage().compareToIgnoreCase(entries) == 0) {
			newMessage = message;
		}
		try {
			LOGGER.info(e.getMessage());

			throw new ModernDrummerJPAException(ModernDrummerMessages.TRANSACTION_ROLLBACK + " " + message);

		} catch (final IllegalStateException | SecurityException e1) {
			LOGGER.error(e1.getMessage(), e1);
			throw new ModernDrummerJPAException(newMessage);

		}

	}

	protected void handleTransactionExceptions(final Exception e, String message) throws ModernDrummerJPAException {

		try {
			LOGGER.info(e.getMessage());

			throw new ModernDrummerJPAException(message);

		} catch (final IllegalStateException | SecurityException e1) {
			LOGGER.error(e1.getMessage(), e1);
			throw new ModernDrummerJPAException("transaction rollback " + message);

		}

	}

	protected List<E> executeNamedQueryReturnListById(int id, String sqlQuery, Class clazz) {
		try {
			final Query q = em.createNamedQuery(sqlQuery, clazz);
			q.setParameter(1, id);
			return q.getResultList();

		} catch (final NoResultException e) {
			return new ArrayList<E>();
		}
	}

	protected E getResult(final Query q) throws NoResultException {
		final E result = JPAResultHelper.getMaxResult1(q);
		if (result == null) {
			throw new NoResultException();
		}

		return result;
	}

	protected int executeQueryByIdReturnInt(final int id, String sqlQuery) {
		try {
			final Query q = em.createQuery(sqlQuery, Integer.class);
			q.setParameter("id", id);
			final Integer result = (Integer) q.getSingleResult();
			return result.intValue();

		} catch (final NoResultException e) {
			return 0;
		}
	}

	protected List<E> executeQueryReturnList(String sqlQuery, Class clazz) {
		return executeSQLQueryOnlyNoParameters(sqlQuery, clazz);

	}

	protected List<E> getResultListById(final int id, String sqlQuery, Class clazz) {
		try {
			final Query q = em.createQuery(sqlQuery, clazz);
			q.setParameter("id", id);
			return q.getResultList();

		} catch (final NoResultException e) {
			return new ArrayList<E>();
		}
	}

	protected long executeNativeQuery(String sqlQuery) {
		Integer result;
		final Query q = em.createNativeQuery(sqlQuery);
		result = (Integer) q.getSingleResult();
		return result.longValue();
	}

	protected List<E> executeSQLQueryOnlyNoParameters(String sqlQuery, Class clazz) {
		try {
			final Query q = em.createQuery(sqlQuery, clazz);
			return q.getResultList();
		} catch (NoResultException | EntityNotFoundException e) {
			return new ArrayList<E>();
		}

	}

	protected Long executeSQLQuery(String sqlQuery) {
		try {
			final Query q = em.createQuery(sqlQuery, Long.class);
			return (Long) q.getSingleResult();
		} catch (final NoResultException e) {
			return 0L;
		}
	}

	protected E executyNamedQueryReturnObject(final int id, String namedQuery, Class clazz) {
		try {
			final Query q = em.createNamedQuery(namedQuery, clazz);
			q.setParameter(1, id);
			return (E) q.setMaxResults(1).getSingleResult();

		} catch (final NoResultException e) {
			return null;
		}
	}

	protected E executeNamedQueryReturnObject(final String param1, String namedQuery)
			throws NoResultException, NonUniqueResultException {
		final Query q = em.createNamedQuery(namedQuery);
		q.setParameter(1, param1);
		final E result = (E) q.getSingleResult();
		return result;
	}

	protected E executeQueryReturnObject(String sqlQuery, Class clazz) throws NoResultException {
		final Query q = em.createQuery(sqlQuery, clazz);
		return (E) q.getSingleResult();
	}

	protected E executeQueryByOneParamReturnObject(Object param1, String sqlQuery, Class clazz)
			throws NoResultException {
		final Query q = em.createQuery(sqlQuery, clazz);
		q.setParameter(1, param1);
		return (E) q.getSingleResult();
	}

	protected E executeNamedQueryOneParamReturnObject(final Object param1, String namedQuery, Class clazz)
			throws NoResultException, NonUniqueResultException {
		final Query q = em.createNamedQuery(namedQuery, clazz);
		q.setParameter(1, param1);
		final E result = (E) q.getSingleResult();
		return result;
	}

	// testme
	protected E find(Object id, Class clazz) {
		try {

			E e = (E) em.find(clazz, id);
			if (e == null) {
				throw new NoResultException();
			}
			return e;

		} catch (final NoResultException e) {
			logInfo(e.getMessage());
			return createNewObjectByClassReference(clazz);
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return createNewObjectByClassReference(clazz);
		}
	}

	protected Set<E> asSet(List<E> list) {
		return new HashSet<E>(list);
	}

	protected E executeNamedQueryByIdReturnObject(final int id, String namedQuery) throws NoResultException {
		try {
			final Query q = em.createNamedQuery(namedQuery);
			q.setParameter("id", id);
			final E result = JPAResultHelper.getSingleResult(q);
			if (result == null) {
				throw new NullPointerException();
			}
			return result;

		} catch (final NoResultException | NullPointerException e) {

			throw new NoResultException("No object found");
		}
	}

	protected E executeNamedQueryByTwoParams(final Object param1, final Object param2, String namedQuery)
			throws NoResultException {
		final Query q = em.createNamedQuery(namedQuery);
		q.setParameter(1, param1);
		q.setParameter(2, param2);
		final E result = JPAResultHelper.getSingleResult(q);
		if (result == null) {
			throw new NoResultException();
		}
		return result;

	}

	protected E executeNamedQueryByThreeParams(final Object param1, final Object param2, final Object param3,
			String namedQuery) {
		final Query q = em.createNamedQuery(namedQuery);
		q.setParameter(1, param1);
		q.setParameter(2, param2);
		q.setParameter(3, param3);
		final E result = JPAResultHelper.getSingleResult(q);
		if (result == null) {
			throw new NoResultException();
		}
		return result;

	}

	protected List<E> executeNamedQueryByOneParamReturnList(final Object param1, String namedQuery, Class clazz)
			throws NoResultException {
		final Query q = em.createNamedQuery(namedQuery, clazz);
		q.setParameter(1, param1);
		final List<E> result = q.getResultList();
		if (result == null) {
			throw new NoResultException();
		}
		return result;

	}

	protected List<E> executeNamedQueryByTwoParamsReturnList(final Object param1, final Object param2,
			String namedQuery, Class clazz) throws NoResultException {
		final Query q = em.createNamedQuery(namedQuery, clazz);
		q.setParameter(1, param1);
		q.setParameter(2, param2);
		final List<E> result = q.getResultList();
		if (result == null) {
			throw new NoResultException();
		}
		return result;

	}

	protected E executeNamedQueryByOneParam(final Object param1, String namedQuery) throws NoResultException {
		try {
			final Query q = em.createNamedQuery(namedQuery);
			q.setParameter(1, param1);
			return (E) q.getSingleResult();
		} catch (final NoResultException | NonUniqueResultException e) {
			throw new NoResultException("no object or non unique" + e.getMessage());
		}

	}

	protected E executyQueryReturnObject(String query, Class clazz) throws NoResultException {
		try {
			final Query q = em.createNamedQuery(query, clazz);
			return (E) q.setMaxResults(1).getSingleResult();

		} catch (final NoResultException e) {
			throw new NoResultException("no object");
		}
	}

	protected List<E> executeQueryByTwoIdsReturnList(String sqlQuery, int id1, int id2, Class clazz) {
		try {
			if (id1 > 0 && id2 > 0) {

				final Query q = em.createQuery(sqlQuery, clazz);
				q.setParameter(1, id1);
				q.setParameter(2, id2);
				final List<E> result = q.getResultList();
				return result;

			} else {
				throw new NoResultException();
			}

		} catch (final NoResultException e) {
			return new ArrayList<E>();
		}
	}

	protected List<E> executeQueryByOneParamReturnList(String sqlQuery, Object param1, Class clazz) {
		try {

			final Query q = em.createQuery(sqlQuery, clazz);
			q.setParameter(1, param1);
			final List<E> result = q.getResultList();
			return result;

		} catch (final NoResultException e) {
			return new ArrayList<E>();
		}
	}

	protected Integer executeNamedQueryByIdReturnInt(final E e, String namedQuery, Class clazz) {

		try {

			return getNamedQueryResultValue(e, namedQuery, clazz);
		} catch (ModernDrummerJPAException | NoResultException e1) {
			return 0;
		}

	}

	protected Integer executeQueryReturnInt(String sqlQuery) {

		try {
			final Long result = executeQueryReturnLong(sqlQuery);
			return result.intValue();
		} catch (NoResultException e1) {
			return 0;
		}

	}

	protected Long executeQueryReturnLong(String sqlQuery) {
		final Query q = em.createQuery(sqlQuery, Long.class);
		final Long result = (Long) JPAResultHelper.getMaxResult1(q);
		if (result == null) {
			throw new NoResultException();
		}
		return result;
	}

	protected Long executeQueryReturnLong(String sqlQuery, Class clazz) {
		final Query q = em.createQuery(sqlQuery, clazz);
		final Long result = (Long) JPAResultHelper.getMaxResult1(q);
		if (result == null) {
			throw new NoResultException();
		}
		return result;
	}

	protected List extractedObjects(final Query q) {
		return q.getResultList();
	}

	protected List<E> executeNamedQueryReturnList(String namedQuery, Class clazz) throws NoResultException {
		try {
			Query q = em.createNamedQuery(namedQuery, clazz);
			List<E> result = q.getResultList();
			return result;
		} catch (NoResultException e1) {
			return new ArrayList<E>();
		}
	}

	protected List<E> executeNamedQueryReturnList(Object param1, String namedQuery, Class clazz)
			throws NoResultException {
		try {
			Query q = em.createNamedQuery(namedQuery, clazz);
			q.setParameter(1, param1);
			List<E> result = q.getResultList();
			return result;
		} catch (NoResultException e1) {
			return new ArrayList<E>();
		}
	}

	protected Integer getNamedQueryResultValue(E e, String namedQuery, Class clazz) throws ModernDrummerJPAException {
		LOGGER.info("id : " + e);
		final Query q = em.createNamedQuery(namedQuery, clazz);
		q.setParameter(1, ObjectUtil.getId(e));
		final Integer result = (Integer) JPAResultHelper.getMaxResult1(q);
		if (result == null) {
			throw new NoResultException();
		}
		return result.intValue();
	}

	protected Integer executeQueryByOneParam(Object param1, String sqlQuery) {
		try {

			final Query q = em.createQuery(sqlQuery);
			q.setParameter(1, param1);
			Object result = JPAResultHelper.getMaxResult1(q);
			return returnResultByObject(result);

		} catch (final NoResultException e) {
			LOGGER.error(e.getMessage(), e);
			return 0;
		}
	}

	protected Integer executeQueryByTwoParams(Object param1, Object param2, String sqlQuery) {
		try {

			final Query q = em.createQuery(sqlQuery);
			q.setParameter(1, param1);
			q.setParameter(2, param1);
			Object result = JPAResultHelper.getMaxResult1(q);
			return returnResultByObject(result);

		} catch (final NoResultException e) {
			LOGGER.error(e.getMessage(), e);
			return 0;
		}
	}

	private Integer returnResultByObject(Object result) {
		if (result == null) {
			throw new NoResultException();
		}
		if (result instanceof Double) {
			return ((Double) result).intValue();
		} else if (result instanceof Integer) {
			return ((Integer) result).intValue();
		} else if (result instanceof Float) {
			return ((Float) result).intValue();
		} else if (result instanceof BigInteger) {
			return ((BigInteger) result).intValue();
		} else if (result instanceof Long) {
			return ((Long) result).intValue();
		} else if (result instanceof Short) {
			return ((Short) result).intValue();
		}
		return 0;
	}

}