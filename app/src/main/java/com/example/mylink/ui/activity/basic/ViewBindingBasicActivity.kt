package com.example.mylink.ui.activity.basic

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.viewbinding.ViewBinding

abstract class ViewBindingBasicActivity<T : ViewBinding> : AppCompatActivity() {
    // view binding
    protected lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = viewBindingInflate(layoutInflater)
        setContentView(binding.root)
        replaceFragmentTo(homeFragment())
        onCreate()
    }


    // abstract methods
    /** 여기서 T.inflate(inflater) 호출할 것. */
    abstract fun viewBindingInflate(inflater: LayoutInflater): T

    /** onCreate()에서 해야 될 일 있으면 여기에서. */
    protected abstract fun onCreate()

    /** 처음에 기본으로 fragmentContainer에 부착할 프래그먼트를 반환. */
    abstract fun homeFragment(): Fragment

    /** fragmentContainer의 layout id를 반환. */
    abstract fun fragmentContainer(): Int


    // change fragment
    protected fun moveToFragment(fragment: Fragment) {
        supportFragmentManager.commit {
            replace(fragmentContainer(), fragment)
            setReorderingAllowed(true)
            val name = fragment.javaClass.name
            addToBackStack(name)
        }
    }

    protected fun replaceFragmentTo(fragment: Fragment) {
        val fragments = supportFragmentManager.fragments
        supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        supportFragmentManager.commit {
            for (fragment in fragments) {
                remove(fragment)
            }
            replace(fragmentContainer(), fragment)
            setReorderingAllowed(true)
        }
    }

}