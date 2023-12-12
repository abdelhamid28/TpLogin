package com.example.tplogin


import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.example.tplogin.Login
import com.example.tplogin.databinding.ActivityMainBinding
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {
    lateinit var auth: FirebaseAuth
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    lateinit var actionToggle: ActionBarDrawerToggle
    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    lateinit var user: FirebaseUser
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        initMenu(binding)
        initNav(navView, binding)
        auth = FirebaseAuth.getInstance()
        user = auth.currentUser!!
        if (user == null) {
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()
        } else {
       /*    binding.tvDetails.text = user!!.email.toString()
        }
        binding.btnLogout.setOnClickListener {
            auth.signOut()
            val intent = Intent(this, Login::class.java)
            startActivity(intent)
            finish()*/
        }
    }

    private fun initNav(navView: NavigationView, binding: ActivityMainBinding) {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.dashboardFragment2, R.id.userListFragment),
            binding.Drawer
        )
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)

        navView?.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.users -> {
                    navController.navigate(R.id.userListFragment)

                    drawerLayout.closeDrawer(Gravity.LEFT)

                    Toast.makeText(this, "users hi", Toast.LENGTH_SHORT).show()
                    true}
                R.id.profile -> {
                    Toast.makeText(this, "profile hi", Toast.LENGTH_SHORT).show()

                    true}
                R.id.logout -> {

                    auth.signOut()
                    val intent = Intent(this, Login::class.java)
                    startActivity(intent)
                    finish()
                    true}
                else -> false

            }
        }
    }

    private fun initMenu(binding: ActivityMainBinding) {

            drawerLayout = binding.Drawer
            navView = binding.navView


            setSupportActionBar(binding.toolbar)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

//make icon menu visible
         actionToggle = ActionBarDrawerToggle(this, drawerLayout, binding.toolbar, 0, 0)
         drawerLayout.addDrawerListener(actionToggle)
            actionToggle.syncState()
        }
    }


