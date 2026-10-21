package com.example.practicalfive;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    /** Intent extra key shared by MainActivity and Municipality. */
    public static final String EXTRA_MUNICIPALITY = "com.example.practicalfive.MUNICIPALITY";

    private EditText editTextMunicipality;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextMunicipality = findViewById(R.id.editTextMunicipality);

        // Trigger validation when the user presses the "Go" / "Done" key.
        editTextMunicipality.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean isActionKey = actionId == EditorInfo.IME_ACTION_GO
                        || actionId == EditorInfo.IME_ACTION_DONE;
                boolean isEnterPressed = event != null
                        && event.getKeyCode() == KeyEvent.KEYCODE_ENTER
                        && event.getAction() == KeyEvent.ACTION_DOWN;

                if (isActionKey || isEnterPressed) {
                    handleSubmit();
                    return true;
                }
                return false;
            }
        });
    }

    /** Reads, validates and forwards the user's input. */
    private void handleSubmit() {
        String input = editTextMunicipality.getText().toString();
        String municipality = TextProcessor.processMunicipality(input);

        if (municipality == null) {
            // Stay on the main screen and show a helpful error.
            if (input.trim().isEmpty()) {
                editTextMunicipality.setError(getString(R.string.error_empty));
            } else {
                editTextMunicipality.setError(getString(R.string.error_invalid));
            }
            return;
        }

        editTextMunicipality.setError(null);

        // Navigate to the second Activity using an explicit Intent.
        Intent intent = new Intent(MainActivity.this, Municipality.class);
        intent.putExtra(EXTRA_MUNICIPALITY, municipality);
        startActivity(intent);
    }
}
