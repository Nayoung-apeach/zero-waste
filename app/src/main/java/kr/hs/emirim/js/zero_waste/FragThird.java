package kr.hs.emirim.js.zero_waste;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class FragThird extends Fragment {
    Dialog mission_done;
    ImageView earth;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.third_mission, container, false);
        earth = rootView.findViewById(R.id.imageView);
        mission_done =new Dialog(getActivity());
        mission_done.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mission_done.setContentView(R.layout.activity_mission_dialog);
        earth.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                mission_done.show();
                mission_done.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button noBtn = mission_done.findViewById(R.id.noBtn);
                Button yesBtn = mission_done.findViewById(R.id.yesBtn);
                yesBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mission_done.dismiss();
                        Toast.makeText(getContext(),"경험치 +10",Toast.LENGTH_SHORT).show();
                    }
                });
                noBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // 원하는 기능 구현
                        mission_done.dismiss();
                    }
                });
            }
        });

        return rootView;
    }

}