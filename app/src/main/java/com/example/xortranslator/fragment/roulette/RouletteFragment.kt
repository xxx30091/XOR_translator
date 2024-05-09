package com.example.xortranslator.fragment.roulette

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.xortranslator.databinding.FragmentRouletteBinding

class RouletteFragment : Fragment() {
    private lateinit var viewBinding: FragmentRouletteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewBinding = FragmentRouletteBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val roulette: CustomRoulette = viewBinding.roulette
        roulette.setNumbers(intArrayOf(1, 3, 5, 7))

        viewBinding.button.setOnClickListener {
            roulette.setNumbers(intArrayOf(2, 4, 6, 8, 10))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = RouletteFragment()

    }
}