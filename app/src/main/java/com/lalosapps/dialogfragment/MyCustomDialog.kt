package com.lalosapps.dialogfragment

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.lalosapps.dialogfragment.databinding.LogoutDialogBinding

class MyCustomDialog : DialogFragment() {

    private var onLogoutClick: (() -> Unit)? = null
    private var onDismissDialog: (() -> Unit)? = null

    private lateinit var binding: LogoutDialogBinding

    fun setOnLogoutClick(listener: () -> Unit) {
        onLogoutClick = listener
    }

    fun setOnDismissListener(listener: () -> Unit) {
        onDismissDialog = listener
    }

    fun changeLogoutButtonText(seconds: Int) {
        if (this::binding.isInitialized) {
            binding.logout.text = context?.getString(R.string.logout_timer, seconds) ?: return
        }
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        onDismissDialog?.let { it() }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        isCancelable = true
        val view = layoutInflater.inflate(R.layout.logout_dialog, null)
        binding = LogoutDialogBinding.bind(view)
        binding.logout.setOnClickListener { onLogoutClick?.let { it() } }
        return MaterialAlertDialogBuilder(requireContext())
            .setView(view)
            .create()
    }
}