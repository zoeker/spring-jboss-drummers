package com.moderndrummer.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.moderndrummer.dao.BlogsDao;
import com.moderndrummer.entity.Member;
import com.moderndrummer.entity.Memberblogpost;
import com.moderndrummer.entity.Memberblogpostimage;
import com.moderndrummer.entity.Memberpostcomment;
import com.moderndrummer.util.DateConverter;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

@Controller("blogRestController")
@RequestMapping("/rest/blogs")
public class BlogRestController {
    @Autowired
    private BlogsDao blogsDao;

    @Context
    private UriInfo uriInfo;

    private static final Logger LOGGER = LoggerFactory.getLogger("errorslog");

    // JSONResponse

    @RequestMapping(value = "/get/json/memberblog/{blogPostId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON)
    public @ResponseBody List<Map<String, Object>> getMemberBlogPost(@PathVariable("blogPostId") final int blogPostId) {

        Memberblogpost memberBlogPost = blogsDao.findBlogPostById(blogPostId);
        Map<String, Object> memberBlogPostMap = new HashMap<String, Object>();
        List<Map<String, Object>> blogGraphics = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> memberBlogList = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> commentList = new ArrayList<Map<String, Object>>();
        try {

            if (memberBlogPost.getBlogPostId() > 0) {
                Set<Memberblogpostimage> images = memberBlogPost.getMemberBlogPostImages();
                for (final Memberblogpostimage image : images) {
                    if (com.moderndrummer.validators.StringUtilValidator.hasValue(image.getFileName())) {
                        final Map<String, Object> graphic = new HashMap<String, Object>();
                        graphic.put("fileLocation", image.getFileName());
                        graphic.put("graphicid", image.getMemberBlogPostImageId());
                        blogGraphics.add(graphic);
                    }
                }

                for (final Memberpostcomment comment : memberBlogPost.getMemberBlogPostComments()) {
                    final Map<String, Object> commentMap = new HashMap<String, Object>();
                    commentMap.put("message", comment.getCommentBody());
                    commentMap.put("postedDate", DateConverter.getDateTimeFormat(comment.getDatePosted()));
                    Member member = comment.getMember();
                    commentMap.put("postedBy", member.getName());

                    commentList.add(commentMap);

                }

                memberBlogPostMap.put("bloginfo", getMemberBlogInfo(memberBlogPost));
                memberBlogPostMap.put("graphics", blogGraphics);
                memberBlogPostMap.put("comments", commentList);

                memberBlogList.add(memberBlogPostMap);

            }

        } catch (RuntimeException e) {
            log(e);
        }

        return memberBlogList;

    }

    private Map<String, Object> getMemberBlogInfo(Memberblogpost memberBlogPost) {
        final Map<String, Object> data = new HashMap<String, Object>();
        data.put("memberBlogPostId", memberBlogPost.getBlogPostId());
        data.put("title", memberBlogPost.getBlogPostTitle());
        data.put("message", memberBlogPost.getBlogPostBody());
        data.put("postedDate", DateConverter.getDateTimeFormat(memberBlogPost.getDatePosted()));
        data.put("postedBy", memberBlogPost.getMember().getName());
        data.put("topic", memberBlogPost.getTopic().getTopicName());

        return data;
    }

    private void log(final Exception e) {
        LOGGER.info(e.getMessage());
        LOGGER.error("Exception", e);
    }
}
