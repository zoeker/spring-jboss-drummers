package com.moderndrummer.ejb.regexp;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class XMLExtractor {
    public static final String DIVETIME = "<DiveTime>[0-9]+</DiveTime>";
    public static final String MAX_DEPTH = "<MaxDepth>[0-9]+\u002E[0-9]+</MaxDepth>";
    public static final String START_TEMPERATURE = "<StartTemperature>[0-9]{0,2}</StartTemperature>";
    public static final String START_TIME = "<StartTime>[0-9a-zA-Z\u002d\u003a]+</StartTime>";
    public static final String DURATION = "<Duration>[0-9]+</Duration>";
    public static final String BOTTOM_TEMPERATURE = "<BottomTemperature>[0-9]{0,2}</BottomTemperature>";
    public static final String SURFACE_TIME = "<SurfaceTime>[0-9]+</SurfaceTime>";
}
