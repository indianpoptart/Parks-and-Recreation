package com.nikhilparanjape.parksandrec;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class PressedStateOnTouchListener implements OnTouchListener
{
    PressedStateOnTouchListener( float alphaNormal )
    {
        mAlphaNormal = alphaNormal;
    }

    public boolean onTouch(View theView, MotionEvent motionEvent)
    {
        switch( motionEvent.getAction() ) {
            case MotionEvent.ACTION_DOWN:
                theView.setAlpha( mAlphaNormal / 5.0f );
                break;

            case MotionEvent.ACTION_UP:
                theView.setAlpha( mAlphaNormal );
                break;
        }

        // return false because I still want this to bubble off into an onClick
        return false;
    }

    private float   mAlphaNormal;
}