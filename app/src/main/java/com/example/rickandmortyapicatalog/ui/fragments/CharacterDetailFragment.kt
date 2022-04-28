package com.example.rickandmortyapicatalog.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.rickandmortyapicatalog.databinding.FragmentCharacterDetailBinding
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import com.example.rickandmortyapicatalog.domain.viewmodel.SingleCharacterViewModel
import kotlinx.android.synthetic.main.fragment_character_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

class CharacterDetailFragment : Fragment() {

    private var thiscontex: Context? = null

    private lateinit var binding: FragmentCharacterDetailBinding

    private val singleCharacterViewModel: SingleCharacterViewModel by viewModel()
    private val args by navArgs<CharacterDetailFragmentArgs>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCharacterDetailBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = singleCharacterViewModel
        }
        if (container != null) {
            thiscontex = container.context
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObservers()
        singleCharacterViewModel.getSingleCharacter(args.idCharacter)

    }

    private fun setupObservers() {
        singleCharacterViewModel.data.observe(viewLifecycleOwner) {
            loadCharacterDetails(it)
        }
    }

    private fun loadCharacterDetails(character: CharacterInfoUi?) {
        loadImage(character)

    }

    private fun loadImage(character: CharacterInfoUi?) {
        Glide.with(this)
            .load(character?.image)
            .into(characterIDetailImage)
    }

}