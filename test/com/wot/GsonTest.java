package com.wot;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wot.server.DaoCommunityClan2;

public class GsonTest {

    public static class TestClass {
        public Map<Integer, Integer> mapField = null;
    }
    

    public static void main(String[] args) throws Exception {
        Gson gson = new GsonBuilder().create();

        String quotes = "{\"mapField\": {\"5\": \"6\", \"7\": \"8\"}}";
        String keyQuotes = "{\"mapField\": {\"5\": 6, \"7\": 8}}";
//        String noQuotes = "{\"mapField\": {5: 6, 7: 8}}";
//
        TestClass res = null;
        res = gson.fromJson(quotes, TestClass.class);
        res = gson.fromJson(keyQuotes, TestClass.class);
        //res = gson.fromJson(noQuotes, TestClass.class); //bug
        
        String wotData = "{	\"status\":\"ok\"," +	
        					"\"count\":1," + 
        					"\"data\":" + 
        						" 	{\"500006074\":{	\"members_count\":56,\"description\":\"la description\","+ "\"created_at\":1328978449," +
        											"\"request_availability\":false," +
        											"\"members\":{" +
        															"\"503224066\":{\"account_id\":503224066,\"created_at\":1362947902,\"updated_at\":0,\"account_name\":\"voleurbleu\"}," + 
        															"\"503777028\":{\"account_id\":503777028,\"created_at\":1369331057,\"updated_at\":0,\"account_name\":\"mwa2\"}" +
        														"}" +
        										   "}" +
        							"}" +
        				  "}";
        DaoCommunityClan2 comClan = null;
        comClan = gson.fromJson(wotData, DaoCommunityClan2.class);
        System.out.println(comClan);
        
        	
    }
}