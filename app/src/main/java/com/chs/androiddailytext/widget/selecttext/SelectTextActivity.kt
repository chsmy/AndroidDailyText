package com.chs.androiddailytext.widget.selecttext

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.chs.androiddailytext.R
import kotlinx.android.synthetic.main.activity_select_text.*

class SelectTextActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_text)

        //        mTextView = findViewById(R.id.main_selectableTextView);
//        mTextView.setSelectTextBackColorRes(R.color.colorAccent);

        val text = """
            “Second Fool” opened-his eyes name and stared at the mud and thatch roof over his head. The quilt covering his body was a deep yellow color and had a musty smell. It was so old that its original color could no longer be distinguished.
            
            Next to him lay his second brother, Han Zhu, who appeared to be in a deep slumber. Snores intermittently floated over as he slept.
            
            Five feet from the bed was an earthen wall that had suffered from numerous cracks due to the passage of time. From the other side of the wall came the nagging voice of his mother and the occasional deep breathing of his father who was smoking his pipe.
            
            Second Fool slowly closed his eyes, trying to force himself to sleep. He knew that if he didn’t go to sleep now, he wouldn’t be able to wake up early the next day. If he woke up late, he would not be able to go up to the mountains with his good friends to gather firewood.
            
            Second Fool’s real name was Han Li. This elegant name was not given to him by his parents. When he was born, his parents had offered two pieces of cornbread to the village’s Elder Zhang in exchange for giving the baby Han Li a second name.
            
            (TL: “Second Fool” [er leng zi 二愣子] in Mandarin has a pleasing sound despite its meaning)
            """.trimIndent()

        val manager = StringManager()
        manager.setOnWordClickListener { wordInfo, position ->
            textView.setText(manager.getClickResult(text, wordInfo, applicationContext, position))
            Toast.makeText(applicationContext, wordInfo.word, Toast.LENGTH_SHORT).show()
        }
        textView.setOnLongClickListener(View.OnLongClickListener {
            textView.setHighlightColor(ContextCompat.getColor(application, android.R.color.holo_blue_light))
            false
        })
        textView.setHighlightColor(ContextCompat.getColor(application, R.color.transparent))
        textView.setMovementMethod(LinkMovementMethod.getInstance())
        textView.setText(manager.getResult(text))

    }
}