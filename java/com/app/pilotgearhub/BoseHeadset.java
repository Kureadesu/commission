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
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class BoseHeadset extends AppCompatActivity {

    Button viewDemo, goBack, hyperlinkButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bose_headset);

        viewDemo = findViewById(R.id.viewDemoBtn);
        goBack = findViewById(R.id.button);
        goBack.setOnClickListener(v -> goBack());

        TextView textView5 = findViewById(R.id.textView5);

        String[] features = {
                "Lightweight design with low clamping force for unparalleled comfort",
                "Unmatched audio clarity with active equalization",
                "Industry-leading active noise reduction with three user-selectable modes",
                "Precision-focused noise-cancelling microphone",
                "Robust, durable design that is FAA TSO and EASA E/TSO-C139a certified",
                "Bluetooth® audio",
                "Digital active noise cancellation",
                "Long term durability",
                "Tap control for talk-through communication",
                "Automatic shutoff",
                "Exceptional battery life",
                "Bypass communications mode",
                "Five-year limited warranty with acclaimed service",
                "Carrying case"
        };

        // Create a SpannableStringBuilder for the entire text
        SpannableStringBuilder fullText = new SpannableStringBuilder();

        // Append the description
        String desc = "The Bose A30 Aviation Headset with Bluetooth is a top-of-the-line headset designed for pilots who want the best in comfort, audio clarity, and noise cancellation.  " +
                "This is the Dual Plug General Aviation Version of this Headset.  " +
                "The headset features a lightweight design with plush ear cushions for long-lasting comfort.\n\n" +
                "It also has an advanced noise cancellation system that reduces cockpit noise to a minimum, allowing you to focus on communication and critical flight information.  " +
                "The Bose A30 also has Bluetooth capability, so you can connect to your devices and stream music or take calls without compromising safety.\n\n" +
                "The headset is also FAA TSO and EASA E/TSO-C139a certified, so you can be sure it's durable and reliable.  " +
                "Some of the key features of the Bose A30 Aviation Headset with Bluetooth include:\n\n";

        fullText.append(desc);

        // Append the bulleted list
        for (String feature : features) {
            SpannableString spannable = new SpannableString(feature);
            spannable.setSpan(new BulletSpan(10, Color.BLACK), 0, feature.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            fullText.append(spannable).append("\n\n");
        }

        // Set the formatted text to the TextView
        textView5.setText(fullText);

        hyperlinkButton = findViewById(R.id.Hyperlink);
        hyperlinkButton.setOnClickListener(v -> setHyperlinkButton());
    }
    private void setHyperlinkButton() {
        String url = "https://www.pilotmall.com/products/bose-a30-aviation-headset-w-bt"; // Replace with your desired URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void goBack(){
        startActivity(new Intent(BoseHeadset.this, Home.class));
    }
}