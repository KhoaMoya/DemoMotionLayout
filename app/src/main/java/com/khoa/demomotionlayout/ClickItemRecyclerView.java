package com.khoa.demomotionlayout;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.constraint.motion.MotionLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ClickItemRecyclerView extends AppCompatActivity {

    private RecyclerView recyclerView;
    private boolean showDetail = false;
    private CardView cardView;
    private float X, Y;
    private View tempView;
    private ConstraintLayout detailLayout;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_item_recycler_view);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter());
        detailLayout = findViewById(R.id.fragment_detail);
        fab = findViewById(R.id.btn_fab);
        fab.hide();
    }

    public void showDetail(View view) {
        tempView = view;
        view.setVisibility(View.INVISIBLE);
        ImageView imageView = copyViewImage(view);
        cardView = new CardView(ClickItemRecyclerView.this);
        cardView.setCardElevation(getResources().getDimension(R.dimen.elavation_item));
        cardView.setRadius(getResources().getDimension(R.dimen.corner_item));
        cardView.addView(imageView);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) view.getLayoutParams();
        X = view.getX() - 28;
        Y = view.getY() - 28;
        cardView.setX(X);
        cardView.setY(Y);
        addContentView(cardView, params);
        fab.show();
        recyclerView.animate()
                .alpha(0)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        recyclerView.setVisibility(View.GONE);
                    }
                })
                .setDuration(300)
                .start();
        cardView.animate()
                .translationY(100)
                .setDuration(300)
                .setInterpolator(new FastOutSlowInInterpolator())
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        cardView.setVisibility(View.GONE);
                        detailLayout.setVisibility(View.VISIBLE);
                    }
                })
                .start();
    }

    public void closeDetailItem() {
        fab.hide();
        showDetail=false;
        recyclerView.setVisibility(View.VISIBLE);
        detailLayout.setVisibility(View.GONE);
        cardView.setVisibility(View.VISIBLE);
        cardView.animate()
                .translationY(Y)
                .translationX(X)
                .withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        if(cardView!=null){
                            ViewManager viewManager = (ViewManager) cardView.getParent();
                            viewManager.removeView(cardView);
                            tempView.setVisibility(View.VISIBLE);
                        }
                    }
                })
                .setDuration(300)
                .setInterpolator(new FastOutSlowInInterpolator())
                .start();
        recyclerView.animate()
                .alpha(1)
                .setDuration(300)
                .start();

    }


    public ImageView copyViewImage(View view) {
        ImageView copy = new ImageView(this);

        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        copy.setImageBitmap(bitmap);
        return copy;
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDetail = !showDetail;
                    showDetail(viewHolder.itemView);
                }
            });

        }

        @Override
        public int getItemCount() {
            return 30;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public ViewHolder(@NonNull View itemView) {
                super(itemView);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(showDetail) closeDetailItem();
        else  super.onBackPressed();
    }


}
