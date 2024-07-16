package fastcampus.part2.chapter1

import android.content.Context
import android.os.Bundle
import android.view.Gravity
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.tabs.TabLayoutMediator
import fastcampus.part2.chapter1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), WebViewFragment.OnTabLayoutNameChanged {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreference = getSharedPreferences(WebViewFragment.Companion.SHARED_PREFERENCE, Context.MODE_PRIVATE)
        val tab0 = sharedPreference?.getString("tab0_name","월요일 웹툰")
        val tab1 = sharedPreference?.getString("tab1_name","화요일 웹툰")
        val tab2 = sharedPreference?.getString("tab2_name","수요일 웹툰")

        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ tab, position ->
            run {
//                val textView = TextView(this@MainActivity)
//                textView.text = when(position){
//                    0 -> tab0
//                    1 -> tab1
//                    else -> tab2
//                }
//                textView.gravity = Gravity.CENTER
//                //callback 기능 활용 가능
//
//                //위의 값들로 커스텀
//                tab.customView = textView
                tab.text = when(position){
                    0 -> tab0
                    1 -> tab1
                    else -> tab2
                }
            }
        }.attach()
    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name

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