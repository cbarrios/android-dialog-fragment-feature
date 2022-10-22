package com.lalosapps.dialogfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.lalosapps.dialogfragment.databinding.ActivityMainBinding

const val LOGOUT_DIALOG_TAG = "LOGOUT_DIALOG_TAG"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var dialog: MyCustomDialog? = null
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState != null) {
            dialog =
                supportFragmentManager.findFragmentByTag(LOGOUT_DIALOG_TAG) as MyCustomDialog?
            dialog?.setOnLogoutClick { finish() }
        }

        binding.showDialog.setOnClickListener {
            dialog = MyCustomDialog().apply {
                setOnLogoutClick { finish() }
            }
            dialog?.show(supportFragmentManager, LOGOUT_DIALOG_TAG)
            viewModel.startCountdown()
        }

        viewModel.delay.observe(this) {
            if (it != null) {
                dialog?.changeLogoutButtonText(it)
                if (it == 0) dialog?.dismiss()
            }
        }
    }
}