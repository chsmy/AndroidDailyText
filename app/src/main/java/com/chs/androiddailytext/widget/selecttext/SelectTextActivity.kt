package com.chs.androiddailytext.widget.selecttext

import android.os.Build
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_select_text.*

class SelectTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_text)

        val text = """
           内容拓展】

1. 本文的标题A Freshly Killed Chicken Is Mightier Than the Coronavirus出自英国作家爱德华·立顿（Edward Bulwer-Lytton）的格言The pen is mightier than the sword.一支笔胜过一把剑。即一支笔的力量有时候能胜过千军万马。

2. 中国的作家中有一位美食家（gourmet）——汪曾祺。他曾经写过昆明的鸡枞菌，火炭杨梅等等。在《昆明的雨》一文中，他描写道：“莲花池外少行人，野店苔痕一寸深。浊酒一杯天过午，木香花湿雨沉沉。我想念昆明的雨。)
            """.trimIndent()

        setYStyle(text)

        btn_y.setOnClickListener {
            StringManager.hasRemoved.clear()
            setYStyle(text)
        }
        btn_j.setOnClickListener {
            StringManager.hasRemoved.clear()
            setJStyle(text)
        }
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
            textView.customSelectionActionModeCallback = MyActionMode()
        }
    }

    private fun setYStyle(text: String){
        StringManager.blank.clear()
        StringManager.needLines.clear()
        StringManager.blank.add("10")
        StringManager.blank.add("15")
        StringManager.blank.add("20")
        StringManager.blank.add("25")
        StringManager.blank.add("30")
        StringManager.blank.add("35")
        StringManager.blank.add("40")
        StringManager.blank.add("45")
        StringManager.blank.add("50")
        StringManager.blank.add("55")
        val manager = StringManager()
        manager.setOnWordClickListener { wordInfo, position ->
            textView.text = manager.getResult(text, wordInfo, applicationContext, position)
            Toast.makeText(applicationContext, wordInfo.word, Toast.LENGTH_SHORT).show()
        }
        textView.setOnLongClickListener(View.OnLongClickListener {
            //改变长按时候的颜色
            textView.highlightColor = ContextCompat.getColor(application, android.R.color.holo_blue_light)
            false
        })
        textView.highlightColor = ContextCompat.getColor(application, R.color.transparent)
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.text = manager.getResult(text)
    }
    private fun setJStyle(text: String){
        StringManager.blank.clear()
        StringManager.needLines.clear()
        StringManager.needLines.add("10")
        StringManager.needLines.add("15")
        StringManager.needLines.add("20")
        StringManager.needLines.add("25")
        StringManager.needLines.add("30")
        StringManager.needLines.add("35")
        StringManager.needLines.add("40")
        StringManager.needLines.add("45")
        StringManager.needLines.add("50")
        StringManager.needLines.add("55")
        val manager = StringManager()
        manager.setOnWordClickListener { wordInfo, position ->
            textView.text = manager.getResult(text, wordInfo, applicationContext, position)
            Toast.makeText(applicationContext, wordInfo.word, Toast.LENGTH_SHORT).show()
        }
        textView.setOnLongClickListener(View.OnLongClickListener {
            textView.highlightColor = ContextCompat.getColor(application, android.R.color.holo_blue_light)
            false
        })
        textView.highlightColor = ContextCompat.getColor(application, R.color.transparent)
        textView.movementMethod = LinkMovementMethod.getInstance()
        textView.text = manager.getResult(text)
    }

}