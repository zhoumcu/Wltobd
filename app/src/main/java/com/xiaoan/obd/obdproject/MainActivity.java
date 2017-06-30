package com.xiaoan.obd.obdproject;

import android.view.ViewStub;

import com.xiaoan.obd.obdproject.module.base.ZhouBaseActivity;

public class MainActivity extends ZhouBaseActivity {

//    @BindView(R.id.tire)
//    TireView tire;
//    @BindView(R.id.button)
//    Button button;
//    Handler handler = new Handler() {
//        public void handleMessage(android.os.Message msg) {
//            if(msg.what==0x123) {
//                importPanel.inflate();
//            }
//        };
//    };
//    private ViewStub importPanel;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main_test_1);
//        importPanel  = ((ViewStub) findViewById(R.id.viewStub));
//        Message msg = new Message();
//        msg.what=0x123;
//        handler.sendMessageDelayed(msg,1000);
////        ButterKnife.bind(this);
////        button.setOnClickListener(view -> test());
//    }

    private void test() {
//        tire.setPressText("18454", getResources().getColor(R.color.colorPrimaryDark));
    }

    @Override
    public int onCreateView() {
        return R.layout.activity_main_test_1;
    }

    @Override
    public void onDelayCreate(ViewStub viewStub) {
        viewStub.inflate();
    }

    @Override
    protected void onFinish() {

    }
}
