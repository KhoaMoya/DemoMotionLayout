package com.khoa.demomotionlayout;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class ActivityDetail extends AppCompatActivity {
    private ImageView imageView;
    private TextView txtName;
    private TextView txtDetail;
    private CardView cardView;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imageView = findViewById(R.id.imageView);
        txtName = findViewById(R.id.txt_title);
        txtDetail = findViewById(R.id.txt_content);

        Bundle extras = getIntent().getExtras();
        Item item = extras.getParcelable(ViewTrasition.KEY_ITEM);
        txtName.setText(item.getName());
        txtDetail.setText(item.getDetail());
//        imageView.setTransitionName("avatar");
//        txtName.setTransitionName("name");
//        txtDetail.setTransitionName("detail");
//        cardView.setTransitionName("cardview");
//        layoutContain.setTransitionName("cardview");
        Glide.with(this)
                .load(item.getImgUrl())
                .asBitmap()
                .skipMemoryCache(true)
                .centerCrop()
                .into(imageView);

//        getWindow().getSharedElementEnterTransition().setDuration(2000);
        getWindow().getSharedElementReturnTransition().setDuration(350).setInterpolator(new FastOutSlowInInterpolator());
    }
}
