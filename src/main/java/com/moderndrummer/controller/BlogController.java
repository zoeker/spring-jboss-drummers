package com.moderndrummer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moderndrummer.data.BlogsDao;
import com.moderndrummer.data.MemberDao;
import com.moderndrummer.model.Member;
import com.moderndrummer.model.Memberblogpost;
import com.moderndrummer.model.Memberpostcomment;
import com.moderndrummer.web.components.JSTabsComponent;


@Controller("blogController")
@RequestMapping(value = "/blogs")
public class BlogController {

  
  @Autowired
  private MemberDao memberDao;
  
  @Autowired
  private BlogsDao blogsDao;
  
  
  //@Autowired
  //private JSTabsComponent jsTabsComponent;
  
  
  
  @RequestMapping( method = RequestMethod.GET)
  public String getBlogs(Model model)
  {
      model.addAttribute("blogPost", new  Memberblogpost() );
      model.addAttribute("postComment", new  Memberpostcomment() );
      
      
      return "blogs";
  }
  
  @RequestMapping(method = RequestMethod.POST)
  public String postBlog(  @Valid @ModelAttribute("blogMember") Member loginMember, BindingResult result, ModelMap model, HttpServletRequest request ){
  
    return "blogs";
  }
  
  
}
