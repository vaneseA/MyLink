package com.example.mylink.ui.activity

import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.mylink.databinding.ActivityPlainBinding
import com.example.mylink.ui.activity.basic.SjBasicActivity
import com.example.mylink.ui.fragment.lock.LockFragment
import com.example.mylink.viewmodel.lock.LockViewModel


class LockActivity : SjBasicActivity<ActivityPlainBinding>() {
    private val viewModel: LockViewModel by viewModels()
    private val lockFragment = LockFragment()

    override fun viewBindingInflate(inflater: LayoutInflater): ActivityPlainBinding =
        ActivityPlainBinding.inflate(inflater)

    override fun onCreate() {
        viewModel.isPasswordCorrect.observe(this, {
            if (it != null) {
                val result = if (it) RESULT_SUCCESS else RESULT_FAILED
                setResult(result)
                this.finish()
            }
        })

    }

    override fun onBackPressed() {
        setResult(RESULT_CANCELED)
        super.onBackPressed()
    }

    override fun homeFragment(): Fragment {
        return lockFragment
    }
}