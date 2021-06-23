package kr.hs.emirim.js.zero_waste;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

public class ModifyActivity extends AppCompatActivity {

    private static final String TAG = "ModifyActivity";
    Button mLoginBtn;
    TextView mJoinText;
    EditText mEmailText, mPasswordText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        firebaseAuth = FirebaseAuth.getInstance();

        findViewById(R.id.sendButton).setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = (v) -> {
        switch (v.getId()){
            case R.id.sendButton:
                send();
                break;
        }
    };
    private void send(){
        String email = ((EditText)findViewById(R.id.login_email_textedit)).getText().toString();

        if (email.length() > 0){
            firebaseAuth.sendPasswordResetEmail(email)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull @NotNull Task<Void> task) {
                    if(task.isSuccessful()){
                        startToast("이메일을 보냈습니다.");
                        finish();
                        Log.d(TAG, "Email sent");
                    }
                }
            });
        }else{
            startToast("이메일을 입력해 주세요.");
        }
    }
    private void startToast(String msg) {Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();}
}
