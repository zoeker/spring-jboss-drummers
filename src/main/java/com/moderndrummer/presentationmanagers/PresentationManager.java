package com.moderndrummer.presentationmanagers;

import java.util.Map;

import com.moderndrummer.entity.exceptions.EntityParseException;
import com.moderndrummer.model.Member;
import com.moderndrummer.model.Memberblogpost;

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
