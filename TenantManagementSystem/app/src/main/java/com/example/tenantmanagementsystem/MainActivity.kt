package com.example.tenantmanagementsystem

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.onNavDestinationSelected
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.tenantmanagementsystem.databinding.ActivityMainBinding
import com.example.tenantmanagementsystem.databinding.NavHeaderBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    // The "driver" that switches Fragments inside the NavHost
    private lateinit var navController: NavController

    // Tells the App Bar which screens are top-level
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("TENANT_LIFECYCLE", "MainActivity: onCreate")

        // Create the views from activity_main.xml (DrawerLayout + Toolbar + NavHost)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1. Use our Toolbar as the App Bar
        //    toolbar <- android:id="@+id/toolbar" in activity_main.xml
        setSupportActionBar(binding.toolbar)

        // 2. Get the NavController from the NavHost
        //    nav_host_fragment <- android:id="@+id/nav_host_fragment" in activity_main.xml
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        // 3. Top-level destinations + the drawer
        //    (top-level screens show the hamburger icon instead of a back arrow)
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dashboardFragment, R.id.addTenantFragment, R.id.viewTenantsFragment),
            binding.drawerLayout
        )

        // 4. Connect the App Bar and the drawer to navigation.
        //    The title comes from each destination's android:label in nav_graph.xml
        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        // 5. Show the user's email in the drawer header.
        //    Read the "EMAIL" extra that LoginActivity attached
        val email = intent.getStringExtra("EMAIL")

        // The header view already exists (app:headerLayout), so BIND to it
        val headerBinding = NavHeaderBinding.bind(binding.navView.getHeaderView(0))

        // headerEmailTextView <- android:id="@+id/headerEmailTextView" in nav_header.xml
        headerBinding.headerEmailTextView.text = email ?: "Signed in"
    }

    // The hamburger opens the drawer; the arrow goes back
    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    // Turn main_menu.xml into menu items in the App Bar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    // What happens when a menu item is tapped
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.action_logout) {
            // Logout is not a screen in the nav graph, so we handle it ourselves
            val intent = Intent(this, LoginActivity::class.java)
            // Close every open screen and start fresh with Login
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            return true
        }

        // Same ID as a destination in nav_graph.xml -> NavigationUI navigates there
        return item.onNavDestinationSelected(navController) || super.onOptionsItemSelected(item)
    }

    // ── LIFECYCLE METHODS ─────────────────────────────────────────
    // Android calls each of these automatically. We only add a log
    // line so we can watch the order in Logcat.

    // The screen is now VISIBLE
    override fun onStart() {
        super.onStart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStart")
    }

    // The screen is in FRONT and the user can interact with it
    override fun onResume() {
        super.onResume()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onResume")
    }

    // The screen is LEAVING the front (e.g. Home was pressed)
    override fun onPause() {
        super.onPause()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onPause")
    }

    // The screen is NO LONGER VISIBLE
    override fun onStop() {
        super.onStop()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onStop")
    }

    // The user came BACK to a stopped screen
    override fun onRestart() {
        super.onRestart()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onRestart")
    }

    // The screen is being DESTROYED
    override fun onDestroy() {
        super.onDestroy()
        Log.d("TENANT_LIFECYCLE", "MainActivity: onDestroy")
    }
}
