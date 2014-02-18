package com.epsi.VignPerzMal.controllers;

import com.epsi.VignPerzMal.daos.JSONParserDao;

public class JSONParserController {
	
	public static JSONParserDao getJSONParser(){
		JSONParserDao jsonParser = new JSONParserDao();
		return jsonParser;
		
	}

	//public List<Store> exemple()  {
		//List<Store> stores = new ArrayList<Store>();

		
	//	return stores;
	//}

}