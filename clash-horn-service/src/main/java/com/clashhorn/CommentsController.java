package com.clashhorn;

import com.clashhorn.model.Comment;
import com.clashhorn.model.CommentRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Marcelo
 */
@RestController
public class CommentsController {
    @Autowired
    private CommentRepository commentRepository;
    
    @RequestMapping("/heap")
    public Map heap() {
        return new HashMap() {{
            put("max", Runtime.getRuntime().maxMemory());
            put("free", Runtime.getRuntime().freeMemory());
        }};
    }
    
    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public void postComment(@RequestBody Comment comment) throws InterruptedException {
        Thread.sleep(1000);
        commentRepository.save(comment);
    }
    
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String commentId) throws InterruptedException {
        Thread.sleep(1000);
        commentRepository.remove(commentId);
    }
    
    @RequestMapping(value = "/comment/{id}", method = RequestMethod.GET)
    public Comment get(@PathVariable("id") String commentId) throws InterruptedException {
        Thread.sleep(1000);
        return commentRepository.get(commentId);
    }
    
    @RequestMapping(value = "/comment", method = RequestMethod.GET)
    public Iterable<Comment> getComments() throws InterruptedException {
        Thread.sleep(1000);
        return commentRepository.findAll();
    }
}
