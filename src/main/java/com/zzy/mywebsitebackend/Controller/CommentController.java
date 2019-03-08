package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.Comment;
import com.zzy.mywebsitebackend.Service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    @Transactional
    public ResponseEntity addComment(@RequestBody @Validated Comment comment) {
        commentService.insert(comment);
        return new ResponseEntity(comment, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getComment(@PathVariable("commentId") Integer commentId) {
        Comment comment = commentService.selectByPrimaryKey(commentId);
        if (comment == null) {
            String msg = "没有找到ID为" + commentId + "的评论";
            log.error("getComment:" + msg);
            return new ResponseEntity(msg, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(comment, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    @Transactional
    public ResponseEntity getComments(Integer blogID, Integer _limit, Integer _page, String _sort, String _order, HttpServletResponse response) {
        List<Comment> comments;
        if (blogID != null && _limit != null && _page != null) {
            Integer offset = (_page - 1) * _limit;
            comments = commentService.selectByBlogIDWithRootNode(blogID, _sort, _order, offset, _limit);
            int xTotalCount = commentService.selectByBlogIDRootCount(blogID);
            comments.addAll(getAllChildNode(comments));
            response.setIntHeader("x-total-count", xTotalCount);
        } else {
            comments = commentService.selectAll();
        }
        return new ResponseEntity(comments, HttpStatus.OK);
    }

    private List<Comment> getAllChildNode(List<Comment> parentComments) {
        if (parentComments.size() == 0) return new ArrayList<>();
        List<Integer> parentIds = new ArrayList<>();
        for (Comment parentComment : parentComments) {
            parentIds.add(parentComment.getId());
        }
        List<Comment> childComments = commentService.selectByParentIDs(parentIds);
        childComments.addAll(getAllChildNode(childComments));
        return childComments;
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.PUT)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Edit"})
    public ResponseEntity updateComment(@PathVariable("commentId") Integer commentId, @RequestBody @Validated Comment comment) {
        comment.setId(commentId);
        commentService.updateByPrimaryKeySelective(comment);
        return new ResponseEntity(commentService.selectByPrimaryKey(commentId), HttpStatus.OK);
    }

    @RequestMapping(value = "/{commentId}", method = RequestMethod.DELETE)
    @Transactional
    @RequiresPermissions(logical = Logical.AND, value = {"Delete"})
    public ResponseEntity deleteComment(@PathVariable("commentId") Integer commentId) {
        commentService.deleteByPrimaryKey(commentId);
        return new ResponseEntity("删除成功", HttpStatus.OK);
    }
}
