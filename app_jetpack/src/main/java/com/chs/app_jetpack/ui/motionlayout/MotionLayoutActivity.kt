package com.chs.app_jetpack.ui.motionlayout

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.chs.app_jetpack.R
import kotlinx.android.synthetic.main.activity_motion_layout6.*

class MotionLayoutActivity : AppCompatActivity() {

    companion object{
        private const val LAYOUT_ID = "layout_id"
        fun start(context:Context,layoutId:Int){
            val intent = Intent(context,MotionLayoutActivity::class.java).apply {
                putExtra(LAYOUT_ID,layoutId)
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layoutId = intent.getIntExtra(LAYOUT_ID,0)
        setContentView(layoutId)

        if(layoutId == R.layout.activity_motion_layout6){
            val constraintSet = motionlayout.getConstraintSet(R.id.end)
            constraintSet.setRotation(R.id.imageView,7200f)
            motionlayout.updateState(R.id.end,constraintSet)
        }

    }
}