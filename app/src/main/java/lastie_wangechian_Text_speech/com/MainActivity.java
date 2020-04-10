package lastie_wangechian_Text_speech.com;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private EditText editText;
    private Button button;
    private TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

        tts = new TextToSpeech(this, this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                voiceOutput();

            }
        });
    }

    private void voiceOutput() {

        CharSequence text = editText.getText();
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, "id1");

    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            //set language that is locale at device
            int result = tts.setLanguage(Locale.ENGLISH);
            float i = 50;

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {

                Toast.makeText(MainActivity.this, "language not supported", Toast.LENGTH_LONG).show();

            } else {

                button.setEnabled(true);
                voiceOutput();

            }
        } else {

            Toast.makeText(MainActivity.this, "Intiliazation failed...", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onDestroy() {

        //closing text_to_speech
        if (tts != null) {

            tts.stop();
            tts.shutdown();
        }

        super.onDestroy();
    }
}
