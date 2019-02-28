package com.zzy.mywebsitebackend.Controller;

import com.zzy.mywebsitebackend.Data.Entity.Tag;
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
        Tag tag = tagService.selectByPrimaryKey(tagId);
        if(tag == null){
            String msg = "没有找到ID为"+tagId+"的标签";
            log.error("getTag:"+msg);
            return new ResponseEntity(msg,HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(tag,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getTags() {
        List<Tag> tagJsonObjs = tagService.selectAll();
        return new ResponseEntity(tagJsonObjs,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity addTag(@RequestBody  @Validated Tag tag){
        tagService.insert(tag);
        return  new ResponseEntity(tag,HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{tagId}",method = RequestMethod.PUT)
    public ResponseEntity updateTag(@PathVariable("tagId")Integer id,@RequestBody @Validated Tag tag){
        tag.setId(id);
        tagService.updateByPrimaryKeySelective(tag);
        return new ResponseEntity(tagService.selectByPrimaryKey(tag.getId()), HttpStatus.OK);
    }

    @RequestMapping(value = "/{tagId}",method = RequestMethod.DELETE)
    public  ResponseEntity deleteTag(@PathVariable("tagId")Integer tagId){
        tagService.deleteByPrimaryKey(tagId);
        return new ResponseEntity("删除成功",HttpStatus.OK);
    }
}
