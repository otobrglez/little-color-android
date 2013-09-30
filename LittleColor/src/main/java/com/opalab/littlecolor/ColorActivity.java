package com.opalab.littlecolor;

import com.opalab.littlecolor.settings.ColorSettings;
import com.opalab.littlecolor.util.SystemUiHider;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import org.json.JSONException;
import org.json.JSONObject;

public class ColorActivity extends Activity {

    public ColorGradient colorGradient = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        colorGradient = new ColorGradient(0,0);

        setContentView(R.layout.activity_color);

        final View colorView = findViewById(R.id.color_view);

        colorView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
            colorGradient.setHeight(view.getHeight());
            colorGradient.setWidth(view.getWidth());

            int maskedAction = motionEvent.getActionMasked();

            String color = null;
            try{
                color = colorGradient.getColor(motionEvent.getX(),motionEvent.getY());
                view.setBackgroundColor(Color.parseColor(color));
            }catch (Exception e){
                color = null;
            };

            if(maskedAction == MotionEvent.ACTION_UP || maskedAction == MotionEvent.ACTION_POINTER_UP || maskedAction == MotionEvent.ACTION_CANCEL )
                if(color != null)
                    sendColor(color);

            return true;
            }
        }); // setOnTouchListener

        new AlertDialog.Builder(this)
            .setMessage("Visit "+ ColorSettings.SERVER_URL+"/"+ColorApplication.getCode()+" on your other device.")
            .show();
    }

    public void sendColor(String color){
        try{
            JSONObject data = new JSONObject();
            data.putOpt("code", ColorApplication.getCode());
            data.putOpt("color", color);
            ColorApplication.get().getSocket().emit("set_color", data);

        }catch(JSONException ex){
            ex.printStackTrace();
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
