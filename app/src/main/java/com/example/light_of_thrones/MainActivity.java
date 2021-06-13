package com.example.light_of_thrones;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Menu;
import android.widget.Button;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.bumptech.glide.Glide;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    View view;
    private MediaPlayer mp;
    private ImageButton botonNext;
    private ImageButton botonForward;
    private ImageButton botonOn;
    private ImageButton botonLinterna;
    String cameraID;
    int images[];
    int turn[];
    View screenview;
    private CameraManager cameraManager;
    int posicion=0;
    int turnCounter=0;
    Boolean ligth=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonNext = (ImageButton) findViewById(R.id.next);
        botonForward = (ImageButton) findViewById(R.id.forward);
        botonLinterna = (ImageButton) findViewById(R.id.Button);
        botonOn = (ImageButton) findViewById(R.id.on);


        images=new int[]{R.drawable.snow,R.drawable.cerci,R.drawable.dayneris,R.drawable.enano,R.drawable.jaime,R.drawable.joy,R.drawable.melisandre,R.drawable.mujer,R.drawable.nigth,R.drawable.rey,R.drawable.tywin,R.drawable.starkhijo,R.drawable.aguja,R.drawable.samsa,R.drawable.stark};
        screenview=findViewById(R.id.rView);

        turn=new int[]{R.drawable.fondoluz,R.drawable.fondonegro};



        cameraManager= (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraID= cameraManager.getCameraIdList()[0];
        }catch (Exception e){
            e.printStackTrace();
        }




        mp = MediaPlayer.create(this, R.raw.music);
        mp.start();
        mp.setLooping(true);

    }



    public void nextImage(View view) {
        posicion++;
        if (posicion >= images.length) {
            posicion=0;
            screenview.setBackgroundResource(images[posicion]);
        }else{
            screenview.setBackgroundResource(images[posicion]);
        }

    }
    public void forwardImage(View view) {

        posicion--;
        if (posicion<0) {
            posicion=14;
            screenview.setBackgroundResource(images[posicion]);
        }else{
            screenview.setBackgroundResource(images[posicion]);
        }

    }



    public void encender(View view) {


        if(ligth==false) {


            try {
                cameraManager.setTorchMode(cameraID, true);
                ligth = true;
                turnCounter=0;
                botonLinterna.setImageResource(R.drawable.fondoluz);
                botonOn.setImageResource(R.drawable.on);

            } catch (Exception e) {
                e.printStackTrace();

            }

        }else if (ligth=true){
            try {
                cameraManager.setTorchMode(cameraID, false);
                ligth = false;
                turnCounter=1;
                botonLinterna.setImageResource(R.drawable.fondonegro);
                botonOn.setImageResource(R.drawable.off);
            } catch (Exception e) {
                e.printStackTrace();

            }

            }

        }



    @Override
    public void onPause() {
        super.onPause();
        mp.pause();

    }

    @Override
    public void onResume() {
        super.onResume();
        mp.start();

    }
    @Override
    protected void onStop() {
        super.onStop();


    }

}