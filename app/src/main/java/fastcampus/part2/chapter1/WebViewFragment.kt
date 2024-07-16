package fastcampus.part2.chapter1

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.EditText
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.addCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import fastcampus.part2.chapter1.databinding.FragmentWebviewBinding

// Fragment 상속 후 생성자 호출 -> ()
class WebViewFragment(private val position: Int, private val webViewUrl: String): Fragment() {

    var listener: OnTabLayoutNameChanged? = null

    private lateinit var binding: FragmentWebviewBinding //fragment_webview.xml
    private lateinit var callback:OnBackPressedCallback
    // const를 사용하기 위해서는 companion object 사용 필요
    companion object{
        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

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

        binding.webView.webViewClient = WebtoonWebViewClient(binding.progressBar){url ->
            activity?.getSharedPreferences("SHARED_PREFERENCE", Context.MODE_PRIVATE)?.edit {
                putString("tab$position", url)
            }
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(webViewUrl)

        binding.btnBackToLast.setOnClickListener{

            val sharedPreference = activity?.getSharedPreferences("SHARED_PREFERENCE", Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position","")
            if(url.isNullOrEmpty()){
                Toast.makeText(context,"마지막 저장 시점이 없습니다.",Toast.LENGTH_SHORT).show()
            }else{
                binding.webView.loadUrl(url)
            }
        }

        binding.btnChangeTabName.setOnClickListener{
            val dialog = AlertDialog.Builder(requireContext()) // context 사용 시, null 가능성 때문에 오류
            val editText = EditText(context)

            dialog.setView(editText)
            dialog.setPositiveButton("저장"){ _, _ ->
                activity?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)?.edit{
                    putString("tab${position}_name",editText.text.toString())
                    listener?.nameChanged(position,editText.text.toString())
                }

            }
            dialog.setNegativeButton("취소"){ dialogInterface, _ ->
                dialogInterface.cancel() // dialog 끄기

            }
            dialog.show()

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
    interface  OnTabLayoutNameChanged{
        fun nameChanged(position: Int, name: String)
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