package fastcampus.part2.chapter1

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import androidx.core.view.isVisible

class WebtoonWebViewClient(private val progressBar:ProgressBar): WebViewClient(){
    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        // WebView 페이지 로드가 끝났을 때 ProgresBar가 사라짐
        progressBar.visibility = View.GONE // =progressBar.isVisible = false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)

        progressBar.visibility = View.VISIBLE
    }

    /*override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)

        //에러가 발생했을 때 할 작업 ex)에러페이지를 띄워줌
    }*/
}