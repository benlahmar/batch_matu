package com.example.demo.comp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.UrlResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.example.demo.entities.Produit;
import com.example.demo.entities.User;

public class XmlItemReader {

	
	
//	public StaxEventItemReader<Produit> xx()
//	{
//		StaxEventItemReader<Produit> reader = new StaxEventItemReader<Produit>();
//		  reader.setResource(new ClassPathResource("produits.xml"));
//		  reader.setFragmentRootElementName("produits");
//		  
//		  Map<String, String> aliases = new HashMap<String, String>();
//		  aliases.put("user", "com.example.demo.entities.Produit");
//		  
//		  XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
//		  xStreamMarshaller.setAliases(aliases);
//		  
//		  reader.setUnmarshaller(xStreamMarshaller);
//		  
//		  return reader;
//	}
	
	public StaxEventItemReader<User> reader2()
	{
		 StaxEventItemReader<User> reader = new StaxEventItemReader<>();
	        //reader.setResource(new UrlResource(""));
	        reader.setResource(new ClassPathResource("produits.xml"));
	        reader.setFragmentRootElementNames(new String[] { "users" });
	        reader.setUnmarshaller(newCustomerDataMarshaller());
	        return reader;
	}
	 private Jaxb2Marshaller newCustomerDataMarshaller() {
	        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
	        XStreamMarshaller xStreamMarshaller = new XStreamMarshaller();
	        
	        
	        marshaller.setClassesToBeBound(User.class);
	        return marshaller;
	    }
}
