package com.hackerrank.trial;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.Iterator;

public class JSONParse {

    public static class Car {
        public String brand;
        public int doors;

        public String toString() {
            return "Brand: " + brand + "\n" + "Doors: " + doors;
        }
    }

    public static void parse() {
        String json = "{\"brand\":\"Jeep\", \"doors\": 3}";
        Gson gson = new Gson();
        Car car = gson.fromJson(json, Car.class);
        System.out.println(car.toString());
    }

    public static void parseStream() {
        String json = "[" +
                "{\"brand\" : \"Toyota\", \"doors\" : 5}," +
                "{\"brand\" : \"Honda\", \"doors\" : 7}," +
                "{\"brand\" : \"LandRover\", \"doors\" : 7}" +
                "]";

        JsonParser parser = new JsonParser();
        JsonElement jsonTree = parser.parse(json);

        JsonArray cars = jsonTree.getAsJsonArray();
        Iterator<JsonElement> iterator = cars.iterator();
        while(iterator.hasNext()) {
            JsonElement element = iterator.next();
            System.out.println(convertToCar(element).toString());
        }
    }

    private static Car convertToCar(JsonElement element) {
        Gson gson = new Gson();
        return gson.fromJson(element, Car.class);
    }


    public static void main(String[] args) {
        System.out.println("***Individual Parse Start***");
        JSONParse.parse();
        System.out.println("***Individual Parse End***");

        System.out.println("***Stream Parse Start***");
        JSONParse.parseStream();
        System.out.println("***Stream Parse End***");
    }
}
