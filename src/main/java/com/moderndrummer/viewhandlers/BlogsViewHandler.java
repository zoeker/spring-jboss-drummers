package com.moderndrummer.viewhandlers;

import java.io.Serializable;

import com.moderndrummer.model.Memberblogpost;
import com.moderndrummer.model.Memberblogpostimage;
import com.moderndrummer.model.Memberpostcomment;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

public class BlogsViewHandler implements Serializable {

    private Memberblogpost memberBlogPost;
    private Memberblogpostimage memberBlogPostImage;
    private Memberpostcomment memberPostComment;

    public Memberblogpost getMemberBlogPost() {
        return memberBlogPost;
    }

    public void setMemberBlogPost(Memberblogpost memberBlogPost) {
        this.memberBlogPost = memberBlogPost;
    }

    public Memberblogpostimage getMemberBlogPostImage() {
        return memberBlogPostImage;
    }

    public void setMemberBlogPostImage(Memberblogpostimage memberBlogPostImage) {
        this.memberBlogPostImage = memberBlogPostImage;
    }

    public Memberpostcomment getMemberPostComment() {
        return memberPostComment;
    }

    public void setMemberPostComment(Memberpostcomment memberPostComment) {
        this.memberPostComment = memberPostComment;
    }

}
