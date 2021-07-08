package com.gurkan.socialapp.adapter;

import android.app.Activity;
import android.content.Context;
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

import com.gurkan.socialapp.R;
import com.gurkan.socialapp.model.Post;

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
            //TODO : resimleri contentte göster
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

        return rootView;
    }
}
