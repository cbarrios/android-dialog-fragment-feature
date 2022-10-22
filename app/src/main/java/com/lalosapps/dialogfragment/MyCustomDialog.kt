package com.lalosapps.dialogfragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lalosapps.dialogfragment.databinding.LogoutDialogBinding

class MyCustomDialog : DialogFragment() {

    private var onLogoutClick: (() -> Unit)? = null

    private lateinit var binding: LogoutDialogBinding

    fun setOnLogoutClick(listener: () -> Unit) {
        onLogoutClick = listener
    }

    fun changeLogoutButtonText(seconds: Int) {
        if (this::binding.isInitialized) {
            binding.logout.text = getString(R.string.logout_timer, seconds)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = false
        val view = layoutInflater.inflate(R.layout.logout_dialog, null)
        binding = LogoutDialogBinding.bind(view)
        binding.logout.setOnClickListener { onLogoutClick?.let { it() } }
        return MaterialAlertDialogBuilder(requireContext())
            .setView(view)
            .create()
    }
}