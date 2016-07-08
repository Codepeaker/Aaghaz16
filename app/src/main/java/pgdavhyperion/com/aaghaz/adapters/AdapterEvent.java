package pgdavhyperion.com.aaghaz.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

import pgdavhyperion.com.aaghaz.R;
import pgdavhyperion.com.aaghaz.anim.AnimationUtils;
import pgdavhyperion.com.aaghaz.datavlaues.Information;

/**
 * Created by vishal on 1/16/2016.
 */
public class AdapterEvent extends RecyclerView.Adapter<AdapterEvent.MyeventViewHolder>{

    private int mPreviousPosition = 0;
    private LayoutInflater inflater;
    private View view;
    private Context context;
    List<Information> data = Collections.emptyList();
    public AdapterEvent(Context context, List<Information> data){
        inflater=LayoutInflater.from(context);
        this.data = data;
        this.context=context;
    }

    @Override
    public MyeventViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.custom_eventview,parent,false);
        MyeventViewHolder holder = new MyeventViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(AdapterEvent.MyeventViewHolder holder, int position) {
        final Information current = data.get(position);
        holder.icon.setImageResource(current.iconId);
        holder.title.setText(current.title);

        if (position > mPreviousPosition) {
            AnimationUtils.animateSunblind(holder, true);
            //  AnimationUtils.animateSunblind(holder, true);
//            AnimationUtils.animate1(holder, true);
//            AnimationUtils.animate(holder,true);

        } else {
            AnimationUtils.animateSunblind(holder, false);
            //  AnimationUtils.animateSunblind(holder, false);
//            AnimationUtils.animate1(holder, false);
//            AnimationUtils.animate(holder, false);
        }
        mPreviousPosition = position;

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class MyeventViewHolder extends RecyclerView.ViewHolder{

        ImageView icon;
        TextView title;
        public MyeventViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView)itemView.findViewById(R.id.eventlistimage);
            title= (TextView) itemView.findViewById(R.id.title);


        }
    }
}
