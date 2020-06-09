package com.alivc.rtc.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alivc.base.ConfigMapUtil;
import com.alivc.base.ResponseResult;
import com.alivc.base.RtcOpenAPI;
import com.alivc.rtc.ScheduledDeleteChannel;
import com.aliyuncs.rtc.model.v20180111.DescribeChannelUsersResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;

@Slf4j
@RestController
public class RtcController {

    @RequestMapping(value = "/app/token", method = RequestMethod.GET)
    public ResponseResult getRtcToken(String channelId) {
        ResponseResult responseResult = new ResponseResult();

        String userId = RandomStringUtils.random(8, false, true);

        String appId = ConfigMapUtil.getValueByKey("rtc.1v1audio.appId");
        String appKey = ConfigMapUtil.getValueByKey("rtc.1v1audio.appKey");
        String gslb = ConfigMapUtil.getValueByKey("rtc.gslb");

        String nonce = RtcOpenAPI.generateNonce();

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 10);
//        nowTime.add(Calendar.HOUR_OF_DAY, 2);
        Long timestamp = nowTime.getTimeInMillis() / 1000;

        try {
            String rtcToken = RtcOpenAPI.createToken(appId, appKey, channelId, userId, nonce, timestamp);

            JSONObject data = new JSONObject();
            data.put("appid", appId);
            data.put("userid", userId);
            JSONArray gslbArray = new JSONArray();
            gslbArray.add(gslb);
            data.put("gslb", gslbArray);
            data.put("token", rtcToken);
            data.put("nonce", nonce);
            data.put("timestamp", timestamp);

            responseResult.setData(data);

            ScheduledDeleteChannel.addChannel(appId, channelId);

            return responseResult;
        } catch (Exception e) {
            responseResult.setResult("false");
            responseResult.setCode("500");
            log.error("create RTC token error", e);
        }
        return responseResult;

    }


    @RequestMapping(value = "/app/descChannelUsers", method = RequestMethod.GET)
    public ResponseResult descChannelUsers(String channelId) {
        ResponseResult responseResult = new ResponseResult();

        String appId = ConfigMapUtil.getValueByKey("rtc.1v1audio.appId");

        try {
            DescribeChannelUsersResponse response = RtcOpenAPI.describeChannelUsers(appId, channelId);

            responseResult.setData(response);

            return responseResult;
        } catch (Exception e) {
            responseResult.setResult("false");
            responseResult.setCode("500");
            log.error("desc Channel Users", e);
        }
        return responseResult;

    }

    @RequestMapping(value = "/app/descChannelStartTime", method = RequestMethod.GET)
    public ResponseResult descChannelStartTime(String channelId) {

        String appId = ConfigMapUtil.getValueByKey("rtc.1v1audio.appId");

        ResponseResult responseResult = new ResponseResult();

        try {
            JSONObject scheduledTask = ScheduledDeleteChannel.TASKS.get(ImmutablePair.of(appId, channelId));
            String channelStartTimeUtc = scheduledTask.getString("channelStartTimeUtc");

            JSONObject channelInfoJson = new JSONObject();

            channelInfoJson.put("channelStartTimeUtc", channelStartTimeUtc);


            responseResult.setData(channelInfoJson);

            return responseResult;
        } catch (Exception e) {
            responseResult.setResult("false");
            responseResult.setCode("500");
            log.error("desc Channel StartTime", e);
        }
        return responseResult;

    }


}


