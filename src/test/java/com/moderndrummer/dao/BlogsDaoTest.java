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
import java.util.Set;

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
import com.moderndrummer.model.Topic;

import common.conpem.homeprojects.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/application-context-test.xml",
        "file:src/main/resources/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ActiveProfiles("test")
public class BlogsDaoTest {

    @Autowired
    private BlogsDao blogsDao;
    
    @Autowired
    private TopicDao topicDao;
    
    @Autowired
    private MemberDao memberDao;

    @Before
    public void init() {
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findBlogPostById_shouldSucceed() {
        //Given
        Long id = 1L;
        
        //when
      
        Memberblogpost result = blogsDao.findBlogPostById(id);
        
        //Then
        assertThat(1L).isEqualTo(result.getBlogPostId());
        assertThat("John Smith").isEqualTo(result.getMember().getName());
        assertThat("Body").isEqualTo(result.getBlogPostBody());
        assertThat("Title").isEqualTo(result.getBlogPostTitle());

    }
    
    @Test
    public void insert_shouldSucceed() {
        //Given
        String blogPostBody = "something happened in the drumming session";
        String blogPostTitle = "new drum session";
      
        Memberblogpost post = new Memberblogpost();
        Topic topic = topicDao.findAllTopics().parallelStream().findFirst().get();
        Member member =  memberDao.findById(1L);
        
        //when
        
        post.setBlogPostBody(blogPostBody);
        post.setBlogPostTitle(blogPostTitle);
        post.setDatePosted(DateUtils.parseToDateTime("2020-01-01 12:12:12"));
        post.setTopic(topic);
        post.setMember(member);
        
        Memberblogpost result = blogsDao.insert(post);
         
        //Then
        assertThat(2L).isEqualTo(result.getBlogPostId());
        assertThat("John Smith").isEqualTo(result.getMember().getName());
        assertThat(blogPostBody).isEqualTo(result.getBlogPostBody());
        assertThat(blogPostTitle).isEqualTo(result.getBlogPostTitle());

    }
    
    
    
    
    
    @Test
    public void getAllBlogPosts_shouldSucceed() {
        //Given
        
        //when
      
        Set<Memberblogpost> resultList = blogsDao.getAllBlogPosts();
        Memberblogpost result = resultList.stream().findFirst().get();
        
        //Then
        assertThat(1L).isEqualTo(result.getBlogPostId());
        assertThat("John Smith").isEqualTo(result.getMember().getName());
        assertThat("Body").isEqualTo(result.getBlogPostBody());
        assertThat("Title").isEqualTo(result.getBlogPostTitle());

    }
}
