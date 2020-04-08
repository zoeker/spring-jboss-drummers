package com.moderndrummer.presentationmanagers;

import java.util.Map;

import com.moderndrummer.entity.Member;
import com.moderndrummer.entity.Memberblogpost;
import com.moderndrummer.entity.exceptions.EntityParseException;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

public abstract class PresentationManager<E> {

    public abstract Memberblogpost buildEntity(Map<String, String> parameters, Member loggedMember)
            throws EntityParseException;

    public abstract Memberblogpost buildEntity(Map<String, String> parameters) throws EntityParseException;

}
