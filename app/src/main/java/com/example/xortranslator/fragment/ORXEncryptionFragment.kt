package com.example.xortranslator.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.xortranslator.databinding.FragmentORXEncryptionBinding
import com.example.xortranslator.utils.EncryptionHelper.decode
import com.example.xortranslator.utils.EncryptionHelper.encode
import com.example.xortranslator.utils.Formatter.JSONFormatter.jsonFormatWithWarning
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class ORXEncryptionFragment : Fragment() {
    private lateinit var viewBinding: FragmentORXEncryptionBinding
    private var mode = Mode.DECODE

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentORXEncryptionBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.apply {

            orxTabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    when (tab.position) {
                        0 -> {
                            mode = Mode.DECODE
                            translate.text = Mode.DECODE.title
                        }
                        1 -> {
                            mode = Mode.ENCODE
                            translate.text = Mode.ENCODE.title
                        }
                    }
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            })

            translate.setOnClickListener {
                val data = translateContent(input.text.toString())
                val formatData = jsonFormatWithWarning(data)
                output.text = formatData
            }

            clean.setOnClickListener {
                input.text.clear()
            }

        }
    }

    private fun translateContent(input: String): String {
        return when (mode) {
            Mode.DECODE -> {
                decode(input)
            }
            Mode.ENCODE -> {
                encode(input)
            }
        }
    }

    companion object {
        fun newInstance() = ORXEncryptionFragment()
    }

    enum class Mode(val title: String) {
        ENCODE("Encode"),
        DECODE("Decode")
    }
}