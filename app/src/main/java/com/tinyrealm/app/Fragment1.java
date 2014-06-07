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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

//***************************************************************
// Fragment1:: Implements the page#1 fragment. This fragment is used for the
// dream part of the story. The parrot can be animated and play a sound when
// clicked on. The story can be narrated by clicking the sound button.
//***************************************************************
public class Fragment1 extends Fragment{
    private ImageButton imgButtonParrotAnimation;
    private View view;
    private boolean clicked;
    private ImageView imgViewPage1;
    private MediaPlayer mp;
    private ImageButton imgBtnPlaySound;
    private MediaPlayer mpNarrate;
    private Button hint;

    //***************************************************************
    // Initialization of all GUI elements.
    //***************************************************************
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

         //Inflate the layout for this fragment
        view = inflater.inflate(R.layout.pg1, container, false);

        //Invisible button laying over the Parrot
        imgButtonParrotAnimation = (ImageButton) view.findViewById(R.id.imageButton1Pg1);

        //Boolean value whether the animation button has been clicked. Used to control animation state
        clicked = false;

        //Primary Image View holding the Picture for this page
        imgViewPage1 = (ImageView) view.findViewById(R.id.imageView1);

        //Button used to display a Toast message for the HINT
        hint = (Button) view.findViewById(R.id.buttonClue1);

        //Media player used to play the parrot sounds
        mp = MediaPlayer.create(view.getContext(), R.raw.mexicanredparrot);

        //Image Button used to play the Narration
        imgBtnPlaySound = (ImageButton) view.findViewById(R.id.imageButtonPlaySound1);

        //Media Player used to play the narration
        mpNarrate = MediaPlayer.create(view.getContext(), R.raw.narrate1);

        //***************************************************************
        // On Complete listener used to change the image of the Play Sound button once
        // the narration has finished.
        //***************************************************************
        mpNarrate.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                imgBtnPlaySound.setBackgroundDrawable(getResources().getDrawable(R.drawable.playsoundbuttongradianoff));
            }
        });

        //***************************************************************
        // OnClick listener for the parrot Animation
        //***************************************************************
        imgButtonParrotAnimation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicked) {

                    imgViewPage1.setBackgroundDrawable(getResources().getDrawable(R.drawable.page1art));
                    clicked = false;

                } else {
                    imgViewPage1.setBackgroundDrawable(getResources().getDrawable(R.drawable.page1artanimate));
                    if (!mp.isPlaying()) {

                        mp.start();
                    }

                    clicked = true;
                }
            }
        });

        //***************************************************************
        // OnClick listener for the parrot Animation
        //***************************************************************
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

        //***************************************************************
        // OnClick listener for the HINT MESSAGE
        //***************************************************************
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                CharSequence text = "Hint: Touch the parrot to make him Squawk!";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });

        return view;
    }


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

    //***************************************************************
    // This method is fired if the Activity is paused (like the user gets
    // a phone call, or hits the back button. This will shut all media
    // players off.
    //***************************************************************
    @Override
    public void onPause() {
        super.onPause();
        closeAllMedia();
    }
}
