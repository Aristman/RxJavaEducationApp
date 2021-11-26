package ru.marslab.rxjavaeducationapp

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import ru.marslab.rxjavaeducationapp.data.room.RmDatabase
import ru.marslab.rxjavaeducationapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private lateinit var rmDatabase: RmDatabase

    private val mainViewModel: MainViewModel by viewModels {
        MainViewModelFactory(rmDatabase = rmDatabase)
    }
    private val disposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        rmDatabase =
            Room.databaseBuilder(baseContext, RmDatabase::class.java, "rm-database").build()
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
                mainViewModel.getAllCharacters(isCache = binding.saveToDb.isChecked)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { Log.d("TASK_RESULT", it.toString()) }
            )
        }
        binding.btnTask28.setOnClickListener {
            disposable.add(
                mainViewModel.getCachedCharacters()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                        { Log.d("TASK_RESULT", it.toString()) },
                        { Log.e("TASK_RESULT", it.message.orEmpty()) }
                    )
            )

        }
    }

    override fun onDestroy() {
        disposable.dispose()
        super.onDestroy()
    }
}

