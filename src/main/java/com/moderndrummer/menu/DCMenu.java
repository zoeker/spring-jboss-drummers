package com.moderndrummer.menu;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class DCMenu {

    public static String buildMenu() {
        final StringBuilder builder = new StringBuilder();
        builder.append("<header id=\"header\">");
        builder.append("<nav class=\"nav m-l-9-imp\">");
        builder.append("<ul>");
        builder.append("<li><a href=\"/ModernDrummer/personal\">Profile</a></li>");
        builder.append("<li><a href=\"/ModernDrummer/videos\">Drum videos</a></li>");
        builder.append("<li><a href=\"#\">Lessons</a></li>");
        builder.append("<li><a href=\"#\">Studio</a></li>");
        builder.append("<li><a href=\"#\">Chat</a></li>");
        builder.append("<li><a href=\"/ModernDrummer/blogs\">Blogs by members</a></li>");
        builder.append("<li><a href=\"/ModernDrummer/logout\">Logout</a></li>");
        builder.append("</ul>");
        builder.append("</nav>");
        builder.append("</header>");
        return builder.toString();
    }

}
