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
 * Created by vishal on 1/14/2016.
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MyViewHolder> {

    private int mPreviousPosition = 0;
    private View view;
    Context context;

    private LayoutInflater inflater;
    List<Information> data = Collections.emptyList();
    public MainAdapter(Context context, List<Information> data){
        inflater=LayoutInflater.from(context);
        this.data = data;
        this.context=context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        view = inflater.inflate(R.layout.custom_view,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final Information current = data.get(position);
        holder.title.setText(current.title);
        holder.tagLine.setText(current.tagLine);
        holder.icon.setImageResource(current.iconId);
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

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView title,tagLine;
        ImageView icon;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.listText);
            tagLine = (TextView)itemView.findViewById(R.id.listText2);
            icon = (ImageView)itemView.findViewById(R.id.listIcon);


        }
    }
}


