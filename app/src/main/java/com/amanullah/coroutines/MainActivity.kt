package com.amanullah.coroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.amanullah.coroutines.databinding.ActivityMainBinding
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.getDataButton.setOnClickListener {
            Toast.makeText(applicationContext, "Requesting for Data", Toast.LENGTH_SHORT).show()
            fetchDataFromServer1()
        }

        binding.getDataParallelButton.setOnClickListener {
            Toast.makeText(applicationContext, "Requesting for Parallel Data", Toast.LENGTH_SHORT).show()
            fetchDataFromServer2()
        }

        binding.getDataAfterTotalCompleteButton.setOnClickListener {
            Toast.makeText(applicationContext, "Requesting for Data After Total Complete", Toast.LENGTH_SHORT).show()
            fetchDataFromServer3()
        }
    }

     fun fetchDataFromServer1()
     {
        lifecycleScope.launch(Dispatchers.IO) {
            val result = doNetworkCall1()

            withContext(Dispatchers.Main){
                binding.showDataTextView.text = result
                Toast.makeText(applicationContext, "Showing Data", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun fetchDataFromServer2()
    {
        lifecycleScope.launch(Dispatchers.IO) {
            val result1 = async { doNetworkCall2() }
            val result2 = async { doNetworkCall3() }

            withContext(Dispatchers.Main){
                binding.showFirstDataTextView.text = result1.await()
                binding.showSecondDataTextView.text = result2.await()
                Toast.makeText(applicationContext, "Showing Data in Parallel", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun fetchDataFromServer3()
    {
        lifecycleScope.launch(Dispatchers.IO) {
            val result1 = doNetworkCall2()
            val result2 = doNetworkCall3()

            withContext(Dispatchers.Main){
                binding.showFirstDataAfterTotalCompleteTextView.text = result1
                binding.showSecondDataAfterTotalCompleteTextView.text = result2
                Toast.makeText(applicationContext, "Showing Data After Total Complete", Toast.LENGTH_SHORT).show()
            }
        }
    }

    suspend fun doNetworkCall1(): String
    {
        delay(3000L)
        val data = Server()
        return data.data1().toString()
    }

    suspend fun doNetworkCall2(): String
    {
        delay(3000L)
        val data = Server()
        return data.data2().toString()
    }

    suspend fun doNetworkCall3(): String
    {
        delay(3000L)
        val data = Server()
        return data.data3().toString()
    }
}