package com.moderndrummer.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Controller("logOutController")
public class LogOutController {

  @RequestMapping(value="/logout",method=RequestMethod.POST)
  public String logout(HttpSession session) {
    session.invalidate();
    return "redirect:/login";
  }
  
  @RequestMapping(value="/logout", method=RequestMethod.GET)
  public String invalidate(HttpSession session, Model model) {
    session.invalidate();
    if(model.containsAttribute("counter")) model.asMap().remove("counter");
    return "redirect:/login";
  }
}
