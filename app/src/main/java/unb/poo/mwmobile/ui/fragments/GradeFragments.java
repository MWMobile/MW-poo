package unb.poo.mwmobile.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import unb.poo.mwmobile.R;
import unb.poo.mwmobile.models.Materia;
import unb.poo.mwmobile.singleton.SingletonUser;
import unb.poo.mwmobile.ui.adapters.GradeCardAdapter;

/**
 * Created by Raphael on 07/12/2015.
 */
public class GradeFragments {
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PeriodoFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        SingletonUser singletonUser;
        ArrayList<Materia> grade;
        ArrayList<Materia> newGrade;

        RecyclerView recList;

        public PeriodoFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PeriodoFragment newInstance(int sectionNumber) {
            PeriodoFragment fragment = new PeriodoFragment();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_grade, container, false);

            newGrade = new ArrayList<>();

            singletonUser = SingletonUser.getInstance();
            grade = singletonUser.getUser().getMaterias();

            for(Materia a: grade){
                newGrade.add(a);
            }

            recList = (RecyclerView) rootView.findViewById(R.id.cardList);
            recList.setLayoutManager(new LinearLayoutManager(getActivity()));
            recList.setHasFixedSize(true);

            recList.setAdapter(new GradeCardAdapter(newGrade, getActivity()));

            return rootView;
        }
    }
}
