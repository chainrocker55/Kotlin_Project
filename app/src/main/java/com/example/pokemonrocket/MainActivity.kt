package com.example.pokemonrocket

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.ui.*
import androidx.viewpager.widget.ViewPager
import com.example.pokemonrocket.databinding.ActivityMainBinding
import com.gigamole.navigationtabstrip.NavigationTabStrip
import kotlinx.android.synthetic.main.content_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home,
                R.id.nav_share
            ), drawerLayout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

//        val  navigationTabStrip:NavigationTabStrip = findViewById(R.id.nts)
//        navigationTabStrip.setTitles("Home", "Search", "About")
//        navigationTabStrip.setTabIndex(0, true)
//        navigationTabStrip.setTitleSize(50F)
//        navigationTabStrip.setStripColor(Color.WHITE)
//        navigationTabStrip.setStripWeight(6F)
//        navigationTabStrip.setStripFactor(2F)
//        navigationTabStrip.setStripType(NavigationTabStrip.StripType.LINE)
//        navigationTabStrip.setStripGravity(NavigationTabStrip.StripGravity.BOTTOM)
//        navigationTabStrip.setTypeface("fonts/typeface.ttf")
//        navigationTabStrip.setCornersRadius(3F)
//        navigationTabStrip.setBackgroundColor(Color.rgb(0,133,119))
//        navigationTabStrip.setAnimationDuration(300)
//        navigationTabStrip.setInactiveColor(Color.GRAY)
//        navigationTabStrip.setActiveColor(Color.WHITE)
//        navigationTabStrip.setOnClickListener{
//            tabClick(navigationTabStrip.tabIndex)
//        }

        //navigationTabStrip.setOnTabStripSelectedIndexListener(navigationTabStrip.onTabStripSelectedIndexListener)
//
//        Log.i("TabIndex", navigationTabStrip.tabIndex.toString())


    }

    private fun tabClick(index: Int) {
        Log.i("TabIndex", index.toString())
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }


    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return when(item?.itemId){
           R.id.nav_share ->{
               shareSuccess()
//               Toast.makeText(this, "Share", Toast.LENGTH_LONG).show()
               true
           }

           else ->{
               NavigationUI.onNavDestinationSelected(item!!,
                   navController)
                       || super.onOptionsItemSelected(item)
           }
       }

    }
    private fun getShareIntent() : Intent {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_success_text))
        return shareIntent
    }
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }
    override fun onResume() {
        super.onResume()

        Timber.i("onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Timber.i("onPause Called")
    }

    override fun onStop() {
        super.onStop()
        Timber.i("onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Timber.i("onDestroy Called")
    }

    override fun onRestart() {
        super.onRestart()
        Timber.i("onRestart Called")
    }



}
