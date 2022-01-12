package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate :TextView ? = null
    private var tvageInMinutes :    TextView ? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDatePicker:Button = findViewById(R.id.btnDatePicker)
        tvageInMinutes = findViewById(R.id.tvageinminute)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
       btnDatePicker.setOnClickListener {
           clickDatePicker()
       }
    }

    private fun clickDatePicker() {
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day =  myCalendar.get(Calendar.DAY_OF_MONTH)
      val dpd =   DatePickerDialog(
          this,
          DatePickerDialog.OnDateSetListener{
                  _, selectedyear, selectedmonth, selecteddayOfMonth ->
              val selectedDate = "$selecteddayOfMonth/${selectedmonth+1}/$selectedyear"
              tvSelectedDate?.text = selectedDate

              val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
              val theDate = sdf.parse(selectedDate)
              theDate?.let {
                  val selectedDateInMinutes = theDate.time / 60000
                  val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                  currentDate?.let{

                      val currentDateInMinutes = currentDate.time / 60000

                      val diff = selectedDateInMinutes - currentDateInMinutes
                      tvageInMinutes?.text = diff.toString()
                  }

              }




          },
          year,
          month,
          day
      )


        dpd.show()
    }

}