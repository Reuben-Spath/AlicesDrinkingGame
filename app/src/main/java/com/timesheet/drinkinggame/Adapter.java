package com.timesheet.drinkinggame;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {

    private List<example> exampleList;
    private String TAG = "hello";

    public Adapter(List<example> exampleList) {
        this.exampleList = exampleList;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_example, viewGroup, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder viewholder, final int position) {
        int resource = exampleList.get(position).getImageResource();
        String one = exampleList.get(position).getText1();
//        String two = exampleList.get(position).getText2();
        viewholder.setData(resource, one);

        viewholder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "MyClass.getView() — get item number " + position);

                exampleList.remove(position);
                notifyDataSetChanged();


            }
        });

    }

    @Override
    public int getItemCount() {
        return exampleList.size();
    }


    class Viewholder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textone;
        private TextView texttwo;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textone = itemView.findViewById(R.id.TrialText);
//            texttwo = itemView.findViewById(R.id.textView2);
        }

        private void setData(int imageResource, String textViewa) {
            imageView.setImageResource(imageResource);
            textone.setText(textViewa);
//            texttwo.setText(textViewb);

        }

    }

}
