package com.example.demo.comp;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.example.demo.entities.Produit;


@Component
public class MyCustomReader implements ItemReader<Produit>{

	@Override
	public Produit read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		// TODO Auto-generated method stub
		return null;
	}
	


}