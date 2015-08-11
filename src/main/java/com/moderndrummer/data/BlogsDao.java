package com.moderndrummer.data;

import java.util.Set;

import com.moderndrummer.entity.exceptions.BlogJPAException;
import com.moderndrummer.model.Memberblogpost;
import com.moderndrummer.model.Memberblogpostimage;
import com.moderndrummer.model.Memberpostcomment;

/***
 * 
 * @author conpem 2015-08-03
 *
 */

public interface BlogsDao {

	Memberblogpost findBlogPostById(int id);

	Set<Memberblogpost> getAllBlogPosts();

	boolean delete(Memberblogpost blogPost) throws BlogJPAException;

	Memberpostcomment insertWithMerge(Memberpostcomment object) throws BlogJPAException;

	boolean insert(Memberblogpost blogPost, Set<Memberblogpostimage> images) throws BlogJPAException;

	Memberblogpost insert(Memberblogpost blogPost) throws BlogJPAException;

	Memberpostcomment insertTruly(Memberpostcomment comment) throws BlogJPAException;

	Memberblogpost update(Memberblogpost object) throws BlogJPAException;

}
