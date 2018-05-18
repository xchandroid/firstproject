package com.vaiyee.model.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends AppCompatActivity {

    @BindView(R.id.show_img)
    ImageView showImg;
    @BindView(R.id.wenben)
    TextView wenben;
    @BindView(R.id.back)
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
        showImg.setImageResource(R.drawable.bg);
        /*
        Intent intent = getIntent();
        wenben.setText(intent.getStringExtra("s"));
        switch (intent.getIntExtra("i",0))
        {
            case 0:
                showImg.setImageResource(R.drawable.p9);
                break;
            case 1:
                 showImg.setImageResource(R.drawable.p1);
                 break;
            case 2:
                showImg.setImageResource(R.drawable.p2);
                 break;
            case 3:
                showImg.setImageResource(R.drawable.p3);
                break;
            case 4:
                showImg.setImageResource(R.drawable.p4);
                break;
            case 5:
                showImg.setImageResource(R.drawable.p5);
                break;
            case 6:
                showImg.setImageResource(R.drawable.p6);
                break;
            case 7:
                showImg.setImageResource(R.drawable.p7);
                break;
            case 8:
                showImg.setImageResource(R.drawable.p8);
                break;
            case 9:
                showImg.setImageResource(R.drawable.p9);
                break;
            case 10:
                showImg.setImageResource(R.drawable.p10);
                break;
        }
        */
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }
}
