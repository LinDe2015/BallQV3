package com.tysci.ballq.wxapi;

import android.os.Bundle;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.socks.library.KLog;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.modelmsg.SendAuth;
import com.tencent.mm.sdk.modelmsg.SendMessageToWX;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tysci.ballq.app.BaseActivity;
import com.tysci.ballq.networks.HttpClientUtil;
import com.tysci.ballq.utils.ToastUtils;
import com.tysci.ballq.utils.WeChatUtils;

import okhttp3.Call;
import okhttp3.Request;

public class WXEntryActivity extends BaseActivity implements IWXAPIEventHandler, HttpClientUtil.StringResponseCallBack {
    //    private static final int WHAT_SUCCESS = 1;
//    private static final int WHAT_ERROR = -1;
//    private static final int GET_USER_INFO_SUCCESS = 12;
//    private static final int GET_USER_INFO_ERROR = -12;
    public static boolean isPermissionSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void setContentView() {
    }

    @Override
    public boolean isNeedBindEventBus() {
        return false;
    }

    @Override
    public void onViewClick(View view) {

    }

    @Override
    protected void initViews() throws Exception {
    }

    @Override
    public void onReq(BaseReq arg0) {
    }

    @Override
    public void onResp(BaseResp resp) {
        KLog.e(TAG, resp.getClass().getName());
        isPermissionSuccess = false;
        switch (resp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                isPermissionSuccess = true;
                ToastUtils.show(this, "用户授权成功");
                KLog.e(TAG, "class = " + resp.getClass());
                if (resp instanceof SendAuth.Resp) {
//                    VolleyUtils.getInstance().get(WeChatUtils.getTokenUrl(this,resp), false, this.getClass());
                    HttpClientUtil.getHttpClientUtil().sendGetRequest(TAG, WeChatUtils.getTokenUrl(this, resp), 0, this);
//                    VolleyUtils.getInstance().get(WXUtils.getTokenUrl(resp),false, LoginOrRegisterActivity.class);
//                    finish();
                } else if (resp instanceof SendMessageToWX.Resp) {
                    finish();
                }
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                ToastUtils.show(this, "用户拒绝授权");
//                SharedPreferencesUtils.exitLogin();
                // TODO do exitLogin
                finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                ToastUtils.show(this, "用户取消授权");
                finish();
                break;
            // default:
            // finish();
            // break;
        }

    }

    @Override
    public void onBefore(Request request) {
    }

    @Override
    public void onError(Call call, Exception error) {
    }

    @Override
    public void onSuccess(Call call, String response) {
        KLog.e(TAG, "response = " + response);
        final JSONObject o = JSON.parseObject(response);
        if (o.containsKey("access_token") && o.containsKey("refresh_token")) {
            WeChatUtils.saveWXToken(this, response);
//            VolleyUtils.getInstance().get(WXUtils.getUserInfoUrl(), false, this.getClass());
            HttpClientUtil.getHttpClientUtil().sendGetRequest(TAG, WeChatUtils.getUserInfoUrl(this), 0, this);
        }
        if (o.containsKey("nickname") && o.containsKey("headimgurl")) {
            WeChatUtils.saveWXUserInfo(this, response);
//            EventMessage em = new EventMessage(EventType.WEIXIN_LOGIN_SUCCESS);
//            em.putClass(LoginOrRegisterActivity.class);
//            EventPost.postMainThread(em);
            finish();
        }
    }

    @Override
    public void onFinish(Call call) {
    }
}
