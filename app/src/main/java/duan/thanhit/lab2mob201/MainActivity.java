package duan.thanhit.lab2mob201;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
BroadcastReceiver broadcastReceiver, broadcastReceivers;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        final EditText editText = findViewById(R.id.edtinput);
        final EditText edtcheck = findViewById(R.id.edtcheck);
        Button btncheck = findViewById(R.id.check);
        btncheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                String masp = edtcheck.getText().toString().toUpperCase();
                if(masp.length()!=9){
                    Toast.makeText(MainActivity.this,"Mã sản phẩm phải có 9 ký tự",Toast.LENGTH_LONG).show();
                }else{
                    if(masp.startsWith("MEM")){
                        if(masp.equalsIgnoreCase("MEM537128")){
                            intent.putExtra("ma",edtcheck.getText().toString().toUpperCase());
                            intent.setAction("GIAM_GIA");
                            sendBroadcast(intent);
                        } else {
                            Toast.makeText(MainActivity.this,"Bạn đã nhập sai mã",Toast.LENGTH_LONG).show();
                        }
                    }else if(masp.startsWith("VIP")){
                        if(masp.equalsIgnoreCase("VIP537128")){
                            intent.putExtra("ma",edtcheck.getText().toString().toUpperCase());
                            intent.setAction("GIAM_GIA");
                            sendBroadcast(intent);
                        }else if(masp.equalsIgnoreCase("VIP537129")){
                            intent.putExtra("ma",edtcheck.getText().toString().toUpperCase());
                            intent.setAction("GIAM_GIA");
                            sendBroadcast(intent);
                        }else {
                            Toast.makeText(MainActivity.this,"Bạn đã nhập sai mã",Toast.LENGTH_LONG).show();
                        }
                    }else {
                        Toast.makeText(MainActivity.this,"Mã khuyến mãi phải bắt đầu bằng MEM hoặc VIP",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("name",editText.getText().toString());
                intent.setAction("CUSTOM_INTENT");
                sendBroadcast(intent);
            }
        });
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //ko xử lý quá 10s
                String data = intent.getStringExtra("name");
                String datas = intent.getStringExtra("ma");
                if(data!=null){
                    Toast.makeText(context,data,Toast.LENGTH_LONG).show();
                }else if(datas!=null){
                    if(datas.equalsIgnoreCase("MEM537128")){
                        Toast.makeText(context,"Chúc mừng bạn được khuyến mãi 10%",Toast.LENGTH_LONG).show();
                    }else if(datas.equalsIgnoreCase("VIP537128")){
                        Toast.makeText(context,"Chúc mừng bạn được khuyến mãi 30%",Toast.LENGTH_LONG).show();
                    }else {
                        Toast.makeText(context,"Chúc mừng bạn được khuyến mãi 50%",Toast.LENGTH_LONG).show();
                    }
                }else{

                }


            }
        };
        broadcastReceivers = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String datas = intent.getStringExtra("ma");
                if(datas.equalsIgnoreCase("MEM537128")){
                    Toast.makeText(context,"Chúc mừng bạn được khuyến mãi 10%",Toast.LENGTH_LONG).show();
                }else if(datas.equalsIgnoreCase("VIP537128")){
                    Toast.makeText(context,"Chúc mừng bạn được khuyến mãi 30%",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(context,"Chúc mừng bạn được khuyến mãi 50%",Toast.LENGTH_LONG).show();
                }
            }
        };
        IntentFilter intentFilter = new IntentFilter("CUSTOM_INTENT");
        IntentFilter intentFilters = new IntentFilter("GIAM_GIA");
        //đăng ký với hệ thống
        registerReceiver(broadcastReceiver,intentFilter);
        registerReceiver(broadcastReceiver,intentFilters);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
