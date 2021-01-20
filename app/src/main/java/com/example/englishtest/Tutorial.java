package com.example.englishtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

public class Tutorial extends AppIntro {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntroFragment.newInstance ("Có thể bạn chưa biết", "Chọn đúng sẽ cộng 1 điểm \n", R.drawable.doraemon, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("Có thể bạn chưa biết", "Chọn sai sẽ không được tính điểm \n", R.drawable.ohboy, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("Có thể bạn chưa biết", "Phải trả lời trong thời gian quy định \n", R.drawable.smile, Color.parseColor("#2196F3")));
        addSlide(AppIntroFragment.newInstance("Bắt đầu thôi!", "Chúc may mắn nhé!", R.drawable.start, Color.parseColor("#2196F3")));
        setDoneText(getString(R.string.close));
        showSkipButton(true);
        showStatusBar(false);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        finish();
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        finish();
    }
}