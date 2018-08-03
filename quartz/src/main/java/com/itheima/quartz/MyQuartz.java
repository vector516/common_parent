package com.itheima.quartz;

import java.util.Date;

public class MyQuartz {

    public void sendMessage(){
        System.out.println("自定义的任务,执行的时间为:"+new Date());
    }


}
