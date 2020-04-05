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

import static org.assertj.core.api.Assertions.assertThat;  // main one
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.moderndrummer.dao.MemberDao;
import com.moderndrummer.model.Member;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/application-context-test.xml",
                                   "file:src/main/resources/META-INF/spring/applicationContext.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ActiveProfiles("test")
public class MemberDaoTest {
    
    @Autowired
    private MemberDao memberDao;
    
    @Before 
    public void init(){
        //MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindById() {
        List<Member> members = memberDao.findAllOrderedByName();
        Member member = members.get(0);
        assertThat("John Smith").isEqualTo(member.getName());
        assertThat("john.smith@mailinator.com").isEqualTo(member.getEmail());
        assertThat("2125551212").isEqualTo(member.getPhoneNumber());
        
    }

    @Test
    public void testFindByEmail() {
        Member member = memberDao.findByEmail("john.smith@mailinator.com");

        assertThat("John Smith").isEqualTo(member.getName());
        assertThat("john.smith@mailinator.com").isEqualTo(member.getEmail());
        assertThat("2125551212").isEqualTo(member.getPhoneNumber());
      
    }

    @Test
    public void testRegister() {
        Member member = new Member();
        member.setEmail("jane.doe@mailinator.com");
        member.setName("Jane Doe");
        member.setPhoneNumber("2125552121");
        member.setPassword("12345678");
        memberDao.register(member);
        Long id = member.getId();
        assertNotNull(id);

        assertEquals(2, memberDao.findAllOrderedByName().size());
        Member newMember = memberDao.findById(id);

        assertThat("Jane Doe").isEqualTo(member.getName());
        assertThat("jane.doe@mailinator.com").isEqualTo(member.getEmail());
        assertThat("2125552121").isEqualTo(member.getPhoneNumber());
       
    }

    @Test
    public void testFindAllOrderedByName() {
        Member member = new Member();
        member.setEmail("jane.doe@mailinator.com");
        member.setName("Jane Doe");
        member.setPhoneNumber("2125552121");
        member.setPassword("12345678");
        
        memberDao.register(member);

        List<Member> members = memberDao.findAllOrderedByName();
        assertEquals(2, members.size());
        Member newMember = members.get(0);

       
       
        assertThat("Jane Doe").isEqualTo(member.getName());
        assertThat("jane.doe@mailinator.com").isEqualTo(member.getEmail());
        assertThat("2125552121").isEqualTo(member.getPhoneNumber());
    }
}
