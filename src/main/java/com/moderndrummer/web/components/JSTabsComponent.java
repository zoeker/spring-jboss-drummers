package com.moderndrummer.web.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moderndrummer.dao.BlogsDao;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

@Component("jsTabsComponent")
public class JSTabsComponent {

    @Autowired
    TopicSearchableSelector topicSearchableSelector;

    @Autowired
    ImageSelector imageSelector;

    @Autowired
    BlogsDao blogsDao;

    @Autowired
    MemberBlogTableFacade memberBlogTableFacade;

    public String buildJSTabs() {
        return buildJSTabs(false);
    }

    public String buildJSTabs(Boolean useViewHandler) {
        final StringBuilder builder = new StringBuilder();

        builder.append("<div id=tab-container class=tab-container style=\"width:100%;\">");
        builder.append("<ul class='etabs'>");
        builder.append("<li class='tab'><a href=\"#tabs1-bloglist\">Blogs by members</a></li>");
        builder.append("<li class='tab'><a href=\"#tabs1-displayblog\">Display blog</a></li>");
        builder.append("<li class='tab'><a href=\"#tabs1-addblogs\">Add blog</a></li>");
        builder.append("<li class='tab'><a href=\"#tabs1-addblogcomments\">Add comment</a></li>");
        builder.append("</ul>");

        builder.append("<div id=\"tabs1-bloglist\" style=\"width:100%;\">");
        builder.append("<div class=search style=\"width:100%;\">");
        builder.append("<h3 class=\"ft-myriad-14\">Latest blogs</h3>");
        builder.append("<input type=hidden name=\"" + "selectedBlogId" + "\" id=\"" + "selectedBlogId" + "\" />");
        // builder.append(
        builder.append(memberBlogTableFacade.printMemberBlogPostAsTableDiv(blogsDao.getAllBlogPosts()));

        builder.append("</div>");// editor
        builder.append("</div>"); // end tabs1-editor

        builder.append("<div id=\"tabs1-displayblog\" style=\"width:100%;\">");
        builder.append("<div class=div-displayblog style=\"width:100%;\">");
        builder.append("<h3 class=\"ft-myriad-14\">Display blog</h3>");
        // add blog view
        builder.append("<div class=\"containerDiv display-blogpost\" style=\"width:95%;\">");

        builder.append("</div>");

        builder.append("<div class=\"containerDiv display-comments\" style=\"width:95%;\">");

        builder.append("</div>");

        builder.append("</div>");
        builder.append("</div>"); // end tabs1-editor

        /****/
        builder.append("<div id=\"tabs1-addblogs\" style=\"width:100%;\">");
        builder.append("<div class=statistics style=\"width:100%;\">");
        builder.append("<h3 class=\"ft-myriad-14\">Add your blog</h3>");

        builder.append("<div class=\"containerDiv\">");

        builder.append(
                "<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11\">Topic: </div><div class=\"cellDiv ft-myriad-11\">");
        builder.append(topicSearchableSelector.buildSelectorAllTopics("selecttopic",
                "inputhandler-classic-rnd m-2-imp w-70-imp", useViewHandler));
        builder.append("</div></div>");

        if (useViewHandler) {
            builder.append(
                    "<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11 w-10-imp\">Blog title:</div><div class=\"cellDiv ft-myriad-11\"><form:input type=\"text\" path=\"memberBlogPost.blogPostTitle\"  name=\"blogtitle\" id=\"blogtitle\" cssClass=\"inputhandler-classic-rnd m-2-imp w-70-imp\"></div></div>");
        } else {
            builder.append(
                    "<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11 w-10-imp\">Blog title:</div><div class=\"cellDiv ft-myriad-11\"><input type=\"text\" name=\"blogtitle\" id=\"blogtitle\" class=\"inputhandler-classic-rnd m-2-imp w-70-imp\"></div></div>");
        }

        if (useViewHandler) {
            builder.append(
                    "<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11 w-10-imp\">Blog comments:</div><div class=\"cellDiv ft-myriad-11\"><form:textarea rows=\"10\" cols=\"40\" path=\"memberBlogPost.blogPostBody\"  name=\"blogpostbody\" id=\"blogcomments\" class=\"inputhandler-classic-rnd m-2-imp w-70-imp\" ></form:textarea></div></div>");
        } else {
            builder.append(
                    "<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11 w-10-imp\">Blog comments:</div><div class=\"cellDiv ft-myriad-11\"><textarea rows=\"10\" cols=\"40\" name=\"blogpostbody\" id=\"blogpostbody\" class=\"inputhandler-classic-rnd m-2-imp w-70-imp\" ></textarea></div></div>");
        }

        builder.append(imageSelector.buildImageSelectorWithRow());

        setSubmitButtonRow(builder, WebComponentsConstants.POST);

        builder.append("</div>"); // end container

        builder.append("</div>");// editor
        builder.append("</div>"); // end tabs1-editor
        /****/

        builder.append("<div id=\"tabs1-addblogcomments\" style=\"width:100%;\">");
        builder.append("<div class=statistics style=\"width:100%;\">");
        builder.append("<h3 class=\"ft-myriad-14\">Add comment</h3>");

        builder.append("<div class=\"containerDiv postdisplay-comments\">");
        builder.append("</div>");

        builder.append("<div class=\"containerDiv\">");
        builder.append(
                "<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11 w-10-imp\">Add comment:</div><div class=\"cellDiv ft-myriad-11\"><textarea rows=\"10\" cols=\"40\" name=\"blogcomments\" id=\"blogcomments\" class=\"inputhandler-classic-rnd m-2-imp w-70-imp\"></textarea></div></div>");
        setSubmitButtonRow(builder, WebComponentsConstants.POST_COMMENT);
        builder.append("</div>");

        builder.append("</div>");// editor
        builder.append("</div>"); // end tabs1-editor

        builder.append("</div>");// end jstabs

        return builder.toString();
    }

    private void setSubmitButtonRow(final StringBuilder builder, String post) {
        if (post.equals(WebComponentsConstants.POST_COMMENT)) {
            builder.append(
                    "<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11 pad-b-5 w-10-imp\"></div><div class=\"cellDiv ft-myriad-11 pad-b-5 w-10-imp p-40-left\">");
        } else {
            builder.append(
                    "<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11 pad-b-5 w-10-imp\"></div><div class=\"cellDiv ft-myriad-11 pad-b-5 w-10-imp p-40-left\">");
        }
        builder.append(
                "<INPUT TYPE=\"SUBMIT\" class=\"styled-button f-r-m-13\" NAME=\"submitInsertForm\" id=\"submitInsertForm\" VALUE=\""
                        + post + "\" onclick=\"" + "\">");
        builder.append("</div></div>");
    }

}
