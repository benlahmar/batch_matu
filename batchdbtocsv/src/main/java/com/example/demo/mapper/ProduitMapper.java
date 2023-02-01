package com.example.demo.mapper;

import com.example.demo.entities.Produit;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProduitMapper implements RowMapper<Produit> {

    @Override
    public Produit mapRow(ResultSet resultSet, int i)
    		throws SQLException {
    	Produit produit=new Produit();
    	produit.setId(resultSet.getLong("id"));
    	produit.setDesg(resultSet.getString("desg"));
    	produit.setPrix(resultSet.getDouble("prix"));
        return produit;
    }
}

