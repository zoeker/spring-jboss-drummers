package com.moderndrummer.data;

import java.util.Set;

import com.moderndrummer.model.Topic;
/***
 * 
 * @author conpem 2015-08-03
 *
 */

public interface TopicDao {

	Set<Topic> findAllTopics();

	public Topic findById(Integer id);
}
