package com.sci.recipeandroid.feature.detail.ui.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sci.recipeandroid.databinding.FragmentDetailBinding
import com.sci.recipeandroid.databinding.FragmentDirectionBinding
import com.sci.recipeandroid.feature.detail.ui.adapter.DirectionAdapter
import com.sci.recipeandroid.feature.detail.ui.viewmodel.DirectionViewModel
import com.sci.recipeandroid.util.SystemUiController.adjustStatusBar
import okhttp3.internal.notify
import org.koin.androidx.viewmodel.ext.android.viewModel

class DirectionFragment : Fragment() {
    private var _binding: FragmentDirectionBinding? = null
    private val binding get() = _binding!!

    private val viewModel : DirectionViewModel by viewModel()

    private var detailId: Double? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDirectionBinding.inflate(inflater, container, false)
        detailId = this.arguments?.getDouble("id")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val toolBar = binding.directionToolBar
        val recyclerView = binding.directionRv
        adjustStatusBar(toolBar, requireActivity())
        val adapter = DirectionAdapter()
        recyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.VERTICAL, false
        )
        recyclerView.adapter = adapter
        detailId?.let { viewModel.getDirection(it) }
        viewModel.directionScnState.observe(viewLifecycleOwner){
            when(it){
                is DirectionViewModel.DirectionScreenState.Loading -> {
                    /*ToDo Write the loading logic */
                }

                is DirectionViewModel.DirectionScreenState.Error -> {}
                is DirectionViewModel.DirectionScreenState.Success -> {
                    adapter.updateList(it.data)
                    adapter.notifyItemChanged(0)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}