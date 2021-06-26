package kr.hs.emirim.js.zero_waste;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class WritePostActivity extends AppCompatActivity {
    private static final String TAG = "WritePostActivity";
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        findViewById(R.id.check).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.check:
                    profileUpdate();
                    break;
            }
        }
    };

    private void profileUpdate(){
        final String title = ((EditText)findViewById(R.id.titleText)).getText().toString();
        final String contents = ((EditText)findViewById(R.id.contentText)).getText().toString();

        if(title.length() > 0 && contents.length() > 0){
            user = FirebaseAuth.getInstance().getCurrentUser();
            WriteInfo writeInfo = new WriteInfo(title, contents, user.getUid());
            uploader(writeInfo);
        }else{
            startToast("회원정보를 입력해주세요.");
        }
    }
    private void uploader(WriteInfo writeInfo){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("posts").add(writeInfo)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot written with ID:" + documentReference.getId());
                        startToast("등록에 성공하였습니다.");
                    }
                });
    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}