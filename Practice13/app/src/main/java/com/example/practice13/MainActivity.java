package com.example.practice13;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MediaController;
import android.widget.VideoView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private VideoView videoView;
    private MediaPlayer mediaPlayer;
    private TextToSpeech textToSpeech;
    private EditText speechText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = findViewById(R.id.videoView);
        speechText = findViewById(R.id.speechText);

        Button playVideoButton = findViewById(R.id.playVideoButton);
        Button playAudioButton = findViewById(R.id.playAudioButton);
        Button speakButton = findViewById(R.id.speakButton);

        Uri videoUri = Uri.parse(
                "android.resource://" + getPackageName() + "/" + R.raw.android_video
        );

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(videoUri);

        playVideoButton.setOnClickListener(v -> videoView.start());

        mediaPlayer = MediaPlayer.create(this, R.raw.android_audio);

        playAudioButton.setOnClickListener(v -> {
            if (mediaPlayer != null) {
                mediaPlayer.start();
            }
        });

        textToSpeech = new TextToSpeech(this, status -> {
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech.setLanguage(Locale.US);
            }
        });

        speakButton.setOnClickListener(v -> {
            String text = speechText.getText().toString();

            if (text.isEmpty()) {
                Toast.makeText(this, "Введіть текст", Toast.LENGTH_SHORT).show();
            } else {
                textToSpeech.speak(
                        text,
                        TextToSpeech.QUEUE_FLUSH,
                        null,
                        null
                );
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }

        if (textToSpeech != null) {
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
    }
}