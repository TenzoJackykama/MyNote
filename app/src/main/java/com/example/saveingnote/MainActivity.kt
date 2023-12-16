package com.example.saveingnote

import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.example.saveingnote.databinding.ActivityMainBinding
import kotlinx.coroutines.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration : AppBarConfiguration
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)

        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController=findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration=AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
        val states = arrayOf("Starting", "Doing Task 1", "Doing Task 2", "Ending")

        val threadOne = Thread{
            Log.v("threadOne", "my log ${Thread.currentThread()}")
        }
        repeat(states.size){
            var threadSleepValue = Random.nextLong(0, 2)
            Thread{
                states.forEach {
                    Log.i("which thread is", "${Thread.currentThread()} - $it")
                    Thread.sleep(threadSleepValue)
                }
            }.start()
        }

        threadOne.start()
/*
        var coroutineNumber = 1
        repeat(states.size){
            lifecycleScope.launch {
                Log.i("get on which thread", "${Thread.currentThread()} - $coroutineNumber")
                coroutineNumber += 1
                Log.i("get forecast", "${getForecast()}")
                Log.i("get Temperature", "${getTemperature()}")

            }
        }*/

        // Dispatcher give coroutine a separate thread by default
        lifecycleScope.launch(Dispatchers.IO) {

            Log.i("get on which thread", "${Thread.currentThread()} - second coroutine")
            val forecast2 = getForecast()
            val temp2 = getTemperature()
            val forecast = async { getForecast() }
            val temp = async { getTemperature() }
            Log.i("get forecast", "${getForecast()}")
            Log.i("get Temperature", "${getTemperature()}")
            forecast.onAwait
            Log.i("get Temperature", "${forecast.await()} and ${temp.await()}")

        }
    }

    override fun onCreateOptionsMenu(menu : Menu) : Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item : MenuItem) : Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onSupportNavigateUp() : Boolean {
        val navController=findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }

    suspend fun getForecast(): String {
        delay(1000)
        return "Sunny"
    }

    suspend fun getTemperature(): String {
        delay(1000)
        return "30\u00b0C"
    }
}