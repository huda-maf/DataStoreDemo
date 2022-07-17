package h.shafoot.datastoredemo.presentation

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import h.shafoot.datastoredemo.R
import h.shafoot.datastoredemo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        collect()
        initListeners()
    }

    private fun collect() {
        lifecycleScope.launch {
            launch {
                viewModel.name.collect {
                    binding.name = it
                }
            }
            launch {
                viewModel.note.collect {
                    binding.note = it
                }
            }
        }
    }

    private fun initListeners() {
        binding.storePrefButton.setOnClickListener {
            viewModel.setName(binding.namePrefEditText.text.toString())
            viewModel.setNote(binding.notePrefEditText.text.toString())
        }

    }

}