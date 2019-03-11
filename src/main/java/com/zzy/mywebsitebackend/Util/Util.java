package com.zzy.mywebsitebackend.Util;

import com.vladsch.flexmark.ast.Node;
import com.vladsch.flexmark.ast.util.TextCollectingVisitor;
import com.vladsch.flexmark.parser.Parser;
import com.vladsch.flexmark.profiles.pegdown.Extensions;
import com.vladsch.flexmark.profiles.pegdown.PegdownOptionsAdapter;
import com.vladsch.flexmark.util.options.DataHolder;
import com.vladsch.flexmark.util.options.MutableDataSet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Slf4j
public class Util {

    /**
     * 统计文本中的字符数,排除所有空格
     * @param text
     * @return
     */
    public static int WordCountExcludeBlank(String text){
        if(StringUtils.isEmpty(text)){
            return 0;
        }
        return text.replaceAll(" ","").length();
    }

    /**
     * markdown文档转为纯文本
     * @param markdown 需要转换的markdown文档
     * @return
     */
    public static  String Markdown2PlainText(String markdown){
        return Markdown2PlainTextUtil.Markdown2PlainText(markdown);
    }

    /**
     * 获取客户端真实IP
     * @param request
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    private static class Markdown2PlainTextUtil{
        private static final DataHolder OPTIONS = PegdownOptionsAdapter.flexmarkOptions(
                Extensions.ALL
        );

        private static final MutableDataSet FORMAT_OPTIONS = new MutableDataSet();
        static {
            // copy extensions from Pegdown compatible to Formatting
            FORMAT_OPTIONS.set(Parser.EXTENSIONS, OPTIONS.get(Parser.EXTENSIONS));
        }

        private static final Parser PARSER = Parser.builder(OPTIONS).build();

        // use the PARSER to parse and RENDERER to parse pegdown indentation rules and render CommonMark

        /**
         * markdown文档转为纯文本
         * @param markdown 需要转换的markdown文档
         * @return
         */
        private static  String Markdown2PlainText(String markdown){
            if(StringUtils.isEmpty(markdown)){
                return markdown;
            }
            Node document = PARSER.parse(markdown);
            TextCollectingVisitor textCollectingVisitor = new TextCollectingVisitor();
            String text = textCollectingVisitor.collectAndGetText(document);
            return text;
        }
    }
}
