## 概念
    1.在微服务框架中，一个由客户端发起的请求在后端系统中会经过多个不同的服务节点调用来协调产生最后的请求结果，
    每一个前段请求都会形成一条复杂的分布式服务调用链路，链路中的任何一环出现高延迟或错误都会引起整个请求最后的失败。

    2.SpringCloud Sleuth提供了一套完整的服务跟踪的解决方案
    在分布式系统中提供追踪解决方案并且兼容支持了zipkin

    3.Sleuth 负责收集整理，Zipkin负责展现。

## 实现
    1.下载Zipkin 只需调用jar包 D:\文档 
    2.运行 java -jar zipkin-server-2.12.9-exec.jar
    3.访问localhost:9411/zipkin

    4.一条链路由trace id唯一标识 span标识发起的请求信息 span通过parent id关联起来 

    5.cloud-provider-payment8001 中pom引入
    yml
          zipkin:
                base-url: http://localhost:9411
          sleuth:
                sampler:
                    #采样率介于0到1之间 1表示全部采集
                    probability: 1
    
    6.controller 中新增方法