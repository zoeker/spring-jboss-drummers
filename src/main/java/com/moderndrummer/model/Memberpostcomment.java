package com.moderndrummer.model;

import java.io.Serializable;
import java.util.Date;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.moderndrummer.entity.transformers.DateAdapter;


/**
 * The persistent class for the memberpostcomments database table.
 * 
 */
@Entity
@Table(name = "memberpostcomments")
@NamedQuery(name = "Memberpostcomment.findAll", query = "SELECT m FROM Memberpostcomment m")
@SequenceGenerator(name = "sq_memberpostcomment", sequenceName = "sq_memberpostcomment", initialValue = 1)
public class Memberpostcomment implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int commentId;

  @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "BlogPostId", nullable = false, insertable = true, updatable = true)
  private Memberblogpost blogPost;

  

  @Column(name = "CommentBody",nullable = false)
  private String commentBody = "";

  @XmlJavaTypeAdapter(value = DateAdapter.class, type = Date.class)
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "DatePosted")
  private Date datePosted = new Date(System.currentTimeMillis());

  @OneToOne
  @JoinColumn(name = "MemberId", nullable = false)
  private Member member;

  public Memberpostcomment() {}

  public int getCommentId() {
    return this.commentId;
  }

  public void setCommentId(int commentId) {
    this.commentId = commentId;
  }

  public Memberblogpost getBlogPostId() {
    return this.blogPost;
  }

  public void setBlogPostId(Memberblogpost blogPost) {
    this.blogPost = blogPost;
  }

  public String getCommentBody() {
    return this.commentBody;
  }

  public void setCommentBody(String commentBody) {
    this.commentBody = commentBody;
  }

  public Date getDatePosted() {
    return this.datePosted;
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
  
  public Memberblogpost getBlogPost() {
    return blogPost;
  }

  public void setBlogPost(Memberblogpost blogPost) {
    this.blogPost = blogPost;
  }

}
