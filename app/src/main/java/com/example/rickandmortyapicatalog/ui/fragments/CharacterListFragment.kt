package com.example.rickandmortyapicatalog.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.rickandmortyapicatalog.databinding.FragmentCharacterListBinding
import com.example.rickandmortyapicatalog.domain.viewmodel.CharacterListViewModel
import com.example.rickandmortyapicatalog.router.abs.CharacterListRouter
import com.example.rickandmortyapicatalog.ui.adapters.CharacterItemsLoadStateAdapter
import com.example.rickandmortyapicatalog.ui.adapters.CharacterViewAdapter
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class CharacterListFragment : Fragment() {

    private val router: CharacterListRouter by inject { parametersOf(findNavController()) }

    private val characterListViewModel: CharacterListViewModel by viewModel()

    private lateinit var binding: FragmentCharacterListBinding

    private val characterViewAdapter = CharacterViewAdapter()
    private val headerAdapter = CharacterItemsLoadStateAdapter { characterViewAdapter.retry() }
    private val footerAdapter = CharacterItemsLoadStateAdapter { characterViewAdapter.retry() }
    private var listener: CharacterViewAdapter.OnItemClickListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterListBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = characterListViewModel
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycler()
        setupObservers()
        characterListViewModel.loadData()

    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setupRecycler() {
        listener = object : CharacterViewAdapter.OnItemClickListener {
            override fun onItemClick(id: String) = handleOnClickEvent(id)
        }
        characterViewAdapter.onItemClickListener = listener

        characterViewAdapter.addLoadStateListener { loadStates ->
            headerAdapter.loadState = loadStates.refresh
            footerAdapter.loadState = loadStates.append
        }

        binding.rvcharacterList.run {
            layoutManager =
                StaggeredGridLayoutManager(3, RecyclerView.VERTICAL).apply {
                    gapStrategy =
                        StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
                    isAutoMeasureEnabled
                }
            itemAnimator = null

            adapter = ConcatAdapter(headerAdapter, characterViewAdapter, footerAdapter)
            setHasFixedSize(true)
        }
        characterViewAdapter.submitData(lifecycle, PagingData.empty())
    }

    private fun setupObservers() {
        characterListViewModel.data.observe(viewLifecycleOwner) {
            characterViewAdapter.submitData(lifecycle, it)
        }
    }

    private fun handleOnClickEvent(id: String) {
//        val args by navArgs<CharacterListFragmentArgs>()
//        if (args.isComparing) {
//            router.goBack()
//        } else {
//            router.goToDetails(id)
//        }
    }
}