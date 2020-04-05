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

import static org.assertj.core.api.Assertions.assertThat; // main one
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
import com.moderndrummer.model.Memberblogpost;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/application-context-test.xml",
        "file:src/main/resources/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ActiveProfiles("test")
public class BlogsDaoTest {

    @Autowired
    private BlogsDao blogsDao;

    @Before
    public void init() {
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findBlogPostById_shouldSucceed() {
        Memberblogpost result = blogsDao.findBlogPostById(1L);

        assertThat(1L).isEqualTo(result.getBlogPostId());
        assertThat("John Smith").isEqualTo(result.getMember().getName());
        assertThat("Body").isEqualTo(result.getBlogPostBody());
        assertThat("Title").isEqualTo(result.getBlogPostTitle());

    }
}
