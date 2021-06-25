package kr.hs.emirim.js.zero_waste;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ReviewFragment extends Fragment {
    private View view;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private boolean profile;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        view.findViewById(R.id.addBtn).setOnClickListener(onClickListener);
        return view;
    }
    View.OnClickListener onClickListener = (v) -> {
        switch (v.getId()){
            case R.id.addBtn:
                myStartActivity(WritePostActivity.class);
                break;
        }
    };
    private void myStartActivity(Class c){
        Intent intent = new Intent(getActivity(), c);
        startActivity(intent);
    }
}
