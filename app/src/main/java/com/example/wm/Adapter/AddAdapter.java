package com.example.wm.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wm.Class.AddPhonenum;
import com.example.wm.R;

import java.util.ArrayList;
import java.util.List;

public class AddAdapter extends RecyclerView.Adapter<AddAdapter.ViewHolder> {


    public final List<AddPhonenum> AddAdapters;
    private  String finalTotal;
    Context context;
    public AddAdapter(Context mcontext, List<AddPhonenum> AddAdapters) {
        this.AddAdapters = AddAdapters;
        this.context=mcontext;

    }

    @Override
    public AddAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cardview_addadapter, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddAdapter.ViewHolder holder, final int position) {
        final AddPhonenum addPhonenum = AddAdapters.get(position);

        holder.num.setText(addPhonenum.getS_phonenum());
        holder.name.setText(addPhonenum.getS_name());
       // holder.date.setText(addPhonenum.getCurrentTime());




        holder.im_Remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onItemDeleted.onItemDelete(AddAdapters.get(position).getS_phonenum());
                AddAdapters.remove(position);
                notifyDataSetChanged();
                //meds_totalItemCount.get_TotalItemCount(String.valueOf(AddAdapters.size()));
              /*  if(AddAdapters.size()==0)
                {
                    //tv_TotalRateSum.setText("0.00");
                }*/

            }
        });
        holder.im_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });

       // total count.get_TotalItemCount(String.valueOf(addAdapters.size()));
    }

    @Override
    public int getItemCount() {
        if(AddAdapters.size()>0) {
            return AddAdapters.size();
        }
        else
        {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final TextView num;
        final TextView name;
       // final TextView date;
        final ImageView im_Remove;
        ImageView im_Edit;

        ViewHolder(View itemView) {
            super(itemView);


            im_Edit = itemView.findViewById(R.id.id_Edit);
            im_Remove = itemView.findViewById(R.id.id_Delete);
            num = itemView.findViewById(R.id.num);
            name = itemView.findViewById(R.id.name);
            //date = itemView.findViewById(R.id.date_time);

        }
    }

    public interface OnItemDeleted
    {
        void onItemDelete(String DrugBrandID);

    }

}
