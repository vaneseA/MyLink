package com.example.mylink.ui.fragment.main.setting.app_info

import android.content.Intent
import android.net.Uri
import com.example.mylink.BuildConfig
import com.example.mylink.R
import com.example.mylink.databinding.FragmentAppInfoBinding
import com.example.mylink.ui.component.SjUtil
import com.example.mylink.ui.fragment.basic.SjBasicFragment

class AppInfoFragment : SjBasicFragment<FragmentAppInfoBinding>() {
    override fun layoutId(): Int = R.layout.fragment_app_info

    override fun onCreateView() {

        //set binding variable
        val appInfoData = AppInfoData(appVersion = BuildConfig.VERSION_NAME)
        binding.data = appInfoData

        binding.githubTextView.setOnClickListener { startWebBrowser(appInfoData.githubAddress) }
        binding.icons8TextView.setOnClickListener { startWebBrowser(appInfoData.iconUrl) }

        binding.devContactTextView.setOnClickListener { sendMailToAddress(appInfoData.developerEmail) }
    }

    private fun sendMailToAddress(email: String) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/text"
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
        intent.putExtra(Intent.EXTRA_SUBJECT, "[MyLink 앱 문의] ")
        intent.putExtra(
            Intent.EXTRA_TEXT,
            """
  장애 및 오류 관련 문의일 경우 아래 간단한 폼을 채워주시면 빠른 해결에 도움이 됩니다!

<클라이언트 정보>
스마트폰 기기명 : 
안드로이드 버전 : 


<문제에 대해서>
언제 문제가 발생하나요? : 
증상이 어떻게 되나요? : 


            """.trimIndent()

        )
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent, "이메일 프로그램을 골라 주세요"))
    }

    private fun startWebBrowser(url: String) {
        if (SjUtil.checkUrlPrefix(url)) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } else {
            // string url is wrong
        }
    }
}

data class AppInfoData(
    val appVersion: String,
    val appName: String = "MyLink",
    val developerEmail: String = "spol@naver.com",
    val developerName: String = "박동근",
    val githubAddress: String = "https://github.com/vaneseA",
    val iconUrl: String = "https://icons8.com/"
)