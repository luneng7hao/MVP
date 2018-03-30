package com.mvp.util.other;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Gson工具
 * @author IT01
 *
 */
public class UtilGson {
     
	/**'
	 * 实体类等转换成为json
	 * @param obj
	 * @return
	 */
	public static String bean2Json(Object obj){
    	 Gson gson=new Gson();
    	 return gson.toJson(obj);
     }

     /**
      * json转换为单个实体类
      * @param json
      * @param cls
      * @return
      */
     public static <T> T json2bean(String json, Class<T> cls){
    	 Gson gson=new Gson();
    	 T t=null;
    	 t=(T) gson.fromJson(json, cls);
    	 
    	 return t;
     }
     /**
      * json转换为实体类集
      * @param jsonString
      * @param cls
      * @return
      */
     public static <T> List<T> json2beans(String jsonString, Class<T> cls) {
         List<T> list = new ArrayList<T>();
//         try {  
//             Gson gson = new Gson();  
//             list = gson.fromJson(jsonString, new TypeToken<List<T>>() {  
//             }.getType());  
//   
//         } catch (Exception e) {  
//             e.printStackTrace();  
//         }  
//         return list;  

         JsonArray array = new JsonParser().parse(jsonString).getAsJsonArray();
         for(final JsonElement elem : array){
        	 list.add(new Gson().fromJson(elem, cls));
         }
         return list; 
     }  
     
     /**
      * json转换为字符串的list的集合
      * @param jsonString
      * @return
      */
     public static List<String> getStringList(String jsonString) {
         List<String> list = new ArrayList<String>();
         try {  
             Gson gson = new Gson();
             list = gson.fromJson(jsonString, new TypeToken<List<String>>() {
             }.getType());  
         } catch (Exception e) {
             // TODO: handle exception  
         }  
         return list;  
   
     }  
     
     /**
      * json转换为map的list集合
      * @param jsonString
      * @return
      */
     public static List<LinkedHashMap<String,Object>> listKeyMap(String jsonString){
         List<LinkedHashMap<String,Object>> list=new ArrayList<LinkedHashMap<String,Object>>();
         try {  
             Gson gson = new Gson();
             list = gson.fromJson(jsonString, new TypeToken<List<LinkedHashMap<String,Object>>>() {
             }.getType());  
         } catch (Exception e) {
             // TODO: handle exception  
         }  
         return list;  
     }  
     
     /**
      * json转换为map集合
      * @param jsonString
      * @return
      */
     public static Map<String,String> keyMap(String jsonString){
         Map<String,String> map=new HashMap<String,String>();
         try {  
             Gson gson = new Gson();
             map = gson.fromJson(jsonString, new TypeToken<Map<String,String>>() {
             }.getType());  
         } catch (Exception e) {
             // TODO: handle exception  
         }  
         return map;  
     }  
}
