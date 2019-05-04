package com.chs.androiddailytext.netease;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import com.chs.androiddailytext.R;

/**
 * @author：chs date：2019/5/1
 * des：
 */
public class PasswordView extends RelativeLayout {

    /**
     * 密码输入框
     */
    private PasswordEditText[]  mEtPassword;

    public PasswordView(Context context) {
        this(context,null);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_password,this);
        mEtPassword = new PasswordEditText[6];
        mEtPassword[0] = view.findViewById(R.id.et_pass1);
        mEtPassword[1] = view.findViewById(R.id.et_pass2);
        mEtPassword[2] = view.findViewById(R.id.et_pass3);
        mEtPassword[3] = view.findViewById(R.id.et_pass4);
        mEtPassword[4] = view.findViewById(R.id.et_pass5);
        mEtPassword[5] = view.findViewById(R.id.et_pass6);
        setListener();
    }

    private void setListener() {
        for (int i = 0;i< mEtPassword.length;i++) {
            mEtPassword[i].setBackground(new ColorDrawable(Color.TRANSPARENT));
            int finalI = i;
            mEtPassword[i].addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                  if(s.length()==1&& finalI +1<=6){
                       if(finalI+1!=6){
                           mEtPassword[finalI+1].requestFocus();
                       }
                  }
                  if(s.length() == 0 && finalI-1>=0){
                     mEtPassword[finalI-1].requestFocus();
                  }
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });
        }
    }


}
