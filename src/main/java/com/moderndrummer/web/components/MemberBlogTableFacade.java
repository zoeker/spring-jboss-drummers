package com.moderndrummer.web.components;

import java.util.Collection;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.moderndrummer.files.FileUtils;
import com.moderndrummer.messages.ModernDrummerMessages;
import com.moderndrummer.model.Member;
import com.moderndrummer.model.Memberblogpost;
import com.moderndrummer.model.Memberblogpostimage;
import com.moderndrummer.model.Memberpostcomment;
import com.moderndrummer.util.DateConverter;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Component("memberBlogTableFacade")
public class MemberBlogTableFacade {

	public String printMemberBlogPost(Memberblogpost memberBlogPost) {
		StringBuilder builder = new StringBuilder();
		printRowDiv(builder, "Title: ", memberBlogPost.getBlogPostTitle());
		printRowDiv(builder, "Message: ", memberBlogPost.getBlogPostBody());
		printRowDiv(builder, "Posted date: ", DateConverter.getDateTimeFormat(memberBlogPost.getDatePosted()));
		Member member = memberBlogPost.getMember();
		printRowDiv(builder, "Posted by: ", member.getName());
		builder.append(printMemberBlogImages(memberBlogPost, WebComponentsParameters.DESTINATION_DIR_PATH));

		return builder.toString();
	}

	public String printMemberBlogPostComment(Memberpostcomment comment) {
		StringBuilder builder = new StringBuilder();
		printRowDiv(builder, "Message: ", comment.getCommentBody());
		printRowDiv(builder, "Commented date: ", DateConverter.getDateTimeFormat(comment.getDatePosted()));
		Member member = comment.getBlogPost().getMember();
		printRowDiv(builder, "Commented by: ", member.getName());
		Member memberComment = comment.getBlogPost().getMember();
		printRowDiv(builder, "Commented on Post: ", " by " + memberComment.getName());
		builder.append(printMemberBlogPost(comment.getBlogPost()));

		return builder.toString();
	}

	private void printDivHeader(final StringBuilder stringBuilder) {
		stringBuilder.append("<div class=\"rowDivHeader\">");
		stringBuilder.append("<div class=\"cellDivHeader ft-myriad-11\" ></div>");
		stringBuilder.append("<div class=\"cellDivHeader ft-myriad-11\" >Title</div>");
		stringBuilder.append("<div class=\"cellDivHeader ft-myriad-11\" >Message</div>");
		stringBuilder.append("<div class=\"cellDivHeader ft-myriad-11\" >Topic</div>");
		stringBuilder.append("<div class=\"cellDivHeader ft-myriad-11\" >Posted by</div>");
		stringBuilder.append("<div class=\"cellDivHeader ft-myriad-11\" >Date</div>");
		stringBuilder.append("<div class=\"cellDivHeader ft-myriad-11\" >Images</div>");
		stringBuilder.append("<div class=\"cellDivHeader ft-myriad-11\" >Comments</div>");
		stringBuilder.append("</div>");

	}

	public String printMemberBlogPostAsTableDiv(Collection<Memberblogpost> memberBlogPosts) {
		StringBuilder builder = new StringBuilder();

		builder.append("<div class=\"containerDiv\" style=\"width:95%;\">");
		printDivHeader(builder);
		for (Memberblogpost memberBlogPost : memberBlogPosts) {
			builder.append("<div class=\"rowDiv\"   >");
			printColumnCheckBox(builder, memberBlogPost);
			printColumnDiv(builder, memberBlogPost.getBlogPostTitle());
			printColumnDiv(builder, memberBlogPost.getBlogPostBody());
			printColumnDiv(builder, memberBlogPost.getTopic().getTopicName());
			Member member = memberBlogPost.getMember();
			printColumnDiv(builder, member.getName());
			printColumnDiv(builder, DateConverter.getDateTimeFormat(memberBlogPost.getDatePosted()));
			printColumnDiv(builder, memberBlogPost.getMemberBlogPostImages().isEmpty() ? "No" : "Yes");
			printColumnDiv(builder, memberBlogPost.getMemberBlogPostComments().isEmpty() ? "No" : "Yes");

			builder.append("</div>");

		}

		builder.append("</div>");

		return builder.toString();
	}

	private void printColumnCheckBox(StringBuilder builder, Memberblogpost memberBlogPost) {
		builder.append(WebComponentsConstants.CELL_DIV + "<input type=\"checkbox\" id=\"blogpost_"
				+ memberBlogPost.getBlogPostId() + "\"  name=\"memberBlogGroup\" class=fl-mrg-5-left value=\""
				+ memberBlogPost.getBlogPostId() + "\" />" + WebComponentsConstants.END_DIV);
	}

	public String printMemberBlogImages(final Memberblogpost memberBlogPost, final String destinationPath) {
		final StringBuilder stringBuilder = new StringBuilder();
		Set<Memberblogpostimage> images = memberBlogPost.getMemberBlogPostImages();
		if (!images.isEmpty()) {

			stringBuilder.append(WebComponentsConstants.CONTAINER_DIV);
			for (final Memberblogpostimage graphic : images) {
				if (FileUtils.isValidFileName(graphic.getFileName())) {
					stringBuilder.append("<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11\">");
					stringBuilder.append(printImage(graphic.getFileName().trim(), destinationPath));
					stringBuilder.append(WebComponentsConstants.END_DIV).append(WebComponentsConstants.END_DIV);
				}

			}

			stringBuilder.append(WebComponentsConstants.END_DIV);

		}

		return stringBuilder.toString();
	}

	protected String printImage(final String fileName, final String destinationPath) {

		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("<div class=\"image-collection-item p-left-5\" style=\"float: left;\">");
		stringBuilder.append("<a class=\"gallery-image\" href=\"" + destinationPath + "/" + fileName + "\" title=\""
				+ fileName + "\" style=\"float: left;\">");
		stringBuilder.append("<img border=2 src=\"" + destinationPath + "/" + fileName + "\"  ");
		stringBuilder.append("class=\"divelog-collection-item2 image-rounded divelog-item-shaded\" ");
		stringBuilder.append("style=\"margin-top: 30px;\" /> </a>");
		stringBuilder.append(WebComponentsConstants.END_DIV);

		return stringBuilder.toString();
	}

	protected void printRowDiv(final StringBuilder stringBuilder, final String column, final String value) {
		stringBuilder.append(WebComponentsConstants.ROW_DIV);
		stringBuilder.append(WebComponentsConstants.CELL_DIV + column);
		stringBuilder.append(WebComponentsConstants.END_DIV);
		stringBuilder.append(WebComponentsConstants.CELL_DIV + value + WebComponentsConstants.END_DIV);
		stringBuilder.append(WebComponentsConstants.END_DIV);
	}

	protected void printColumnDiv(final StringBuilder stringBuilder, final String value) {
		if (value != null && value.length() > 0) {
			stringBuilder.append(WebComponentsConstants.CELL_DIV + value + WebComponentsConstants.END_DIV);
		} else {
			stringBuilder.append(
					WebComponentsConstants.CELL_DIV + ModernDrummerMessages.NOT_FOUND + WebComponentsConstants.END_DIV);

		}

	}

}
