package com.moderndrummer.web.components;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

import com.moderndrummer.entity.Topic;
import com.moderndrummer.validators.StringUtilValidator;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Component("searchableSelector")
public class SearchableSelector<E> implements Serializable {

    private static final long serialVersionUID = -4279557967549196262L;

    public String buildSelector(final String elementId, final List<E> listOfElements) {
        final StringBuilder builder = new StringBuilder();
        builder.append("<div id=\"search\">");
        builder.append("<select id=\"" + elementId + "\">");
        builder.append("</select>");
        builder.append("</div>");
        return builder.toString();
    }

    public String buildSelector(final String elementId, String elementParameterClasses, final List<E> listOfElements,
            final E e) {
        final StringBuilder builder = new StringBuilder();
        builder.append("<div id=\"search\">");
        builder.append("<select id=\"" + elementId + "\"  name=\"" + elementId + "\" class=\"" + elementParameterClasses
                + "\">");
        buildOptionValues(listOfElements, e, builder);
        builder.append("</select>");
        builder.append("</div>");
        return builder.toString();
    }

    protected void buildOptionValues(final List<E> listOfElements, final E e, final StringBuilder builder) {
        if (e instanceof Topic) {
            builder.append("<option value=\"" + 0 + "\">" + " -- choose topic -- " + "</option>");
            for (final E element : listOfElements) {
                builder.append("<option value=\"" + ((Topic) element).getTopicId() + "\">"
                        + ((Topic) element).getTopicName() + "</option>");

            }
        }

    }

    protected void buildOptionValuesWithChooserValue(final List<E> listOfElements, final E e,
            final StringBuilder builder, String optionChooseValue) {
        if (!StringUtilValidator.hasValue(optionChooseValue)) {
            buildOptionValues(listOfElements, e, builder);
        } else {
            if (e instanceof Topic) {
                builder.append("<option value=\"" + 0 + "\">" + " -- choose topic -- " + "</option>");
                for (final E element : listOfElements) {
                    builder.append("<option value=\"" + ((Topic) element).getTopicId() + "\">"
                            + ((Topic) element).getTopicName() + "</option>");

                }
            }

        }
    }

    public String initScripts() {
        final StringBuilder builder = new StringBuilder();
        builder.append("<link href=\"resources/css/searchableselector/select2.css\" rel=\"stylesheet\"/>");
        builder.append("<link href=\"resources/css/searchableselector/select2-bootstrap.css\" rel=\"stylesheet\"/>");
        builder.append("<script src=\"resources/js/searchableselector/select2.js\"></script>");
        return builder.toString();

    }

    public String initSelector(final String elementId) {
        final StringBuilder builder = new StringBuilder();
        builder.append("jq('" + elementId + "').select2();");
        return builder.toString();
    }

}
