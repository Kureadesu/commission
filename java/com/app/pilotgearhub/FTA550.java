package com.app.pilotgearhub;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.BulletSpan;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class FTA550 extends AppCompatActivity {

    private TextView description;
    private Button hyperlinkButton, goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_fta550);

        String desc = "Yaesu's FTA-550 brings users an impressive array of features in a compact, handheld transceiver. Immediately notable is its large 1.7\" x 1.7\" dot matrix LCD display. This high-resolution screen features an intuitive, icon-based menu system and is fully backlit and dimmable for easy viewing in virtually any lighting condition. It also features the full aviation COM/NAV frequency band complete with VOR and ILS localizer navigational capabilities. Additional features include full NOAA weather channel reception & weather alerts, as well as the capability to store 200 memory frequencies with 15-character alphanumeric labels. This version is powered by six AA alkaline batteries that deliver an impressive 800 mW of clear audio. In addition, the FTA-550 holds an IPX5 water protection rating. An extremely capable handheld unit for all your aviation communication needs.\n" +
                "\n" +
                "Yaesu FTA-550AA includes:\n";

        String[] bulletA = {"Cigarette Lighter Adapter",
        "BNC Helical Antenna",
        "Headset Adapter Cable",
        "Alkaline Battery Tray",
        "Belt Clip",
        "USB Cable",
        "Ferrite Core",
        "Operating Manual",
        "Warranty Card"};

        description = findViewById(R.id.desc);
        // Create a SpannableStringBuilder for the entire text
        SpannableStringBuilder fullText = new SpannableStringBuilder();
        fullText.append(desc);

        // Append the bulleted list
        for (String feature : bulletA) {
            SpannableString spannable = new SpannableString(feature);
            spannable.setSpan(new BulletSpan(10, Color.BLACK), 0, feature.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            fullText.append(spannable).append("\n\n");
        }

        description.setText(fullText);

        hyperlinkButton = findViewById(R.id.Hyperlink);
        hyperlinkButton.setOnClickListener(v -> setHyperlinkButton());

        goBack = findViewById(R.id.button);
    }
    private void setHyperlinkButton(){
        String url = "https://www.pilotmall.com/products/yaesu-fta-550-aa-handheld-vhf-transceiver?_pos=1&_psq=FTA&_ss=e&_v=1.0"; // Replace with your desired URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void goBack(){
        startActivity(new Intent(FTA550.this, Home.class));
    }
}