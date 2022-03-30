package edu.escuelaing.arep.proxy;

import spark.Request;
import spark.Response;
import static spark.Spark.port;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.net.HttpURLConnection;
import static spark.Spark.*;

public class App {
    private static List<String> urls = new ArrayList<String>();
    private static Boolean semaforo;
    public static void main( String[] args )
    {
        semaforo=true;
        urls.add("http://http://ec2-34-203-225-83.compute-1.amazonaws.com:4567/");
        urls.add("http://http://ec2-54-84-139-108.compute-1.amazonaws.com:4567/");
        port(getPort());

        get("/exp","application/json",(req,res)->{
            res.type("application/json");
            return getRequest("exp",req.queryParams("value"));
        });
        get("/ln","application/json",(req,res)->{
            res.type("application/json");
            return getRequest("ln",req.queryParams("value"));
        });
    }


    public static String getRequest(String oper,String value) throws IOException
    {
        URL url;
        String ans="respuesta";
        System.out.println(ans);
        String server;
        try{
            if(semaforo)
            {
                server=urls.get(0);
            }else
            {
                server=urls.get(1);
            }
            semaforo= !semaforo;
            url=new URL(server+ oper + "?value="+ value);
            System.out.println(url);
            HttpURLConnection urlconection=(HttpURLConnection) url.openConnection();
            System.out.println(urlconection);
            urlconection.setRequestMethod("GET");

            BufferedReader input= new BufferedReader(new InputStreamReader(urlconection.getInputStream()));
            String inputline;
            StringBuffer content=new StringBuffer();
            while ((inputline = input.readLine()) !=null)
            {
                content.append(inputline);

            }
            System.out.println(inputline);
            input.close();
            urlconection.disconnect();
            ans= content.toString();
            System.out.println(ans);
            return ans;
        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        }

        return ans;


    }

    private static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4568; //returns default port if heroku-port isn't set(i.e. on localhost)
    }
}
