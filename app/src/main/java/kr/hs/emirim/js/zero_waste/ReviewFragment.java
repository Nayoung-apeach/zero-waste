package kr.hs.emirim.js.zero_waste;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class ReviewFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private View view;
    private String mParam1;
    private String mParam2;

    public ReviewFragment() {
    }

    public static ReviewFragment newInstance(String param1, String param2) {
        ReviewFragment fragment = new ReviewFragment();
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

    private void myStartActivity(Class c){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_review, container, false);
        view.findViewById(R.id.head_btn).setOnClickListener(onClickListener);
        view.findViewById(R.id.body_btn).setOnClickListener(onClickListener);
        view.findViewById(R.id.leftarm_btn).setOnClickListener(onClickListener);
        view.findViewById(R.id.rightarm_btn).setOnClickListener(onClickListener);
        view.findViewById(R.id.leftleg_btn).setOnClickListener(onClickListener);
        view.findViewById(R.id.rightleg_btn).setOnClickListener(onClickListener);
        return view;
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.head_btn:
                    ClickHead(v);
                    break;
                case R.id.body_btn:
                    ClickBody(v);
                    break;
                case R.id.leftarm_btn:
                    ClickLeftArm(v);
                    break;
                case R.id.rightarm_btn:
                    ClickRightArm(v);
                    break;
                case R.id.leftleg_btn:
                    ClickLeftLeg(v);
                    break;
                case R.id.rightleg_btn:
                    ClickRightLeg(v);
                    break;
            }
        }
    };
    private void ClickHead(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://earthplogging.com/"));
        startActivity(intent);
    }
    private void ClickBody(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://news.samsung.com/kr/tag/%EA%B7%B8%EB%A6%B0%EC%B1%8C%EB%A6%B0%EC%A7%80"));
        startActivity(intent);
    }
    private void ClickLeftArm(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://petlatte.kr/"));
        startActivity(intent);
    }
    private void ClickRightArm(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://love.seoul.go.kr/asp/articleView.asp?intSeq=8189"));
        startActivity(intent);
    }
    private void ClickLeftLeg(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.worldvision.or.kr/story/covid19-respond/"));
        startActivity(intent);
    }
    private void ClickRightLeg(View v){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.greenpeace.org/korea/issue-climate/"));
        startActivity(intent);
    }
}
