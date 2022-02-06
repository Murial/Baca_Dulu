package com.bacadulu.bacaduluapps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bacadulu.bacaduluapps.databinding.ActivityHomeBinding
import com.bacadulu.bacaduluapps.fragments.FavoriteFragment
import com.bacadulu.bacaduluapps.fragments.HistoryFragment
import com.bacadulu.bacaduluapps.fragments.HomeFragment
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        val binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val HomeFragment = HomeFragment()
        val FavoriteFragment = FavoriteFragment()
        val HistoryFragment = HistoryFragment()

        makeCurrentFragment(HomeFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.ic_home -> makeCurrentFragment(HomeFragment)
                R.id.ic_favorite -> makeCurrentFragment(FavoriteFragment)
                R.id.ic_history -> makeCurrentFragment(HistoryFragment)
                R.id.ic_logout -> btnLogoutClicked()
            }
            true
        }
    }

    private fun btnLogoutClicked() {
        auth.signOut()
        Intent(this@HomeActivity, LoginActivity::class.java).also {
            it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(it)
        }
    }

    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }
    }
}