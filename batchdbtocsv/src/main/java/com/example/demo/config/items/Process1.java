package com.example.demo.config.items;

import org.springframework.batch.item.ItemProcessor;

import com.example.demo.entities.Produit;

public class Process1 implements ItemProcessor<Produit, Produit>{

	@Override
	public Produit process(Produit item) throws Exception {
		item.setFinalprix(item.getFinalprix()+20);
		return item;
	}

}
