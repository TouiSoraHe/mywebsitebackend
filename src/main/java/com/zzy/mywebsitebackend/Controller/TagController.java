package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.JsonObj.BlogJsonObj;
import com.zzy.mywebsitebackend.Data.JsonObj.TagJsonObj;
import com.zzy.mywebsitebackend.Service.BlogService;
import com.zzy.mywebsitebackend.Service.TagService;
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
@RequestMapping("/tag")
@Slf4j
public class TagController {

    @Autowired
    private TagService tagService;

    @RequestMapping(value = "/{tagId}",method = RequestMethod.GET)
    public ResponseEntity getTag(@PathVariable("tagId")Integer tagId) {
        TagJsonObj tagJsonObj = tagService.selectByPrimaryKey(tagId);
        if(tagJsonObj == null){
            String msg = "没有找到ID为"+tagId+"的标签";
            log.error("getTag:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(tagJsonObj,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTags() {
        List<TagJsonObj> tagJsonObjs = tagService.selectAll();
        return new ResponseEntity(tagJsonObjs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addTag(@RequestBody  @Validated TagJsonObj tagJsonObj){
        int isSuccess = tagService.insert(tagJsonObj);
        if(isSuccess == 1)
            return  new ResponseEntity(tagJsonObj,HttpStatus.CREATED);
        String msg = "addTag:新增标签失败,"+ tagJsonObj.toString();
        log.error(msg);
        return  new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{tagId}",method = RequestMethod.PUT)
    public ResponseEntity updateTag(@PathVariable("tagId")Integer id,@RequestBody @Validated TagJsonObj tagJsonObj){
        tagJsonObj.setId(id);
        int isSuccess = tagService.updateByPrimaryKey(tagJsonObj);
        if(isSuccess == 1)
            return new ResponseEntity(tagJsonObj, HttpStatus.OK);
        String msg = "updateTag:更新标签失败,"+ tagJsonObj.toString();
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/{tagId}",method = RequestMethod.DELETE)
    public  ResponseEntity deleteTag(@PathVariable("tagId")Integer tagId){
        int isSuccess = tagService.deleteByPrimaryKey(tagId);
        if(isSuccess == 1){
            return new ResponseEntity("删除成功",HttpStatus.OK);
        }
        String msg = "deleteTag:没有找到该标签,tagId:"+tagId;
        log.error(msg);
        return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
    }
}
