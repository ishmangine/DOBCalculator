package com.freewarestudio.dobcalculator

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var showDateInMinute: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        showDateInMinute = findViewById(R.id.showDateInMinute)
        btnDatePicker.setOnClickListener{
            clickDatePicker()
        }
    }
    private fun clickDatePicker(){
//        Toast.makeText(this,"clickDatePicker clicked",Toast.LENGTH_SHORT).show()
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener{view,selectedYear,selectedMonth,selectedDayOfMonth->

                var selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    val selectedDateInMinutes = theDate.time/(60*1000) //time will result in milliseconds

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinute = currentDate.time/(60*1000)
                        val diffInMinute = currentDateInMinute - selectedDateInMinutes
                        showDateInMinute?.text = diffInMinute.toString()
                    }

                }

                Toast.makeText(this,"selected date is $selectedDate",Toast.LENGTH_LONG).show()

            },
            year,month,day)

        dpd.datePicker.maxDate = System.currentTimeMillis() - (24*60*60*1000)
        dpd.show()
    }
}