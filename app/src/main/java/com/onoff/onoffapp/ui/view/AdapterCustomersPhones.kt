package com.onoff.onoffapp.ui.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.onoff.onoffapp.databinding.ItemTelefonoBinding
import com.onoff.onoffapp.domain.model.CustomersPhones

/**
 * Created by hans fritz ortega on 20/06/02.
 */
class AdapterCustomersPhones :

    RecyclerView.Adapter<AdapterCustomersPhones.ViewHolder>() {

    private lateinit var items: List<CustomersPhones>
    val menuClicked = MutableLiveData<CustomersPhones>()

    fun update(items: List<CustomersPhones>) {
        this.items = items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val layoutInflator = LayoutInflater.from(parent.context)
        val binding = ItemTelefonoBinding.inflate(layoutInflator)

        return ViewHolder(binding)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position])

    inner class ViewHolder(private val binding: ItemTelefonoBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: CustomersPhones) {

            binding.txtTelefono.text = item.cpPhone

        }
    }
}