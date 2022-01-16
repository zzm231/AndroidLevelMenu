package com.swu.zzm.java72;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 记录第三层菜单的状态
    private boolean isLevel3open = true;
    private boolean isLevel2open = true;
    private RelativeLayout level3;
    private ImageButton ivHome;
    private RelativeLayout level2;
    private RelativeLayout level1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 加载容器布局
        level3 = findViewById(R.id.level3);
        level2 = findViewById(R.id.level2);

        // menu按钮
        ImageButton menu = findViewById(R.id.iv_menu);
        ImageButton home = findViewById(R.id.iv_home);

        // 添加点击事件
        menu.setOnClickListener(this);
        home.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        // 判断哪一个按钮被点击
        switch (view.getId()){
            case R.id.iv_menu:
                if (isLevel3open) {
                    // 关闭
                    close(level3,0);
                }else {
                    // 打开
                    open(level3);
                }
                // 改变状态
                isLevel3open = !isLevel3open;
                break;
            case R.id.iv_home:
                if (isLevel3open) {
                    // 关闭第三层菜单
                    close(level3,0);
                }
                if (isLevel2open) {
                    // 关闭第二层
                    close(level2,500);
                }else {
                    // 打开第二层菜单
                    open(level2);
                }
                isLevel2open = !isLevel2open;
                break;
            default:
                break;
        }
    }

    public void open(RelativeLayout rl){
        Animation in = AnimationUtils.loadAnimation(this,R.anim.rotate_in_anim);
        rl.startAnimation(in);
        // 子控件可点
        changeState(rl,true);
    }

    public void close(RelativeLayout rl,long delay){
        Animation out = AnimationUtils.loadAnimation(this,R.anim.rotate_out_anim);
        out.setStartOffset(delay);
        rl.startAnimation(out);
        // 子控件不可点
        changeState(rl,false);
    }

    public void changeState(RelativeLayout rl,boolean enable){
        // 获取容器子控件个数
        int childCount = rl.getChildCount();
        // 便利容器子控件
        for (int i = 0; i < childCount; i++) {
            // 取出对应的子控件
            View v = rl.getChildAt(i);
            // 设置子控件状态
            v.setEnabled(enable);
        }
    }
}
















