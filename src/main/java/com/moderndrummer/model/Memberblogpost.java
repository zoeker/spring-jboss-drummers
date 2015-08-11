package com.moderndrummer.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.moderndrummer.entity.transformers.DateAdapter;

/***
 * 
 * @author conpem 2015-08-03
 *
 */

/**
 * The persistent class for the memberblogpost database table.
 * 
 */
@Entity
@NamedQuery(name = "Memberblogpost.findAll", query = "SELECT m FROM Memberblogpost m order by m.datePosted desc")
@SequenceGenerator(name = "sq_memberblogpost", sequenceName = "sq_memberblogpost", initialValue = 1)
public class Memberblogpost implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int blogPostId;

  @Column(name = "BlogPostBody")
  private String blogPostBody = "";

  @Column(name = "BlogPostTitle")
  private String blogPostTitle = "";

  @XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DatePosted")
  private Date datePosted = new Date(System.currentTimeMillis());

  @OneToOne
  @JoinColumn(name = "MemberAuthorId", nullable = false)
  private Member member = new Member();

  @ManyToOne
  @JoinColumn(name = "TopicId", nullable = false)
  private Topic topic = new Topic();

  @OneToMany(mappedBy = "blogPost",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  //@JoinColumn(name = "BlogPostId", nullable = false)
  private Set<Memberblogpostimage> blogPostImages = new HashSet<Memberblogpostimage>();

  @OneToMany(mappedBy = "blogPost",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  //@JoinColumn(name = "BlogPostId", nullable = false)
  private Set<Memberpostcomment> blogPostComments = new HashSet<Memberpostcomment>();

  public Memberblogpost() {}

  public int getBlogPostId() {
    return this.blogPostId;
  }

  public void setBlogPostId(int blogPostId) {
    this.blogPostId = blogPostId;
  }

  public String getBlogPostBody() {
    return this.blogPostBody;
  }

  public void setBlogPostBody(String blogPostBody) {
    this.blogPostBody = blogPostBody;
  }

  public String getBlogPostTitle() {
    return this.blogPostTitle;
  }

  public void setBlogPostTitle(String blogPostTitle) {
    this.blogPostTitle = blogPostTitle;
  }

  public Date getDatePosted() {
    return datePosted;
  }

  public void setDatePosted(Date datePosted) {
    this.datePosted = datePosted;
  }

  public Member getMember() {
    return this.member;
  }

  public void setMember(Member member) {
    this.member = member;
  }

  public Topic  getTopic() {
    return this.topic;
  }

  public void setTopic(Topic  topic) {
    this.topic = topic;
  }

  public Set<Memberblogpostimage> getMemberBlogPostImages() {
    return this.blogPostImages;
  }

  public void setMemberBlogPostImages(Collection<Memberblogpostimage> images) {
    if(!this.blogPostImages.isEmpty()){
      this.blogPostImages.clear();
    }
    this.blogPostImages.addAll(images);
  }

  public Memberblogpostimage addMemberblogpostimage(Memberblogpostimage memberblogpostimage) {
    getMemberBlogPostImages().add(memberblogpostimage);
    memberblogpostimage.setMemberBlogPost(this);
    return memberblogpostimage;
  }

  public Memberblogpostimage removeMemberblogpostimage(Memberblogpostimage memberblogpostimage) {
    getMemberBlogPostImages().remove(memberblogpostimage);
    memberblogpostimage.setMemberBlogPost(null);
    return memberblogpostimage;
  }
  
  
  public Set<Memberpostcomment> getMemberBlogPostComments() {
    return this.blogPostComments;
  }

  public void setMemberBlogPostComments(Collection<Memberpostcomment> comments) {
    if(!this.blogPostComments.isEmpty()){
      this.blogPostComments.clear();
    }
    this.blogPostComments.addAll(comments);
  }

  public Memberpostcomment addMemberBlogPostComment(Memberpostcomment comment) {
    blogPostComments.add(comment);
    comment.setBlogPost(this);
    return comment;
  }

  public Memberpostcomment removeMemberblogpostimage(Memberpostcomment comment) {
	  blogPostComments.remove(comment);
    comment.setBlogPost(null);
    return comment;
  }

}
