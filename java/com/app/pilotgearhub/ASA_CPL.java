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

public class ASA_CPL extends AppCompatActivity {

    private TextView description;
    private Button button, goBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_asa_cpl);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        button = findViewById(R.id.Hyperlink);
        button.setOnClickListener(v -> setHyperlinkButton());

        String paragraphA = "Okay, let's get this show on the road!Okay, let's get this show on the road!";
        String paragraphB = "ASA's Commercial Pilot Test Prep 2025-2026: Paperback Plus Software\n" +
                "\n" +
                "Tired of being broke and stuck on the ground? Well, fear not, aspiring pilot! ASA's got your ticket to the skies...literally. " +
                "Our test prep package is so packed with knowledge, it's like having a personal flight instructor in your pocket " +
                "(minus the pesky \"no talking during takeoff\" rule).";

        String paragraphC = "What to expect?";
        String[] bulletA = {"A massive book filled with tons of questions, answers, and explanations. Think of it as your own private study session, but without the awkward silence.",
        "A Prepware software download code. Basically, it's like having a flight simulator for your brain. Practice, practice, practice until you're a test-taking champion.",
        "A free 24-month subscription to Prepware Online. Because let's face it, who doesn't love studying online?"};

        String paragraphD = "Why choose ASA?";
        String[] bulletB = {"They are the OG of test prep. Millions of pilots and mechanics before you have trusted ASA to help them pass their exams.",
        "Their questions are spot on. They've got questions covering everything from airplanes to airships. Even if you're a balloon pilot, they've got you covered.",
        "ASA is always up-to-date. The FAA changes tests all the time, but ASA is always one step ahead. They will keep you in the loop with free online updates."};

        String paragraphE = "So, what are you waiting for? Pass this test, get your license, and start raking in that pilot cash. Your future self will thank you.\n" +
                "\n" +
                "Book Specifics:";

        String[] bulletC = {"Book Page Count: 424",
        "Book ISBN: 978-1-64425-428-8",
        "Book Dimensions: 8.25 x 10.75 Inches",
        "Book Copyright: © 1957-2024 Aviation Supplies & Academics, Inc. All rights reserved.",
        "Book Date Published: 2024",
        "Book Illustrations: Black and white",
        "Book Edition: 2025",
        "Book Effective Date: 2024",
        "Book Inclusions: Softcover Book, Test Supplement, Software Redemption Code"};

        description = findViewById(R.id.desc);

        // Create a SpannableStringBuilder for the entire text
        SpannableStringBuilder fullText = new SpannableStringBuilder();

        // Append each paragraph with formatting
        fullText.append(paragraphA).append("\n\n");
        fullText.append(paragraphB).append("\n\n");
        fullText.append(paragraphC).append("\n\n");

        // Append the bulleted lists
        for (String feature : bulletA) {
            SpannableString spannable = new SpannableString(feature);
            spannable.setSpan(new BulletSpan(20, Color.BLACK), 0, feature.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            fullText.append(spannable).append("\n\n");
        }

        fullText.append(paragraphD).append("\n\n");

        for (String feature : bulletB) {
            SpannableString spannable = new SpannableString(feature);
            spannable.setSpan(new BulletSpan(20, Color.BLACK), 0, feature.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            fullText.append(spannable).append("\n\n");
        }

        fullText.append(paragraphE).append("\n\n");

        for (String feature : bulletC) {
            SpannableString spannable = new SpannableString(feature);
            spannable.setSpan(new BulletSpan(20, Color.BLACK), 0, feature.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            fullText.append(spannable).append("\n\n");
        }

        // Set the text to the TextView
        description.setText(fullText);

        goBack = findViewById(R.id.backBtn);
        goBack.setOnClickListener(v -> goBack());
    }
    private void setHyperlinkButton() {
        String url = "https://www.pilotmall.com/products/asa-commercial-pilot-test-prep-2025-2026-paperback-plus-software-pass-your-faa-exam-2025-2026?_pos=3&_psq=ASA+Commercial&_ss=e&_v=1.0"; // Replace with your desired URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void goBack(){
        startActivity(new Intent(ASA_CPL.this, Home.class));
    }
}