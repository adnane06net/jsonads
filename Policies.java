package chatromm.ma;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdError;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

import static chatromm.ma.Splash.ADS;
import static chatromm.ma.Splash.INTERVAL;

public class Policies extends AppCompatActivity {

    private LinearLayout ppolny;
    private CheckBox checkBox;
    private Button btn;

    private TextView ar,it,app_policies;

    private LinearLayout contact;

    private InterstitialAd mInterstitialAd;


    
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            Drawable background = this.getResources().getDrawable(R.drawable.shape_top_bar);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(this.getResources().getColor(android.R.color.transparent));
            window.setNavigationBarColor(this.getResources().getColor(android.R.color.transparent));
            window.setBackgroundDrawable(background);
        }
        setContentView(R.layout.policies);

        ////
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.ads_inter), adRequest,
                new InterstitialAdLoadCallback() {
                    
@Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        mInterstitialAd = interstitialAd;
                    }

                    
@Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        mInterstitialAd = null;
                    }
                });

        ////


        ppolny = findViewById(R.id.ppolny);
        checkBox = findViewById(R.id.checkBox);
        btn = findViewById(R.id.btn);

        ar = findViewById(R.id.ar);
        it = findViewById(R.id.it);

        contact = findViewById(R.id.contact);

        app_policies = findViewById(R.id.app_policies);

        ar.setBackgroundResource(R.drawable.shape_lan_used);
        it.setBackgroundResource(R.drawable.shape_lan);


        ar.setOnClickListener(new View.OnClickListener() {
            
@Override
            public void onClick(View v) {
                app_policies.setText(getString(R.string.policies_ar));
                ar.setBackgroundResource(R.drawable.shape_lan_used);
                it.setBackgroundResource(R.drawable.shape_lan);
            }});

        it.setOnClickListener(new View.OnClickListener() {
            
@Override
            public void onClick(View v) {
                app_policies.setText(getString(R.string.policies_it));
                ar.setBackgroundResource(R.drawable.shape_lan);
                it.setBackgroundResource(R.drawable.shape_lan_used);
            }});


        ppolny.setOnClickListener(new View.OnClickListener() {
            
@Override
            public void onClick(View v) {

                if(checkBox.isChecked()){
                    checkBox.setChecked(false);
                }else{
                    checkBox.setChecked(true);
                }
            }});


        btn.setOnClickListener(new View.OnClickListener() {
            
@Override
            public void onClick(View v) {
                if(checkBox.isChecked()){
                   startActivity(new Intent(Policies.this , EnterUser.class));
                   finish();
                    Show();
                }else{
                    Toast.makeText(getApplicationContext(), getString(R.string.agree_to_policies) , Toast.LENGTH_SHORT).show();
                }
            }
        });


        contact.setOnClickListener(new View.OnClickListener() {
            
@Override
            public void onClick(View v) {
                sedEmail();
            }
        });


    }


    private void sedEmail(){

     /*   Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"Marocchinitalia1
gmail.com"});
        email.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.app_name));
       // email.putExtra(Intent.EXTRA_TEXT, "message");

        email.setData(Uri.parse("mailto:"));

        //need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client :"));*/


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_EMAIL, "Marocchinitalia1gmail.com");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }else{
            Toast.makeText(getApplicationContext(), getString(R.string.no_mail_app) , Toast.LENGTH_SHORT).show();
        }
    }

    private void Show(){

        if (INTERVAL % ADS == 0 & mInterstitialAd != null) {
            mInterstitialAd.show(this);
        }
        INTERVAL++;
    }

}