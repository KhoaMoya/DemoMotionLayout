package com.khoa.demomotionlayout;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ViewTrasition extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Item> list;
    static String KEY_ITEM = "keyItem";
    static String KEY_IMAGE_TRASITION_NAME = "keyImageTransitionName";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_trasition);

        recyclerView = findViewById(R.id.recyclerview);
        initList();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new Adapter());
    }

    public void initList(){
        list = new ArrayList<>();
        list.add(new Item("Dog", getString(R.string.dog_blurb), "https://c1.staticflickr.com/1/188/417924629_6832e79c98_z.jpg?zz=1"));
        list.add(new Item("Penguin", getString(R.string.penguin_blurb), "https://c1.staticflickr.com/9/8616/16237154608_c5489cae31_z.jpg"));
        list.add(new Item("Eagle", getString(R.string.eagle_blurb), "https://c1.staticflickr.com/5/4010/4210875342_7cb06a9b62_z.jpg?zz=1"));
        list.add(new Item("Rabbit", getString(R.string.rabbit_blurb), "https://c2.staticflickr.com/4/3285/2819978026_175072995a_z.jpg?zz=1"));
        list.add(new Item("Dolphin", getString(R.string.dolphin_blurb), "https://c1.staticflickr.com/8/7619/16124006043_60bc4d8ca5_z.jpg"));
        list.add(new Item("Snek", getString(R.string.snek_blurb), "https://c1.staticflickr.com/9/8796/17158681740_a6caa5099f_z.jpg"));
        list.add(new Item("Seal", getString(R.string.seal_blurb), "https://c1.staticflickr.com/4/3852/14729534910_62b338dd72_z.jpg"));
        list.add(new Item("Rhino", getString(R.string.rhino_blurb), "https://c1.staticflickr.com/1/335/18040640224_f56f05f8dc_z.jpg"));
        list.add(new Item("Leopard", getString(R.string.leopard_blurb), "https://c1.staticflickr.com/9/8678/16645189230_b0e96e7af9_z.jpg"));
        list.add(new Item("Hippo", getString(R.string.hippo_blurb), "https://c2.staticflickr.com/4/3774/9377370000_6a57d1cfec_z.jpg"));
    }

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview, viewGroup, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {

            viewHolder.txtName.setText(list.get(i).getName());
            viewHolder.txtDetail.setText(list.get(i).getDetail());
            Glide.with(ViewTrasition.this)
                    .load(list.get(i).getImgUrl())
                    .asBitmap()
                    .centerCrop()
                    .into(viewHolder.imgAvatar);
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(ViewTrasition.this, ActivityDetail.class);
                    intent.putExtra(KEY_ITEM, list.get(i));
                    Pair<View, String> imgPair = Pair.create((View)viewHolder.imgAvatar, "avatar");
                    Pair<View, String> namePair = Pair.create((View)viewHolder.txtName, "name");
                    Pair<View, String> detailPair = Pair.create((View)viewHolder.txtDetail, "detail");
                    Pair<View, String> cardPair = Pair.create((View)viewHolder.cardView, "cardview");
                    ActivityOptionsCompat optionsCompat =
                            ActivityOptionsCompat.makeSceneTransitionAnimation(ViewTrasition.this, imgPair, namePair, detailPair, cardPair);
                    startActivity(intent, optionsCompat.toBundle());
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView imgAvatar;
            private TextView txtName;
            private TextView txtDetail;
            private CardView cardView;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                imgAvatar = itemView.findViewById(R.id.imageView);
                txtName = itemView.findViewById(R.id.txt_title);
                txtDetail = itemView.findViewById(R.id.txt_content);
                cardView = itemView.findViewById(R.id.cardView);
            }
        }
    }
}
