package au.edu.cqu.propertyWatch.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelStoreOwner
import androidx.recyclerview.widget.RecyclerView
import au.edu.cqu.propertyWatch.Database.Property
import au.edu.cqu.propertyWatch.MapsActivity
import au.edu.cqu.propertyWatch.R

class PropertyAdapter(var properties: List<Property>) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false);
        parent.context as ViewModelStoreOwner
        return ViewHolder(view)
    }

    override fun getItemCount() = properties.size

    override fun onBindViewHolder(propertyHolder: ViewHolder, position: Int) {
        val property = properties[position]
        propertyHolder.bind(property)
    }

    inner class ViewHolder (val view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }
        private var v = view;

        override fun onClick(v: View) {
            val intent = Intent(v.context, MapsActivity::class.java)
            Log.d("lat",property.lat.toString())
            Log.d("lon",property.lon.toString())
            var selectedProp = Property("",property.address,property.price,property.phone,property.lat,property.lon)
            intent.putExtra("map_data",selectedProp)

                v.context.startActivity(intent)
        }

        lateinit var property: Property
        fun bind(property: Property) {
            this.property = property

            val addressView: TextView = view.findViewById(R.id.address)
            val priceView: TextView = view.findViewById(R.id.price)
            val phoneView: TextView = view.findViewById(R.id.phone)

            addressView.text = property.address
            priceView.text = "Price: $" + property.price.toString()
            phoneView.text = "Phone: " + property.phone

            val emailButton: Button = view?.findViewById<Button>(R.id.email)
            emailButton?.setOnClickListener{ _ ->

                sendEmail()
            }

        }

        private fun sendEmail() {

            var emailSubject = v.context.resources.getString(R.string.email_subject)
            var emailBody = v.context.resources.getString(R.string.email_body, property.address,property.price.toString())


            val intent = Intent(Intent.ACTION_SEND)

            intent.putExtra(Intent.EXTRA_SUBJECT, emailSubject)
            intent.putExtra(Intent.EXTRA_TEXT, emailBody)

            intent.type = "text/plain"

            v.context.startActivity(Intent.createChooser(intent, "Send using: "))

        }



    }
    }

