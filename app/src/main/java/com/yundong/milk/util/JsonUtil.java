package com.yundong.milk.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lj on 2016/3/21.
 */
public class JsonUtil {

    private static Gson mGson;

    public static void init() {
        mGson = new GsonBuilder()
                .setPrettyPrinting() //对json结果格式化.
                .serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss:SSS")//时间转化为特定格式
                .enableComplexMapKeySerialization() //支持Map的key为复杂对象的形式
                .setVersion(1.0)    //有的字段不是一开始就有的,会随着版本的升级添加进来,那么在进行序列化和返序列化的时候就会根据版本号来选择是否要序列化.
                .create();
    }

    /**
     * 对象转json
     * @param obj
     * @return
     */
    public static String objectToJson(Object obj) {
        return mGson.toJson(obj);
    }

    /**
     * json转对象集合
     * @param jsonStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T extends Object> T jsonToBeanList(String jsonStr, Class<?> cls) {
        List<Object> list = new ArrayList<>();
        JSONArray jsonArray;
        try {
            jsonArray = new JSONArray(jsonStr);
            int length = jsonArray.length();
            for (int i = 0; i < length; i++) {
                String json = jsonArray.getString(i);
                Object obj = jsonToBean(json, cls);
                list.add(obj);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return (T) list;
    }

    /**
     * json转对象
     * @param jsonStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T extends Object> T jsonToBean(String jsonStr, Class<?> cls) {
        Object obj = null;
        if (!TextUtils.isEmpty(jsonStr)) {
            obj = mGson.fromJson(jsonStr, cls);
        }
        return (T) obj;
    }

}
