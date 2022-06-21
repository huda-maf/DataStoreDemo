package h.shafoot.datastoredemo.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import h.shafoot.datastoredemo.R
import h.shafoot.datastoredemo.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val viewModel : MainViewModel by viewModels()

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        observe()
        initListeners()
    }

    private fun observe() {
        viewModel.name.observe(this) {
            binding.name = it
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        }

        viewModel.note.observe(this) {
            binding.note = it
            Toast.makeText(this,it,Toast.LENGTH_LONG).show()
        }

    }

    private fun initListeners() {
        binding.storePrefButton.setOnClickListener {
            viewModel.setName(binding.namePrefEditText.text.toString())
            viewModel.setNote(binding.notePrefEditText.text.toString())
        }

        binding.storeProtoButton.setOnClickListener {
            viewModel.setName(binding.nameEditText.text.toString())
            viewModel.setName(binding.noteEditText.text.toString())
        }

        viewModel.nameProto.value?.name

    }


}