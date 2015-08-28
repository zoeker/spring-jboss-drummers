package com.moderndrummer.entity.transformers;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public class DateAdapter extends XmlAdapter<String, Date> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    /**
     * Method marshal.
     * 
     * @param v
     *            Date
     * 
     * 
     * 
     * 
     * @return String * @throws Exception * @throws Exception * @throws
     *         Exception * @throws Exception
     */
    @Override
    public String marshal(final Date v) throws Exception {
        return dateFormat.format(v);
    }

    /**
     * Method unmarshal.
     * 
     * @param v
     *            String
     * 
     * 
     * 
     * 
     * @return Date * @throws Exception * @throws Exception * @throws Exception
     *         * @throws Exception
     */
    @Override
    public Date unmarshal(final String v) throws Exception {
        return new Date(dateFormat.parse(v).getTime());
    }

}
