package com.helloanatoly.imagevieweranimated.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.helloanatoly.imagevieweranimated.R;


/**
 * Created by shagspb on 16.06.17.
 * shagspb@gmail.com
 */

public class ImageOverlayView extends RelativeLayout {

    private TextView tvDescription;

    private String sharingText;

    private int size;
    private int startPosition;

    public ImageOverlayView(Context context, int size, int startPosition) {
        super(context);
        this.size = size;
        this.startPosition = startPosition;
        init();
    }

    public ImageOverlayView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ImageOverlayView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setDescription(String description) {
        tvDescription.setText(description + getContext().getString(R.string.of) + size);
    }

    public void setShareText(String text) {
        this.sharingText = text;
    }

    private void sendShareIntent() {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, sharingText);
        sendIntent.setType("text/plain");
        getContext().startActivity(sendIntent);
    }

    private void init() {
        View view = inflate(getContext(), R.layout.view_image_overlay, this);
        tvDescription = (TextView) view.findViewById(R.id.tvDescription);
        int pos = startPosition + 1;
        tvDescription.setText(pos + getContext().getString(R.string.of) + size);
    }
}
