package edu.escuelaing.arep;

import spark.Request;
import spark.Response;

import static spark.Spark.*;

public class sparkApp {

    public static Funciones func = new Funciones();
    public static void main(String[] args) {
        port(getPort());
        get("/exp", (req,res) -> expOperation(req,res));
        get("/ln", (req,res) -> lnOperation(req,res));
    }

    private static String expOperation(Request req, Response res) {

        Double num = Double.parseDouble(req.queryParams("value"));
        Funciones funcionExp = new Funciones();

        res.type("application/json");

        String pageContent=

                "{"+
                        " \"operation\": \"exp\","+"\n"+
                        "\"input\":"+ num +"\n"+
                        " \"output\":" +funcionExp.exp(num) +
                        "}";
        return pageContent;


    }

    private static String lnOperation(Request req, Response res) {
        Double num = Double.parseDouble(req.queryParams("value"));
        Funciones funcionLn = new Funciones();
        res.type("application/json");

        String pageContent=
                "{"+
                        " \"operation\": \"ln\","+"\n"+
                        "\"input\":"+ num +"\n"+
                        " \"output\":" +funcionLn.ln(num) +
                        "}";

        return pageContent;


    }
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 4567; //returns default port if heroku-port isn't set (i.e. on localhost)
    }
}
