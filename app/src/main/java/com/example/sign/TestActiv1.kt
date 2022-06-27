package com.example.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.sign.databinding.ActivityMainBinding
import com.example.sign.databinding.ActivityTestActiv1Binding

class TestActiv1 : AppCompatActivity() {
    lateinit var bindingClass: ActivityTestActiv1Binding
    var state="empty"

    override fun onCreate(s: Bundle?) {

        super.onCreate(s)

        bindingClass = ActivityTestActiv1Binding.inflate(layoutInflater)
        setContentView(bindingClass.root)
        bindingClass.imageView.visibility = View.GONE

        state = intent.getStringExtra(Constanse.SIGN_STATE)!!
        if(state == Constanse.SIGN_IN_STATE){

            bindingClass.edName.visibility = View.GONE
            bindingClass.edName2.visibility = View.GONE
            bindingClass.edName3.visibility = View.GONE
            bindingClass.done.visibility = View.VISIBLE
            bindingClass.bAvatar.visibility = View.INVISIBLE
            bindingClass.imageView.visibility = View.INVISIBLE

        }
    }


    fun onClickDone(view: View) {
        val i = Intent()
        if(state==Constanse.SIGN_UP_STATE){
          //  val i = Intent()
            i.putExtra(Constanse.LOGIN, bindingClass.edLogin.text.toString())
            i.putExtra(Constanse.PASSWORD, bindingClass.edPass.text.toString())
            i.putExtra(Constanse.NAME_1, bindingClass.edName.text.toString())
            i.putExtra(Constanse.NAME_2, bindingClass.edName2.text.toString())
            i.putExtra(Constanse.NAME_3, bindingClass.edName3.text.toString())
            if(bindingClass.imageView.isVisible) {
                i.putExtra(Constanse.AVATAR_ID, R.drawable.avatarka)
            }
            setResult(Constanse.REQUEST_CODE_SIGN_UP, i)
            finish()
        }
        else if(state == Constanse.SIGN_IN_STATE){
         //   val i = Intent()
            i.putExtra(Constanse.LOGIN, bindingClass.edLogin.text.toString())
            i.putExtra(Constanse.PASSWORD, bindingClass.edPass.text.toString())
            setResult(Constanse.REQUEST_CODE_SIGN_IN, i)
            finish()

        }
    }

    fun onClickAvatar(view: View) {
        bindingClass.imageView.visibility = View.VISIBLE

    }


}



