package com.example.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import com.example.sign.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var bindingClass: ActivityMainBinding
    private var login : String = "empty"
    private var password : String= "empty"
    private var name : String = "empty"
    private var name2 : String = "empty"
    private var name3 : String = "empty"
    private var avatarImage : Int = 0

    override fun onCreate(s: Bundle?) {
        super.onCreate(s)
        bindingClass = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindingClass.root)
    }

    fun onClickSignIn(view: View) {

        if(bindingClass.imAvatar.isVisible && bindingClass.tvEdit.text.toString() != "Такого аккаунта не существует"){
            bindingClass.imAvatar.visibility = View.INVISIBLE
            bindingClass.tvEdit.text = ""
            bindingClass.signIn.text = getString(R.string.sign_in)
            bindingClass.signUp.visibility = View.VISIBLE


        }
        else {
            val i = Intent(this, TestActiv1::class.java)
            i.putExtra(Constanse.SIGN_STATE, Constanse.SIGN_IN_STATE)
            st.launch(Intent(i))
        }
    }

    fun onClickSignUp(view: View) {

        val i = Intent(this,TestActiv1::class.java)
        i.putExtra(Constanse.SIGN_STATE, Constanse.SIGN_UP_STATE)
        st.launch(Intent(i))

    }
    val st = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if(result.resultCode == Constanse.REQUEST_CODE_SIGN_IN){
            val l : String = result.data?.getStringExtra(Constanse.LOGIN)!!
            val p : String =result.data?.getStringExtra(Constanse.PASSWORD)!!
            if(login == l && password == p){
                    bindingClass.imAvatar.visibility = View.VISIBLE
                    bindingClass.imAvatar.setImageResource(avatarImage)
                    val n = "$name $name2 $name3"
                    bindingClass.tvEdit.text = n
                    bindingClass.signUp.visibility = View.GONE
                    bindingClass.signIn.text = "Выйти"
                }
            else {
                    bindingClass.tvEdit.text = "Такого аккаунта не существует"
                    bindingClass.imAvatar.visibility = View.VISIBLE
                    bindingClass.imAvatar.setImageResource(R.drawable.warn)
            }




        }
        else if(result.resultCode == Constanse.REQUEST_CODE_SIGN_UP){

             login = result.data?.getStringExtra(Constanse.LOGIN)!!
             password = result.data?.getStringExtra(Constanse.PASSWORD)!!
             name = result.data?.getStringExtra(Constanse.NAME_1)!!
             name2 = result.data?.getStringExtra(Constanse.NAME_2)!!
             name3 = result.data?.getStringExtra(Constanse.NAME_3)!!
             avatarImage = result.data?.getIntExtra(Constanse.AVATAR_ID, 0)!!
             bindingClass.imAvatar.visibility = View.VISIBLE
             bindingClass.imAvatar.setImageResource(avatarImage)
             val n = "$name $name2 $name3"
             bindingClass.tvEdit.text = n
             bindingClass.signUp.visibility = View.GONE
             bindingClass.signIn.text = "Выйти"
        }
    }
}