package com.moderndrummer.dao;

import static org.assertj.core.api.Assertions.assertThat; // main one
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
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

import com.moderndrummer.entity.Member;

import common.conpem.homeprojects.util.DateUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/application-context-test.xml",
        "file:src/main/resources/META-INF/spring/applicationContext.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
@ActiveProfiles("test")
public class MemberDaoTest {

    @Autowired
    private MemberDao memberDao;

    @Before
    public void init() {
        // MockitoAnnotations.initMocks(this);
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
    public void findByEmail_shouldSucceed() {
        Member member = memberDao.findByEmail("john.smith@mailinator.com");

        assertThat("John Smith").isEqualTo(member.getName());
        assertThat("john.smith@mailinator.com").isEqualTo(member.getEmail());
        assertThat("2125551212").isEqualTo(member.getPhoneNumber());

    }

    @Test
    public void findMemberByUserName_shouldSucceed() {
        Member member = memberDao.findMemberByUserName("John Smith");

        assertThat("John Smith").isEqualTo(member.getName());
        assertThat("john.smith@mailinator.com").isEqualTo(member.getEmail());
        assertThat("2125551212").isEqualTo(member.getPhoneNumber());

    }

    @Test
    public void findAllOrderedByName_shouldSucceed() {
        // given

        // when
        List<Member> resultList = memberDao.findAllOrderedByName();
        Member result = resultList.stream().findFirst().get();

        // then
        assertThat("John Smith").isEqualTo(result.getName());
        assertThat("john.smith@mailinator.com").isEqualTo(result.getEmail());
        assertThat("2125551212").isEqualTo(result.getPhoneNumber());

    }

    @Test
    public void findAllCreatedMembersByFromAndToDate_shouldSucceed() {
        Date from = DateUtils.parseDate("2020-01-02");
        Date end = DateUtils.parseDate("2020-12-02");

        List<Member> resultList = memberDao.findAllCreatedMembersByFromAndToDate(from, end);
        Member member = resultList.stream().findFirst().get();

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
