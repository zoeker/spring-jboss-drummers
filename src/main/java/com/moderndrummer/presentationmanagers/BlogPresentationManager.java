package com.moderndrummer.presentationmanagers;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moderndrummer.dao.MemberDao;
import com.moderndrummer.dao.TopicDao;
import com.moderndrummer.entity.Member;
import com.moderndrummer.entity.Memberblogpost;
import com.moderndrummer.entity.Memberpostcomment;
import com.moderndrummer.entity.exceptions.EntityParseException;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Component("blogPresentationManager")
public class BlogPresentationManager extends PresentationManager<Memberblogpost> {

    @Autowired
    MemberDao memberDao;

    @Autowired
    TopicDao topicDao;

    @Override
    public Memberblogpost buildEntity(Map<String, String> parameters, Member loggedMember)
            throws com.moderndrummer.entity.exceptions.EntityParseException {
        Memberblogpost post = buildEntity(parameters);
        post.setMember(memberDao.findById(loggedMember.getId()));
        return post;
    }

    public Memberpostcomment buildEntityComment(Map<String, String> parameters, Member loggedMember, int selectedBlogId)
            throws EntityParseException {
        Memberpostcomment comment = buildEntityComment(parameters);
        comment.setMember(memberDao.findById(loggedMember.getId()));
        return comment;
    }

    @Override
    public Memberblogpost buildEntity(Map<String, String> parameters) throws EntityParseException {
        Memberblogpost post = new Memberblogpost();
        post.setBlogPostTitle(parameters.get("blogtitle"));
        post.setDatePosted(new Date(System.currentTimeMillis()));
        post.setBlogPostBody(parameters.get("blogpostbody"));
        post.setTopic(topicDao.findById(Integer.valueOf(parameters.get("selecttopic"))));

        return post;
    }

    public Memberpostcomment buildEntityComment(Map<String, String> parameters) throws EntityParseException {
        Memberpostcomment comment = new Memberpostcomment();
        comment.setCommentBody(parameters.get("blogcomments"));
        comment.setDatePosted(new Date(System.currentTimeMillis()));
        return comment;
    }

}
