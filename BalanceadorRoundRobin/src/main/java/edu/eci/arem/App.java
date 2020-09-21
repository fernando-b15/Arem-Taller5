package edu.eci.arem;

import static spark.Spark.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

import spark.Request;
import spark.Response;


/**
 * Hello world!
 *
 */
public class App 
{
	private static int ports[]= {8001,8002,8003};
	private static int selected = 0 ; 
    public static void main( String[] args ) throws UnknownHostException
    {
    	port(getPort());
    	get("/", (req, res) ->  inputView(req, res));
    	post("/", (req, res) ->  register(req, res));
    	
    	   	
    }
    private static String  inputView(Request req, Response res){
    	 String view="";
		 try {
			URL url = new URL("http://ec2-3-92-214-111.compute-1.amazonaws.com:"+String.valueOf(ports[selected])+"/consultlogs");
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			String inputLine;
			String datos="";
			while ((inputLine = reader.readLine()) != null) {
	                System.out.println(inputLine);
	                datos+=inputLine;
	        }
			String logs[]=datos.split(",");
			String logsToTable="";
			String server = "Esta Actualmente en el LogService No"+String.valueOf(selected+1);
			changePortBalance();
			for(String log:logs) {
				System.out.println("xd  "+log);
				String info[]=log.split("-");
				logsToTable+="<tr><td>"+info[0]+"</td><td>"+info[1]+"</td><td>"+info[2]+"</td></tr>";
				
			}
			view = "<!DOCTYPE html>"
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
	                 +"<h2>"+server+"</h2>"
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
	 			     + logsToTable
	 			     + "</Table>"
	                 +"</center>"
	                 + "</body>"
	                 + "</html>";
		 
	    } catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
    	return view;
    	
    }
    private static String  register(Request req, Response res){
    	try {
			URL url = new URL("http://ec2-3-92-214-111.compute-1.amazonaws.com:"+String.valueOf(ports[selected])+"/savelogs?message="+(req.queryParams("message").replace(' ','_')));
			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			changePortBalance();
			String inputLine;
			while ((inputLine = reader.readLine()) != null) {
	                System.out.println(inputLine);
			}        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return inputView(req,res);
    	
    }
    private static void changePortBalance() {
    	if(selected<2) {
    		selected+=1;
    	}
    	else {
    		selected  = 0;
    	}
    }
    static int getPort() {
	   	 if (System.getenv("PORT") != null) {
	   		 return Integer.parseInt(System.getenv("PORT"));
	   	 }
	   	 return 4000; //returns default port if heroku-port isn't set
  }
}  
