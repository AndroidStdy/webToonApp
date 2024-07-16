package fastcampus.part2.chapter1

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import fastcampus.part2.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, WebViewFragment())
                commit()
            } //fragment관리 기능

        }
        binding.btn2.setOnClickListener{
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, BFragment())
                commit()
            } //fragment관리 기능

        }
    }
    /*
    //더 이상 지원X
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.fragments[0]
        if(currentFragment is WebViewFragment){
            if(currentFragment.canGoBack()){
                currentFragment.goBack()
            }
            else{
                super.onBackPressed()
            }
        }
    } */
}