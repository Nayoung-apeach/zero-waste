package kr.hs.emirim.js.zero_waste;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

public class WritePostActivity extends AppCompatActivity {
    private static final String TAG = "WritePostActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        findViewById(R.id.check).setOnClickListener(onClickListener);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
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
        String title = ((EditText)findViewById(R.id.titleeditText)).getText().toString();
        String contents = ((EditText)findViewById(R.id.contenteditText)).getText().toString();

        if(title.length() > 0 && contents.length() > 0){
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            WriteInfo writeInfo = new WriteInfo(title, contents);

            if(user != null){
                db.collection("posts").document(user.getUid()).set(writeInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                startToast("게시물 등록을 성공하였습니다.");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull @NotNull Exception e) {
                                startToast("게시물 등록에 실패하였습니다.");
                                Log.w(TAG, "Error writing document", e);
                            }
                        });
            }
        }else {
            startToast("회원정보를 입력해주세요.");
        }
    }
    //
//    private void uploader(WriteInfo writeInfo){
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//        db.collection("posts").add(writeInfo)
//                .addOnSuccessListener(new OnSuccessListener<DocumentReference>(){
//                    @Override
//                    public void onSuccess(DocumentReference documentReference){
//                        Log.d(TAG, "DocumentSnapshot written with ID:" + documentReference.getId());
//                    }
//                })
//                .addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull @NotNull Exception e) {
//                        Log.w(TAG, "Error adding document", e);
//                    }
//                });
//    }
    private void startToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
