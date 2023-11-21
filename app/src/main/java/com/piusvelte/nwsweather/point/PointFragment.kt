package com.piusvelte.nwsweather.point

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import com.google.android.material.snackbar.Snackbar
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
            viewModel.uiState
                .asLiveData()
                .observe(viewLifecycleOwner) {
                    loading.isVisible = it.isLoading

                    labelId.isVisible = it.pointId.isNotEmpty()
                    pointId.isVisible = it.pointId.isNotEmpty()
                    pointId.text = it.pointId

                    labelGridId.isVisible = it.pointGridId.isNotEmpty()
                    pointGridId.isVisible = it.pointGridId.isNotEmpty()
                    pointGridId.text = it.pointGridId


                    labelGridX.isVisible = it.pointGridX != 0
                    pointGridX.isVisible = it.pointGridX != 0
                    pointGridX.text = it.pointGridX.toString()


                    labelGridY.isVisible = it.pointGridY != 0
                    pointGridY.isVisible = it.pointGridY != 0
                    pointGridY.text = it.pointGridY.toString()

                    it.error.consume { msg ->
                        Snackbar.make(
                            root,
                            msg,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }
                }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
