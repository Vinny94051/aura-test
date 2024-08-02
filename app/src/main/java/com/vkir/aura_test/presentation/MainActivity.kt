package com.vkir.aura_test.presentation

import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.vkir.aura_test.R
import com.vkir.aura_test.databinding.ActivityMainBinding
import com.vkir.aura_test.utils.PermissionManager
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val binding by viewBinding<ActivityMainBinding>()
    private val viewModel: MainActivityViewModel by viewModel()

    override fun onStart() {
        super.onStart()
        requestNotificationPermission()
        initViews()
    }

    private fun requestNotificationPermission() {
        val isNotificationPermissionGranted =
            PermissionManager.isNotificationPermissionGranted(this)

        if (!isNotificationPermissionGranted) {
            PermissionManager.requestNotificationPermission(this)
        }
    }

    private fun initViews() {
        binding.input.text = Editable.Factory.getInstance()
            .newEditable(viewModel.getMaxCancellationsNumber().toString())

        binding.btnMain.setOnClickListener {
            val number = binding.input.text
            try {
                viewModel.saveMaxCancellationsNumber(number.toString().toInt())
            } catch (ex: NumberFormatException) {
                ex.printStackTrace()
                Toast.makeText(this, "This is not a number!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}