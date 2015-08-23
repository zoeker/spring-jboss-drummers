package com.moderndrummer.presentationmanagers;

import java.util.Map;

import com.moderndrummer.entity.exceptions.EntityParseException;
import com.moderndrummer.model.Member;
import com.moderndrummer.model.Memberblogpost;
/***
 * 
 * @author conpem 2015-08-03
 *
 */

public abstract class PresentationManager<E> {

	public abstract Memberblogpost buildEntity(Map<String, String> parameters, Member loggedMember) throws EntityParseException ;

	public abstract Memberblogpost buildEntity(Map<String, String> parameters) throws EntityParseException;


}
