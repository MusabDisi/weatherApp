package com.dissiapps.weatherapp.ui.extraInformationList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dissiapps.weatherapp.databinding.ExtraWeatherInformationFragmentBinding
import com.dissiapps.weatherapp.data.local.models.ExtraInfo
import com.dissiapps.weatherapp.ui.extraInformationList.extraInformationRecycler.ExtraInformationListAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ExtraInformationFragment : Fragment() {

    private val viewModel: ExtraInformationViewModel by viewModels()
    lateinit var binding: ExtraWeatherInformationFragmentBinding
    lateinit var listAdapter: ExtraInformationListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        listAdapter = ExtraInformationListAdapter()
        binding = ExtraWeatherInformationFragmentBinding.inflate(inflater, container, false)

        binding.recycler.apply {
            this.adapter = listAdapter
            layoutManager = LinearLayoutManager(requireContext())
            overScrollMode = View.OVER_SCROLL_NEVER
            val dividerItemDecoration = DividerItemDecoration(
                this.context,
                LinearLayout.VERTICAL
            )
            addItemDecoration(dividerItemDecoration)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val extraInformationDataObserver = Observer<List<ExtraInfo>> {
            handleExtraInformationData(it)
        }
        viewModel.data.observe(viewLifecycleOwner, extraInformationDataObserver)
    }

    private fun handleExtraInformationData(extraInfoList: List<ExtraInfo>?) {
        extraInfoList?.let{
            listAdapter.submitList(it)
        }
    }

}