package fastcampus.part2.chapter1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import fastcampus.part2.chapter1.databinding.FragmentSecondBinding
import fastcampus.part2.chapter1.databinding.FragmentWebviewBinding

class BFragment: Fragment()  {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View { //Fragment에서 그려지는 View
        binding = FragmentSecondBinding.inflate(inflater) //inflate 호출
        return binding.root //root -> fragment_webview

    }
}