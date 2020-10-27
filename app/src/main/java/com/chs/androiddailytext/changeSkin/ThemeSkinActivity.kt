package com.chs.androiddailytext.changeSkin

import android.os.Bundle
import android.util.TypedValue
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.blankj.utilcode.util.SPUtils
import com.chs.androiddailytext.R

class ThemeSkinActivity : AppCompatActivity() {

    private var isChange = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ThemeUtil.setBaseTheme(this)
        setContentView(R.layout.activity_theme_skin)
    }

    fun changeSkin(view: View) {
        if(isChange){
            ThemeUtil.setNewTheme(this, R.style.BlackStyle)
//            setTheme(R.style.BlackStyle)
            setContentView(R.layout.activity_theme_skin)
//            resetAttrs()
        }else{
            ThemeUtil.setNewTheme(this, R.style.LightStyle)
//            setTheme(R.style.LightStyle)
//            resetAttrs()
            setContentView(R.layout.activity_theme_skin)
        }
        isChange = !isChange
    }

    fun resetAttrs(){
        val themeId = if (isChange) R.style.LightStyle else R.style.BlackStyle
        val typedValue = TypedValue()
        theme.resolveAttribute(themeId, typedValue, true)
        typedValue.data
    }

}