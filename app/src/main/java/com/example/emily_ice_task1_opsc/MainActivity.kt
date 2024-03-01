package com.example.emily_ice_task1_opsc

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.emily_ice_task1_opsc.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)

        val btnFrench = findViewById<Button>(R.id.BtnFrench)
        val btnGerman = findViewById<Button>(R.id.BtnGerman)
        val btnAfrikaans = findViewById<Button>(R.id.BtnAfrikaans)
        val btnItalian = findViewById<Button>(R.id.BtnItalian)
        val btnLatin = findViewById<Button>(R.id.BtnLatin)
        val btnEnglish = findViewById<Button>(R.id.BtnEnglish)

        btnFrench.setOnClickListener { changeLanguage("fr") }
        btnGerman.setOnClickListener { changeLanguage("de") }
        btnAfrikaans.setOnClickListener { changeLanguage("af") }
        btnItalian.setOnClickListener { changeLanguage("it") }
        btnLatin.setOnClickListener { changeLanguage("la") }
        btnEnglish.setOnClickListener { changeLanguage("en") }
    }

    // Function to change language
    private fun changeLanguage(languageCode: String) {
        val locale = Locale(languageCode)
        Locale.setDefault(locale)
        val config = Configuration()
        config.setLocale(locale)
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)

        // Save selected language for future use
        val prefs = getSharedPreferences("Settings", Context.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString("My_Lang", languageCode)
        editor.apply()

        // Display toast message indicating language change
        val languageName = when (languageCode) {
            "fr" -> "French"
            "de" -> "German"
            "af" -> "Afrikaans"
            "it" -> "Italian"
            "la" -> "Latin"
            else -> "English"
        }
        Toast.makeText(this, "Language changed to $languageName", Toast.LENGTH_SHORT).show()

        // Recreate the activity to apply the language changes
        recreate()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}
