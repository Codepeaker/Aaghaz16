package pgdavhyperion.com.aaghaz.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import pgdavhyperion.com.aaghaz.R;
import pgdavhyperion.com.aaghaz.adapters.MainAdapter;
import pgdavhyperion.com.aaghaz.adapters.RecyclerItemClickListener;
import pgdavhyperion.com.aaghaz.datavlaues.Information;
import pgdavhyperion.com.aaghaz.activities.Society;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSociety#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSociety extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private RecyclerView societyView;
    private MainAdapter adapter;


    public FragmentSociety() {
        // Required empty public constructor
    }



    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSociety.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSociety newInstance(String param1, String param2) {
        FragmentSociety fragment = new FragmentSociety();
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
        View layout = inflater.inflate(R.layout.fragment_society, container, false);
        societyView=(RecyclerView)layout.findViewById(R.id.societylist);
        adapter = new MainAdapter(getActivity(),getData());
        societyView.setAdapter(adapter);
        societyView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent in=new Intent(getContext(), Society.class);
                in.putExtra("society_index",position);
                startActivity(in);
            }
        }));
        societyView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public  List<Information> getData(){
        List<Information> data = new ArrayList<>();
        int icons[] = {R.drawable.ic_logochanakya,R.drawable.ic_logoconundrum,R.drawable.ic_logodiversity,R.drawable.ic_logoimpression,R.drawable.ic_logoiris,R.drawable.ic_logonavrang,R.drawable.ic_logoraaga,R.drawable.ic_logorapbeats,R.drawable.ic_logorudra,R.drawable.ic_logotechwiz};
        String[] title = getResources().getStringArray(R.array.society_name);
        String[] tagLine = getResources().getStringArray(R.array.society_type);
        for(int i=0;i<title.length&&i<icons.length;i++){
            Information current = new Information();
            current.iconId = icons[i];
            current.title = title[i];
            current.tagLine= tagLine[i];
            data.add(current);

        }
        return data;
    }


}
