package com.zzy.mywebsitebackend.Component;

import com.zzy.mywebsitebackend.Data.Entity.BlogInfo;
import com.zzy.mywebsitebackend.Service.BlogInfoService;
import com.zzy.mywebsitebackend.Util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class ViewsCount {

    @Autowired
    private BlogInfoService blogInfoService;

    private Timer timer = new Timer(true);
    private long period = 86400000;//每隔period毫秒,执行一次
    private Map<Integer,Integer> blogInfoIdToCount = new HashMap<>();
    private Map<Integer, Set<String>> blogInfoIdToIPs = new HashMap<>();

    private TimerTask task = new TimerTask() {
        public void run() {
            WriteToDatabase();
            ClearData();
        }
    };

    @PostConstruct
    private void Init(){
        timer.schedule(task,period,period);
    }

    public void AddCount(int blogInfoId, HttpServletRequest request){
        if(!blogInfoIdToIPs.containsKey(blogInfoId)){
            blogInfoIdToIPs.put(blogInfoId,new HashSet<>());
        }
        Set<String> IPs = blogInfoIdToIPs.get(blogInfoId);
        String ip = Util.getIpAddress(request);
        if(!IPs.contains(ip)){
            IPs.add(ip);
            if(!blogInfoIdToCount.containsKey(blogInfoId)){
                blogInfoIdToCount.put(blogInfoId,0);
            }
            Integer count = blogInfoIdToCount.get(blogInfoId);
            count++;
            blogInfoIdToCount.put(blogInfoId,count);
        }
    }

    public int GetCount(int blogInfoId){
        if(blogInfoIdToCount.containsKey(blogInfoId)){
            return blogInfoIdToCount.get(blogInfoId);
        }
        return 0;
    }

    private void WriteToDatabase(){
        if(blogInfoIdToCount.size()>0){
            for (Map.Entry<Integer, Integer> entry : blogInfoIdToCount.entrySet()) {
                if(entry.getValue()>0){
                    BlogInfo blogInfo = blogInfoService.selectByPrimaryKey(entry.getKey());
                    blogInfo.setViews(entry.getValue()+blogInfo.getViews());
                    blogInfoService.updateByPrimaryKeySelective(blogInfo);
                }
            }
            blogInfoIdToCount.clear();
        }
    }

    private void ClearData(){
        blogInfoIdToIPs.clear();
        System.gc();
    }

}
