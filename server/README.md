解压dist/rtc-api.zip

修改配置文件 BOOT-INF/classes/application.properties

修改 rtc 应用appId、appKey
rtc.1v1audio.appId = *****
rtc.1v1audio.appKey = *****

设置AK，需要添加 AliyunRTCFullAccess 权限
accessKeyID=*****
accessKeySecret=*****


执行restart.sh即可重启appserver。

访问http://127.0.0.1:8080/1v1-audio/index.html即可看到启动成功提示。


