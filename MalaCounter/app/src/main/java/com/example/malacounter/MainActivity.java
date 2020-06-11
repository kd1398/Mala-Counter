package com.example.malacounter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.BufferedReader;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.txtManka) TextView txtMankka;
    @BindView(R.id.txtMala) TextView txtMala;
    @BindView(R.id.gayatrimaScreen) FrameLayout gayatrimantraScreen;
    @BindView(R.id.txtcount) EditText txtcount;
    @BindView(R.id.btnresume) Button btnresume;
    @BindView(R.id.btnpause) Button btnpause;

    int manka,mala=0,i=0;
    MediaPlayer player;

    @Optional
    @OnClick({R.id.gayatrimaScreen,R.id.btnpause,R.id.btnresume})
    public void OnClick(View v){
        switch (v.getId()){
            case R.id.gayatrimaScreen:
//                Toast.makeText(this,"First enter the number of mala",Toast.LENGTH_LONG).show();
                counter();
                break;
            case R.id.btnpause:
                player.pause();
                btnresume.setEnabled(true);
                break;
            case R.id.btnresume:
                player.start();
                break;
        }
    }

    private void counter() {
        btnpause.setEnabled(true);
        i++;
        //player.start();
        int count = Integer.parseInt(txtcount.getText().toString());
        int m = count*108;
        gayatrimantraScreen.setEnabled(true);
        if(i<=m){
            player.start();
            gayatrimantraScreen.setEnabled(false);
            player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                manka++;
                txtMankka.setText("" + manka);
                if(manka==108){
                    manka=0;
                    mala++;
                    txtMankka.setText(""+manka);
                    txtMala.setText(""+mala);
                }
                counter();
            }
        });

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //hide actionbar
        getSupportActionBar().hide();

        //hide statusbar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        ButterKnife.bind(this);

        player = MediaPlayer.create(this,R.raw.gayatrimantra);
        Snackbar snackbar = Snackbar
                .make(gayatrimantraScreen, "First enter the number of mala and then tap on the screen to play music", Snackbar.LENGTH_LONG);
        snackbar.show();
        btnpause.setEnabled(false);
        btnresume.setEnabled(false);

        }
    }

