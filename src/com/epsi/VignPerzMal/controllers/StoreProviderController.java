package com.epsi.VignPerzMal.controllers;

import java.net.URL;
import java.util.AbstractList;

import com.epsi.VignPerzMal.models.Store;
import com.epsi.VignPerzMal.parser.FacadeParser;

public class StoreProviderController {

	public AbstractList<Store> retrieve(URL url){
		FacadeParser facade = new FacadeParser();
		return facade.parse(url);
	}
}