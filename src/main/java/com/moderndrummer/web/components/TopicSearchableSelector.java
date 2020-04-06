package com.moderndrummer.web.components;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.moderndrummer.dao.TopicDao;
import com.moderndrummer.model.Topic;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Component("topicSearchableSelector")
public class TopicSearchableSelector extends SearchableSelector<Topic> implements Serializable {

    private static final long serialVersionUID = -7735771752201224531L;

    @Autowired
    private TopicDao topicDao;

    @Override
    public String buildSelector(final String elementId, final List<Topic> listOfElements) {
        final StringBuilder builder = new StringBuilder();
        builder.append("<div id=\"search\">");
        builder.append("<select id=\"" + elementId + "\" class=\"inputhandler-classic-rnd m-2-imp w-70-imp\">");
        buildOptionValues(listOfElements, new Topic(), builder);
        builder.append("</select>");
        builder.append("</div>");
        return builder.toString();
    }

    public String buildSelector(final String elementId, final List<Topic> listOfElements, final String classes) {
        return printSelector(elementId, listOfElements, classes, false);
    }

    public String buildSelectorAllTopics(final String elementId, final String classes, boolean useViewHandler) {
        Set<Topic> topics = topicDao.findAllTopics();
        return printSelector(elementId, new ArrayList<Topic>(topics), classes, useViewHandler);
    }

    private String printSelector(final String elementId, final List<Topic> listOfElements, final String classes,
            Boolean useViewHandler) {
        final StringBuilder builder = new StringBuilder();
        builder.append("<div id=\"search\">");

        if (!useViewHandler) {
            builder.append("<select name=\"" + elementId + "\" id=\"" + elementId + "\" class=\"" + classes + "\">");
        } else {
            builder.append("<form:select path=\"memberBlogPost.topic\" name=\"" + elementId + "\" id=\"" + elementId
                    + "\" cssClass=\"" + classes + "\">");
        }
        // <form:select path="myFormVariable">

        buildOptionValues(listOfElements, new Topic(), builder);
        if (!useViewHandler) {
            builder.append("</select>");
        } else {
            builder.append("</form:select>");
        }

        builder.append("</div>");
        return builder.toString();
    }

    public String buildSelector(final String elementId, final List<Topic> listOfElements, final String classes,
            String optionValue) {
        final StringBuilder builder = new StringBuilder();
        builder.append("<div id=\"search\">");
        builder.append("<select name=\"" + elementId + "\" id=\"" + elementId + "\" class=\"" + classes + "\">");
        buildOptionValuesWithChooserValue(listOfElements, new Topic(), builder, optionValue);
        builder.append("</select>");
        builder.append("</div>");
        return builder.toString();
    }

}
