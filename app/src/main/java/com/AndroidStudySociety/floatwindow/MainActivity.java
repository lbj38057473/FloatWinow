package com.AndroidStudySociety.floatwindow;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean hasPermission;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView permissonView = findViewById(R.id.bt_permission);
        TextView showBallView = findViewById(R.id.bt_ball);
        showBallView.setOnClickListener(this);
        permissonView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_permission:
                FloatPermissionChecker.jumpToPermissonPage(this);
                break;
            case R.id.bt_ball:
                Toast.makeText(this, "权限已开启", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, FloatService.class);
                startService(intent);
                break;
        }
    }
}
