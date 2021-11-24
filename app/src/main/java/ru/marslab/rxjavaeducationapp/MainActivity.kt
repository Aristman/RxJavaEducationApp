package ru.marslab.rxjavaeducationapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.marslab.rxjavaeducationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val mainViewModel by viewModels<MainViewModel>()
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initListeners()
    }

    private fun initListeners() {
        binding.btnTask21.setOnClickListener {
            disposable.add(
                mainViewModel.getAllNews()
                    .subscribe(
                        { Log.d("TAG", "Successful -> $it") },
                        { Log.d("TAG", "Request Error! -> ${it.message}") }
                    )
            )
        }
        binding.btnTask27.setOnClickListener {
            disposable.add(
                mainViewModel.getAllCharacters()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.d("TASK_RESULT", it.toString()) }
            )
        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}
