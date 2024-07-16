package fastcampus.part2.chapter1

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.view.isVisible

class WebtoonWebViewClient(
    private val progressBar:ProgressBar,
    private val saveData:(String) -> Unit,
): WebViewClient(){

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        //
        if(request != null && request.url.toString().contains("comic.naver.com/webtoon/detail")){
           //fragment, activity없는 상태에서 sharedPreference 가져오는 방법

            saveData(request.url.toString())
        }

        return super.shouldOverrideUrlLoading(view, request)
    }
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        // WebView 페이지 로드가 끝났을 때 ProgresBar가 사라짐
        progressBar.visibility = View.GONE // =progressBar.isVisible = false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE
    }
    /*
    //request를 확인 후 loading 유무 결정
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        //true: 현재 URL로드를 처리했으므로 Webview가 기본적으로 URL을 로드하지 않게 함
        //false: Webview가 기본적으로 URL을 로드하게 함

        //comic.naver.com을 포함한 URL외 주소는 접근이 안됨
        if(request != null && request.url.toString().contains("comic.naver.com"))
            return false
        else return true
    } */
    //https://comic.naver.com/webtoon/detail?titleId=769209&no=128&week=wed

    /*
    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)

        //에러가 발생했을 때 할 작업 ex)에러페이지를 띄워줌
    }*/
}