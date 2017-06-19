package com.helloanatoly.imagevieweranimated.ui;

import android.support.v4.app.ActivityCompat;
import android.support.v4.app.SharedElementCallback;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.helloanatoly.imagevieweranimated.adapters.Adapter;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    private Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityCompat.setExitSharedElementCallback(this, ExitTransitionCallback);

        super.onCreate(savedInstanceState);

        ScrollView sw = new ScrollView(this);
        LinearLayout ll = new LinearLayout(this);
        sw.addView(ll);
        setContentView(sw);

        adapter = new Adapter();
        adapter.initViews(ll);
    }

    private final SharedElementCallback ExitTransitionCallback = new SharedElementCallback() {
        @Override
        public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
            if (SecondActivity.SelectedIndex >= 0 && adapter.getView(SecondActivity.SelectedIndex) != null && names.size() > 0) {
                sharedElements.put(names.get(0), adapter.getView(SecondActivity.SelectedIndex));
                SecondActivity.SelectedIndex = -1;
            }
        }
    };
}
