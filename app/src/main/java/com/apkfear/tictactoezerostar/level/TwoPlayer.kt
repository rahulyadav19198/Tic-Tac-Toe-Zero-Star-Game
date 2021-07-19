package com.apkfear.tictactoezerostar.level


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.apkfear.tictactoezerostar.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds


class TwoPlayer : AppCompatActivity() {


    private var currentPlay = 1
    private var turnChanger = 1
    private var gameState = arrayListOf<Int>(10,10,10,10,10,10,10,10,10,10)
    private var winLocation = arrayOf(arrayOf(0,1,2), arrayOf(3,4,5), arrayOf(6,7,8), arrayOf(0,3,6),
        arrayOf(1,4,7), arrayOf(2,5,8), arrayOf(2,4,6), arrayOf(0,4,8))
    private var gameOver:Boolean = false
    private lateinit var win:TextView
    private lateinit var btn:Button
    private lateinit var img1:ImageView
    private  lateinit var img2:ImageView
    private lateinit var img3:ImageView
    private lateinit var img4:ImageView
    private lateinit var img5:ImageView
    private lateinit var img6:ImageView
    private lateinit var img7:ImageView
    private lateinit var img8:ImageView
    private lateinit var img9:ImageView
    private lateinit var turn:TextView
    private lateinit var lineY1:ImageView
    private lateinit var lineY2:ImageView
    private lateinit var lineY3:ImageView
    private lateinit var lineRight:ImageView
    private lateinit var lineLeft:ImageView
    private lateinit var lineX1:ImageView
    private lateinit var lineX2:ImageView
    private lateinit var lineX3:ImageView
    private var draw:Int = 0
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private lateinit var mAdView : AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.player_two)

        win = findViewById(R.id.win)
        btn = findViewById(R.id.btn)
        img1 = findViewById(R.id.img1)
        img2 = findViewById(R.id.img2)
        img3 = findViewById(R.id.img3)
        img4 = findViewById(R.id.img4)
        img5 = findViewById(R.id.img5)
        img6 = findViewById(R.id.img6)
        img7 = findViewById(R.id.img7)
        img8 = findViewById(R.id.img8)
        img9 = findViewById(R.id.img9)
        turn = findViewById(R.id.turn)
        lineY1 = findViewById(R.id.lineY1)
        lineY2 = findViewById(R.id.lineY2)
        lineY3 = findViewById(R.id.lineY3)
        lineX1 = findViewById(R.id.lineX1)
        lineX2 = findViewById(R.id.lineX2)
        lineX3 = findViewById(R.id.lineX3)
        lineRight = findViewById(R.id.lineRight)
        lineLeft = findViewById(R.id.lineLeft)

        mAdView = findViewById(R.id.adView)

        MobileAds.initialize(this,getString(R.string.app_id_adds))

        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)


        mAdView.visibility= View.GONE


        mAdView.adListener = object : AdListener(){

            override fun onAdLoaded() {

                mAdView.visibility = View.VISIBLE
                super.onAdLoaded()
            }

        }



        btn.setOnClickListener {

            win.text = ""
            gameState = arrayListOf<Int>(10,10,10,10,10,10,10,10,10,10)
            gameOver = false
            currentPlay = turnChanger
            img1.setImageResource(0)
            img2.setImageResource(0)
            img3.setImageResource(0)
            img4.setImageResource(0)
            img5.setImageResource(0)
            img6.setImageResource(0)
            img7.setImageResource(0)
            img8.setImageResource(0)
            img9.setImageResource(0)
            lineY1.visibility = View.INVISIBLE
            lineY2.visibility = View.INVISIBLE
            lineY3.visibility = View.INVISIBLE
            lineLeft.visibility = View.INVISIBLE
            lineRight.visibility = View.INVISIBLE
            lineX1.visibility = View.INVISIBLE
            lineX2.visibility = View.INVISIBLE
            lineX3.visibility = View.INVISIBLE
            player1.clear()
            player2.clear()
            draw=0

            turn.setTextColor(Color.rgb(255,0,0))
            turn.text = getString(R.string.your_move)
            

        }

        turn.setTextColor(Color.rgb(255,0,0))
        turn.text = getString(R.string.your_move)

    }



    fun gameLogic(view: View){

        /*  Instead of craeting findViewById 9 times we declare view as Image view */
        val clickView:ImageView = view as ImageView

        /* Locate position where user click */
        val clickLocate:Int = Integer.parseInt(view.getTag().toString())

        playGame(clickLocate,clickView)

    }

    private fun playGame(clickLocate: Int, clickView: ImageView) {



        /* gameState is used to check user click on it or not or once user click any location then avoid reuseability it */
        /*  gameOver is used so once winner declare disable click */
        if(gameState[clickLocate] == 10 && gameOver == false) {

            /* currentPlay is used for user such as for circle 1 and for star 2 */
            /* 1  circle */
            /* 2 star   */

            gameState[clickLocate] = currentPlay

            draw+=1

            /* translationY is used to repositioning view on screen */
            clickView.translationY = -3000f
            if (currentPlay == 1) {
                clickView.setImageResource(R.drawable.circle)
                player1.add(clickLocate)

                currentPlay=2

                turn.setTextColor(Color.rgb(2,174,0))
                turn.text = getString(R.string.opponent)

            } else {

                clickView.setImageResource(R.drawable.star)

                player2.add(clickLocate)
                currentPlay = 1

                turn.setTextColor(Color.rgb(255,0,0))
                turn.text = getString(R.string.your_move)
            }
            /* to understand user some changes makes in ui and set View on previous position and setDuration is used to time taken to make changes*/
            clickView.animate().translationYBy(3000f).setDuration(10)

        }

        for(x in winLocation ){

            /*  check if any user win game */
            /*   gameState[x[0]] != 10)  is used so it wont declare winner if position is unselect */

            if(gameState[x[0]] == gameState[x[1]] && gameState[x[1]] == gameState[x[2]] && gameState[x[0]] != 10){


                if(x[0] == 0 && x[1] == 3 && x[2] == 6) {
                    lineY1.visibility = View.VISIBLE
                }

                if(x[0] == 1 && x[1] == 4 && x[2] == 7) {
                    lineY2.visibility = View.VISIBLE
                }

                if(x[0] == 2 && x[1] == 5 && x[2] == 8) {
                    lineY3.visibility = View.VISIBLE
                }

                if(x[0] == 2 && x[1] == 4 && x[2] == 6) {
                    lineRight.visibility = View.VISIBLE
                }

                if(x[0] == 0 && x[1] == 4 && x[2] == 8) {
                    lineLeft.visibility = View.VISIBLE
                }

                if(x[0] == 0 && x[1] == 1 && x[2] == 2) {
                    lineX1.visibility = View.VISIBLE
                }

                if(x[0] == 3 && x[1] == 4 && x[2] == 5) {
                    lineX2.visibility = View.VISIBLE
                }

                if(x[0] == 6 && x[1] == 7 && x[2] == 8) {
                    lineX3.visibility = View.VISIBLE
                }

                if(currentPlay == 1){

                    /*  1 is circle but we declare star as winner bcoz after set image we change current play*/

                    win.setTextColor(Color.rgb(2,174,0))
                    win.text = getString(R.string.star)
                    turnChanger = 2

                    turn.text = ""
                    draw = 0


                }

                if(currentPlay == 2){

                    /*  2 is star but we declare star as winner bcoz after set image we change current play*/
                    win.setTextColor(Color.rgb(255,0,0))
                    win.text = getString(R.string.circle)
                    turnChanger = 1
                    turn.text = ""
                    draw = 0

                }

                gameOver = true

            }

        }

        if(draw == 9){

            win.setTextColor(Color.rgb(48,173,255))
            win.text = getString(R.string.draw)
            turn.text = ""

        }

    }

}








