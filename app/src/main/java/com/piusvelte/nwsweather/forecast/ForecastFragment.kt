package com.piusvelte.nwsweather.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.piusvelte.nwsweather.common.show
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

            viewModel.forecast.observe(viewLifecycleOwner) { periods ->
                forecast.show(periods) {
                    adapter.submitList(it)
                }
            }

            viewModel.isLoading.observe(viewLifecycleOwner) {
                loading.isVisible = it
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
