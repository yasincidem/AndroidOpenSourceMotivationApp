package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter.ChannelRecyclerAdapter;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter.WallpaperRecyclerAdapter;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.ChannelsData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChannelFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChannelFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChannelFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    List<ChannelsData> channelsDataList;
    DatabaseReference mRef;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    private OnFragmentInteractionListener mListener;

    public ChannelFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChannelFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChannelFragment newInstance(int param1, String param2) {
        ChannelFragment fragment = new ChannelFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_videos, container, false);
        recyclerView = view.findViewById(R.id.videos_recyclerview);
        progressBar = view.findViewById(R.id.videos_progressBar);
        mRef = FirebaseDatabase.getInstance().getReference("Channels");
        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                channelsDataList = new ArrayList<>();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    ChannelsData channelsData = snap.getValue(ChannelsData.class);
                    channelsDataList.add(channelsData);
                }
                ChannelRecyclerAdapter adapter = new ChannelRecyclerAdapter(channelsDataList, getContext());
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
