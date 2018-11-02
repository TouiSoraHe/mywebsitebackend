package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.JsonObj.BlogJsonObj;
import com.zzy.mywebsitebackend.Data.JsonObj.CommentJsonObj;
import com.zzy.mywebsitebackend.Service.BlogService;
import com.zzy.mywebsitebackend.Service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/comment")
@Slf4j
public class CommentController {
    @Autowired
    private CommentService commentService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addComment(@RequestBody  @Validated CommentJsonObj commentJsonObj){
        int isSuccess = commentService.insert(commentJsonObj);
        if(isSuccess == 1)
            return  new ResponseEntity(commentJsonObj, HttpStatus.CREATED);
        String msg = "addComment:新增评论失败,"+ commentJsonObj.toString();
        log.error(msg);
        return  new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{commentId}",method = RequestMethod.GET)
    public ResponseEntity getComment(@PathVariable("commentId")Integer commentId) {
        CommentJsonObj commentJsonObj = commentService.selectByPrimaryKey(commentId);
        if(commentJsonObj == null){
            String msg = "没有找到ID为"+commentId+"的评论";
            log.error("getComment:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(commentJsonObj,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getComments() {
        List<CommentJsonObj> commentJsonObjs = commentService.selectAll();
        return new ResponseEntity(commentJsonObjs,HttpStatus.OK);
    }

    @RequestMapping(value = "/{commentId}",method = RequestMethod.PUT)
    public ResponseEntity updateComment(@PathVariable("commentId")Integer commentId,@RequestBody  @Validated CommentJsonObj commentJsonObj){
        commentJsonObj.setId(commentId);
        int isSuccess = commentService.updateByPrimaryKey(commentJsonObj);
        if(isSuccess == 1)
            return new ResponseEntity(commentJsonObj,HttpStatus.OK);
        String msg = "updateComment:更新评论失败,"+ commentJsonObj.toString();
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{commentId}",method = RequestMethod.DELETE)
    public  ResponseEntity deleteComment(@PathVariable("commentId")Integer commentId){
        int isSuccess = commentService.deleteByPrimaryKey(commentId);
        if(isSuccess == 1){
            return new ResponseEntity("删除成功",HttpStatus.OK);
        }
        String msg = "deleteComment:删除评论失败,commentId:"+commentId;
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
    }
}
