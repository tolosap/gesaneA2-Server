/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.rafaelaznar.helper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import eu.rafaelaznar.connection.BoneCPImpl;
import eu.rafaelaznar.connection.ConnectionInterface;



public class AppConfigurationHelper {
     public static int getJsonMsgDepth() {
        return 1;
    }

    public static ConnectionInterface getSourceConnection() throws Exception {
        ConnectionInterface oDataConnectionSource = new BoneCPImpl();
        return oDataConnectionSource;
    }

    public static Gson getGson() throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("dd/MM/yyyy HH:mm");
        Gson oGson = gsonBuilder.excludeFieldsWithoutExposeAnnotation().create();
        return oGson;
    }
}
