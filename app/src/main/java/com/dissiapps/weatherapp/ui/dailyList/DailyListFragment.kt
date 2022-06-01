package com.dissiapps.weatherapp.ui.dailyList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.dissiapps.weatherapp.data.local.models.OneDaysData
import com.dissiapps.weatherapp.databinding.DailyCardBinding
import com.dissiapps.weatherapp.databinding.DailyListFragmentBinding
import com.dissiapps.weatherapp.ui.dailyList.dailyListRecycler.DailyListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DailyListFragment : Fragment() {

    private val viewModel: DailyListFragmentViewModel by viewModels()
    private lateinit var binding: DailyListFragmentBinding
    private lateinit var dailyCardBinding: DailyCardBinding
    private lateinit var dailyListAdapter: DailyListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dailyListAdapter = DailyListAdapter()
        binding = DailyListFragmentBinding.inflate(inflater, container, false)
        dailyCardBinding = DailyCardBinding.bind(binding.root)

        dailyCardBinding.recycler.apply{
            adapter = dailyListAdapter
            layoutManager = LinearLayoutManager(activity)
            overScrollMode = View.OVER_SCROLL_NEVER
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dailyListDataObserver = Observer<List<OneDaysData>>{
            handleDailyDataList(it)
        }
        viewModel.data.observe(viewLifecycleOwner, dailyListDataObserver)
    }

    private fun handleDailyDataList(list: List<OneDaysData>?) {
        list?.let {
            dailyListAdapter.submitList(it)
        }
    }

}