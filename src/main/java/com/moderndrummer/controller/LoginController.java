package com.moderndrummer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moderndrummer.data.MemberDao;
import com.moderndrummer.messages.ModernDrummerMessages;
import com.moderndrummer.model.Member;

//conpem 16 dec 2014
@Controller("loginController")
//@RequestMapping(value="/login")
//@SessionAttributes("loggedUser")
public class LoginController {
  
  //@Resource
  //UserService userService;
  
  @Autowired
  private MemberDao memberDao;

    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model)
    {
        model.addAttribute("loginMember", new Member());
        return "login";
    }
    
    
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(
         @Valid @ModelAttribute("loginMember") Member loginMember, BindingResult result, ModelMap model, HttpServletRequest request ){
    
  
        //boolean isValid = userService.validateMemberByUserNameAndPassword(loginMember.getName(), loginMember.getPassword());
      boolean isValid = memberDao.isValidUser(loginMember.getName(), loginMember.getPassword());  
      if(!isValid){
        
          model.addAttribute("errorMessage", ModernDrummerMessages.INVALID_LOGIN);
          return "login";
        }
        else{
         // Member loggedMember = userService.findMemberByUserName(loginMember.getName());
          Member loggedMember = memberDao.findMemberByUserName(loginMember.getName());
          HttpSession session = request.getSession();
          session.setAttribute("loggedUser" , loggedMember);
          model.addAttribute("loggedInMember", loggedMember);
          return "redirect:personal?memberId=" + loggedMember.getId();
          //RequestDispatcher rd = servletContext.getRequestDispatcher("/personal");
          //rd.forward(request, response);
        }
        
    }
}

