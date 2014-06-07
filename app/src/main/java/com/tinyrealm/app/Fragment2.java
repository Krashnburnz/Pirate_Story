/**
 * Created by Daniel Henderson
 * University of Washington Spring 2014
 * Android Development TCSS 498
 * Story Book Application
 * Pirate Story
 */
package com.tinyrealm.app;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.net.URL;

//***************************************************************
// Fragment2:: Implements the page#2 fragment.
// last updated 2/08/14 (efa)
//***************************************************************
public class Fragment2 extends Fragment{
    private ImageButton ib;
    private View view;
    private boolean clicked;
    private ImageView img;
    private WebView web;
    private MediaPlayer mp;
    private MediaPlayer mpNarrate;
    private ImageButton imgBtnPlaySound;

    //hint button
    Button hint;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pg2, container, false);
        ib = (ImageButton) view.findViewById(R.id.imageButton1Pg2);
        clicked = false;
        img = (ImageView) view.findViewById(R.id.imageView2);
        hint = (Button) view.findViewById(R.id.buttonClue2);
        web = (WebView) view.findViewById(R.id.webView2);
        //web.getSettings().setLoadWithOverviewMode(true);
        //web.getSettings().setUseWideViewPort(true);
        //web.getSettings().setBuiltInZoomControls(true);

        web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        web.loadUrl("file:///android_res/drawable/page2noanimation.png");
        mp = MediaPlayer.create(view.getContext(), R.raw.shovelsoundshort);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(clicked) {
                    web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                    web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                    web.loadUrl("file:///android_res/drawable/page2noanimation.png");
                    // fit the width of screen
                    web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                    web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                    clicked = false;
                    if(!mp.isPlaying()) {
                        mp.start();
                    }

                    mp.start();
                } else  {
                    web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                    web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                    web.loadUrl("file:///android_res/drawable/page2animation.gif");
                    web.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
                    web.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
                    clicked = true;
                    if(!mp.isPlaying()) {
                        mp.start();
                    }
                }
            }
        });

        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                CharSequence text = "Hint: Touch the butterfly to watch it fly!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });


        imgBtnPlaySound = (ImageButton) view.findViewById(R.id.imageButtonPlaySound2);

        mpNarrate = MediaPlayer.create(view.getContext(), R.raw.narrate2);

        mpNarrate.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgBtnPlaySound.setBackgroundDrawable(getResources().getDrawable(R.drawable.playsoundbuttongradianoff));
            }
        });

        imgBtnPlaySound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mpNarrate.isPlaying()) {
                    imgBtnPlaySound.setBackgroundDrawable(getResources().getDrawable(R.drawable.playsoundbuttongradiant));
                    mpNarrate.start();
                } else {
                    mpNarrate.pause();
                    imgBtnPlaySound.setBackgroundDrawable(getResources().getDrawable(R.drawable.playsoundbuttongradianoff));

                }

            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    /*
    private int getScale(){
        Context context = view.getContext();
        Display display = ((WindowManager) context.getSystemService((Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width)/new Double(PIC_WIDTH);
        val = val * 100d;
        return val.intValue();
    }
    */

    //***************************************************************
    // Method that will stop all media players for this fragment.
    //***************************************************************
    public void closeAllMedia() {
        if(mpNarrate != null) {
            if(mpNarrate.isPlaying()) {
                mpNarrate.pause();
                imgBtnPlaySound.setBackgroundDrawable(getResources().getDrawable(R.drawable.playsoundbuttongradianoff));
            }
        }
        if(mp != null) {
            if(mp.isPlaying()) {
                mp.pause();
            }
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        closeAllMedia();
    }
}
