package ru.myitschool.mte

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.*
import ru.myitschool.mte.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button
    private lateinit var job: Job
    private var counter = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        btnStart = binding.content.startBtn
        btnStop = binding.content.stopBtn
        val fragments = arrayOf(FirstFragment(), ProceedingFragment())
        val fragmentManager = supportFragmentManager

        btnStart.setOnClickListener {
             job = GlobalScope.launch {
            //   job = CoroutineScope(Dispatchers.IO).launch {
                while(true) {
                    val transaction = fragmentManager.beginTransaction()
                    val currentFragment = fragments[counter++ %2]
                    transaction.replace(R.id.output_fragment, currentFragment)
                    transaction.commit()
                    delay(3000)
                }
            }
        }

        btnStop.setOnClickListener {
            job.cancel()
        }
    }


}