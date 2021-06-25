package kr.hs.emirim.js.zero_waste;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        findViewById(R.id.addBtn).setOnClickListener(onClickListener);
    }
    View.OnClickListener onClickListener = (v)->{
        switch (v.getId()){
            case R.id.addBtn:
                myStartActivity(WritePostActivity.class);
                break;
        }
    };

    private void myStartActivity(Class c) {
        Intent intent = new Intent(this, c);
        startActivity(intent);
    }
}