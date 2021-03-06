package com.example.sterling.hw1;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.RadioGroup;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TextQuestionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TextQuestionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TextQuestionFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private Button submitButton;
    private RadioGroup radioGroup;

    private int selectedRadioId = -1;

    private OnFragmentInteractionListener mListener;

    public TextQuestionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TextQuestionFragment.
     */
    public static TextQuestionFragment newInstance(String param1, String param2) {
        TextQuestionFragment fragment = new TextQuestionFragment();
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
        ((QuizActivity)getActivity()).addQuestion();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_text_question, container, false);

        submitButton = (Button) view.findViewById(R.id.button3);
        submitButton.setOnClickListener(this);

        radioGroup = (RadioGroup) view.findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                selectedRadioId = checkedId;
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        onSubmit(v);
    }



    public void onSubmit(View view) {

        if(selectedRadioId == -1) {
            new AlertDialog.Builder(getActivity())
                    .setTitle("Error!")
                    .setMessage("You must select an answer!")
                    .setPositiveButton("OK", null)
                    .show();
            return;
        }

        if(R.id.radio_1492 == selectedRadioId) {
            ((QuizActivity)getActivity()).addCorrect();
        }

        String resultString = "You answered " + Integer.toString(((QuizActivity)getActivity()).getCorrect())
                            + " out of " + Integer.toString(((QuizActivity) getActivity()).getQuestions())
                            + " questions correctly.";

        new AlertDialog.Builder(getActivity())
                .setCancelable(true)
                .setTitle("Results")
                .setMessage(resultString)
                .setPositiveButton("Restart", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        ((QuizActivity)getActivity()).resetCounts();

                        getFragmentManager()
                                .popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

                        getFragmentManager()
                                .beginTransaction()
                                .replace(R.id.quiz_fragment_container, ImageQuestionFragment.newInstance(null, null))
                                .addToBackStack(null)
                                .commit();
                    }
                })
                .setNegativeButton("Quit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((QuizActivity)getActivity()).resetCounts();
                        getActivity().finish();
                    }
                })
                .show();
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
