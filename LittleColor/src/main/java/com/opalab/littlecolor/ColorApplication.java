package com.opalab.littlecolor;


import android.app.Application;
import android.util.Log;

import com.opalab.littlecolor.settings.ColorSettings;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.util.Random;

import io.socket.*;

public class ColorApplication extends Application {

    private static ColorApplication instance;
    private static String code;

    public static ColorApplication get(){
        return instance;
    }

    public static String getCode(){
        return code;
    }

    private SocketIO socket;

    public SocketIO getSocket(){
        return socket;
    }

    private String generateCode(){
        Random rand = new Random();
        String new_code = "";
        for(int i=0; i<4; i++) new_code += ""+rand.nextInt(9);
        return new_code;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        code = this.generateCode();

        try{
            socket = new SocketIO(ColorSettings.SERVER_URL);

            socket.connect(new IOCallback() {
                @Override
                public void onDisconnect() {
                    Log.i("Socket","Disconect");
                }

                @Override
                public void onConnect() {
                    Log.i("Socket","Conect");
                }

                @Override
                public void onMessage(String s, IOAcknowledge ioAcknowledge) {
                    Log.i("Socket","Message");
                }

                @Override
                public void onMessage(JSONObject jsonObject, IOAcknowledge ioAcknowledge) {
                }

                @Override
                public void on(String s, IOAcknowledge ioAcknowledge, Object... objects) {
                }

                @Override
                public void onError(SocketIOException e) {
                    Log.i("Socket",e.getLocalizedMessage());
                }
            });

        } catch (MalformedURLException ex){
            Log.e("URL",ex.getLocalizedMessage());
        } catch (Exception ex){
            ex.printStackTrace();
        };

    }
}
