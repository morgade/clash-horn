package com.clashhorn.model;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

/**
 *
 * @author x4rb
 */
@Repository
public class CommentInMemoryRepository implements CommentRepository {
    private final Map<String, Comment> comments = new LinkedHashMap<>();

    @Override
    public Iterable<Comment> findAll() {
        return comments.values();
    }

    @Override
    public Comment save(Comment comment) {
        comments.put(comment.getId(), comment);
        return comment;
    }

    @Override
    public void remove(String commentId) {
        comments.remove(commentId);
    }

    @Override
    public Comment get(String commentId) {
        return comments.get(commentId);
    }
    
}
