package com.helloanatoly.imagevieweranimated.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.helloanatoly.animatedimagesviewer.ImageViewer;
import com.helloanatoly.animatedimagesviewer.OnLoadComplete;
import com.helloanatoly.imagevieweranimated.ImageSource;

/**
 * Created by shagspb on 15.06.17.
 * shagspb@gmail.com
 */

public class SecondActivity extends AppCompatActivity {

    public static int SelectedIndex = -1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);

        final ImageOverlayView overlayView = new ImageOverlayView(this, ImageSource.urls().size(), getIntent().getIntExtra(MainActivity.EXTRA_POSITION, 0));

        new ImageViewer.Builder(SecondActivity.this, ImageSource.urls())
                .setStartPosition(getIntent().getIntExtra(MainActivity.EXTRA_POSITION, 0))
                .setOverlayView(overlayView)
                .hideStatusBar(false)
                .exitAnimation(true)
                .setImageChangeListener(new ImageViewer.OnImageChangeListener() {
                    @Override
                    public void onImageChange(int position) {
                        SelectedIndex = position;
                        overlayView.setDescription(String.valueOf(position));
                    }
                })
                .show();
    }
}
