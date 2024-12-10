package com.app.pilotgearhub;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class GatsJar extends AppCompatActivity {

    Button back, viewDemo, hyperlinkButton;
    TextView Desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gats_jar);

        back = findViewById(R.id.button);
        viewDemo = findViewById(R.id.viewDemoBtn);
        back.setOnClickListener(v -> goBack());
        viewDemo.setOnClickListener(v -> demo());
        Desc = findViewById(R.id.desc);


        String desc = "Studies indicate that over 3 million gallons of avgas get poured on the ground every year during preflight inspections - in the US alone! " +
                "In addition to being costly, leaded fuel pollutes the environment and can even get into ground water supplies. " +
                "To protect the environment and save pilots money, Aviation Specialties developed the 12 oz. " +
                "GATS Jar that eliminates needlessly wasting aviation fuel.\n" +
                "\n" +
                "This transparent fuel tester features an integral filter that separates water and particulate matter from the fuel supply, " +
                "allowing sampled fuel to be poured back into your aircraft's fuel tanks. Once the clean fuel is back in the tanks, " +
                "users can discard the water and particulate matter without fear of harming the environment. " +
                "Also includes a sturdy pin for use on pin-type fuel drains. A proven, affordable fuel tester for eco-friendly aviation operations.";

        Desc.setText(desc);

        hyperlinkButton = findViewById(R.id.Hyperlink);
        hyperlinkButton.setOnClickListener(v -> setHyperlinkButton());
    }
    private void setHyperlinkButton() {
        String url = "https://www.pilotmall.com/products/gats-jar-fuel-tester?_pos=1&_psq=gat&_ss=e&_v=1.0"; // Replace with your desired URL
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void demo(){
        startActivity(new Intent(GatsJar.this, VideoDemo_gatsJar.class));
    }
    public void goBack(){
        startActivity(new Intent(GatsJar.this, Home.class));
    }
}