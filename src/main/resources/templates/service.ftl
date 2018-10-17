<#assign
mapperClassName=classObj.getSimpleName()
methods=classObj.getDeclaredMethods()
>

package com.zzy.mywebsitebackend.Service;

import com.zzy.mywebsitebackend.Data.Entity.${entityClassName};
import com.zzy.mywebsitebackend.Data.Mapper.${mapperClassName};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${entityClassName}Service {

    @Autowired
    private ${mapperClassName} mapper;

<#list methods as item>
    public ${item.getReturnType().getSimpleName()} ${item.getName()}(<#list item.getParameterTypes() as para>${para.getSimpleName()} var${para_index}</#list>){
        <#if item.getReturnType().getSimpleName() == "void">mapper.${item.getName()}(<#list item.getParameterTypes() as para>var${para_index}</#list>);
        <#else >return mapper.${item.getName()}(<#list item.getParameterTypes() as para>var${para_index}</#list>);
        </#if>
    }

</#list>
}
