package com.example.speechtotext;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech textToSpeech;
    EditText editText;
    ImageButton speakerBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        speakerBtn = findViewById(R.id.board);

        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR){
                    textToSpeech.setLanguage(Locale.FRENCH);
                }
            }
        });

        speakerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textSpeak = editText.getText().toString();
                textToSpeech.speak(textSpeak, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    @Override
    protected void onDestroy() {
        if(textToSpeech != null){
            textToSpeech.stop();
            textToSpeech.shutdown();
        }
        super.onDestroy();
    }
}
