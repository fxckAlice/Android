package com.example.practice14;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView imageView;
    private AnimationDrawable frameAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        Button startFrameAnimation =
                findViewById(R.id.startFrameAnimation);

        Button startRotateAnimation =
                findViewById(R.id.startRotateAnimation);

        Button startScaleAnimation =
                findViewById(R.id.startScaleAnimation);

        imageView.setBackgroundResource(
                R.drawable.frame_animation
        );

        frameAnimation =
                (AnimationDrawable) imageView.getBackground();

        startFrameAnimation.setOnClickListener(v -> {
            frameAnimation.start();
        });

        startRotateAnimation.setOnClickListener(v -> {

            Animation rotateAnimation =
                    AnimationUtils.loadAnimation(
                            this,
                            R.anim.rotate_animation
                    );

            imageView.startAnimation(rotateAnimation);
        });

        startScaleAnimation.setOnClickListener(v -> {

            Animation scaleAnimation =
                    AnimationUtils.loadAnimation(
                            this,
                            R.anim.scale_animation
                    );

            imageView.startAnimation(scaleAnimation);
        });
    }
}