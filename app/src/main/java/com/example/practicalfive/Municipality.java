package com.example.practicalfive;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Municipality extends AppCompatActivity {

    private TextView textViewName;
    private TextView textViewInfo;
    private ImageView imageViewMunicipality;

    /** The validated, correctly capitalised municipality name. */
    private String municipalityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_municipality);

        textViewName = findViewById(R.id.textViewName);
        textViewInfo = findViewById(R.id.textViewInfo);
        imageViewMunicipality = findViewById(R.id.imageViewMunicipality);

        // Retrieve and re-validate the name passed via the Intent extra.
        String received = getIntent().getStringExtra(MainActivity.EXTRA_MUNICIPALITY);
        municipalityName = TextProcessor.processMunicipality(received);

        if (municipalityName == null) {
            // Missing or invalid data: close the screen safely.
            Toast.makeText(this, R.string.error_invalid, Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        updateInterface();
    }

    /** Sets the name, summary, image and colours for the selected municipality. */
    private void updateInterface() {
        int colorRes;
        int imageRes;
        int summaryIndex;

        switch (municipalityName) {
            case "Beijing":
                colorRes = R.color.municipality_beijing;
                imageRes = R.drawable.beijing;
                summaryIndex = 0;
                break;
            case "Shanghai":
                colorRes = R.color.municipality_shanghai;
                imageRes = R.drawable.shanghai;
                summaryIndex = 1;
                break;
            case "Chongqing":
                colorRes = R.color.municipality_chongqing;
                imageRes = R.drawable.chongqing;
                summaryIndex = 2;
                break;
            case "Tianjin":
                colorRes = R.color.municipality_tianjin;
                imageRes = R.drawable.tianjin;
                summaryIndex = 3;
                break;
            default:
                return;
        }

        int municipalityColor = ContextCompat.getColor(this, colorRes);
        int lightTextColor = ContextCompat.getColor(this, R.color.color_text_light);

        // Name banner: municipality colour background with light, readable text.
        textViewName.setText(municipalityName);
        textViewName.setBackgroundColor(municipalityColor);
        textViewName.setTextColor(lightTextColor);

        // Information text: municipality colour on the neutral background.
        String[] summaries = getResources().getStringArray(R.array.municipalities_array);
        textViewInfo.setText(summaries[summaryIndex]);
        textViewInfo.setTextColor(municipalityColor);

        imageViewMunicipality.setImageResource(imageRes);
        imageViewMunicipality.setContentDescription(municipalityName);
    }
}
