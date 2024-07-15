package fastcampus.part2.chapter1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import fastcampus.part2.chapter1.databinding.FragmentWebviewBinding

// Fragment 상속 후 생성자 호출 -> ()
class WebViewFragment: Fragment() {
    private lateinit var binding: FragmentWebviewBinding //fragment_webview.xml

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { //Fragment에서 그려지는 View
        binding = FragmentWebviewBinding.inflate(inflater) //inflate 호출
        return binding.root //root -> fragment_webview

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.webViewClient = WebViewClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://google.com")
    }
    /* 생명주기 확인
    override fun onStart() {
        super.onStart()
    }
    override fun onStop() {
        super.onStop()
    }
    */
}