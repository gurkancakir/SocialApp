package com.gurkan.socialapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.gurkan.socialapp.R;
import com.gurkan.socialapp.model.Post;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TimelineListViewAdapter extends BaseAdapter {

    public static final Integer POST = 0;
    public static final Integer MAP  = 1;

    private LayoutInflater layoutInflater;
    private Activity activity;
    private List<Post> postList;


    public TimelineListViewAdapter(Activity activity, List<Post> postList) {
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.activity = activity;
        this.postList = postList;
    }

    @Override
    public int getCount() {
        return postList.size();
    }

    @Override
    public Post getItem(int position) {
        return postList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return postList.get(position).getViewType();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rootView = convertView;
        int type = this.getItemViewType(position);

        if (rootView == null) {
            rootView = layoutInflater.inflate(R.layout.cardview_item, null);
        }

        TextView txtTitle      = rootView.findViewById(R.id.cardViewTitle);
        TextView txtContent    = rootView.findViewById(R.id.cardViewContent);
        TextView txtDate       = rootView.findViewById(R.id.cardViewDate);
        TextView txtLocation   = rootView.findViewById(R.id.cardViewLocation);
        ImageButton btnOptions = rootView.findViewById(R.id.cardViewOptions);
        LinearLayout maps      = rootView.findViewById(R.id.mapsView);
        ImageView imgContent   = rootView.findViewById(R.id.imageContent);

        Post post = this.getItem(position);
        txtTitle.setText(post.getTitle());
        txtContent.setText(post.getContent());
        txtDate.setText(post.getDate());
        txtLocation.setText(post.getLocation());

        if (post.getListContentImage().isEmpty()) {
            imgContent.setVisibility(View.GONE);
        } else {
            System.out.println(post.getListContentImage().get(0));
            Picasso.with(activity)
                    .load(post.getListContentImage().get(0))
                    .into(imgContent);
        }

        PopupMenu popupMenu = new PopupMenu(activity, btnOptions);

        String[] menuItems = {"hide", "unfollow", "spam", "other options"};
        for (int i = 0; i < menuItems.length; i++) {
            popupMenu.getMenu().add(1, i, i, menuItems[i]);
        }

        btnOptions.setOnClickListener(v -> {
            popupMenu.show();
        });

        popupMenu.setOnMenuItemClickListener(item -> {
            Toast.makeText(activity, "Title: " + item.getTitle()
                    + " Order : " + item.getOrder()
                    + " itemId : " + item.getItemId()
                    + " info : " + item.getMenuInfo(), Toast.LENGTH_SHORT).show();

            //TODO: menu islemlerini yap
            return false;
        });

        if (imgContent.getVisibility() != View.GONE) {
            imgContent.setOnClickListener(v -> {
                View dialogView = activity.getLayoutInflater().inflate(R.layout.timeline_popup_image_slider, null);
                SliderLayout slider = dialogView.findViewById(R.id.slider);
                slider.setPresetTransformer(SliderLayout.Transformer.Stack);
                slider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
                slider.setCustomAnimation(new DescriptionAnimation());
                slider.setDuration(100_000); // 100sn
                for (String imageUrl: post.getListContentImage()) {
                    TextSliderView textSliderView = new TextSliderView(activity);
                    textSliderView
                            .description("Test A????klama")
                            .image(imageUrl)
                            .setScaleType(BaseSliderView.ScaleType.Fit)
                    .bundle(new Bundle());
                    textSliderView.getBundle().putString("extra", "Test resim ad??");
                    slider.addSlider(textSliderView);
                }
                new AlertDialog.Builder(activity).setView(dialogView).create().show();
            });
        }

        return rootView;
    }
}
