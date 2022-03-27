package com.aradevs.investigacion_moviles.view_models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aradevs.domain.binnacles.Binnacle
import com.aradevs.domain.coroutines.Status
import com.aradevs.domain.general.Company
import com.aradevs.storagemanager.use_cases.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val saveBinnacleUseCase: SaveBinnacleUseCase,
    private val getBinnaclesUseCase: GetBinnaclesUseCase,
    private val deleteBinnacleUseCase: DeleteBinnacleUseCase,
    private val saveCompanyUseCase: SaveCompanyUseCase,
    private val getCompanyUseCase: GetCompanyUseCase,
    private val deleteCompaniesUseCase: DeleteCompaniesUseCase,
) : ViewModel() {

    //region live data variables
    private val _binnacleStatus: MutableLiveData<Status<List<Binnacle>>> =
        MutableLiveData(Status.Loading())

    /**
     * Value to be observed for [Binnacle] related transactions
     */
    val binnacleStatus: LiveData<Status<List<Binnacle>>> get() = _binnacleStatus

    private val _companyStatus: MutableLiveData<Status<Company?>> =
        MutableLiveData(Status.Loading())

    /**
     * Value to be observed for [Company] related transactions
     */
    val companyStatus: LiveData<Status<Company?>> get() = _companyStatus
    //endregion

    /**
     * Retrieves [Status.Success] if the request to the database was successful
     * and [Status.Error] in case something happens while obtaining the data.
     * If the result is [Status.Success] null safety checks still need to be applied to
     * determine if an empty screen or an item list should be displayed to the user
     */
    fun getBinnacles() {
        viewModelScope.launch(Dispatchers.IO) {
            _binnacleStatus.postValue(Status.Loading())
            when (val status = getBinnaclesUseCase()) {
                is Status.Success -> _binnacleStatus.postValue(status)
                is Status.Error -> _binnacleStatus.postValue(status)
                else -> {
                    //do nothing
                }
            }
        }
    }

    /**
     * Retrieves [Status.Success] if the request to save the [Binnacle] was successful
     * and [Status.Error] in case something happens while saving the data.
     * If the result is [Status.Success], [getBinnacles] will be called to obtain the latest
     * data from the database
     *
     * @param binnacle represents the binnacle to be saved in the database
     */
    fun saveBinnacle(binnacle: Binnacle) {
        viewModelScope.launch(Dispatchers.IO) {
            _binnacleStatus.postValue(Status.Loading())
            when (val status = saveBinnacleUseCase(binnacle)) {
                is Status.Success -> getBinnacles()
                is Status.Error ->  _binnacleStatus.postValue(Status.Error(status.exception))
                else -> {//do nothing
                }
            }
        }
    }

    /**
     * Retrieves [Status.Success] if the request to delete the [Binnacle] was successful
     * and [Status.Error] in case something happens while deleting the registry.
     * If the result is [Status.Success], [getBinnacles] will be called to obtain the latest
     * data from the database
     *
     * @param id represents the id of the binnacle to be deleted
     */
    fun deleteBinnacle(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            _binnacleStatus.postValue(Status.Loading())
            when (val status = deleteBinnacleUseCase(id)) {
                is Status.Success -> getBinnacles()
                is Status.Error -> _binnacleStatus.postValue(Status.Error(status.exception))
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
                is Status.Error ->  _binnacleStatus.postValue(Status.Error(status.exception))
                else -> {//do nothing
                }
            }
        }
    }

}