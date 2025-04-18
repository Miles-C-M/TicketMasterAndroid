package com.example.retrofitexample

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ticketmasterandroid.Event
import com.example.ticketmasterandroid.R
import org.w3c.dom.Text
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale


class EventAdapter(private val events: ArrayList<Event>) : RecyclerView.Adapter<EventAdapter.MyViewHolder>() {

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder.
    inner class MyViewHolder (itemView: View): RecyclerView.ViewHolder (itemView) {
        // This class will represent a single row in our recyclerView list
        // This class also allows caching views and reuse them
        // Each MyViewHolder object keeps a reference to 3 view items in our row_item.xml file
        val eventName = itemView.findViewById<TextView>(R.id.event_name)
        val venueName = itemView.findViewById<TextView>(R.id.venue_name)
        val address = itemView.findViewById<TextView>(R.id.address)
        val date = itemView.findViewById<TextView>(R.id.date)
        val price = itemView.findViewById<TextView>(R.id.price_range)
        val ticketButton = itemView.findViewById<Button>(R.id.ticket_button)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // Inflate a layout from our XML (row_item.XML) and return the holder
        // create a new view
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_item, parent, false)
        return MyViewHolder(view)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        val currentItem = events[position]
        holder.eventName.text = currentItem.name
        holder.venueName.text = currentItem.embedded.venues[0].name
        holder.address.text = buildString {
            append(currentItem.embedded.venues[0].address.line1)
            append(", ")
            append(currentItem.embedded.venues[0].city.name)
            append(", ")
            append(currentItem.embedded.venues[0].state.stateCode)
        }
        //"localDate": "2025-04-16",
        //"localTime": "19:30:00"
        val localDate = LocalDate.parse(currentItem.dates.start.localDate)
        val localTime = LocalTime.parse(currentItem.dates.start.localTime)
        // Format the date as MM/dd/yyyy
        val dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        // Format the time as h:mm a
        val timeFormatter = DateTimeFormatter.ofPattern("h:mm a", Locale.ENGLISH)
        val formattedDate = localDate.format(dateFormatter)
        val formattedTime = localTime.format(timeFormatter)

        holder.date.text = buildString {
            append(formattedDate)
            append(" @ ")
            append(formattedTime)
        }

        // Get the context for glide
        val context = holder.itemView.context

        // Load the image from the url using Glide library
//        Glide.with(context)
//            .load(currentItem.imageUrl.medium)
//            .placeholder(R.drawable.baseline_account_circle_24) // In case the image is not loaded show this placeholder image
//            .circleCrop() // optional - Circle image with rounded corners
//            .into(holder.profileImage)

    }

    override fun getItemCount(): Int {
        // Return the size of your dataset (invoked by the layout manager)
        return events.size
    }

}