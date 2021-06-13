package kr.hs.emirim.js.zero_waste;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

// dayeon-choi

public class JoinActivity extends AppCompatActivity {
    private EditText email_join;
    private EditText name_join;
    private EditText id_join;
    private EditText pwd_join;
    private EditText re_pwd_join;
    private Button btn_join;
    FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        email_join = (EditText) findViewById(R.id.join_email_textedit);
        name_join = (EditText) findViewById(R.id.join_name_textedit);
        id_join = (EditText) findViewById(R.id.join_id_textedit);
        pwd_join = (EditText) findViewById(R.id.join_pwd_textedit);
        re_pwd_join = (EditText) findViewById(R.id.join_pwd_re_textedit);
        btn_join = (Button) findViewById(R.id.join_button);

        firebaseAuth = FirebaseAuth.getInstance();
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_join.getText().toString().trim();
                String name = name_join.getText().toString().trim();
                String id = id_join.getText().toString().trim();
                String pwd = pwd_join.getText().toString().trim();
                String re_pwd = re_pwd_join.getText().toString().trim();

                // 암호 확인 체크
                if (pwd != re_pwd) {
                    Toast.makeText(JoinActivity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                }
                // 유효성 체크
                else if(email.isEmpty() && name.isEmpty() && id.isEmpty() && pwd.isEmpty() && re_pwd.isEmpty()) {
                    Toast.makeText(JoinActivity.this, "입력되지 않은 항목이 있습니다.", Toast.LENGTH_SHORT).show();
                } else{
                    firebaseAuth.createUserWithEmailAndPassword(email,pwd).addOnCompleteListener(JoinActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull @org.jetbrains.annotations.NotNull Task<AuthResult> task) {
                            // 가입 성공 시
                            if(task.isSuccessful()){
                                // Firebase에서 가져온 현재 유저 정보 (email, uid)
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                String email=user.getEmail();
                                String uid = user.getUid();

                                // 해쉬맵 테이블을 Firebase 데이터베이스에 저장
                                HashMap<Object,String> hashMap = new HashMap<>();

                                hashMap.put("uid",uid);
                                hashMap.put("email",email);
                                hashMap.put("name",name);
                                hashMap.put("id",id);
                                hashMap.put("password",pwd);

                                // DB 접근 권한 가짐
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                // DB Users라는 인스턴스 가짐
                                DatabaseReference reference = database.getReference("Users");
                                // 그 child에 HashMap 집어넣음
                                reference.child(uid).setValue(hashMap);

                                // 가입이 이루어졌을 시 가입 화면을 빠져나감.

                            }
                        }
                    });
                }
            }
        });
    }
}
