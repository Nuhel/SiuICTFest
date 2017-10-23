package com.siu.siuictfest.adapter;

/**
 * Created by Nuhel on 10/24/2017.
 */

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.siu.siuictfest.GetDataBaseReference;
import com.siu.siuictfest.Model.DataModel;
import com.siu.siuictfest.R;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by Nuhel on 8/18/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private int visibleThreshold = 5;
    private int lastVisibleItem, totalItemCount;
    private boolean loading = true;
    private Context context;
    private RecyclerView recyclerView;
    private LinkedHashMap<String, DataModel> team_list;
    private DatabaseReference db;
    private Boolean is_first = true;
    private LinearLayoutManager linearLayoutManager;
    private String oldestPostId;

    public RecyclerViewAdapter(Context context, RecyclerView recyclerView,String ref) {
        this.context = context;
        this.recyclerView = recyclerView;
        this.db = GetDataBaseReference.GetDataBaseInstance().getReference(ref);
        this.team_list = new LinkedHashMap<>();
        loadMoreData();
        this.recyclerView.setAdapter(this);
        init();
    }

    private void init() {
        linearLayoutManager = (LinearLayoutManager) recyclerView
                .getLayoutManager();
        recyclerView
                .addOnScrollListener(new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrolled(RecyclerView recyclerView,
                                           int dx, int dy) {
                        super.onScrolled(recyclerView, dx, dy);
                        totalItemCount = linearLayoutManager.getItemCount();
                        lastVisibleItem = linearLayoutManager
                                .findLastVisibleItemPosition() < lastVisibleItem ? lastVisibleItem : linearLayoutManager
                                .findLastVisibleItemPosition();
                        if (loading && totalItemCount <= (lastVisibleItem + visibleThreshold)) {
                            loading = false;
                            loadMoreData();
                        }
                    }
                });
    }

    private void loadMoreData() {

        if (team_list.size()!=0){
            oldestPostId = (String) team_list.keySet().toArray()[team_list.size() - 1];
        }
        ChildEventListener vl = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                DataModel model = getModel(dataSnapshot);
                team_list.put(dataSnapshot.getKey(), model);
                notifyDataSetChanged();
                loading = true;
            }

            @Override
            public void onChildChanged(DataSnapshot ds, String s) {
                String key = ds.getKey();
                DataModel model = getModel(ds);
                if (model != null) {
                    team_list.put(key, model);
                    notifyItemChanged(new ArrayList<String>(team_list.keySet()).indexOf(key));
                }
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String key = dataSnapshot.getKey();
                Toast.makeText(context, key, Toast.LENGTH_SHORT).show();
                int position = new ArrayList<String>(team_list.keySet()).indexOf(key);
                team_list.remove(key);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, team_list.size());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        if (is_first){
            db.limitToFirst(3).addChildEventListener(vl);
            is_first=false;

        }else {
            db.orderByKey().startAt(oldestPostId).limitToFirst(5).addChildEventListener(vl);
        }

    }


    private DataModel getModel(DataSnapshot ds) {

        DataModel model = null;
        try {

            String teamName = ds.child("").getValue().toString();
            model = new DataModel(teamName);

           // String m1name = ds.getChildren().get;
            String m2name;
            String m3name;

            String m1roll;
            String m2roll;

            String m3roll;

            String m1dept;
            String m2dept;
            String m3dept;


            /*model.setPost_id(ds.getKey());
            model.setArea(areaText);
            model.setImage1(img1);
            model.setImage2(img2);
            model.setImage3(img3);
            model.setRoom(roomsText);
            model.setType(typeText);*/


        } catch (Exception e) {

        }

        return model;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.teamlistmodel, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.bindData((DataModel) (team_list.values().toArray()[position]));


    }

    @Override
    public int getItemCount() {
        return team_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView area, room, type;

        public ViewHolder(View itemView) {
            super(itemView);
        }


        public void bindData(DataModel data) {
            if (data != null) {


            }
        }
    }
}