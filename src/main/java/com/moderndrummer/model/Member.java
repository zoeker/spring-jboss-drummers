package com.moderndrummer.model;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

//import com.drumchops.authentication.DrumChopsRoles;
/***
 * 
 * @author conpem 2015-08-03
 *
 */

@Entity
@NamedQueries({
		@NamedQuery(name = "findMemberByUserNameOrEmail", query = "SELECT m FROM Member m WHERE m.name LIKE ?1 or m.email LIKE ?2") })

@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email") )
public class Member implements Serializable {
	/** Default value included to remove warning. Remove or modify at will. **/
	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "MemberId", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull
	@Size(min = 1, max = 30)
	@Pattern(regexp = "[A-Za-z 0-9]*", message = "must contain only letters numbers and spaces")
	@Column(name = "Name")
	private String name;

	@NotNull
	@NotEmpty
	@Email
	@Pattern(regexp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})")
	@Column(name = "Email")
	private String email;

	@Size(min = 8, max = 15)
	@Digits(fraction = 0, integer = 15)
	@Column(name = "PhoneNumber")
	private String phoneNumber;

	@NotNull
	@Size(min = 6, max = 200)
	@Column(name = "Password")
	private String password;

	@Column(name = "CreatedDate")
	private Timestamp createdDate;

	@Column(name = "MemberStory")
	// @Pattern(regexp = "[A-Za-z 0-9,-]*")
	private String memberStory;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPassword(final String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPhoneNumber(final String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Timestamp getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getMemberStory() {
		return memberStory;
	}

	public void setMemberStory(String memberStory) {
		this.memberStory = memberStory;
	}

}
