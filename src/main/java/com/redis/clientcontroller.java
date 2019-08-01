package com.redis;

//import java.io.InputStream;
//import java.util.Properties;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import redis.clients.jedis.Jedis;

@RestController
@RequestMapping("server")
public class clientcontroller {
 public static String datainport,key;
	@RequestMapping("/getData")
	public String server1(@RequestParam String server , @RequestParam int port ) throws Exception {
		
		//InputStream input = clientcontroller.class.getClassLoader().getResourceAsStream("application.properties");
		
        System.out.println(server);
        System.out.println(port);
	 //JSONObject obj = new JSONObject(data);
		//String server = obj.getString("server");
		// int port=(int) Long.parseLong(port);
		//int port = obj.getInt("port");
		Jedis jedis = new Jedis("192.168.195.233", 6379);
         try {
         String serverstring =jedis.get(server);
         JSONObject object = new JSONObject(serverstring);
		 JSONArray n1 = object.getJSONArray("instance");
		 for (int i = 0; i <n1.length(); i++) {
		  int key = n1.getJSONObject(i).getInt("port");
		 if( port == key ) {
		 JSONObject value =n1.getJSONObject(i); 
		String output=value.toString();
		  datainport="Server : "+server+".   Data : "+output;
		 //System.out.println(value);
		 }}
		 }finally {
			 jedis.close();
		 }
		return datainport;
		 }
	
}
