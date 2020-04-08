package com.moderndrummer.dao;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.moderndrummer.entity.Member;
import com.moderndrummer.entity.Memberblogpost;
import com.moderndrummer.entity.Memberblogpostimage;
import com.moderndrummer.entity.Memberpostcomment;
import com.moderndrummer.entity.exceptions.BlogJPAException;
import com.moderndrummer.entity.exceptions.ModernDrummerJPAException;
import com.moderndrummer.entity.exceptions.NotFoundException;
import com.moderndrummer.repo.base.BaseJPQLDao;

/**
 * @author conpem
 * @realname Conny Pemfors
 * @version $Revision: 1.0 $
 */

@Repository // ("blogsDao")
@Transactional
public class BlogsDaoImpl extends BaseJPQLDao implements BlogsDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlogsDaoImpl.class);

    @Override
    public Memberblogpost insert(Memberblogpost blogPost) throws BlogJPAException {
        Set<Memberblogpostimage> images = Optional.ofNullable(blogPost.getMemberBlogPostImages())
                .filter(list -> list.isEmpty()).isPresent() ? blogPost.getMemberBlogPostImages()
                        : new HashSet<Memberblogpostimage>();
        images.forEach(image -> image.setMemberBlogPost(blogPost));
        return insertTruly(blogPost);
    }

    private Memberblogpost insertTruly(Memberblogpost blogPost) throws BlogJPAException {

        try {

            return Optional.ofNullable(insert(blogPost, Memberblogpost.class)).map(Memberblogpost.class::cast)
                    .orElseThrow(() -> new ModernDrummerJPAException("Not able to save new post"));

        } catch (RuntimeException e) {
            LOGGER.error("failed to insert ", e);
            throw new ModernDrummerJPAException(e.getMessage());
        }

    }

    @Override
    public Memberpostcomment insertTruly(Memberpostcomment comment) throws BlogJPAException {

        try {

            return insertWithMerge(comment);

        } catch (RuntimeException e) {
            LOGGER.error("failed to insert ", e);
            throw new BlogJPAException(e.getMessage());
        }

    }

    @Override
    public Memberpostcomment insertWithMerge(Memberpostcomment object) throws BlogJPAException {
        try {

            Memberblogpost inserted = em.find(Memberblogpost.class, object.getBlogPost().getBlogPostId());
            object.setBlogPost(
                    Optional.ofNullable(object.getBlogPost()).filter(b -> b.getBlogPostId() > 0).orElse(inserted));

            em.persist(object);
            // em.merge(object) ;

            em.flush();

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
    public Memberblogpost update(Memberblogpost object) throws BlogJPAException {
        try {

            Member member = em.find(Member.class, object.getMember().getId());

            // em.persist(object);
            em.merge(object);

            em.flush();

            Memberblogpost inserted = em.find(Memberblogpost.class, object.getBlogPostId());

            em.refresh(inserted);

            return object;

        } catch (final RuntimeException ex) {
            LOGGER.error(ex.getMessage(), ex);
            throw new BlogJPAException(ex.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws BlogJPAException {

        try {

            Memberblogpost post = Optional.ofNullable(find(id, Memberblogpost.class)).map(Memberblogpost.class::cast).filter(blog -> blog.getBlogPostId() > 0L)
                    .orElseThrow(() -> new NotFoundException("memberblogpost not found"));
            List<Memberblogpostimage> images = executeNamedQueryByOneParamReturnList(post.getBlogPostId(),
                    "Memberblogpostimage.findAllImagesByPostId", Memberblogpostimage.class);
            images.forEach(image -> em.remove(image));

            List<Memberpostcomment> comments = executeNamedQueryByOneParamReturnList(post.getBlogPostId(),
                    "Memberpostcomment.findAllCommentsByPostId", Memberpostcomment.class);
            comments.forEach(comment -> em.remove(comment));
            em.remove(post);
            return true;

        } catch (RuntimeException e) {
            LOGGER.error("failed to delete ", e);
            throw new BlogJPAException("Unable to delete blog");
        }

    }

    @Override
    public Set<Memberblogpost> getAllBlogPosts() {
        return asSet(executeNamedQueryReturnList("Memberblogpost.findAll", Memberblogpost.class));
    }

    @Override
    public Memberblogpost findBlogPostById(long id) {
        Optional<Memberblogpost> result = Optional.ofNullable(find(id, Memberblogpost.class))
                .map(Memberblogpost.class::cast);
        return result.filter(entity -> entity.getBlogPostId() > 0L)
                .orElseThrow(() -> new NotFoundException("Member blog not found"));
    }

}
