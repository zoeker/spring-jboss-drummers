package com.moderndrummer.data;

import java.util.Set;

import com.moderndrummer.model.Topic;

public interface TopicDAO {

	Set<Topic> findAllTopics();

}
