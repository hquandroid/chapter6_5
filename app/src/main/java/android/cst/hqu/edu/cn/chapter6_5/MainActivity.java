package android.cst.hqu.edu.cn.chapter6_5;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
    Button postBtn,getBtn;
    TextView showTxtView;
    String response;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        postBtn=findViewById(R.id.postBtn);
        getBtn=findViewById(R.id.getBtn);
        showTxtView=findViewById(R.id.txtView);
        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        response=Request.Get("http://www.hqu.edu.cn",null);
                        handler.sendEmptyMessage(0x123);
                    }
                }.start();

            }
        });

        postBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(){
                    @Override
                    public void run() {
                        response=Request.Post("http://www.hqu.edu.cn",null);
                        handler.sendEmptyMessage(0x123);
                    }
                }.start();

            }
        });

       handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==0x123){
                    showTxtView.setText(response);
                }
            }
        };
    }
}
