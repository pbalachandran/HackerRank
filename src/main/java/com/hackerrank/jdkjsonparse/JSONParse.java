package com.hackerrank.jdkjsonparse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class JSONParse {

    private ScriptEngine engine;

    public void initEngine() {
        ScriptEngineManager sem = new ScriptEngineManager();
        this.engine = sem.getEngineByName("javascript");
    }

    public void parseJson() throws IOException, ScriptException {
        initEngine();
        Path path = Paths.get("src/main/resources/sample.json");

        String json = new String(Files.readAllBytes(path));
        String script = "Java.asJSONCompatible(" + json + ")";
        Object result = this.engine.eval(script);

        List<Map<String, String>> contents =  (List<Map<String, String>>)result;
        contents.forEach(jMap -> {
            jMap.keySet().forEach(k -> {
                System.out.println(k + ":" + jMap.get(k));
            });
        });
    }

    public static void main(String[] args) {
        JSONParse jsonParse = new JSONParse();
        try {
            jsonParse.parseJson();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }
}