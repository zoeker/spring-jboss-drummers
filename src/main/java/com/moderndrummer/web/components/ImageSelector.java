package com.moderndrummer.web.components;

import org.springframework.stereotype.Component;

import com.moderndrummer.messages.ModernDrummerMessages;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
@Component("imageSelector")
public class ImageSelector {

    public String buildImageSelector(String image) {
        final StringBuilder builder = new StringBuilder();
        builder.append("<div id=\"imageselector\" class=\"imageselector\">");
        builder.append("<div id=\"imageviewer\" class=\"imageviewer\" style=\"float:left;\">");
        builder.append(
                "<label class=\"signup font-layout-data-tables bottom-spacing-20\" for=\"file1\" style=\"display: block;\">Upload files (images) &nbsp;</label><img class=\"image-rounded image-shadowing image-rounded-10 top-spacing-20\" src=\"resources/images/"
                        + image + "\" title=\"" + ModernDrummerMessages.IMAGE_BROWSE
                        + "\" width=150px height=100px name=profile_image id=profile_image></img>");
        builder.append("</div>");
        buildFileButtonWrapper(builder);

        builder.append("</div>");
        return builder.toString();
    }

    private void buildFileButtonWrapper(final StringBuilder builder) {
        builder.append("<div class='file_browse_wrapper' title=\"add pictures by browse and press update\">");
        builder.append(
                "<input class=\"file_browse file\" type=\"file\" size=\"35\" name=\"file1\" id=\"file1\" multiple></input>");
        builder.append("</div>");
    }

    public String imageSelectButton() {
        final StringBuilder builder = new StringBuilder();
        builder.append("<div class=\"image-radio-add\">");
        builder.append(
                "<input id=\"radioAddProfileImageGroup\" type=\"radio\" name=\"radioAddProfileImage\" value=\"radioAddProfileImage_1\">");
        builder.append("<label for=\"radioAddProfileImageGroup\" class=\"label-profile-radio\">Add image</label>");
        builder.append("</div>");
        return builder.toString();
    }

    public String buildImageSelectorWithRow() {
        final StringBuilder builderContent = new StringBuilder();
        builderContent.append("<div class=\"rowDiv\"><div class=\"cellDiv ft-myriad-11 w-10-imp\">");

        builderContent.append(
                "<label class=\"signup font-layout-data-tables bottom-spacing-20\" for=\"profile_image\">Upload files (images/videos) (Max 500MB) &nbsp;<img class=\"image-rounded image-shadowing image-rounded-10 top-spacing-20\" src=\"resources/images/scuba-diving-vector-background.jpg\" title=\"if browsed to a new file of image format, the picture is added and will be processed with the divelog\" width=150px height=100px name=profile_image id=profile_image></img></label>");
        builderContent.append("</div>");
        builderContent.append("<div class=\"cellDiv ft-myriad-11\">");

        builderContent.append("<div class='file_browse_wrapper' title=\"add pictures by browse and press update\">");
        builderContent.append(
                "<input class=\"file_browse file\" type=\"file\" size=\"35\" name=\"file1\" id=\"file1\" multiple></input></div>");

        builderContent.append("</div></div>"); // end row
        return builderContent.toString();
    }

}
