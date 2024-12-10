package com.app.pilotgearhub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class LogitechYokeAndQuadrant extends AppCompatActivity {

    private Button hyperlinkButton, goBack;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logitech_yoke_and_quadrant);

        hyperlinkButton = findViewById(R.id.Hyperlink);
        hyperlinkButton.setOnClickListener(v -> setHyperlinkButton());


        goBack = findViewById(R.id.backBtn);
        goBack.setOnClickListener(v -> goBack());

        String paragraph_A = "For a truly lifelike aviation simulation experience, you owe it to yourself to check out Saitek Industries' " +
                "incredibly well-designed Flight Yoke & Throttle Quadrant System. " +
                "This top-of-the-line device features a stainless steel shaft with ultra-precise bearings for exceptionally smooth " +
                "pitch and roll control inputs. It also boasts a dedicated control quadrant with realistic throttle, " +
                "prop, and mixture controls; as well as a whopping 14 buttons including a 3-position mode switch and a POV hat switch. " +
                "Additionally, the unit's integrated digital chronograph is perfect for brushing up on a variety of IFR and " +
                "cross-country procedures including holds, instrument approaches, time between checkpoints, and loads of additional aviation-specific features.";

        String paragraph_B = "Connects via a USB 2.0 port and is compatible with Windows® 10, Windows 8.1, Windows 7 operating systems. Features Saitek's Smart Technology (SST) " +
                "software for complete customizability of control configuration. Also ideal for use with Saitek's Pro Flight Rudder Pedals [sold separately]. " +
                "An exceptionally capable, easily configurable computer accessory for realistic, action-packed aviation adventures from the comfort of your computer.";

        description = findViewById(R.id.desc);
        String sb = paragraph_A +
                "\n\n" +
                paragraph_B;

        description.setText(sb);
    }
    private void setHyperlinkButton() {
        String url = "https://www.pilotmall.com/products/saitek-pro-flight-yoke"; // Replace with your desired URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void demo(){
        //startActivity(new Intent(LogitechYokeAndQuadrant.this, VideoDemo_LogitechYokeAndQuadrant.class));
        finish();
    }
    public void goBack(){
        startActivity(new Intent(LogitechYokeAndQuadrant.this, Home.class));
        finish();
    }
}