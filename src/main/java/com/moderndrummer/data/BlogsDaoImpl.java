package com.moderndrummer.data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moderndrummer.entity.exceptions.BlogJPAException;
import com.moderndrummer.model.Memberblogpost;
import com.moderndrummer.model.Memberblogpostimage;
import com.moderndrummer.model.Memberpostcomment;
import com.moderndrummer.repo.base.BaseJPQLDao;

@Repository//("blogsDao")
@Transactional
public class BlogsDaoImpl extends BaseJPQLDao implements BlogsDao
{
   
  private static final Logger LOGGER = LoggerFactory.getLogger(BlogsDaoImpl.class);



  @Override
  public Memberblogpost insert(Memberblogpost blogPost) throws BlogJPAException {
    if (blogPost.getMemberBlogPostImages() == null) {
      blogPost.setMemberBlogPostImages(new HashSet<Memberblogpostimage>());
    }
    else{
      for(Memberblogpostimage image : blogPost.getMemberBlogPostImages()){
        image.setMemberBlogPost(blogPost);
      }
    }
    return insertTruly(blogPost);

  }

  @Override
  public boolean insert(Memberblogpost blogPost, Set<Memberblogpostimage> images)
      throws BlogJPAException  {

    try {
      if (blogPost.getMemberBlogPostImages().isEmpty() && !images.isEmpty()) {
        blogPost.setMemberBlogPostImages(images);
      }
      em.persist(blogPost);

      return true;

    } catch (RuntimeException e) {
      LOGGER.error("failed to insert ", e);
      throw new BlogJPAException("failed to insert blog post " +  e.getMessage());
    }


  }
  
  private Memberblogpost insertTruly(Memberblogpost blogPost)
      throws BlogJPAException {

    try {
      
     return (Memberblogpost) insert(blogPost, Memberblogpost.class);

    } catch (RuntimeException e) {
      LOGGER.error("failed to insert ", e);
      return new Memberblogpost();
    }


  }
  
  public Memberpostcomment insertTruly(Memberpostcomment comment)
      throws BlogJPAException {

    try {
      
     return insertWithMerge(comment);

    } catch (RuntimeException e) {
      LOGGER.error("failed to insert ", e);
      return new Memberpostcomment();
    }


  }
  
  @Override
  public Memberpostcomment insertWithMerge(Memberpostcomment object) throws BlogJPAException {
    boolean activatedTransaction = false;
     try {

    
      em.persist(object);
      em.flush();
 
      Memberblogpost inserted = em.find(Memberblogpost.class, object.getBlogPost().getBlogPostId());
      Memberpostcomment insertedComment = em.find(Memberpostcomment.class, object.getCommentId());
      
      em.refresh(inserted);
      em.refresh(insertedComment);
    
      return object;

    } catch (final Exception ex) {
      LOGGER.error(ex.getMessage(), ex);
      throw new BlogJPAException(ex.getMessage());
    }
  }
  
  @Override
  public boolean delete(Memberblogpost blogPost)
      throws BlogJPAException {

    try {
      List<Memberblogpostimage> images = executeNamedQueryByOneParamReturnList(blogPost.getBlogPostId(), "Diverblogpost.findAllImagesById",Memberblogpostimage.class);
      for(Memberblogpostimage i : images){
        em.remove(i);
      }
      
      List<Memberpostcomment> comments = executeNamedQueryByOneParamReturnList(blogPost.getBlogPostId(), "Diverblogpost.findAllCommentsById", Memberpostcomment.class);
      for(Memberpostcomment c : comments){
        em.remove(c);
      }
      em.remove(blogPost);
      return true;

    } catch (RuntimeException e) {
      LOGGER.error("failed to delete ", e);
      return false;
    }


  }
  
  @Override
  public Set<Memberblogpost> getAllBlogPosts(){
    return asSet(executeNamedQueryReturnList("Diverblogpost.findAll",Memberblogpost.class));
  }
  
  @Override
  public Memberblogpost findBlogPostById(int id){
    return (Memberblogpost ) find(id, Memberblogpost.class);
  }
   
}
