package com.example.tenantmanagementsystem.data.repository

import androidx.lifecycle.LiveData
import com.example.tenantmanagementsystem.data.local.TenantDao
import com.example.tenantmanagementsystem.data.model.Tenant

// The only door into the data layer: the ViewModel talks to this, not to the DAO
class TenantRepository(private val tenantDao: TenantDao) {

    val tenants: LiveData<List<Tenant>> = tenantDao.getAllTenants()

    suspend fun addTenant(tenant: Tenant) {
        tenantDao.insertTenant(tenant)
    }
}