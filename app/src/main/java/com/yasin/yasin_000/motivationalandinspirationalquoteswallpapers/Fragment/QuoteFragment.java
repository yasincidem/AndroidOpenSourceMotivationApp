package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Fragment;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter.QuoteRecyclerAdapter;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Adapter.WallpaperRecyclerAdapter;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.ImagesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.QuotesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.QuoteViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link QuoteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link QuoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuoteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String BUNDLE_RECYCLER_LAYOUT = "classname.recycler.layout";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    DatabaseReference ref;

    private OnFragmentInteractionListener mListener;
    private List<QuotesData> quotesDataList;
    private Parcelable stateList;
    private FirebaseRecyclerAdapter<QuotesData, QuoteViewHolder> firebaseRecyclerAdapter;
    private ProgressBar progressBar;

    public QuoteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuoteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static QuoteFragment newInstance(int param1, String param2) {
        QuoteFragment fragment = new QuoteFragment();
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
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Saving instance of the state in Parcelable stateList
        stateList = recyclerView.getLayoutManager().onSaveInstanceState();
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, stateList);
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState!=null) {
            //The hack!
            stateList = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            recyclerView.getLayoutManager().onRestoreInstanceState(stateList);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_quote, container, false);
        recyclerView = view.findViewById(R.id.quoteRecyclerView);
        progressBar = view.findViewById(R.id.quote_progressBar);
        ref = FirebaseDatabase.getInstance().getReference("Data");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                quotesDataList = new ArrayList<QuotesData>();
                for (DataSnapshot snap: dataSnapshot.getChildren()) {
                    QuotesData quotes = snap.getValue(QuotesData.class);
                    quotesDataList.add(quotes);
                }
                QuoteRecyclerAdapter adapter = new QuoteRecyclerAdapter(quotesDataList,getContext());
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        FirebaseRecyclerOptions<QuotesData> firebaseListOptions = new FirebaseRecyclerOptions.Builder<QuotesData>()
//                .setIndexedQuery(FirebaseDatabase.getInstance().getReference("Data"),
//                        FirebaseDatabase.getInstance().getReference("Data").getRef(),
//                        QuotesData.class)
//                .setLifecycleOwner(getActivity())
//                .build();
//
//        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<QuotesData, QuoteViewHolder>(firebaseListOptions) {
//            @Override
//            protected void onBindViewHolder(QuoteViewHolder holder, int position, QuotesData model) {
//                holder.bind(model);
//            }
//
//            @Override
//            public QuoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                return new QuoteViewHolder(LayoutInflater.from(parent.getContext())
//                        .inflate(R.layout.quote_row, parent, false));
//            }
//        };

        layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

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
