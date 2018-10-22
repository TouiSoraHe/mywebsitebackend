package com.zzy.mywebsitebackend.Util.Generate;


import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerateUtils {

    /**
     * 根据Mapper自动生成Service层代码
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception{
        CodeGenerateUtils codeGenerateUtils = new CodeGenerateUtils();
        List<Class<?>> classes = ClassUtil.getClasses("com.zzy.mywebsitebackend.Data.Mapper");
        for (Class clas : classes) {
            codeGenerateUtils.generateService(clas);
        }
    }

    public void generateService(Class clas) throws IOException, TemplateException {
        Template template = FreeMarkerTemplateUtils.getTemplate("service.ftl");
        Map<String, Object> root = new HashMap<String, Object>();
        root.put("classObj", clas);
        root.put("entityClassName", clas.getSimpleName().replaceAll("Mapper",""));
        File javaFile = new File("src/main/java/com/zzy/mywebsitebackend/Service", clas.getSimpleName().replaceAll("Mapper","") + "Service.java");
        if (javaFile != null) {
            if(!javaFile.exists()){
                Writer javaWriter = new FileWriter(javaFile);
                template.process(root, javaWriter);
                javaWriter.flush();
                System.out.println("文件生成路径：" + javaFile.getCanonicalPath());
                javaWriter.close();
            }else{
                System.out.println(javaFile.getCanonicalPath()+"文件已经存在");
            }
        }
    }
}