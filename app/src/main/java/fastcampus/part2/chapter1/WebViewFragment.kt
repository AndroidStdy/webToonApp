package fastcampus.part2.chapter1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import fastcampus.part2.chapter1.databinding.FragmentWebviewBinding

// Fragment 상속 후 생성자 호출 -> ()
class WebViewFragment(private val position: Int): Fragment() {
    private lateinit var binding: FragmentWebviewBinding //fragment_webview.xml
    private lateinit var callback:OnBackPressedCallback
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

        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://comic.naver.com/webtoon/detail?titleId=769209&no=128&week=wed")

        binding.btnBackToLast.setOnClickListener{

            val sharedPreference = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position","")
            if(url.isNullOrEmpty()){
                Toast.makeText(context,"마지막 저장 시점이 없습니다.",Toast.LENGTH_SHORT).show()
            }else{
                binding.webView.loadUrl(url)
            }

        }
    }

    //뒤로가기: onBackPressedDispatcher 사용
    override fun onResume() {
        super.onResume()
        callback = requireActivity().onBackPressedDispatcher.addCallback(this) {
            if(binding.webView.canGoBack()){
                binding.webView.goBack()
            }
            else{
                //더이상 뒤로 갈 수 없을 때 종료 다이얼로그 출력
                AlertDialog.Builder(requireContext()).apply {
                    setTitle("종료")
                    setMessage("앱을 종료하시겠습니까?")
                    setPositiveButton("네"){_,_->
                        requireActivity().finish()
                    }
                    setNegativeButton("아니오"){dialog,_->
                        dialog.dismiss()
                    }
                }.show()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        callback.remove()
    }

    /* onBackPressed 관련
    fun canGoBack(): Boolean{
        return binding.webView.canGoBack()
    }
    fun goBack(){
        binding.webView.goBack()
    }*/
    /* 생명주기 확인
    override fun onStart() {
        super.onStart()
    }
    override fun onStop() {
        super.onStop()
    }
    */
}