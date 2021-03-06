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

import java.util.Date;
import java.util.List;

import com.moderndrummer.entity.Member;
import com.moderndrummer.entity.exceptions.ModernDrummerException;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

public interface MemberDao {

    public Member findById(Long id);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public Member register(Member member) throws ModernDrummerException;

    List<Member> findAllCreatedMembersByFromAndToDate(Date fromDate, Date toDate);

    Member findMemberByUserName(String userName);

    boolean isValidUser(String userName, String password);

    public Member updateMember(Member member) throws ModernDrummerException;

    Member findByEmailOrUsername(String userName, String email);
}
