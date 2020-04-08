package com.moderndrummer.dao;

import java.util.Set;

import com.moderndrummer.entity.Topic;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

public interface TopicDao {

    Set<Topic> findAllTopics();

    public Topic findById(Integer id);
}
