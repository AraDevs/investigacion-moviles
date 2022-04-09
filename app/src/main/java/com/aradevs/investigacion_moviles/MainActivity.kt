package com.aradevs.investigacion_moviles

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import by.kirich1409.viewbindingdelegate.viewBinding
import com.aradevs.domain.logs.Log
import com.aradevs.domain.coroutines.Status
import com.aradevs.domain.general.Company
import com.aradevs.investigacion_moviles.adapters.LogsAdapter
import com.aradevs.investigacion_moviles.databinding.ActivityMainBinding
import com.aradevs.investigacion_moviles.dialogs.AddLogDialog
import com.aradevs.investigacion_moviles.dialogs.AddCompanyDialog
import com.aradevs.investigacion_moviles.view_models.MainActivityViewModel
import com.c3rberuss.androidutils.gone
import com.c3rberuss.androidutils.visible
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val binding: ActivityMainBinding by viewBinding()
    private val viewModel: MainActivityViewModel by viewModels()
    private val logsAdapter: LogsAdapter by lazy {
        LogsAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeLogsStatus()
        observeCompanyStatus()
        viewModel.getBinnacles()
        viewModel.getCompany()
        setupUiBinding()

    }

    /**
     * Sets up the Main Screen related listeners
     */
    private fun setupUiBinding() {
        binding.addBinnacle.setOnClickListener {
            AddLogDialog.newInstance {
                viewModel.saveBinnacle(it)
            }.show(supportFragmentManager, "add_binnacle_dialog")
        }

        binding.deleteAll.setOnClickListener {
            viewModel.deleteBinnacles()
            viewModel.deleteCompanies()
        }
    }


    /**
     * Checks [Company] null safety and shows a dialog to add the required information before
     * accessing the rest of the app. Shows the company name in the main activity layout if
     * a company is returned from the database.
     *
     * @param company represents the company to be null checked
     */
    private fun companyDataBinding(company: Company?) {
        when (company) {
            null -> {
                binding.companyName.text = getString(R.string.empty_string)
                AddCompanyDialog.newInstance {
                    viewModel.saveCompany(it)
                }.show(supportFragmentManager, "add_company_dialog")
            }
            else -> binding.companyName.text = company.name
        }
    }

    /**
     * Checks if the provided [Log] list is null or empty and shows a message if that condition is met,
     * otherwise it will fill the binnacle list with the provided data
     */
    private fun logsDataBinding(items: List<Log>) {
        when {
            items.isNullOrEmpty() -> {
                binding.noBinnacles.visible()
                binding.binnacleList.adapter = null
            }
            items.isNotEmpty() -> {
                binding.noBinnacles.gone()
                binding.binnacleList.adapter = logsAdapter
                logsAdapter.submitList(items.reversed())
            }
        }
    }

    /**
     * Observes live data provided from view model to detect binnacle related state changes
     */
    private fun observeLogsStatus() {
        viewModel.logStatus.observe(this) {
            when (it) {
                is Status.Success -> {
                    binding.loader.root.gone()
                    logsDataBinding(it.data)
                }
                is Status.Error -> {
                    Timber.d("Error ${it.exception}")
                    binding.loader.root.gone()
                }
                is Status.Loading -> binding.loader.root.visible()
                else -> {
                    //do nothing
                }
            }
        }
    }

    /**
     * Observes live data provided from view model to detect company related state changes
     */
    private fun observeCompanyStatus() {
        viewModel.companyStatus.observe(this) {
            when (it) {
                is Status.Success -> companyDataBinding(it.data)
                is Status.Error -> {
                    Timber.d("Error ${it.exception}")
                }
                else -> {
                    //do nothing
                }
            }
        }
    }

}