package com.vaiyee.model.model;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestActivity extends AppCompatActivity {

    @BindView(R.id.load)
    Button load;
    @BindView(R.id.viewstub)
    ViewStub viewstub;
    @BindView(R.id.viewstub2)
    ViewStub viewstub2;
    @BindView(R.id.viewstub3)
    ViewStub viewstub3;
    @BindView(R.id.viewstub4)
    ViewStub viewstub4;
    @BindView(R.id.viewstub5)
    ViewStub viewstub5;
    private View inflateview, inflateview2, inflateview3, inflateview4, inflateview5;
    private boolean isfirsrt = true, firstload = true, isfirsrt2 = true, firstload2 = true, isfirsrt3 = true, firstload3 = true, isfirsrt4 = true, firstload4 = true, isfirsrt5 = true, firstload5 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.load, R.id.load2, R.id.load3, R.id.load4, R.id.load5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.load:
                if (firstload) {
                    firstload = false;
                    inflateview = viewstub.inflate();
                } else {
                    if (isfirsrt) {
                        isfirsrt = false;
                        inflateview.setVisibility(View.GONE);
                    } else {
                        isfirsrt = true;
                        inflateview.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.load2:
                if (firstload2) {
                    firstload2 = false;
                    inflateview2 = viewstub2.inflate();
                } else {
                    if (isfirsrt2) {
                        isfirsrt2 = false;
                        inflateview2.setVisibility(View.GONE);
                    } else {
                        isfirsrt2 = true;
                        inflateview2.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.load3:
                if (firstload3) {
                    firstload3 = false;
                    inflateview3 = viewstub3.inflate();
                }
                else {
                    if (isfirsrt3) {
                        isfirsrt3 = false;
                        inflateview3.setVisibility(View.GONE);
                    } else {
                        isfirsrt3 = true;
                        inflateview3.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.load4:
                if (firstload4) {
                    firstload4 = false;
                    inflateview4 = viewstub4.inflate();
                }
                else {
                    if (isfirsrt4) {
                        isfirsrt4 = false;
                        inflateview4.setVisibility(View.GONE);
                    } else {
                        isfirsrt4 = true;
                        inflateview4.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case R.id.load5:
                if (firstload5) {
                    firstload5 = false;
                    inflateview5 = viewstub5.inflate();
                }
                else {
                    if (isfirsrt5) {
                        isfirsrt5 = false;
                        inflateview5.setVisibility(View.GONE);
                    } else {
                        isfirsrt5 = true;
                        inflateview5.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }
}
