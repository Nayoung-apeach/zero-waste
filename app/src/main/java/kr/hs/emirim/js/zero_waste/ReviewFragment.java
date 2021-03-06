package kr.hs.emirim.js.zero_waste;

import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ReviewFragment extends Fragment {
    private static final String TAG = "ReviewFragment";
    private View view;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private boolean profile;
    private TextView text;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private ArrayList<WriteInfo> postList = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_review, container, false);
        view.findViewById(R.id.addBtn).setOnClickListener(onClickListener);

        String[] menuItems = {"알맹상점을 갔다오니, 일회용품을 대체할 물품들이 많다는 것을 깨닫게 되었어요! 일회용품 사용이 늘어나는 요즘, 환경을 위해 다회용품을 사용하고 알맹이만 담아서 쓰는 것은 어떨까요?",
                "새로운 경험과 친환경 가치, 수준 높은 서비스로 고객들의 높은 호응이 기대된다",
                "지구샵 곳곳에 친절한 제품 사용 설명이 적혀 있어 처음 접해보는 물건이라도 어려움 없이 시도해 볼 수 있을 것 같아요!"
                , "더피커의 곡식류는 특정 농장에서 가져오는 것인지 농장 이름도 소개돼 있다. 이런 포인트로 인해 어쩐지 신회를 가지게 되는 것 같다. "
                , "쓰레기를 줄이려는 노력이 나만의 고민, 나만의 노력이 아니라는 것도 자각했다. ",
                "쓰레기를 만들지 않으려다 보니 꼭 필요한 것만 사게 되고, 내가 타인에게 선물을 할 때도 쓰레기로 전락하지 않을 것만 주려고 노력했는데, 나눔의 의미를 다시금 깨달은 것 같다."};

        ListView listView = (ListView) view.findViewById(R.id.mainMenu);

        CustomAdapter adapter = new CustomAdapter(getContext(), postList);


        db.collection("posts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                postList.add(new WriteInfo(document.getData().get("title").toString(),
                            document.getData().get("contents").toString(),
                            document.getData().get("publisher").toString()));
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                        adapter.notifyDataSetChanged();
                    }

                });

        listView.setAdapter(adapter);
         Log.d(TAG, String.valueOf(postList));

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

    public class CustomAdapter extends ArrayAdapter {

        public CustomAdapter(@NonNull Context context, ArrayList writeInfo) {
            super(context, 0, writeInfo);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            // Check if an existing view is being reused, otherwise inflate the view
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.reivew_list_item, parent, false);
            }

            // Get the data item for this position
            WriteInfo writeInfo = (WriteInfo) getItem(position);
            Log.d(TAG, writeInfo.getContents());

            // Lookup view for data population
            TextView tvName = (TextView) convertView.findViewById(R.id.item_text);
            // Populate the data into the template view using the data object
            tvName.setText(writeInfo.getContents());
            // Return the completed view to render on screen
            return convertView;
        }


    }
}
