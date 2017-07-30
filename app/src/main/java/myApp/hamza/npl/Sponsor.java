package myApp.hamza.npl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Hamza Ahmed on 27-Jul-17.
 */

public class Sponsor extends AppCompatActivity {
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sponsors);
        backButton=(ImageView) findViewById(R.id.backButtonSponsors);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Sponsor.this,OptionsActivity.class);
                startActivity(i);
            }
        });
    }
}
