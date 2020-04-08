package com.moderndrummer.dao;

import static org.assertj.core.api.Assertions.assertThat; // main one

import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.moderndrummer.entity.Member;
import com.moderndrummer.entity.Memberblogpost;
import com.moderndrummer.entity.Topic;
import com.moderndrummer.entity.exceptions.NotFoundException;

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

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void init() {
        // MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findBlogPostById_shouldSucceed() {
        // Given
        Long id = 1L;

        // when

        Memberblogpost result = blogsDao.findBlogPostById(id);

        // Then
        assertThat(1L).isEqualTo(result.getBlogPostId());
        assertThat("John Smith").isEqualTo(result.getMember().getName());
        assertThat("Body").isEqualTo(result.getBlogPostBody());
        assertThat("Title").isEqualTo(result.getBlogPostTitle());

    }

    @Test
    public void findBlogPostById_NonExisting_shouldThrowError() {
        // Given
        Long id = 12L;

        // Then
        expectedException.expect(NotFoundException.class);

        // when
        Memberblogpost result = blogsDao.findBlogPostById(id);

    }

    @Test
    public void delete_shouldSucceed() {

        // Given
        String blogPostBody = "something happened in the drumming session";
        String blogPostTitle = "new drum session";

        Memberblogpost post = getInsertableMemberBlogPost(blogPostBody, blogPostTitle);

        Memberblogpost result = blogsDao.insert(post);

        // when
        boolean deleted = blogsDao.delete(result.getBlogPostId());

        // Then
        assertThat(deleted).isEqualTo(true);

    }
    
    @Test
    public void update_shouldSucceed() {

        // Given
        String blogPostBody = "something happened in the drumming session";
        String blogPostTitle = "new drum session";
        String blogPostBodyUpdate = "updating body";
        
        Memberblogpost post = getInsertableMemberBlogPost(blogPostBody, blogPostTitle);

        Memberblogpost result = blogsDao.insert(post);
        
        result.setBlogPostBody(blogPostBodyUpdate);
        

        // when
        Memberblogpost updated = blogsDao.update(result);

        // Then
        assertThat(updated.getBlogPostBody()).isEqualTo(blogPostBodyUpdate);

    }

    private Memberblogpost getInsertableMemberBlogPost(String blogPostBody, String blogPostTitle) {
        Memberblogpost post = new Memberblogpost();
        Topic topic = topicDao.findAllTopics().parallelStream().findFirst().get();
        Member member = memberDao.findById(1L);

        // when

        post.setBlogPostBody(blogPostBody);
        post.setBlogPostTitle(blogPostTitle);
        post.setDatePosted(DateUtils.parseToDateTime("2020-01-01 12:12:12"));
        post.setTopic(topic);
        post.setMember(member);
        return post;
    }

    @Test
    public void insert_shouldSucceed() {
        // Given
        String blogPostBody = "something happened in the drumming session";
        String blogPostTitle = "new drum session";

        Memberblogpost post = getInsertableMemberBlogPost(blogPostBody, blogPostTitle);

        // when

        Memberblogpost result = blogsDao.insert(post);

        // Then
        assertThat(2L).isEqualTo(result.getBlogPostId());
        assertThat("John Smith").isEqualTo(result.getMember().getName());
        assertThat(blogPostBody).isEqualTo(result.getBlogPostBody());
        assertThat(blogPostTitle).isEqualTo(result.getBlogPostTitle());

    }

    @Test
    public void getAllBlogPosts_shouldSucceed() {
        // Given

        // when

        Set<Memberblogpost> resultList = blogsDao.getAllBlogPosts();
        Memberblogpost result = resultList.stream().findFirst().get();

        // Then
        assertThat(1L).isEqualTo(result.getBlogPostId());
        assertThat("John Smith").isEqualTo(result.getMember().getName());
        assertThat("Body").isEqualTo(result.getBlogPostBody());
        assertThat("Title").isEqualTo(result.getBlogPostTitle());

    }
}
