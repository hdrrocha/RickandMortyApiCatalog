package com.example.rickandmortyapicatalog.router

import androidx.navigation.NavController
import com.example.rickandmortyapicatalog.router.abs.CharacterListRouter
import com.example.rickandmortyapicatalog.ui.fragments.CharacterListFragmentDirections

class CharacterListRouterImp(
    private val navController: NavController
) : CharacterListRouter {

    override fun goToDetails(id: String) {
        val directions =
            CharacterListFragmentDirections.fragmentCharacterListToFragmentCharacterDetails(id)
        navController.navigate(directions)
    }

    override fun goBack() {
        navController.navigateUp()
    }
}
