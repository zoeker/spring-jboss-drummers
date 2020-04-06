/*
 * JBoss, Home of Professional Open Source
 * Copyright 2014, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.moderndrummer.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moderndrummer.entity.exceptions.ModernDrummerException;
import com.moderndrummer.entity.exceptions.NotFoundException;
import com.moderndrummer.messages.ModernDrummerMessages;
import com.moderndrummer.model.Member;
import com.moderndrummer.repo.base.BaseJPQLDao;
import com.moderndrummer.util.ObjectUtil;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

@Repository
@Transactional
public class MemberDaoImpl extends BaseJPQLDao<Member> implements MemberDao {

    @Override
    public Member findById(Long id) {
        return find(id, Member.class);
    }

    @Override
    public Member findByEmail(String email) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

        criteria.select(member).where(builder.equal(member.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }

    @Override
    public Member findByEmailOrUsername(String userName, String email) {
        try {
            Member found = executeNamedQueryByTwoParams(userName.toLowerCase(), email.toLowerCase(),
                    "findMemberByUserNameOrEmail");
            if (found == null) {
                throw new NoResultException();
            }
            return found;
        } catch (NoResultException e) {
            return new Member();
        }
    }

    @Override
    public List<Member> findAllOrderedByName() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public boolean isValidUser(String userName, String password) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

        criteria.select(member).where(builder.equal(member.get("name"), userName))
                .where(builder.equal(member.get("password"), password));
        try {

            Member validMember = em.createQuery(criteria).setMaxResults(1).getSingleResult();
            return ObjectUtil.verifyMemberExists(validMember);

        } catch (NoResultException e) {
            return false;
        }

    }

    @Override
    public List<Member> findAllCreatedMembersByFromAndToDate(Date fromDate, Date toDate) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        Path<Date> dateCreatedPath = member.get("createdDate");
        List<Predicate> predicates = new ArrayList<Predicate>();
        predicates.add(cb.lessThanOrEqualTo(dateCreatedPath, toDate));
        predicates.add(cb.greaterThanOrEqualTo(dateCreatedPath, fromDate));
        criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }

    @Override
    public Member register(Member member) throws ModernDrummerException {
        try {
            return (Member) insert(member, Member.class);
        } catch (Exception e) {
            throw new ModernDrummerException(ModernDrummerMessages.INSERT_FAILURE + " " + " Member ");
        }

    }

    @Override
    public Member updateMember(Member member) throws ModernDrummerException {
        try {

            return update(member, false, Member.class);

        } catch (Exception e) {
            throw new ModernDrummerException(ModernDrummerMessages.UPDATE_FAILURE + " " + " Member ");
        }

    }

    @Override
    public Member findMemberByUserName(String userName) {
        try {

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
            Root<Member> member = criteria.from(Member.class);

            criteria.select(member).where(builder.equal(member.get("name"), userName));
            return em.createQuery(criteria).getSingleResult();

        } catch (NoResultException e) {
            throw new NotFoundException("Could not find member");
        }

    }
}
