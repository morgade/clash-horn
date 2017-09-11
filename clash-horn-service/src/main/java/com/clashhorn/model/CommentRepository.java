package com.clashhorn.model;

/**
 *
 * @author x4rb
 */
public interface CommentRepository {
    Iterable<Comment> findAll();
    Comment save(Comment comment);
    Comment get(String commentId);
    void remove(String commentId);
}
