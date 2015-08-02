package com.moderndrummer.web.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moderndrummer.data.TopicDAO;
import com.moderndrummer.model.Topic;


@Component("topicSearchableSelector")
public class TopicSearchableSelector extends SearchableSelector<Topic> implements Serializable {

  private static final long serialVersionUID = -7735771752201224531L;
  
  @Autowired
  private TopicDAO topicDao;

  @Override
  public String buildSelector(final String elementId, final List<Topic> listOfElements) {
    final StringBuilder builder = new StringBuilder();
    builder.append("<div id=\"search\">");
    builder.append("<select id=\"" + elementId
        + "\" class=\"inputhandler-classic-rnd m-2-imp w-70-imp\">");
    buildOptionValues(listOfElements, new Topic(), builder);
    builder.append("</select>");
    builder.append("</div>");
    return builder.toString();
  }

  public String buildSelector(final String elementId, final List<Topic> listOfElements,
      final String classes) {
    return printSelector(elementId, listOfElements, classes);
  }
  
  public String buildSelectorAllTopics(final String elementId, 
      final String classes) {
    Set<Topic> topics = topicDao.findAllTopics();
    return printSelector(elementId,new ArrayList<Topic>(topics) , classes);
  }

  private String printSelector(final String elementId, final List<Topic> listOfElements,
      final String classes) {
    final StringBuilder builder = new StringBuilder();
    builder.append("<div id=\"search\">");
    builder.append("<select name=\"" + elementId + "\" id=\"" + elementId + "\" class=\"" + classes
        + "\">");
    buildOptionValues(listOfElements, new Topic(), builder);
    builder.append("</select>");
    builder.append("</div>");
    return builder.toString();
  }

  public String buildSelector(final String elementId, final List<Topic> listOfElements,
      final String classes, String optionValue) {
    final StringBuilder builder = new StringBuilder();
    builder.append("<div id=\"search\">");
    builder.append("<select name=\"" + elementId + "\" id=\"" + elementId + "\" class=\"" + classes
        + "\">");
    buildOptionValuesWithChooserValue(listOfElements, new Topic(), builder, optionValue);
    builder.append("</select>");
    builder.append("</div>");
    return builder.toString();
  }


}
