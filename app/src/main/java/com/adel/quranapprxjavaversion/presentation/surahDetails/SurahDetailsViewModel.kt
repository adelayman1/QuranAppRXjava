package com.adel.quranapprxjavaversion.presentation.surahDetails

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.adel.quranapprxjavaversion.data.models.SurahModel
import com.adel.quranapprxjavaversion.data.models.VerseModel
import com.adel.quranapprxjavaversion.domian.entities.Result
import com.adel.quranapprxjavaversion.domian.usecases.GetSurahDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class SurahDetailsViewModel @Inject constructor(
    useCase: GetSurahDetailsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    var compositeDisposable: CompositeDisposable? = null

    var surahDetails: MutableLiveData<Result<SurahModel>> =
        MutableLiveData(Result.Loading<SurahModel>())

    var verseList: MutableLiveData<Result<List<VerseModel>>> =
        MutableLiveData(Result.Loading<List<VerseModel>>())

    init {
        compositeDisposable = CompositeDisposable()
        surahDetails.postValue(Result.Loading<SurahModel>())
        verseList.postValue(Result.Loading<List<VerseModel>>())
        val surahNumber = savedStateHandle["surahNum"] ?: 1
        compositeDisposable?.add(
            useCase.invoke(
                surahNum = surahNumber,
            ).subscribe { result ->
                when (result) {
                    is Result.Success -> {
                        val data = result.data.data
                        data.let {
                            val surah: SurahModel =
                                SurahModel(it.name, it.arabicName, it.verseNum, it.type)
                            surahDetails.postValue(Result.Success<SurahModel>(surah))
                            verseList.postValue(Result.Success<List<VerseModel>>(it.verses))
                        }
                    }
                    is Result.Error -> {
                                surahDetails.postValue(Result.Error<SurahModel>(result.error))
                    }
                }
            }
        )
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable!!.dispose()
    }
}
