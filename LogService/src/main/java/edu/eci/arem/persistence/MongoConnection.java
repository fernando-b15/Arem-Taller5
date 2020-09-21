package edu.eci.arem.persistence;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;



public class MongoConnection {
	private DBCollection coleccion;
	private DB db; 
	public MongoConnection() throws UnknownHostException {
		MongoClient mongoClient = new MongoClient("ec2-3-92-214-111.compute-1.amazonaws.com" , 27017);
		db= mongoClient.getDB("arem");
        coleccion= db.getCollection("logs");
        
	}
	public void add(String message,Date date) {
		BasicDBObject objeto= new BasicDBObject();
        objeto.put("message",message);
        objeto.put("date",date);
        coleccion.insert(objeto);
		
	}
	public ArrayList<BasicDBObject> consult() {
		ArrayList<BasicDBObject> registros = new ArrayList<BasicDBObject>();
		DBCursor mensajes = coleccion.find();
		while (mensajes.hasNext()){
			  
			  registros.add((BasicDBObject) mensajes.next());
		}
		return registros;
	}

}