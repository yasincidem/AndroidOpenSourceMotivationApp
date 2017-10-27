package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.QuotesData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Entity.WebsiteData;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.QuoteViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Holder.WebsiteViewHolder;
import com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link WebsiteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link WebsiteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WebsiteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int mParam1;
    private String mParam2;
    private RecyclerView recyclerView;

    private OnFragmentInteractionListener mListener;
    private FirebaseRecyclerAdapter<WebsiteData, WebsiteViewHolder> firebaseRecyclerAdapter;
    private LinearLayoutManager layoutManager;

    public WebsiteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WebsiteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WebsiteFragment newInstance(int param1, String param2) {
        WebsiteFragment fragment = new WebsiteFragment();
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
        View view = inflater.inflate(R.layout.fragment_website, container, false);

        recyclerView = view.findViewById(R.id.websiteRecyclerView);
        FirebaseRecyclerOptions<WebsiteData> firebaseListOptions = new FirebaseRecyclerOptions.Builder<WebsiteData>()
                .setIndexedQuery(FirebaseDatabase.getInstance().getReference("Website"),
                        FirebaseDatabase.getInstance().getReference("Website").getRef(),
                        WebsiteData.class)
                .setLifecycleOwner(getActivity())
                .build();

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<WebsiteData, WebsiteViewHolder>(firebaseListOptions) {
            @Override
            protected void onBindViewHolder(WebsiteViewHolder holder, int position, WebsiteData model) {
                holder.bind(model);
            }

            @Override
            public WebsiteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new WebsiteViewHolder(LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.website_row, parent, false));
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
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
