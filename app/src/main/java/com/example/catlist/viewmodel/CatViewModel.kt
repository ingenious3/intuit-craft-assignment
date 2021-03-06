package com.example.catlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.catlist.data.CatListItem
import com.example.catlist.datasource.network.ResultData
import com.example.catlist.repository.AppRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatViewModel @Inject constructor(private val appRepository: AppRepository) : ViewModel () {

    private var _listResult: MutableLiveData<List<CatListItem>> = MutableLiveData<List<CatListItem>>()
    var listResult: LiveData<List<CatListItem>> = _listResult

    private var _errorGetData = MutableLiveData<String>()
    var errorGetData: LiveData<String> = _errorGetData

    var pageNumber: Int = 0

    fun getData() {

        viewModelScope.launch {
            try {
                when (val response = appRepository.getCatList(10, pageNumber, "DESC")) {
                    is ResultData.Success -> {
                        pageNumber++
                        _listResult.postValue(response.data)
                    }
                    is ResultData.Error -> {
                        pageNumber = -1
                        _errorGetData.postValue(response.exception.toString())
                    }
                }
            } catch (e: Exception) {
                pageNumber = -1
                _errorGetData.postValue(e.message)
            }
        }
    }


}