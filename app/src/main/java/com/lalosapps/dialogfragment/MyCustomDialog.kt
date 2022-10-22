package com.lalosapps.dialogfragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lalosapps.dialogfragment.databinding.LogoutDialogBinding

class MyCustomDialog : DialogFragment() {

    private var onLogoutClick: (() -> Unit)? = null

    fun setOnLogoutClick(listener: () -> Unit) {
        onLogoutClick = listener
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = layoutInflater.inflate(R.layout.logout_dialog, null)
        val binding = LogoutDialogBinding.bind(view)
        binding.logout.setOnClickListener { onLogoutClick?.let { it() } }
        return MaterialAlertDialogBuilder(requireContext())
            .setView(view)
            .create()
    }
}