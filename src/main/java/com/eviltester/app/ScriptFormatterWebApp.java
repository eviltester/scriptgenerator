package com.eviltester.app;

import static spark.Spark.get;

public class ScriptFormatterWebApp {

    public static void main(String[] args) {
        get("/", (req, res) -> "Hello World");
    }


}
