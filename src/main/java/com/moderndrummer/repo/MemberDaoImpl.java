package com.moderndrummer.repo;
/*
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.moderndrummer.entity.exceptions.DrumChopsException;
import com.moderndrummer.messages.DrumChopsMessages;
import com.moderndrummer.model.Member;
import com.moderndrummer.repo.base.BaseJPQLDao;
import com.moderndrummer.util.ObjectUtil;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("memberDao")
@Transactional
public class MemberDaoImpl extends BaseJPQLDao<Member> implements MemberDao
{
   

    @Override
    public Member findById(Long id)
    {
        return find(id, Member.class);
    }

    @Override
    public Member findByEmail(String email)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

    
        criteria.select(member).where(builder.equal(member.get("email"), email));
        return em.createQuery(criteria).getSingleResult();
    }
    
    @Override
    public Member findByEmailOrUsername(String userName, String email)
    {
       try{
        Member found = executeNamedQueryByTwoParams(userName.toLowerCase(), email.toLowerCase(), "findMemberByUserNameOrEmail");
        if(found== null){
          throw new NoResultException();
        }
        return found;
       }catch(NoResultException e){
          return new Member();
       }
    }

    @Override
    public List<Member> findAllOrderedByName()
    {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = cb.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);

     

        criteria.select(member).orderBy(cb.asc(member.get("name")));
        return em.createQuery(criteria).getResultList();
    }
    
    
    @Override
    public boolean isValidUser(String userName, String password)
    {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
        Root<Member> member = criteria.from(Member.class);
        
     
        criteria.select(member).where(builder.equal(member.get("name"), userName)).where(builder.equal(member.get("password"), password));
        try{
          
          Member validMember = em.createQuery(criteria).setMaxResults(1).getSingleResult();
          return ObjectUtil.verifyMemberExists(validMember);
          
        }catch(NoResultException e){
            return false;
        }
        
       
        
    }
    
    
    @Override
    public List<Member> findAllCreatedMembersByFromAndToDate(Date fromDate, Date toDate)
    {
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
    public Member register(Member member) throws DrumChopsException
    {
      try{
        return (Member) insert(member, Member.class);
      }catch(Exception e){
        throw new DrumChopsException(DrumChopsMessages.INSERT_FAILURE + " " + " Member ");
      }
        
    }
    
    @Override
    public Member updateMember(Member member) throws DrumChopsException
    {
      try{
        
        return update(member, false, Member.class);
      
      }catch(Exception e){
        throw new DrumChopsException(DrumChopsMessages.UPDATE_FAILURE + " " + " Member ");
      }
     
      
    }
    
    @Override
    public Member findMemberByUserName(String userName){
        try{
            
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Member> criteria = builder.createQuery(Member.class);
            Root<Member> member = criteria.from(Member.class);

        
            criteria.select(member).where(builder.equal(member.get("name"), userName));
            return em.createQuery(criteria).getSingleResult();
            
            
        }catch(NoResultException e){
            
            return new Member();
        }
       
    }
}*/
