package com.dissiapps.weatherapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dissiapps.weatherapp.databinding.MainFragmentBinding
import com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.abs


@AndroidEntryPoint
class MainFragment : Fragment(){

    private val viewModel: MainFragmentViewModel by viewModels()
    private lateinit var mainFragmentBinding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainFragmentBinding = MainFragmentBinding.inflate(inflater, container, false)

        //animating
        mainFragmentBinding.apply {
            appBar.addOnOffsetChangedListener(OnOffsetChangedListener{ appBarLayout, verticalOffset ->
                bigTitle.alpha = 1.0f - abs(
                    verticalOffset / (appBarLayout.totalScrollRange/1.5)
                        .toFloat()
                )
            })

        }

        return mainFragmentBinding.root
    }

}