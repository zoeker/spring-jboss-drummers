package com.moderndrummer.model;

import java.io.Serializable;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */


/**
 * The persistent class for the memberblogpostimages database table.
 * 
 */
@Entity
@Table(name = "memberblogpostimages")
@NamedQuery(name = "Memberblogpostimage.findAll", query = "SELECT m FROM Memberblogpostimage m")
@SequenceGenerator(name = "sq_memberblogpostimage", sequenceName = "sq_memberblogpostimage", initialValue = 1)
public class Memberblogpostimage implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int memberBlogPostImageId;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "BlogPostId", nullable = false, insertable = true, updatable = true)
	private Memberblogpost blogPost;

	@Column(name = "FileName", nullable = false)
	private String fileName = "";

	public Memberblogpostimage() {
	}

	public int getMemberBlogPostImageId() {
		return this.memberBlogPostImageId;
	}

	public void setMemberBlogPostImageId(int memberBlogPostImageId) {
		this.memberBlogPostImageId = memberBlogPostImageId;
	}

	public String getFileName() {
		return this.fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Memberblogpost getMemberBlogPost() {
		return this.blogPost;
	}

	public void setMemberBlogPost(Memberblogpost blogpost) {
		this.blogPost = blogpost;
	}

}
