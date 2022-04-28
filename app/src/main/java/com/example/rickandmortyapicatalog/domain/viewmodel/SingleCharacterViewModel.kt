package com.example.rickandmortyapicatalog.domain.viewmodel


import androidx.lifecycle.*
import com.example.rickandmortyapicatalog.domain.uimodel.CharacterInfoUi
import com.example.rickandmortyapicatalog.domain.usercase.abs.SingleCharactertUseCase
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SingleCharacterViewModel(
    private val useCase: SingleCharactertUseCase
) : ViewModel() {
    private val mutableData = MutableLiveData<CharacterInfoUi>()

    val data get() = mutableData as LiveData<CharacterInfoUi>

    val error = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    val name = Transformations.map(mutableData) {
        it.name
    }


    val status = Transformations.map(mutableData) {
        it.status
    }

    val species = Transformations.map(mutableData) {
        it.species
    }
    val type = Transformations.map(mutableData) {
        it.type
    }
    val gender = Transformations.map(mutableData) {
        it.gender + ","
    }
    val origin = Transformations.map(mutableData) {
        it.origin?.name
    }
    val location = Transformations.map(mutableData) {
        it.location?.name
    }
    val image = Transformations.map(mutableData) {
        it.image
    }
    val created = Transformations.map(mutableData) {
        it.created
    }


    fun getSingleCharacter(id: String) = viewModelScope.launch {
        try {
            loading.value = true
            if (mutableData.value != null) return@launch
            mutableData.value = useCase.getSingleCharacter(id)
        } catch (e: Exception) {
            error.value = true
            loading.value = false
        } finally {
            loading.value = false
        }
    }
}