package com.example.xortranslator

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.xortranslator.databinding.ActivityMainBinding
import com.example.xortranslator.fragment.ORXEncryptionFragment
import com.example.xortranslator.fragment.roulette.RouletteFragment

class MainActivity : AppCompatActivity() {
    private val viewBinding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(LayoutInflater.from(this)) }
    private val orxEncryptionFragment: ORXEncryptionFragment by lazy { ORXEncryptionFragment() }
    private val rouletteFragment: RouletteFragment by lazy { RouletteFragment() }
//    private var oxrFragment = ORXEncryptionFragment.newInstance() // 創建新實例，適合需要多次創建且帶不同參數時使用
//    private lateinit var oxrFragment2: ORXEncryptionFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(viewBinding.root)

//        oxrFragment2 = ORXEncryptionFragment.newInstance()

        showFragment()
    }

    private fun showFragment(
//        index: Int
    ) {
//        val fragment = when (index) {
//            0 -> orxEncryptionFragment
//            else -> orxEncryptionFragment
//        }
        val fragment = rouletteFragment
        with(supportFragmentManager.beginTransaction()) {
            supportFragmentManager.fragments.forEach {
                hide(it)
            }

            if (supportFragmentManager.fragments.contains(fragment)) {
                show(fragment)
            } else {
                add(viewBinding.container.id, fragment)
            }

            commitAllowingStateLoss()
        }
    }

}