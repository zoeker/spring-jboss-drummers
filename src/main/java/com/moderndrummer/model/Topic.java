package com.moderndrummer.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
/**
 * The persistent class for the topic database table.
 * 
 */
@Entity
@NamedQuery(name = "Topic.findAll", query = "SELECT t FROM Topic t order by t.topicName asc")
@SequenceGenerator(name = "sq_topic", sequenceName = "sq_topic", initialValue = 1)
public class Topic implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int topicId;

	@Column(name = "TopicDescription")
	private String topicDescription = "";

	@Column(name = "TopicName")
	private String topicName = "";

	public Topic() {
	}

	public int getTopicId() {
		return this.topicId;
	}

	public void setTopicId(int topicId) {
		this.topicId = topicId;
	}

	public String getTopicDescription() {
		return this.topicDescription;
	}

	public void setTopicDescription(String topicDescription) {
		this.topicDescription = topicDescription;
	}

	public String getTopicName() {
		return this.topicName;
	}

	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}

}
