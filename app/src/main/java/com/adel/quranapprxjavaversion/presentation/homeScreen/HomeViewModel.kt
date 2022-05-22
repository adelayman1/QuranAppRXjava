package com.adel.quranapprxjavaversion.presentation.homeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.adel.quranapprxjavaversion.data.models.SurahModel
import com.adel.quranapprxjavaversion.domian.entities.ErrorEntity
import com.adel.quranapprxjavaversion.domian.usecases.GetAllSurahUseCase
import com.adel.quranapprxjavaversion.domian.entities.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: GetAllSurahUseCase
) : ViewModel() {
    var surahList: MutableLiveData<Result<List<SurahModel>>> =
        MutableLiveData(Result.Loading())
    var compositeDisposable: CompositeDisposable? = null

    init {
        compositeDisposable = CompositeDisposable()
        loadData()
    }

    fun loadData() {
        surahList.postValue(Result.Loading())

        compositeDisposable?.add(
            useCase.invoke().subscribe { result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.data.data
                                surahList.postValue(Result.Success<List<SurahModel>>(data))
                    }
                    is Result.Error -> {
                        when (result.error) {
                            ErrorEntity.Network -> {
                                surahList.postValue(Result.Error<List<SurahModel>>(result.error))
                            }
                            else -> surahList.postValue(Result.Error<List<SurahModel>>(result.error))
                        }
                    }
                }
            }

        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable?.dispose()
    }
}
