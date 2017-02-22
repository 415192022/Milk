package com.yundong.milk.net;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.yundong.milk.config.ApiConfig;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by lj on 2016/12/20.
 * volley网络请求封装类
 */
public class VolleyUtil {

    private static Gson gson = new Gson();
    private static RequestQueue requestQueue = null;
    private static VolleyUtil volleyUtil;

    public static VolleyUtil getVolleyUtil(Context context) {
        if (volleyUtil == null)
            volleyUtil = new VolleyUtil();
        requestQueue = Volley.newRequestQueue(context);
        return volleyUtil;
    }

    /**
     * Post请求 url,HashMap,volleyInterface,Class
     * 以实体类形式返回，然后进行强转
     */
    public static void SendVolleyPostBean(String url, HashMap<?, ?> hashMap, final volleyInterface volleyInterface) {

        JSONObject jsonObject = new JSONObject(hashMap);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, ApiConfig.HTTP_API + url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                try {
                    JSONObject object = new JSONObject(jsonObject.toString());
                    JSONObject allData = new JSONObject(object.toString());
                    String returnCode = allData.getString("code");
                    if (returnCode.equals("2000")) { //成功
                        volleyInterface.ResponseResult(allData.getString("data"));
                    }else if (returnCode.equals("0")){ //未配置权限
                        volleyInterface.ResponseError("请求失败,错误码0，未配置权限或http错误");
                    }else if (returnCode.equals("400")){ // bad request 请求出现语法错误
                        volleyInterface.ResponseError("请求失败,错误码0，未配置权限或http错误");
                    }else if (returnCode.equals("403")){ // forbidden 资源不可用
                        volleyInterface.ResponseError("请求失败,错误码403，资源不可用");
                    }else if (returnCode.equals("404")){ // not found 无法找到指定位置的资源
                        volleyInterface.ResponseError("请求失败,错误码400，语法格式有误");
                    }else if (returnCode.equals("500")){ // // 服务器内部错误
                        volleyInterface.ResponseError("请求失败,错误码500，服务器内部错误");
                    }else { //未知错误
                        volleyInterface.ResponseError("请求失败，未知错误");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyInterface.ResponseError(volleyError.toString());
            }
        });
        AddRequestQueue(jsonObjectRequest, true);
    }

    /**
     * Post请求 url,HashMap,volleyInterface,Class
     * 以实体类形式返回，然后进行强转
     */
    public static void SendVolleyPostBean(String url, HashMap<?, ?> hashMap, final volleyInterface volleyInterface, final Class<?> aClass) {

        JSONObject jsonObject = new JSONObject(hashMap);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                Object userBean = (Object) gson.fromJson(jsonObject.toString(), aClass);
                volleyInterface.ResponseResult(userBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyInterface.ResponseError(volleyError);
            }
        });

        AddRequestQueue(jsonObjectRequest, true);
    }

    /**
     * GET请求 url,volleyInterface,Class
     * 以实体类形式返回，然后进行强转
     **/
    public static void SendVolleyGetBean(String url, final volleyInterface volleyInterface, final Class<?> aClass) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,  ApiConfig.HTTP_API + url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Object userBean = (Object) gson.fromJson(jsonObject.toString(), aClass);
                volleyInterface.ResponseResult(userBean);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyInterface.ResponseError(volleyError);
            }
        });
        AddRequestQueue(jsonObjectRequest, true);
    }


    /**
     * POST请求 url,volleyInterface,Class
     * 以JSON形式返回，然后进行强转 为JSONobject
     **/
    public static void SendVolleyPostJsonobject(String url, final volleyInterface volleyInterface, final Class<?> aClass) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                volleyInterface.ResponseResult(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyInterface.ResponseError(volleyError);
            }
        });
        AddRequestQueue(jsonObjectRequest, true);
    }

    /**
     * POST请求 url,volleyInterface,Class
     * 以JSON形式返回，然后进行强转 为JSONobject
     **/
    public static void SendVolleyPostJsonobject(String url, final volleyInterface volleyInterface) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                volleyInterface.ResponseResult(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyInterface.ResponseError(volleyError);
            }
        });
        AddRequestQueue(jsonObjectRequest, true);
    }

    /**
     * GET请求 url,volleyInterface,Class
     * 以JSON形式返回，然后进行强转 为JSONobject
     **/
    public static void SendVolleyGetJsonobject(String url, final volleyInterface volleyInterface, final Class<?> aClass) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                volleyInterface.ResponseResult(jsonObject);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                volleyInterface.ResponseError(volleyError);
            }
        });
        // 加入队列 及设置 请求时间
        AddRequestQueue(jsonObjectRequest, true);
    }

    // 此方法是 Volley配置方法
    public static void AddRequestQueue(JsonObjectRequest req, boolean isSave) {
        // 设置超时时间
        req.setRetryPolicy(new DefaultRetryPolicy(3 * 1000, 1, 1.0f));
        // 是否开启缓存；
        req.setShouldCache(isSave);
        // 将请求加入队列
        requestQueue.add(req);
        // 开始发起请求
        requestQueue.start();
    }

    public interface volleyInterface {

        void ResponseResult(Object jsonObject);

        void ResponseError(Object volleyError);

    }

}
