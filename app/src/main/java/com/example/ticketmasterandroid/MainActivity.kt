package com.example.ticketmasterandroid

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    private var eventType = ""
    private var cityName = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create list of strings to appear in spinner
        val optionList = listOf("Choose an event category", "Music", "Sports", "Theater", "Family", "Arts & Theater", "Concerts", "Comedy", "Dance")

        // Create an adapter with 3 parameters: activity (this), layout (using a pre-built layout), list to show
        val optionAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, optionList)

        // Store the the listView widget in a variable
        val optionSpinner = findViewById<Spinner>(R.id.event_types)

        // set the adapter to the spinner
        optionSpinner.adapter = optionAdapter

        // set the onItemSelectedListener as (this).  (this) refers to this activity that implements OnItemSelectedListener interface
        optionSpinner.onItemSelectedListener = this
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        //Callback method to be invoked when the selection disappears from this view. For example,
        // if the list becomes empty and the ArrayAdapter is notified, this callback will be invoked
        Toast.makeText(this, "Nothing is selected!", Toast.LENGTH_SHORT).show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

        // Based on the index of position selected, set the corresponding event type
        eventType = when(position){
            0 -> ""
            1 -> "Music"
            2 -> "Sports"
            3 -> "Theater"
            4 -> "Family"
            5 -> "Arts & Theater"
            6 -> "Concerts"
            7 -> "Comedy"
            8 -> "Dance"
            else -> "Error"
        }
    }

    fun buttonClick(view: View) {
        cityName = findViewById<EditText>(R.id.city_input).text.toString()
        Toast.makeText(this, eventType, Toast.LENGTH_SHORT).show()
        if (eventType == "" || eventType == "Error") {
            eventMissing()
        }
        if (cityName == "") {
            locationMissing()
        }
    }

    // Shows dialog message for missing location input
    private fun locationMissing() {
        // Create an alertdialog builder object,
        // then set attributes that you want the dialog to have
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Location missing")
        builder.setMessage("City cannot be empty. Please enter a city.")
        // Set an icon, optional
        builder.setIcon(android.R.drawable.ic_delete)

        // Set the button actions, all of them are optional
        builder.setPositiveButton("Okay"){ dialog, which ->
            // code to run when YES is pressed
        }

        // create the dialog and show it
        val dialog = builder.create()
        dialog.show()
    }

    // Shows dialog message for missing event category
    private fun eventMissing() {
        // Create an alertdialog builder object,
        // then set attributes that you want the dialog to have
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Event category missing")
        builder.setMessage("Event category cannot be empty. Please select an event category")
        // Set an icon, optional
        builder.setIcon(android.R.drawable.ic_delete)

        // Set the button actions, all of them are optional
        builder.setPositiveButton("Okay"){ dialog, which ->
            // code to run when YES is pressed
        }

        // create the dialog and show it
        val dialog = builder.create()
        dialog.show()
    }
}