package com.helloanatoly.imagevieweranimated.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.helloanatoly.imagevieweranimated.ImageSource;
import com.helloanatoly.imagevieweranimated.ui.MainActivity;
import com.helloanatoly.imagevieweranimated.ui.SecondActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by shagspb on 16.06.17.
 * shagspb@gmail.com
 */

public class Adapter {
    private static final String TRANSITION_NAME = "transition";

    private ArrayList<LinearLayout> linearLayouts;
    private ArrayList<ImageView> imageViews;
    private Context context;


    public void initViews(LinearLayout ll) {
        ll.setOrientation(LinearLayout.VERTICAL);
        this.context = ll.getContext();

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getMetrics(displaymetrics);
        int width = (displaymetrics.widthPixels - 60) / 3;

        linearLayouts = new ArrayList<>();
        imageViews = new ArrayList<>();

        for (int count = 0; count < ImageSource.urls().size(); count++) {
            if (count % 3 == 0) {
                linearLayouts.add(new LinearLayout(context));
                linearLayouts.get(linearLayouts.size() - 1).setOrientation(LinearLayout.HORIZONTAL);
                ll.addView(linearLayouts.get(linearLayouts.size() - 1));
            }

            imageViews.add(new ImageView(context));
            linearLayouts.get(linearLayouts.size() - 1).addView(imageViews.get(imageViews.size() - 1));
            Picasso.with(context).load(ImageSource.urls().get(count)).resize(width, width).centerCrop().into(imageViews.get(imageViews.size() - 1));
            imageViews.get(imageViews.size() - 1).setPadding(10, 10, 10, 10);

            click(imageViews.get(imageViews.size() - 1), count);
        }
    }

    private void click(View view, final int position) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SecondActivity.class);
                intent.putExtra(MainActivity.EXTRA_POSITION, position);

                Activity activity = (Activity)context;
                ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, view, TRANSITION_NAME);
                ActivityCompat.startActivity(activity, intent, options.toBundle());
            }
        });
    }

    public View getView (int position) {
        if (imageViews.size() > position)
            return imageViews.get(position);
        else
            return null;
    }
}
