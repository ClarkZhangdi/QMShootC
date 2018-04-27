package com.amiba.frame.androidframe.util.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * com.amiba.frame.androidframe.util
 * Created by wudl on 17/9/7.
 */

public class GsonUtil {

    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    public GsonUtil() {
    }

    /**
     * 转成json格式的字符串
     *
     * @param object
     * @return
     */
    public static String toString(Object object) {
        String jsonStrg = null;
        if (gson != null) {
            jsonStrg = gson.toJson(object);
        }
        return jsonStrg;
    }

    /**
     * 转成Bean
     *
     * @param jsonStr
     * @param tClass 转换的类型Class
     * @param <T>
     * @return
     */
    public static <T> T toBean(String jsonStr, Class<T> tClass) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(jsonStr, tClass);
        }
        return t;
    }

    /**
     *
     * @param jsonStr
     * @param type 转换的类型Type
     * @param <T>
     * @return
     */
    public static <T> T toBean (String jsonStr, Type type){
        T t = null;
        if (gson !=null){
            t = gson.fromJson(jsonStr, type);
        }
        return t;
    }

    /**
     * 转成List
     *
     * @param jsonStr
     * @param tClass
     * @param <T>
     * @return
     */
    public static <T> List<T> toList(String jsonStr, Class<T> tClass) {
        List<T> list = null;
        if (gson != null) {
            list = gson.fromJson(jsonStr, new TypeToken<List<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成list中带有map的
     *
     * @param jsonStr
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> toListMaps(String jsonStr) {
        List<Map<String, T>> list = null;
        if (gson != null) {
            list = gson.fromJson(jsonStr,
                    new TypeToken<List<Map<String, T>>>() {
                    }.getType());
        }
        return list;
    }

    public static <T> Map<String, T> toMaps(String jsonStr) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(jsonStr, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    /**
     * 将一个对象转成json字符串并指定需要排除的属性名称列表
     * 如果没有指定属性名称集合，则将会全部转换
     * 默认时间会以yyyy-MM-dd HH:mm:ss的格式进行转换
     *
     * @param obj
     * @return
     */
    public static String toJsonExclude(Object obj, String... exclusionFields) {

        //创建GsonBuilder
        GsonBuilder builder = new GsonBuilder();

        //设置时间格式
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");

        //设置需要被排除的属性列表
        if (exclusionFields != null && exclusionFields.length > 0) {
            GsonExclusion gsonFilter = new GsonExclusion();
            gsonFilter.addExclusionField(exclusionFields);
            builder.setExclusionStrategies(gsonFilter);
        }

        //创建Gson并进行转换
        Gson gson = builder.create();
        return gson.toJson(obj);
    }

    /**
     * 将一个对象转成json字符串并指定需要需要转换的属性名称列表
     * 如果没有指定属性名称集合，则将会全部转换
     * 默认时间会以yyyy-MM-dd HH:mm:ss的格式进行转换
     *
     * @param obj
     * @return
     */
    public static String toJsonInclude(Object obj, String... includeFields) {
        //创建GsonBuilder
        GsonBuilder builder = new GsonBuilder();

        //设置时间格式
        builder.setDateFormat("yyyy-MM-dd HH:mm:ss");

        //设置需要转换的属性名称
        if (includeFields != null && includeFields.length > 0) {
            GsonInclusion gsonFilter = new GsonInclusion();
            gsonFilter.addInclusionFields(includeFields);
            builder.setExclusionStrategies(gsonFilter);
        }

        //创建Gson并进行转换
        Gson gson = builder.create();
        return gson.toJson(obj);
    }
}
