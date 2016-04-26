package com.tysci.ballq.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.socks.library.KLog;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.modelmsg.WXMediaMessage;
import com.tencent.mm.sdk.modelmsg.WXWebpageObject;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tysci.ballq.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class WXUtils {
    private static final String TAG = "WXUtils";
    public static IWXAPI api;

    public static final String APP_ID_WEI_XIN = "wx764a44d6325f4975";
    public static final String APP_SECRET_WEI_XIN = "35758ebd06710d202acf6270ce1be146";
    public static final String APP_MCH_ID_WEI_XIN = "1235168302";

    public static final String ACCESS_TOKEN = "access_token";
    public static final String REFRESH_TOKEN = "refresh_token";
    public static final String OPEN_ID = "openid";
    public static final String NICK_NAME = "nickname";
    public static final String SEX = "sex";
    public static final String PROVINCE = "province";
    public static final String CITY = "city";
    public static final String COUNTRY = "country";
    public static final String HEAD_IMG_UTL = "headimgurl";
    public static final String UNION_ID = "unionid";
    public static final String PRIVILEGE = "privilege";

    public static boolean login(Context c) {
        if (!isHasWeiXin(c)) {
            ToastUtils.show(c, "请先安装微信APP");
            return false;
        }
        // send oauth request
        SendAuth.Req req = new SendAuth.Req();
        req.scope = "snsapi_userinfo";
        req.state = "wechat_sdk_demo";
        api.sendReq(req);
        return true;
    }

    public static void pay(Context c, String json) {
        try {
            PayReq req = new PayReq();

            final JSONObject object = new JSONObject(json);
            final JSONObject data = object.optJSONObject("data");

            req.appId = data.optString("appid");
            req.partnerId = data.optString("partnerid");
            req.prepayId = data.optString("prepayid");
            req.packageValue = data.optString("package");
            req.nonceStr = data.optString("noncestr");
            req.timeStamp = data.optString("timestamp");
            req.sign = data.optString("paySign");

            api.sendReq(req);
            ToastUtils.show(c, "获取订单成功");
        } catch (Exception e) {
            e.printStackTrace();
            ToastUtils.show(c, "获取订单失败");
        }
    }

    /**
     * 微信token接口
     */
    public static String getTokenUrl(Context c, BaseResp arg0) {
        final Resources res = c.getResources();
        return "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + res.getString(R.string.app_id_wx) + "&secret="
                + res.getString(R.string.app_secret_wx) + "&code="
                + ((SendAuth.Resp) arg0).code
                + "&grant_type=authorization_code";
    }

    /**
     * @return 微信刷新token
     */
    public static String refreshTokenUrl(Context c) {
        final Resources res = c.getResources();
        return "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="
                + res.getString(R.string.app_id_wx)
                + "&grant_type=refresh_token&refresh_token="
                + WXUtils.getWXRefreshToken(c);
    }

    private static String getWXRefreshToken(Context c) {
        return SPUtils.read(c, REFRESH_TOKEN, "");
    }

    /**
     * 获取用户信息
     */
    public static String getUserInfoUrl(Context c) {
        return "https://api.weixin.qq.com/sns/userinfo?access_token=" + WXUtils.getWXToken(c) + "&openid=" + WXUtils.getWXOpenId(c);
    }

    //    public static void shareText(String s, int tag)
//    {
//        if (s == null || s.length() == 0)
//        {
//            return;
//        }
//
//        if (!isHasWeiXin())
//        {
//            return;
//        }
//
//        // 初始化一个WXTextObject对象
//        WXTextObject textObj = new WXTextObject();
//        textObj.text = s;
//
//        // 用WXTextObject对象初始化一个WXMediaMessage对象
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = textObj;
//        // 发送文本类型的消息时，title字段不起作用
//        // msg.title = "Will be ignored";
//        msg.description = s;
//
//        // 构造一个Req
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("text"); // transaction字段用于唯一标识一个请求
//        req.message = msg;
//        req.scene = tag;
//        req.openId = SPUtils.getWXOpenId();
//        // 调用api接口发送数据到微信
//        api.sendReq(req);
//    }
//
//    public static boolean shareImage(String title, Bitmap bmp, int tag)
//    {
//        LogUtils.logE("GuessRecordActivity", "shareImage");
//        try
//        {
//            if (!isHasWeiXin())
//            {
//                return false;
//            }
//            LogUtils.logE("GuessRecordActivity", "ok");
//            WXImageObject imgObj = new WXImageObject(bmp);
//
//            WXMediaMessage msg = new WXMediaMessage();
//            msg.mediaObject = imgObj;
//
//            Bitmap thumbBmp = null;
//            thumbBmp = Bitmap.createScaledBitmap(bmp, THUMB_SIZE_WIDTH, THUMB_SIZE_HEIGHT, true);
////          bmp.recycle();
//            msg.thumbData = Util.bmpToByteArray(thumbBmp, true);
//
//            SendMessageToWX.Req req = new SendMessageToWX.Req();
//            req.transaction = buildTransaction("img");
//            req.message = msg;
//            req.scene = tag;
//            api.sendReq(req);
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
////        WXAppExtendObject eo = new WXAppExtendObject();SendMessageToWX.Req.WXSceneSession
////
////        eo.extInfo = title;
////
////        WXMediaMessage msg = new WXMediaMessage();
////
////        msg.mediaObject = eo;//这里的mediaObject是我们前面对应的WXAppExtendObject
////        msg.description = "图片描述";
////
////        msg.thumbData = ImageUtil.Bitmap2Bytes(bmp);
////        msg.title = title;
////        SendMessageToWX.Req req = new SendMessageToWX.Req();
////        req.transaction = "img" + String.valueOf(System.currentTimeMillis());
////        req.message = msg;
////        req.scene = SendMessageToWX.Req.WXSceneTimeline;
////        api.sendReq(req);
//        return true;
//    }
//
    public static boolean shareWebPage(Context c, String title, String excerpt, String shareUrl, int tag, int logo) {
        if (!isHasWeiXin(c)) {
            return false;
        }
        WXWebpageObject webPage = new WXWebpageObject();
        webPage.webpageUrl = shareUrl;
        WXMediaMessage msg = new WXMediaMessage(webPage);
        if (title != null && !"".equals(title)) {
            if (title.length() < 100) msg.title = title;
            else msg.title = title.substring(0, 100);
        } else {
            msg.title = "球商";
        }
        if (excerpt != null && !"".equals(excerpt)) {
            if (excerpt.length() < 100) {
                msg.description = excerpt;
            } else {
                msg.description = excerpt.substring(0, 100);
            }
        } else {
            msg.description = "竞猜";
        }
        Bitmap thumb = BitmapFactory.decodeResource(c.getResources(), logo);
        msg.thumbData = bitmap2Bytes(thumb);

        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = buildTransaction("webpage");
        req.message = msg;
        req.scene = tag;
        req.openId = getWXOpenId(c);
        api.sendReq(req);
        return true;
    }

    //
    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    //
    public static String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    //
//    private static String genNonceStr()
//    {
//        Random random = new Random();
//        return MD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
//    }
//
//    private static long genTimeStamp()
//    {
//        return System.currentTimeMillis() / 1000;
//    }
//
    private static boolean isHasWeiXin(Context c) {
        return PackageUtils.getInstance(c).getCanSharePackageNameList().contains("com.tencent.mm");
    }

    public static void saveWXToken(Context c, String s) {
        JSONObject json;
        try {
            json = new JSONObject(s);
            SPUtils.write(c, ACCESS_TOKEN, json.optString(ACCESS_TOKEN));
            SPUtils.write(c, REFRESH_TOKEN, json.optString(REFRESH_TOKEN));
            SPUtils.write(c, OPEN_ID, json.optString(OPEN_ID));
            SPUtils.write(c, UNION_ID, json.optString(UNION_ID));
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    public static String getWXToken(Context c) {
        return SPUtils.read(c, ACCESS_TOKEN, "");
    }

    public static String getWXOpenId(Context c) {
        final String result = SPUtils.read(c, OPEN_ID, "");
        if (result.equals("")) {
            login(c);
        }
        return result;
    }

    public static void saveWXUserInfo(Context c,String s) {
        Set<String> set = new HashSet<>();
        try {
            JSONObject json = new JSONObject(s);
            SPUtils.write(c,OPEN_ID, json.optString(OPEN_ID));
            SPUtils.write(c,NICK_NAME, json.optString(NICK_NAME));
            SPUtils.write(c,SEX, json.optString(SEX));
            SPUtils.write(c,PROVINCE, json.optString(PROVINCE));
            SPUtils.write(c,CITY, json.optString(CITY));
            SPUtils.write(c,COUNTRY, json.optString(COUNTRY));
            SPUtils.write(c,HEAD_IMG_UTL, json.optString(HEAD_IMG_UTL));
            SPUtils.write(c,UNION_ID, json.optString(UNION_ID));
            if (json.has(PRIVILEGE)) {
                for (int i = 0; i < json.optJSONArray(PRIVILEGE).length(); i++) {
                    set.add(json.optJSONArray(PRIVILEGE).getString(i));
                }
            }
            SPUtils.write(c,PRIVILEGE, set);
        } catch (JSONException ex) {
            ex.printStackTrace();
        }
    }

    public static HashMap<String, String> getWXUserInfo(Context c) {
        HashMap<String, String> map = new HashMap<>();
        map.put(OPEN_ID, SPUtils.read(c,OPEN_ID, ""));
        map.put(NICK_NAME, SPUtils.read(c,NICK_NAME, ""));
        map.put(SEX, SPUtils.read(c,SEX, ""));
        map.put(PROVINCE, SPUtils.read(c,PROVINCE, ""));
        map.put(CITY, SPUtils.read(c,CITY, ""));
        map.put(COUNTRY, SPUtils.read(c,COUNTRY, ""));
        map.put(HEAD_IMG_UTL, SPUtils.read(c,HEAD_IMG_UTL, ""));
        map.put(UNION_ID, SPUtils.read(c,UNION_ID, ""));
        map.put("origin_type", "5");
        for (String key : map.keySet()) {
            KLog.e(TAG, key + " = " + map.get(key));
        }
        return map;
    }

    public static HashMap<String, String> rewardMoney(Context c,double money, String bounty_type) {
        final HashMap<String, String> result = new HashMap<>();
        result.put("appid", APP_ID_WEI_XIN);
        result.put("mch_id", APP_MCH_ID_WEI_XIN);
        result.put("nonce_str", WXUtils.buildTransaction(APP_MCH_ID_WEI_XIN));
        result.put("product_id", WXUtils.buildTransaction(APP_MCH_ID_WEI_XIN));
        result.put("body", "Wechat");
        result.put("total_fee", (int) (money * 100) + "");
        result.put("openid", getWXOpenId(c));
        result.put("bounty_type", bounty_type /*"article" : "tip"*/);
        return result;
    }
}
