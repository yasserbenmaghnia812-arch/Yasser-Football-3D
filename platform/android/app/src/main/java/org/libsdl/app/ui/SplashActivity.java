package org.libsdl.app.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        BlurzTheme.applyImmersive(this);

        FrameLayout root = new FrameLayout(this);
        GradientDrawable background = new GradientDrawable(
            GradientDrawable.Orientation.TL_BR,
            new int[] {
                Color.parseColor("#240006"),
                Color.parseColor("#B00018"),
                Color.parseColor("#51000B")
            }
        );
        root.setBackground(background);

        TextView name = new TextView(this);
        name.setText("Yasser Benmaghnia");
        name.setTextColor(Color.WHITE);
        name.setTextSize(32);
        name.setTypeface(Typeface.create("sans-serif", Typeface.BOLD));
        name.setLetterSpacing(0.045f);
        name.setGravity(Gravity.CENTER);
        name.setShadowLayer(24f, 0f, 0f, Color.parseColor("#CCFFFFFF"));
        name.setPadding(24, 24, 24, 24);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            Gravity.CENTER
        );
        root.addView(name, params);
        setContentView(root);

        AnimationSet cinematic = new AnimationSet(true);
        cinematic.setInterpolator(new AccelerateDecelerateInterpolator());
        AlphaAnimation fade = new AlphaAnimation(0f, 1f);
        fade.setDuration(1700);
        ScaleAnimation scale = new ScaleAnimation(
            0.82f, 1.0f, 0.82f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        );
        scale.setDuration(1700);
        cinematic.addAnimation(fade);
        cinematic.addAnimation(scale);
        name.startAnimation(cinematic);

        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            startActivity(new Intent(SplashActivity.this, MainMenuActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 3000);
    }
}
