package com.example.sterling.hw1;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.content.DialogInterface;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ImageQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ImageQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ImageQuestionFragment extends Fragment implements View.OnClickListener{
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button submitButton;

    private OnFragmentInteractionListener mListener;

    public ImageQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param correctCount Parameter 1.
     * @param incorrectCount Parameter 2.
     * @return A new instance of fragment ImageQuestionFragment.
     */
    public static ImageQuestionFragment newInstance(String correctCount, String incorrectCount) {
        ImageQuestionFragment fragment = new ImageQuestionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, correctCount);
        args.putString(ARG_PARAM2, incorrectCount);
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
        ((QuizActivity)getActivity()).addQuestion();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_image_question, container, false);

        submitButton = (Button) view.findViewById(R.id.button2);
        submitButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        onSubmit(v);
    }



    @SuppressWarnings({"NullableProblems"})
    public void onSubmit(View view) {
        if(((EditText)getView().findViewById(R.id.editText)).getText().toString().trim().toLowerCase().length() <= 0) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error!")
                    .setMessage("You must answer the question!")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        if(((EditText)getView().findViewById(R.id.editText)).getText().toString().trim().toLowerCase().compareTo(getResources().getString(R.string.yellow)) == 0) {
            ((QuizActivity)getActivity()).addCorrect();
        }
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.quiz_fragment_container, TextQuestionFragment.newInstance(null,null))
                .addToBackStack(null)
                .commit();
    }

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
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
