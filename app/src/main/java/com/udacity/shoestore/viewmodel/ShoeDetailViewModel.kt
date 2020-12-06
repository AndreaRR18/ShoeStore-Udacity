package com.udacity.shoestore.viewmodel

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.udacity.shoestore.BR
import com.udacity.shoestore.models.Shoe

class ShoeDetailViewModel : BaseObservable() {

    private var name: String? = null
    private var company: String? = null
    private var size: String? = null
    private var description: String? = null

    @Bindable
    fun getName(): String? = name
    fun setName(name: String) {
        if (!name.equals(this.name, true)) {
            this.name = name
            notifyPropertyChanged(BR.name)
        }
    }

    @Bindable
    fun getCompany(): String? = company
    fun setCompany(company: String) {
        if (!company.equals(this.company, true)) {
            this.company = company
            notifyPropertyChanged(BR.company)
        }
    }

    @Bindable
    fun getSize(): String? = size
    fun setSize(size: String) {
        if (!size.equals(this.size, true)) {
            this.size = size
            notifyPropertyChanged(BR.size)
        }
    }

    @Bindable
    fun getDescription(): String? = description
    fun setDescription(description: String) {
        if (!description.equals(this.description, true)) {
            this.description = description
            notifyPropertyChanged(BR.description)
        }
    }

    fun tryGetShoe(): Shoe? {
        try {
            val size: Double? = size?.toDouble()
            return Shoe(
                name = name ?: return null,
                size = size ?: return null,
                company = company ?: return null,
                description = description ?: return null
            )
        } catch (e: NumberFormatException) {
            return null
        }
    }

}