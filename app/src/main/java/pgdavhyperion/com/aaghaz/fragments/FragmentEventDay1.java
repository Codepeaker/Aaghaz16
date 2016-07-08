package pgdavhyperion.com.aaghaz.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pgdavhyperion.com.aaghaz.R;
import pgdavhyperion.com.aaghaz.adapters.AdapterEvent;
import pgdavhyperion.com.aaghaz.adapters.RecyclerItemClickListener;
import pgdavhyperion.com.aaghaz.datavlaues.Information;
import pgdavhyperion.com.aaghaz.activities.Events;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentEventDay1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentEventDay1 extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView eventViewDay1;
    private AdapterEvent adapterEvent;



    public FragmentEventDay1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentEventDay1.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentEventDay1 newInstance(String param1, String param2) {
        FragmentEventDay1 fragment = new FragmentEventDay1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout = inflater.inflate(R.layout.fragment_event_day1, container, false);

        eventViewDay1=(RecyclerView)layout.findViewById(R.id.eventlist);

        adapterEvent = new AdapterEvent(getActivity(),getData());
        eventViewDay1.setAdapter(adapterEvent);

        eventViewDay1.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(final View view, final int position) {
                TextView title= (TextView) view.findViewById(R.id.title);
                Intent in=new Intent(getContext(), Events.class);
                in.putExtra("event_name",title.getText());
                startActivity(in);
            }
        }));
        eventViewDay1.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int icons[] = {R.drawable.ic_event_debate,R.drawable.ic_event_video,R.drawable.ic_event_coding,R.drawable.ic_event_lan,R.drawable.ic_eventlist_painting,R.drawable.ic_event_folk,R.drawable.ic_event_quiz,R.drawable.ic_event_rap,R.drawable.ic_event_beatboxing,R.drawable.ic_event_lightsinging,R.drawable.ic_event_soloclassicaldance,R.drawable.ic_event_techquiz,R.drawable.ic_event_treasure,R.drawable.ic_event_poetry,R.drawable.ic_event_bob};
        String title[]={"Rebuttal","On Spot Videography","Code Storm","LAN Racing","Chatori Dilli","Jashan","360 degrees","R.A.P.","Beat Boxing","Sursringar","Nrityangana","Techquiz","Treasure Hunt","Radeef","Battle of Bands"};

        for(int i=0;i<icons.length;i++){
            Information current = new Information();
            current.iconId = icons[i];
            current.title=title[i];
            data.add(current);

        }
        return data;
    }




}
