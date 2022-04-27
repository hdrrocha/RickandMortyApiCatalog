package com.example.rickandmortyapicatalog.domain.viewmodel


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import com.example.rickandmortyapicatalog.domain.usercase.abs.CharacterListUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class CharacterListViewModel(
    private val useCase: CharacterListUseCase
) : ViewModel() {
    private val mutableData = MutableLiveData<PagingData<CharacterInfoUi>>()

    val data get() = mutableData as LiveData<PagingData<CharacterInfoUi>>

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun loadData() = viewModelScope.launch {
        try {
            loading.value = true
            if (mutableData.value != null) return@launch
            useCase.fetchCharacter().cachedIn(viewModelScope).collect {
                mutableData.value = it

            }
        } catch (e: Exception) {
            error.value = true
            loading.value = false
        } finally {
            loading.value = false
        }
    }
}