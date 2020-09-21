package edu.eci.arem;

import static spark.Spark.*;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import spark.Request;
import spark.Response;
import com.mongodb.BasicDBObject;

import edu.eci.arem.persistence.MongoConnection;

/**
 * Hello world!
 *
 */
public class App 
{
	private static MongoConnection mongo;
    public static void main( String[] args ) throws UnknownHostException
    {
    	port(getPort());
    	mongo = new MongoConnection();
    	get("/", (req, res) ->  inputView(req, res));
    	post("/", (req, res) ->  insertNewLog(req, res));
    	
    	   	
    }
    private static String  inputView(Request req, Response res){
    	String datos="";
		ArrayList<BasicDBObject> list= mongo.consult();
		int cont = 1;
		if(list.size()<=10) {
			for(BasicDBObject d:list) {
				 
				   datos+="<tr><td>"+String.valueOf(cont)+"</td><td>"+(d.get("message")).toString()+"</td><td>"+(d.get("date")).toString()+"</td></tr>";
				   cont++;
			}
		}
		else {
			for(int i=list.size()-10;i<list.size();i++) {
				 datos+="<tr><td>"+String.valueOf(cont)+"</td><td>"+((list.get(i)).get("message")).toString()+"</td><td>"+((list.get(i)).get("date")).toString()+"</td></tr>";
				 cont++;
			}
		}

    	 String view = "<!DOCTYPE html>"
                 + "<html>"
                 + "<body style=\"background-color:#32CD32;\">"
                 + "<style>"
                 + "table, th, td {"
                 + "border: 1px solid black;"
                 + "border-collapse: collapse;"
                 + "}"
                 + "</style>"
                 +"<center>"
                 +"<h1>Lista de Logs</h1>"
                 +"<br/>"
                 + "<form name='loginForm' method='post' action='/'>"
                 +"Log: <input type='text' name='message'/> <br/>"
                 +"<br/>"
                 +"<input type='submit' value='submit' />"
                 +"</form>"
                 +"<br/>"
 				 + "<Table>"
 				 + "<tr>"
 				+ "<th>No</th>"
 			     + "<th>Message</th>"
 			     + "<th>Date</th>"
 			  	 + "</tr>"
 			     + datos
 			     + "</Table>"
                 +"</center>"
                 + "</body>"
                 + "</html>";
    	 return view;
    	
    }
    private static String insertNewLog(Request req, Response res){
    	mongo.add(req.queryParams("message"), new Date());
    	return inputView(req,res);
    }
    static int getPort() {
	   	 if (System.getenv("PORT") != null) {
	   		 return Integer.parseInt(System.getenv("PORT"));
	   	 }
	   	 return 5000; //returns default port if heroku-port isn't set
   }
}
