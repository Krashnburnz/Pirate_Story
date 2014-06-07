/**
 * Created by Daniel Henderson
 * University of Washington Spring 2014
 * Android Development TCSS 498
 * Story Book Application
 * Pirate Story
 */
package com.tinyrealm.app;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

//***************************************************************
// FragmentTitle:: Implements the title page fragment.
// last updated 2/08/14 (efa)
//***************************************************************
public class FragmentTitle extends Fragment{

    private Button btnAbout;
    private View view;
    private Context context;
    //hint button
    private Button btnAboutHint;
    private MediaPlayer mpHint;
    private MediaPlayer mpAmbiant;
    private boolean isAmbiantPaused;
    private boolean isHintPaused;
    private ImageButton imgBtnPirateSong;

    private boolean clicked;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.title, container, false);
        //Inflate the layout for this fragment
        context = view.getContext();
        btnAbout = (Button) view.findViewById(R.id.buttonAbout);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final StringBuilder sb = new StringBuilder();

                sb.append("Story borrowed from www.magickeys.com/books/pirate\n");
                sb.append("\n");
                sb.append("Written by Carol Moore\n");
                sb.append("Illustrated by Aura Moser\n");
                sb.append("Animation and Programming by Daniel Henderson\n");
                sb.append("\n");
                sb.append("Starter Code provided by Dr. Edwin Armstrong\n");
                sb.append("University of Washington, Spring 2014\n");
                String message = sb.toString();

                showAlertDialog(message);
            }
        });

        btnAboutHint = (Button) view.findViewById(R.id.buttonAboutHints);
        mpHint = MediaPlayer.create(view.getContext(), R.raw.hintbuttonexplain);
        clicked = false;
        isAmbiantPaused = false;
        isHintPaused = false;
        mpAmbiant = MediaPlayer.create(view.getContext(), R.raw.piratelifeshorter);
        mpAmbiant.setLooping(false);


        btnAboutHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    if(!mpHint.isPlaying()) {//Not Playing
                        mpHint.start();
                        if(mpAmbiant.isPlaying()) {
                            isAmbiantPaused = true;
                            mpAmbiant.pause();
                        }

                    }

            }
        });

        imgBtnPirateSong = (ImageButton) view.findViewById(R.id.imageButtonTitlePlay);
        imgBtnPirateSong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mpAmbiant.isPlaying()) { //Not playing
                    imgBtnPirateSong.setBackgroundDrawable(getResources().getDrawable(R.drawable.playsoundtitle));
                    mpAmbiant.start();
                    if(mpHint.isPlaying()) {
                        isHintPaused = true;
                        mpHint.pause();
                    }

                } else {                //is playing and button is clicked again

                    mpAmbiant.pause();
                    imgBtnPirateSong.setBackgroundDrawable(getResources().getDrawable(R.drawable.noplaysoundtitle));
                }
            }
        });
        mpHint.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                if (isAmbiantPaused) {
                    mpAmbiant.start();
                    isAmbiantPaused = false;
                }
            }
        });
        mpAmbiant.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer arg0) {
                if (isHintPaused) {
                    mpHint.start();
                    isHintPaused = false;
                }
                imgBtnPirateSong.setBackgroundDrawable(getResources().getDrawable(R.drawable.noplaysoundtitle));
            }
        });


        return view;
    }


    public void showAlertDialog(String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(view.getContext()).create();
        alertDialog.setTitle("About");
        alertDialog.setMessage(message);
        alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // here you could add functions
            }
        });

        //alertDialog.setIcon(R.drawable.icon);//could add icon if you'd like
        alertDialog.show();

    }




        //***************************************************************
        // Method that will stop all media players for this fragment.
        //***************************************************************
        public void closeAllMedia() {
            isAmbiantPaused = false;
            isHintPaused = false;
            if(mpAmbiant != null) {
                if(mpAmbiant.isPlaying()) {
                    mpAmbiant.pause();
                    imgBtnPirateSong.setBackgroundDrawable(getResources().getDrawable(R.drawable.noplaysoundtitle));
                }
            }
            if(mpHint != null) {
                if(mpHint.isPlaying()) {
                    mpHint.pause();
                }
            }
        }


    @Override
    public void onPause() {
        super.onPause();
        closeAllMedia();
    }

}
