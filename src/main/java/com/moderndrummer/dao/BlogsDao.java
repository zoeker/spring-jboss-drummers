package com.moderndrummer.dao;

import java.util.Set;

import com.moderndrummer.entity.Memberblogpost;
import com.moderndrummer.entity.Memberblogpostimage;
import com.moderndrummer.entity.Memberpostcomment;
import com.moderndrummer.entity.exceptions.BlogJPAException;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */
public interface BlogsDao {

    Memberblogpost findBlogPostById(long id);

    Set<Memberblogpost> getAllBlogPosts();

    boolean delete(Long id) throws BlogJPAException;

    Memberpostcomment insertWithMerge(Memberpostcomment object) throws BlogJPAException;

  
    Memberblogpost insert(Memberblogpost blogPost) throws BlogJPAException;

    Memberpostcomment insertTruly(Memberpostcomment comment) throws BlogJPAException;

    Memberblogpost update(Memberblogpost object) throws BlogJPAException;

}
