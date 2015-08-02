package com.moderndrummer.files;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public enum FileTypes {
    XML, HTML, XSL, PDF;

    public String getFileTypeExtension() {
        switch (this) {
        case XML:
            return "xml";
        case HTML:
            return "html";
        case XSL:
            return "xsl";
        case PDF:
            return "pdf";
        default:
            return "";
        }
    }
}
