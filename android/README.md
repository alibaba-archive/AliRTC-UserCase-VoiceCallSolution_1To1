1.本Demo调用RTC SDK需要的鉴权信息是从server端通过接口请求的形式获取的，用户在使用时需要将地址换成自己server端的地址。
配置文位置：AlivcVoiceCall/AlivcVoiceCall_demo/src/main/java/com/aliyun/rtc/voicecall/constant/Constant.java中
2.server端相关接口：
        BASE_URL + "/app/token" 获取鉴权信息 在点击加入房间joinChannel时调用
        BASE_URL + "/app/descChannelUsers" 获取频道内用户的信息 在获取鉴权信息之后调用，用于限制频道内人数，低于限制人数时调用joinChannel加入房间
        BASE_URL + "/app/descChannelStartTime" 获取频道创建时间 收到进入房间回调时调用，开始计时。