package com.example.tenantmanagementsystem

// Serializable is built into Java/Kotlin: no plugin needed
import java.io.Serializable

// ": Serializable" means "a Tenant can be packed up and sent
// to another screen". Safe Args needs this in Practical 3
data class Tenant(
    val name: String,
    val phone: String,
    val rent: String
) : Serializable {
    // Exercise 2: wording changed to "Rent paid: KSh ..."
    fun summary(): String {
        return "Tenant: $name\nPhone: $phone\nRent paid: KSh $rent"
    }
}
