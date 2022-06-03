package com.onoff.onoffapp.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.onoff.onoffapp.databinding.ItemCustomersBinding
import com.onoff.onoffapp.domain.model.Customers
import com.onoff.onoffapp.util.Utils.minuscula

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class AdapterCustomers :

    RecyclerView.Adapter<AdapterCustomers.ViewHolder>() {

    private lateinit var items: List<Customers>
    val menuClicked = MutableLiveData<Customers>()
    val verMasClicked = MutableLiveData<Customers>()
    fun update(items: List<Customers>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = ItemCustomersBinding.inflate(layoutInflator)

        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: ItemCustomersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: Customers) {

            binding.txtCustomers.text =
                minuscula(item.cName1) + " " + minuscula(item.cName2) + " " + minuscula(item.cLastName1) + " " + minuscula(
                    item.cLastName2)

            binding.txtDocumento.text = item.cDocument.toString()

            binding.imgMenu.tag = item
            binding.imgMenu.setOnClickListener { customers ->

                menuClicked.postValue(customers.tag as Customers)

            }

            binding.imgVerMas.tag = item
            binding.imgVerMas.setOnClickListener { customers ->

                verMasClicked.postValue(customers.tag as Customers)

            }

        }

    }
}