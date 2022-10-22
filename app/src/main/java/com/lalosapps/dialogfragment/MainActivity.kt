package com.lalosapps.dialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lalosapps.dialogfragment.databinding.ActivityMainBinding

const val LOGOUT_DIALOG_TAG = "LOGOUT_DIALOG_TAG"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            val dialog =
                supportFragmentManager.findFragmentByTag(LOGOUT_DIALOG_TAG) as MyCustomDialog?
            dialog?.setOnLogoutClick { finish() }
        }

        binding.showDialog.setOnClickListener {
            MyCustomDialog().apply {
                setOnLogoutClick { finish() }
            }.show(supportFragmentManager, LOGOUT_DIALOG_TAG)
        }
    }
}