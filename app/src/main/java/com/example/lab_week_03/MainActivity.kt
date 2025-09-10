package com.example.lab_week_03

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.lab_week_03.CoffeeListener
import com.example.lab_week_03.DetailFragment
import com.example.lab_week_03.R

interface CoffeeListener {
    fun onSelected(id: Int)
}

class MainActivity : AppCompatActivity(), CoffeeListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onSelected(id: Int) {
        val detailFragment = DetailFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_detail, detailFragment)
            .addToBackStack(null) // supaya bisa kembali ke list
            .commit()

        // Pastikan transaksi langsung dijalankan sebelum set data
        supportFragmentManager.executePendingTransactions()
        detailFragment.setCoffeeData(id)
    }
}
