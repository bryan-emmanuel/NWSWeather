package com.piusvelte.nwsweather.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.piusvelte.nwsweather.databinding.FragmentForecastBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ForecastFragment @Inject constructor() : Fragment() {

    private val viewModel: ForecastViewModel by viewModels()
    private var binding: FragmentForecastBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentForecastBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding!!) {
            val adapter = ForecastAdapter()
            forecast.adapter = adapter

            viewModel.uiState.asLiveData().observe(viewLifecycleOwner) {
                loading.isVisible = it.isLoading

                forecast.isVisible = it.periods.isNotEmpty()
                adapter.submitList(it.periods)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
