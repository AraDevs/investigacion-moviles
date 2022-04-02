package com.aradevs.investigacion_moviles.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aradevs.domain.logs.Log
import com.aradevs.domain.coroutines.Status
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val saveLogUseCase: SaveLogUseCase,
    private val getLogsUseCase: GetLogsUseCase,
    private val deleteLogsUseCase: DeleteLogsUseCase,
    private val saveCompanyUseCase: SaveCompanyUseCase,
    private val getCompanyUseCase: GetCompanyUseCase,
    private val deleteCompaniesUseCase: DeleteCompaniesUseCase,
) : ViewModel() {

    //region live data variables
    private val _logStatus: MutableLiveData<Status<List<Log>>> =
        MutableLiveData(Status.Loading())
    private val _companyStatus: MutableLiveData<Status<Company?>> =
        MutableLiveData(Status.Loading())

    /**
     * Value to be observed for [Company] related transactions
     */
    val companyStatus: LiveData<Status<Company?>> get() = _companyStatus

    /**
     * Value to be observed for [Log] related transactions
     */
    val logStatus: LiveData<Status<List<Log>>> get() = _logStatus
    //endregion

    /**
     * Retrieves [Status.Success] if the request to the database was successful
     * and [Status.Error] in case something happens while obtaining the data.
     * If the result is [Status.Success] null safety checks still need to be applied to
     * determine if an empty screen or an item list should be displayed to the user
     */
    fun getBinnacles() {
        viewModelScope.launch(Dispatchers.IO) {
            _logStatus.postValue(Status.Loading())
            when (val status = getLogsUseCase()) {
                is Status.Success -> _logStatus.postValue(status)
                is Status.Error -> _logStatus.postValue(status)
                else -> {
                    //do nothing
                }
            }
        }
    }

    /**
     * Retrieves [Status.Success] if the request to save the [Log] was successful
     * and [Status.Error] in case something happens while saving the data.
     * If the result is [Status.Success], [getBinnacles] will be called to obtain the latest
     * data from the database
     *
     * @param log represents the binnacle to be saved in the database
     */
    fun saveBinnacle(log: Log) {
        viewModelScope.launch(Dispatchers.IO) {
            _logStatus.postValue(Status.Loading())
            when (val status = saveLogUseCase(log)) {
                is Status.Success -> getBinnacles()
                is Status.Error -> _logStatus.postValue(Status.Error(status.exception))
                else -> {//do nothing
                }
            }
        }
    }

    /**
     * Retrieves [Status.Success] if the request to delete the [Log] was successful
     * and [Status.Error] in case something happens while deleting the registry.
     * If the result is [Status.Success], [getBinnacles] will be called to obtain the latest
     * data from the database
     */
    fun deleteBinnacles() {
        viewModelScope.launch(Dispatchers.IO) {
            _logStatus.postValue(Status.Loading())
            when (val status = deleteLogsUseCase()) {
                is Status.Success -> getBinnacles()
                is Status.Error -> _logStatus.postValue(Status.Error(status.exception))
                else -> {//do nothing
                }
            }
        }
    }

    /**
     * Retrieves [Status.Success] if the request to the database was successful, null safety
     * checks needs to be applied to determine if a [Company] item already existed in the database,
     * [Status.Error] is returned in case something happens while obtaining the data.
     */
    fun getCompany() {
        viewModelScope.launch(Dispatchers.IO) {
            _companyStatus.postValue(Status.Loading())
            when (val status = getCompanyUseCase()) {
                is Status.Success -> _companyStatus.postValue(status)
                is Status.Error -> _companyStatus.postValue(status)
                else -> {
                    //do nothing
                }
            }
        }
    }

    /**
     * Retrieves [Status.Success] if the request to save the [Company] was successful
     * and [Status.Error] in case something happens while saving the data.
     * If the result is [Status.Success], [getCompany]  will be called to obtain the latest
     * data from the database
     *
     * @param company represents the company to be saved in the database
     */
    fun saveCompany(company: Company) {
        viewModelScope.launch(Dispatchers.IO) {
            _companyStatus.postValue(Status.Loading())
            when (val status = saveCompanyUseCase(company)) {
                is Status.Success -> getCompany()
                is Status.Error -> _companyStatus.postValue(Status.Error(status.exception))
                else -> {//do nothing
                }
            }
        }
    }

    /**
     * Retrieves [Status.Success] if the request to delete all the companies was successful
     * and [Status.Error] in case something happens while deleting the registries.
     * If the result is [Status.Success], [getCompany] will be called to obtain the latest
     * data from the database
     */
    fun deleteCompanies() {
        viewModelScope.launch(Dispatchers.IO) {
            _companyStatus.postValue(Status.Loading())
            when (val status = deleteCompaniesUseCase()) {
                is Status.Success -> getCompany()
                is Status.Error -> _logStatus.postValue(Status.Error(status.exception))
                else -> {//do nothing
                }
            }
        }
    }

}