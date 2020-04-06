package com.moderndrummer.util;

import java.text.NumberFormat;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class DoubleFormatter {
    private enum NumberFormatKind {
        Below, Normal, Above
    }

    private static final double EXP_DOWN = 1.e-3;

    /**
     * Convert "3.1416e+12" to "<b>3</b>.1416e<b>+12</b>" It is a html format of
     * a number which highlight the integer and exponent part
     */
    private static String htmlize(final String s) {
        final StringBuilder resu = new StringBuilder("<b>");
        int p1 = s.indexOf('.');

        if (p1 > 0) {
            resu.append(s.substring(0, p1));
            resu.append("</b>");
        } else {
            p1 = 0;
        }

        final int p2 = s.lastIndexOf('e');
        if (p2 > 0) {
            resu.append(s.substring(p1, p2));
            resu.append("<b>");
            resu.append(s.substring(p2, s.length()));
            resu.append("</b>");
        } else {
            resu.append(s.substring(p1, s.length()));
            if (p1 == 0) {
                resu.append("</b>");
            }
        }

        return resu.toString();
    }

    private double EXP_UP; // always = 10^maxInteger
    private int maxInteger_;
    private int maxFraction_;
    private NumberFormat nfBelow_;

    private NumberFormat nfNormal_;

    private NumberFormat nfAbove_;

    public String format(final double v) {
        if (Double.isNaN(v)) {
            return "-";
        }
        if (v == 0) {
            return "0";
        }
        final double absv = Math.abs(v);

        if (absv < EXP_DOWN) {
            return nfBelow_.format(v);
        }

        if (absv > EXP_UP) {
            return nfAbove_.format(v);
        }

        return nfNormal_.format(v);
    }

    /**
     * format and higlight the important part (integer part & exponent part)
     */
    public String formatHtml(final double v) {
        if (Double.isNaN(v)) {
            return "-";
        }
        return htmlize(format(v));
    }

}
