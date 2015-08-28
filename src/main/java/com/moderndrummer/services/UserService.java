package com.moderndrummer.services;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.moderndrummer.data.MemberDao;
import com.moderndrummer.entity.exceptions.ModernDrummerException;
import com.moderndrummer.model.Member;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Service("userService")  
public class UserService {
    
    @Resource
    private MemberDao memberDao;
    
    public Member findMemberByUserName(String userName){
       return memberDao.findMemberByUserName(userName);
    }
    
    public Member findMemberByUserNameOrEmail(String userName, String email) throws ModernDrummerException{
      return memberDao.findByEmailOrUsername(userName, email);
   }
   
    
    
   public boolean  validateMemberByUserNameAndPassword(String userName, String password){
      return memberDao.isValidUser(userName, password);
   }
   
   public Member findMemberById(Integer memberId){
     return memberDao.findById(memberId.longValue());
   }
   
   public Member updateMember(Member member) throws ModernDrummerException{
     return memberDao.updateMember(member);
   }
   
   public Member registerMember(Member member) throws ModernDrummerException{
     return memberDao.register(member);
   }

}

