package com.tysci.ballq.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tencent.mm.sdk.constants.ConstantsAPI;
import com.tencent.mm.sdk.modelbase.BaseReq;
import com.tencent.mm.sdk.modelbase.BaseResp;
import com.tencent.mm.sdk.openapi.IWXAPIEventHandler;
import com.tysci.ballq.app.BaseActivity;
import com.tysci.ballq.utils.ToastUtils;
import com.tysci.ballq.utils.WeChatUtils;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        WeChatUtils.api.handleIntent(getIntent(), this);
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
    protected void onNewIntent(Intent intent)
    {
        super.onNewIntent(intent);
        setIntent(intent);
        WeChatUtils.api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq arg0)
    {

    }

    @Override
    public void onResp(final BaseResp resp)
    {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX)
        {
            switch (resp.errCode)
            {
                case 0:
                    ToastUtils.show(this,"打赏成功");
                    // TODO do reward success
//                    if (BallQApplication.getRewardActivity() != null)
//                    {
//                        BallQApplication.getRewardActivity().payOk();
//                    }
                    break;
                case -1:
                    ToastUtils.show(this,"打赏失败");
                    break;
                case -2:
                    ToastUtils.show(this,"打赏取消");
                    break;
            }
            WXPayEntryActivity.this.finish();
        }
    }
}
