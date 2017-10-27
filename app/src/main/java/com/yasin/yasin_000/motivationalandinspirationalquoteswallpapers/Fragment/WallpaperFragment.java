package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter.WallpaperRecyclerAdapter;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.ImagesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.WallpaperViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WallpaperFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WallpaperFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WallpaperFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter<ImagesData, WallpaperViewHolder> firebaseRecyclerAdapter;
    private GridLayoutManager gridLayoutManager;
    private int positionIndex;
    private int topView;
    List<ImagesData> imageDataList;
    DatabaseReference mRef;
    int itemPosition;
    int lastFirstVisiblePosition;
    private Parcelable stateList;
    private ProgressBar progressBar;

    @Override
    public void onPause() {
        super.onPause();
//        lastFirstVisiblePosition = ((GridLayoutManager)recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();
    }

    private OnFragmentInteractionListener mListener;

    public WallpaperFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WallpaperFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WallpaperFragment newInstance(int param1, String param2) {
        WallpaperFragment fragment = new WallpaperFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        //Saving instance of the state in Parcelable stateList
//        stateList = recyclerView.getLayoutManager().onSaveInstanceState();
//        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, stateList);
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
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
//        if(savedInstanceState!=null) {
//            //The hack!
//            stateList = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
//            recyclerView.getLayoutManager().onRestoreInstanceState(stateList);
//        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_wallpaper, container, false);
        recyclerView = view.findViewById(R.id.wallpaperRecyclerView);
        progressBar = view.findViewById(R.id.wallpaper_progressbar);

        final DatabaseReference ref = FirebaseDatabase.getInstance().getReference("Wallpapers");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                imageDataList = new ArrayList<ImagesData>();
                for (DataSnapshot snap : dataSnapshot.getChildren()) {
                    ImagesData imageData = snap.getValue(ImagesData.class);
                    imageDataList.add(imageData);
                }

                WallpaperRecyclerAdapter adapter = new WallpaperRecyclerAdapter(imageDataList, getContext());
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        FirebaseRecyclerOptions<ImageData> firebaseListOptions = new FirebaseRecyclerOptions.Builder<ImageData>()
//                .setIndexedQuery(FirebaseDatabase.getInstance().getReference("Data"),
//                        FirebaseDatabase.getInstance().getReference("Data").getRef(),
//                        ImageData.class)
//                .setLifecycleOwner(getActivity())
//                .build();
//
//        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<ImageData, WallpaperViewHolder>(firebaseListOptions) {
//            @Override
//            protected void onBindViewHolder(WallpaperViewHolder holder, int position, ImageData model) {
//                holder.bind(model);
//            }
//
//            @Override
//            public WallpaperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new WallpaperViewHolder(LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.wallpaper_row, parent, false));
//            }
//        };

        gridLayoutManager = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT ?
                new GridLayoutManager(getContext(), 3) :
                new GridLayoutManager(getContext(), 5);
        recyclerView.setLayoutManager(gridLayoutManager);


        return view;
    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
//        ((GridLayoutManager)recyclerView.getLayoutManager()).scrollToPositionWithOffset(lastFirstVisiblePosition,0);
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
