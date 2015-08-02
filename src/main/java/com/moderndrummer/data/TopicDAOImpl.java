package com.moderndrummer.data;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moderndrummer.model.Memberblogpost;
import com.moderndrummer.model.Topic;
import com.moderndrummer.repo.base.BaseJPQLDao;

@Repository//("blogsDao")
@Transactional
public class TopicDAOImpl  extends BaseJPQLDao implements TopicDAO {

	
	 private static final Logger LOGGER = LoggerFactory.getLogger(BlogsDaoImpl.class);

	 
	 @Override
	  public Set<Topic> findAllTopics(){
	    return asSet(executeNamedQueryReturnList("Topic.findAll",Topic.class));
	  }
}
