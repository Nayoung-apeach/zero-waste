package kr.hs.emirim.js.zero_waste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.jetbrains.annotations.NotNull;

// dayeon-choi

public class LoginActivity extends AppCompatActivity {
    Button mLoginBtn;
    TextView mJoinText;
    EditText mEmailText, mPasswordText;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        //버튼 등록하기
        mJoinText = findViewById(R.id.login_join_text);
        mLoginBtn = findViewById(R.id.login_btn);
        mEmailText = findViewById(R.id.login_email_textedit);
        mPasswordText = findViewById(R.id.login_password_textedit);
        
        // 회원가입 텍스트 눌리면
        mJoinText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 회원가입 화면(액티비티)로 전환
                startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                finish();
            }
        });
        
        // 로그인 버튼이 눌리면
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = mEmailText.getText().toString().trim();
                String pwd = mPasswordText.getText().toString().trim();
                firebaseAuth.signInWithEmailAndPassword(email,pwd).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this,"로그인 정보가 일치하지 않습니다.",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
