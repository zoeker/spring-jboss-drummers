package com.moderndrummer.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.moderndrummer.data.BlogsDao;
import com.moderndrummer.data.MemberDao;
import com.moderndrummer.entity.exceptions.BlogJPAException;
import com.moderndrummer.entity.exceptions.EntityParseException;
import com.moderndrummer.entity.exceptions.ModernDrummerException;
import com.moderndrummer.enums.GraphicType;
import com.moderndrummer.model.Member;
import com.moderndrummer.model.Memberblogpost;
import com.moderndrummer.model.Memberblogpostimage;
import com.moderndrummer.model.Memberpostcomment;
import com.moderndrummer.presentationmanagers.BlogPresentationManager;
import com.moderndrummer.requesthandler.FileItemRequestHandler;
import com.moderndrummer.viewhandlers.BlogsViewHandler;
import com.moderndrummer.web.components.WebComponentsConstants;
/***
 * 
 * @author conpem 2015-08-03
 *
 */


@Controller("blogController")
@RequestMapping(value = "/blogs")
public class BlogController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(BlogController.class);

	@Autowired
	private MemberDao memberDao;

	@Autowired
	private BlogsDao blogsDao;

	@Autowired
	private FileItemRequestHandler fileItemRequestHandler;

	@Autowired
	private BlogPresentationManager blogPresentationManager;

	private Member loggedMember = new Member();

	// @Autowired
	// private JSTabsComponent jsTabsComponent;

	@RequestMapping(method = RequestMethod.GET)
	public String getBlogs(Model model, HttpServletRequest request) {
		model.addAttribute("blogPost", new Memberblogpost());
		model.addAttribute("postComment", new Memberpostcomment());
		loggedMember = (Member) request.getSession().getAttribute("loggedUser");
		model.addAttribute("loggedMember", loggedMember);

		return "blogs";
	}

	@RequestMapping(method = RequestMethod.POST)
	// public String postBlog( @Valid @ModelAttribute("blogMember") Member
	// loginMember, BindingResult result, ModelMap model, HttpServletRequest
	// request ){
	public String postBlog(@Valid BlogsViewHandler blogsViewHandler, BindingResult result, ModelMap model,
			HttpServletRequest request) {
		try {
			Map<String, String> mapData = fileItemRequestHandler.getRequestParameters(request, GraphicType.BLOG_IMAGE);
			String btn = mapData.get("submitInsertForm");
			if (btn != null && btn.equals(WebComponentsConstants.POST)) {
				Memberblogpost inserted = postBlog(mapData);
				model.addAttribute("blogPost", inserted);
			} else if (btn != null && btn.equals(WebComponentsConstants.POST_COMMENT)) {
				 Memberpostcomment inserted = postBlogComment(model, mapData);
				 model.addAttribute("postComment", inserted );
			}
			
		} catch (EntityParseException | BlogJPAException e) {
			LOGGER.error("error post request blog controller", e);
			model.addAttribute("errorMessage", "failed to insert blog");
		}
		return "blogs";
	}

	private Memberpostcomment postBlogComment(ModelMap model, Map<String, String> mapData) throws EntityParseException {
		int selectedBlogId = Integer.valueOf(mapData.get("selectedBlogId"));
		 if(selectedBlogId > 0){
		 //  postComment(request, response, loggedUser, parameters, selectedBlogId);
			 Memberpostcomment comment = blogPresentationManager.buildEntityComment(mapData,loggedMember,selectedBlogId);
		     Memberblogpost post  = blogsDao.findBlogPostById(selectedBlogId);
		     //post.addBlogPostComment(comment);
		     comment.setBlogPost(post);
		     return blogsDao.insertTruly(comment);
		    
		 }
		 else{
		   throw new ModernDrummerException("Choose blog to post comment on");
		 }
	}

	private Memberblogpost postBlog(Map<String, String> mapData) throws EntityParseException {
		List<Memberblogpostimage> images = (List<Memberblogpostimage>) fileItemRequestHandler.getListGraphics();
		Memberblogpost blogPost = blogPresentationManager.buildEntity(mapData, loggedMember);
		if (!images.isEmpty()) {
			blogPost.setMemberBlogPostImages(images);
		}
		Memberblogpost inserted = blogsDao.insert(blogPost);
		fileItemRequestHandler.clearListGraphics();
		return inserted;
	}

}
