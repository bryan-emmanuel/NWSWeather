package com.piusvelte.nwsweather.point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.piusvelte.nwsweather.common.show
import com.piusvelte.nwsweather.databinding.FragmentPointBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class PointFragment @Inject constructor() : Fragment() {

    private val viewModel: PointViewModel by viewModels()
    private var binding: FragmentPointBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return FragmentPointBinding.inflate(inflater, container, false)
            .also { binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding!!) {
            viewModel.isLoading.observe(viewLifecycleOwner) { loading.isVisible = it }

            viewModel.pointId.observe(viewLifecycleOwner) { hideable ->
                labelId.show(hideable)
                pointId.show(hideable) {
                    text = it
                }
            }

            viewModel.pointGridId.observe(viewLifecycleOwner) { hideable ->
                labelGridId.show(hideable)
                pointGridId.show(hideable) { text = it }
            }

            viewModel.pointGridX.observe(viewLifecycleOwner) { hideable ->
                labelGridX.show(hideable)
                pointGridX.show(hideable) {
                    text = it.toString()
                }
            }

            viewModel.pointGridY.observe(viewLifecycleOwner) { hideable ->
                labelGridY.show(hideable)
                pointGridY.show(hideable) {
                    text = it.toString()
                }
            }

            viewModel.error.observe(viewLifecycleOwner) { it.consume { Snackbar.make(root, it, Snackbar.LENGTH_SHORT).show() }}
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
