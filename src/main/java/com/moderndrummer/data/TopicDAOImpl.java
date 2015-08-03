package com.moderndrummer.data;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moderndrummer.model.Topic;
import com.moderndrummer.repo.base.BaseJPQLDao;
/***
 * 
 * @author conpem 2015-08-03
 *
 */

@Repository//("blogsDao")
@Transactional
public class TopicDaoImpl  extends BaseJPQLDao implements TopicDao {

	
	 private static final Logger LOGGER = LoggerFactory.getLogger(BlogsDaoImpl.class);

	 
	 @Override
	  public Set<Topic> findAllTopics(){
	    return asSet(executeNamedQueryReturnList("Topic.findAll",Topic.class));
	  }
	 
	 @Override
	  public Topic findById(Integer id)
	  {
	      return (Topic) find(id, Topic.class);
	  }
}
