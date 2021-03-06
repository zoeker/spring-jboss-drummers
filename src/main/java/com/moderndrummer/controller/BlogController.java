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

import com.moderndrummer.dao.BlogsDao;
import com.moderndrummer.dao.MemberDao;
import com.moderndrummer.entity.Member;
import com.moderndrummer.entity.Memberblogpost;
import com.moderndrummer.entity.Memberblogpostimage;
import com.moderndrummer.entity.Memberpostcomment;
import com.moderndrummer.entity.exceptions.BlogJPAException;
import com.moderndrummer.entity.exceptions.EntityParseException;
import com.moderndrummer.entity.exceptions.InvalidAttributeException;
import com.moderndrummer.enums.GraphicType;
import com.moderndrummer.presentationmanagers.BlogPresentationManager;
import com.moderndrummer.requesthandler.FileItemRequestHandler;
import com.moderndrummer.util.ObjectUtil;
import com.moderndrummer.validators.StringUtilValidator;
import com.moderndrummer.viewhandlers.BlogsViewHandler;
import com.moderndrummer.web.components.WebComponentsConstants;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
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

    @RequestMapping(method = RequestMethod.GET)
    public String getBlogs(Model model, HttpServletRequest request) {
        Member loggedMember = (Member) request.getSession().getAttribute("loggedUser");
        if (ObjectUtil.verifyMemberExists(loggedMember)) {

            model.addAttribute("blogPost", new Memberblogpost());
            model.addAttribute("postComment", new Memberpostcomment());
            loggedMember = (Member) request.getSession().getAttribute("loggedUser");
            model.addAttribute("loggedMember", loggedMember);

            return "blogs";

        } else {
            return "redirect:login";
        }
    }

    @RequestMapping(method = RequestMethod.POST)
    public String postBlog(@Valid BlogsViewHandler blogsViewHandler, BindingResult result, ModelMap model,
            HttpServletRequest request) {
        try {
            Member loggedMember = (Member) request.getSession().getAttribute("loggedUser");
            if (!ObjectUtil.verifyMemberExists(loggedMember)) {
                return "redirect:login";
            } else {
                Map<String, String> mapData = fileItemRequestHandler.getRequestParameters(request,
                        GraphicType.BLOG_IMAGE);
                String btn = mapData.get("submitInsertForm");
                if (btn != null && btn.equals(WebComponentsConstants.POST)) {
                    Memberblogpost inserted = postBlog(mapData, loggedMember);
                    model.addAttribute("blogPost", inserted);
                } else if (btn != null && btn.equals(WebComponentsConstants.POST_COMMENT)) {
                    Memberblogpost inserted = postBlogComment(model, mapData, loggedMember);
                    model.addAttribute("blogPost", inserted);
                }
            }

        } catch (EntityParseException | BlogJPAException e) {
            LOGGER.error("error post request blog controller", e);
            model.addAttribute("errorMessage", "failed to insert blog");
        }
        return "blogs";
    }

    private Memberblogpost postBlogComment(ModelMap model, Map<String, String> mapData, Member loggedMember)
            throws EntityParseException {
        try {
            if (StringUtilValidator.isEmptyOrNull(mapData.get("selectedBlogId"))) {
                throw new InvalidAttributeException("Choose blog.");
            }
            int selectedBlogId = Integer.valueOf(mapData.get("selectedBlogId"));
            if (selectedBlogId > 0) {
                Memberpostcomment comment = blogPresentationManager.buildEntityComment(mapData, loggedMember,
                        selectedBlogId);
                Memberblogpost post = blogsDao.findBlogPostById(selectedBlogId);
                comment.setBlogPost(post);
                post.addMemberBlogPostComment(comment);
                Memberblogpost updated = blogsDao.update(post);
                return updated;
            } else {
                throw new EntityParseException("Choose blog to post comment on");
            }

        } catch (InvalidAttributeException | NumberFormatException e) {
            throw new EntityParseException(e.getMessage());
        }

    }

    private Memberblogpost postBlog(Map<String, String> mapData, Member loggedMember) throws EntityParseException {
        List<Memberblogpostimage> images = fileItemRequestHandler.getListGraphics();
        Memberblogpost blogPost = blogPresentationManager.buildEntity(mapData, loggedMember);
        if (!images.isEmpty()) {
            blogPost.setMemberBlogPostImages(images);
        }
        Memberblogpost inserted = blogsDao.insert(blogPost);
        fileItemRequestHandler.clearListGraphics();
        return inserted;
    }

}
