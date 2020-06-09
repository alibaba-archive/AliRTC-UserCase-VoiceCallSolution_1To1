package com.alivc.base;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.rtc.model.v20180111.DeleteChannelRequest;
import com.aliyuncs.rtc.model.v20180111.DeleteChannelResponse;
import com.aliyuncs.rtc.model.v20180111.DescribeChannelUsersRequest;
import com.aliyuncs.rtc.model.v20180111.DescribeChannelUsersResponse;
import com.aliyuncs.rtc.model.v20180111.DescribeRTCAppKeyRequest;
import com.aliyuncs.rtc.model.v20180111.DescribeRTCAppKeyResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;


@Slf4j
public class RtcOpenAPI {

    public static DefaultAcsClient initVodClient() throws Exception {
        JSONObject akInfoJson = AkUtil.getAkInfo();
        DefaultProfile profile;

        if (StringUtils.isNotBlank(akInfoJson.getString("SecurityToken"))) {
            profile = DefaultProfile.getProfile("cn-hangzhou", akInfoJson.getString("AccessKeyId"), akInfoJson.getString("AccessKeySecret"), akInfoJson.getString("SecurityToken"));
        } else {
            profile = DefaultProfile.getProfile("cn-hangzhou", akInfoJson.getString("AccessKeyId"), akInfoJson.getString("AccessKeySecret"));
        }

        DefaultAcsClient client = new DefaultAcsClient(profile);
        return client;
    }


    public static DeleteChannelResponse deleteChannel(String appId, String channelId) throws Exception {

        DefaultAcsClient client = initVodClient();
        DeleteChannelRequest request = new DeleteChannelRequest();

        request.setAppId(appId);
        request.setChannelId(channelId);

        DeleteChannelResponse response = client.getAcsResponse(request);
        log.error("del" + appId + channelId + JSON.toJSONString(response));

        return response;
    }

    public static DescribeChannelUsersResponse describeChannelUsers(String appId, String channelId)
            throws Exception {
        DefaultAcsClient client = initVodClient();
        DescribeChannelUsersRequest request = new DescribeChannelUsersRequest();
        request.setAppId(appId);
        request.setChannelId(channelId);
        DescribeChannelUsersResponse response = client.getAcsResponse(request);

        return response;
    }

    public static String createToken(
            String appId, String appKey, String channelId, String userId,
            String nonce, Long timestamp
    ) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.update(appId.getBytes());
        digest.update(appKey.getBytes());
        digest.update(channelId.getBytes());
        digest.update(userId.getBytes());
        digest.update(nonce.getBytes());
        digest.update(Long.toString(timestamp).getBytes());

        String token = DatatypeConverter.printHexBinary(digest.digest()).toLowerCase();
        return token;
    }

    public static String generateNonce() {
        return String.format("AK-%s", UUID.randomUUID().toString());
    }

    public static DescribeRTCAppKeyResponse describeRTCAppKey(String appId) throws Exception {

        DefaultAcsClient client = initVodClient();
        DescribeRTCAppKeyRequest request = new DescribeRTCAppKeyRequest();

        request.setAppId(appId);

        DescribeRTCAppKeyResponse response = client.getAcsResponse(request);

        return response;
    }


}
