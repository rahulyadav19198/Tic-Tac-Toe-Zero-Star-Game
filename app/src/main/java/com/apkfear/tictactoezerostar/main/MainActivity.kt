package com.apkfear.tictactoezerostar.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RelativeLayout
import com.apkfear.tictactoezerostar.R
import com.apkfear.tictactoezerostar.level.DifficultLevel
import com.apkfear.tictactoezerostar.level.EasyLevel
import com.apkfear.tictactoezerostar.level.TwoPlayer
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

class MainActivity : AppCompatActivity() {

    lateinit var multi:Button
    lateinit var easy:Button
    lateinit var hard:Button
    private lateinit var progressLayout: RelativeLayout
    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        multi = findViewById(R.id.multi)
        easy = findViewById(R.id.easy)
        hard = findViewById(R.id.hard)
        progressLayout=findViewById(R.id.progressLayout)

        MobileAds.initialize(this,getString(R.string.app_id_adds))
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = getString(R.string.interstitial_id)

        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object: AdListener() {

            override fun onAdFailedToLoad(errorCode: Int) {

                Log.d("TAG", "The interstitial wasn't loaded yet.")
            }

            override fun onAdClosed() {
                // Code to be executed when the interstitial ad is closed.

                mInterstitialAd.loadAd(AdRequest.Builder().build())
                open()

            }
        }


        Handler().postDelayed({
            progressLayout.visibility = View.INVISIBLE

        }, 2000)

        multi.setOnClickListener {

            val intent = Intent(this,
                TwoPlayer::class.java)
            startActivity(intent)
        }

        easy.setOnClickListener {

            open()
        }

        hard.setOnClickListener {

            val intent = Intent(this,
                DifficultLevel::class.java)
            startActivity(intent)
        }


    }

    fun open()
    {

        if(mInterstitialAd.isLoaded){
            mInterstitialAd.show()


        }
        else {
            val intent=Intent(this@MainActivity, EasyLevel::class.java)
            startActivity(intent)

        }

    }

    override fun onBackPressed() {
        finishAffinity()
        super.onBackPressed()
    }
}