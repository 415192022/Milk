package com.yundong.milk.util;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by lj on 2016/12/6.
 * 分享
 */
public class ShareUtil {

    public static RequestQueue volleyQueue;
    public static Context mContext;

    public static void showShare(Context context, final String title, String imgUrl, String linkUrl, final String goodsId) {
        mContext = context;
        volleyQueue = Volley.newRequestQueue(context);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网、QQ和QQ空间使用
//        oks.setTitle("标题");
        oks.setTitle(title);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
//        oks.setTitleUrl("http://sharesdk.cn");
        oks.setTitleUrl(linkUrl);

        // text是分享文本，所有平台都需要这个字段
        oks.setText("说点什么吧...");
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
//        oks.setImageUrl("http://f1.sharesdk.cn/imgs/2014/02/26/owWpLZo_638x960.jpg");
        oks.setImageUrl(imgUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
//        oks.setUrl("http://sharesdk.cn");
        oks.setUrl(linkUrl);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
//        oks.setComment("我是测试评论文本");
        oks.setComment("评论点什么吧...");
        // site是分享此内容的网站名称，仅在QQ空间使用
//        oks.setSite("ShareSDK");
        oks.setSite("快来购物吧");
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
//        oks.setSiteUrl("http://sharesdk.cn");
        oks.setSiteUrl(linkUrl);

        // 分享回调
        oks.setCallback(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Log.e("我分享商品----", platform.getName() + "  ***   ");
                String platformMethod = platform.getName();
                if (platformMethod.equals("SinaWeibo")){ //新浪微博
//                    shareGoods(goodsId, title, "5");
                }else if (platformMethod.equals("QZone")){ // QQ空间
//                    shareGoods(goodsId, title, "3");
                } else if (platformMethod.equals("Wechat")) { //微信好友
//                    shareGoods(goodsId, title, "2");
                }else if (platformMethod.equals("QQ")){ // QQ好友
//                    shareGoods(goodsId, title, "1");
                }else if (platformMethod.equals("WechatMoments")){ // 微信朋友圈
//                    shareGoods(goodsId, title, "4");
                }
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(Platform platform, int i) {

            }
        });
        // 启动分享GUI
        oks.show(context);
    }

//    private static void shareGoods(String goodsId, String goodsName, String type){
//        HashMap<String, String> map = new HashMap<>();
//        map.put("uid", UserInfoCache.getCurrentUserId());
//        map.put("goods_id", goodsId);
//        map.put("goods_name", goodsName);
//        map.put("type", type); // 1 QQ    2 微信    3 Sina
//        JSONObject ob = new JSONObject(map);
//        JsonObjectRequest request = new JsonObjectRequest(MainApi.SHARE_GOODS, ob, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject jsonObject) {
//                try {
//                    Log.e("----------", jsonObject.toString() + "  *****  ");
//                    JSONObject object = new JSONObject(jsonObject.toString());
//                    if (object.getString("code").equals("2000")) {
//                        Log.e("调用第三方登录时我调用的后台接口", "***********");
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, errorListener);
//        volleyQueue.add(request);
//    }
//
//    static Response.ErrorListener errorListener = new Response.ErrorListener() {
//
//        @Override
//        public void onErrorResponse(VolleyError volleyError) {
//            Toast.makeText(mContext, "网络出现异常，请稍后再试", Toast.LENGTH_SHORT).show();
//        }
//    };
}
