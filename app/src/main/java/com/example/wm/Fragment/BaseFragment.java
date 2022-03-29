package com.example.wm.Fragment;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.example.wm.R;


public class BaseFragment extends Fragment {
    private String TAG=BaseFragment.class.getSimpleName();
    private  ImageView success;
    private  ImageView faliure;


    public interface DoneClickListener
    {
        void onDoneClick();
    }
    public  void showSucessDialog(String message,DoneClickListener doneClickListener)
    {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.success_layout, null);
        dialogBuilder.setView(dialogView);
        success = (ImageView) dialogView.findViewById(R.id.id_tick);
        TextView orderid = (TextView) dialogView.findViewById(R.id.ordr_number);
        TextView done = (TextView) dialogView.findViewById(R.id.done);
        orderid.setText(message);
        AlertDialog alertDialognew = dialogBuilder.create();
        alertDialognew.setCancelable(false);
        alertDialognew.show();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doneClickListener.onDoneClick();
                alertDialognew.cancel();
            }
        });
        if(message.equals("Saved UnSuccessful")) {
            Glide.with(getActivity())
                    .load(R.raw.cross)
                    .into(success);

        }
        else
        {
            Glide.with(getActivity())
                    .load(R.raw.tick_new)
                    .into(success);
        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                ((GifDrawable)success.getDrawable()).stop();
//            }
//        }, 1800);
    }

    @Override
    public void onResume() {
        super.onResume();
        if(success!=null)
        {
            ((GifDrawable)success.getDrawable()).stop();
        }/*if(faliure!=null)
        {
            ((GifDrawable)faliure.getDrawable()).stop();
        }*/

    }
}
