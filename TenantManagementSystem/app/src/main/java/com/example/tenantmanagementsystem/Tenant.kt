package com.example.tenantmanagementsystem

data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) {
    // Exercise 2: wording changed to "Rent paid: KSh ..."
    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent paid: KSh $rent"
    }
}
