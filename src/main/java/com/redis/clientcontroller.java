package com.redis;

import java.io.InputStream;
import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("server")
public class clientcontroller {
 public static String datainport,key,value;
	@RequestMapping("/getData")
	public String server1(@RequestBody String data) throws Exception {
		Properties prop = new Properties();
		InputStream input = clientcontroller.class.getClassLoader().getResourceAsStream("application.properties");
		prop.load(input);

	  JSONObject obj = new JSONObject(data);
		String server = obj.getString("server");
		int port = obj.getInt("port");
		Jedis jedis = new Jedis("192.168.195.233", 6379);
         try {
         String serverstring =jedis.get(server);
         JSONObject object = new JSONObject(serverstring);
		 JSONArray n1 = object.getJSONArray("instance");
		 for (int i = 0; i <n1.length(); i++) {
		  int key = n1.getJSONObject(i).getInt("port");
		 if( port == key ) {
		 JSONObject value =n1.getJSONObject(i); 
		 datainport=value.toString();
		 //System.out.println(value);
		 }}
		 }finally {
			 jedis.close();
		 }
		return datainport;
	
		 }
}