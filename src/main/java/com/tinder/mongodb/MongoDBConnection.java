package com.tinder.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;


public class MongoDBConnection {
    private static final String HOST = "mongodb+srv://posttwit:bOy8ELHPM7tm1bG8@testtinder.sy6jmqr.mongodb.net/test";
    private static final int PORT = 27017;
    private static final String DATABASE = "mydatabase";

    public static MongoDatabase getConnection() {
        MongoClient client = new MongoClient(HOST, PORT);
        return client.getDatabase(DATABASE);
    }
}
