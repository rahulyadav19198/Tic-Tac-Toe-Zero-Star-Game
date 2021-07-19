package com.apkfear.tictactoezerostar.level

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.apkfear.tictactoezerostar.R
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.util.*
import kotlin.collections.ArrayList

class DifficultLevel : AppCompatActivity() {

    private var currentPlay = 1
    private var gameState = arrayListOf<Int>(10,10,10,10,10,10,10,10,10)
    private var winLocation = arrayOf(arrayOf(0,1,2), arrayOf(3,4,5), arrayOf(6,7,8), arrayOf(0,3,6),
        arrayOf(1,4,7), arrayOf(2,5,8), arrayOf(2,4,6), arrayOf(0,4,8))
    private var gameOver:Boolean = false
    private lateinit var win: TextView
    private lateinit var btn: Button
    private lateinit var img1: ImageView
    private lateinit var img2: ImageView
    private lateinit var img3: ImageView
    private lateinit var img4: ImageView
    private lateinit var img5: ImageView
    private lateinit var img6: ImageView
    private lateinit var img7: ImageView
    private lateinit var img8: ImageView
    private lateinit var img9: ImageView
    private lateinit var turn2:TextView
    private lateinit var lineY1:ImageView
    private lateinit var lineY2:ImageView
    private lateinit var lineY3:ImageView
    private lateinit var lineRight:ImageView
    private lateinit var lineLeft:ImageView
    private lateinit var lineX1:ImageView
    private lateinit var lineX2:ImageView
    private lateinit var lineX3:ImageView
    private var turnChanger = 1
    private var draw:Int = 0
    private var m=1
    private var first = 1
    private var player1 = ArrayList<Int>()
    private var player2 = ArrayList<Int>()
    private lateinit var mAdView : AdView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_difficult_level)

        win = findViewById(R.id.win2)
        btn = findViewById(R.id.btn2)
        img1 = findViewById(R.id.img21)
        img2 = findViewById(R.id.img22)
        img3 = findViewById(R.id.img23)
        img4 = findViewById(R.id.img24)
        img5 = findViewById(R.id.img25)
        img6 = findViewById(R.id.img26)
        img7 = findViewById(R.id.img27)
        img8 = findViewById(R.id.img28)
        img9 = findViewById(R.id.img29)
        turn2 = findViewById(R.id.turn2)
        lineY1 = findViewById(R.id.lineY1)
        lineY2 = findViewById(R.id.lineY2)
        lineY3 = findViewById(R.id.lineY3)
        lineX1 = findViewById(R.id.lineX1)
        lineX2 = findViewById(R.id.lineX2)
        lineX3 = findViewById(R.id.lineX3)
        lineRight = findViewById(R.id.lineRight)
        lineLeft = findViewById(R.id.lineLeft)
        mAdView = findViewById(R.id.adView2)

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
            gameState = arrayListOf<Int>(10,10,10,10,10,10,10,10,10)
            gameOver = false
            currentPlay = 1
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
            m=1
            first =1

            if(turnChanger ==1) {
                autoPlay(gameState)
            }

            turn2.setTextColor(Color.rgb(255,0,0))
            turn2.text =getString(R.string.your_move)


        }

        if(turnChanger ==1) {
            autoPlay(gameState)
        }

        turn2.setTextColor(Color.rgb(255,0,0))
        turn2.text =getString(R.string.your_move)

    }

    fun gameLogic2(view: View){

        /*  Instead of creating findViewById 9 times we declare view as Image view */
        val clickView:ImageView = view as ImageView

        /* Locate position where user click */
        val clickLocate:Int = Integer.parseInt(view.getTag().toString())



        playGame(clickLocate,clickView)


    }

    private fun playGame(clickLocate: Int, clickView: ImageView) {

        if(gameState[clickLocate] == 10 && !gameOver) {

            gameState[clickLocate] = currentPlay

            draw+=1

            clickView.translationY = -3000f

            if (currentPlay == 1) {

                clickView.setImageResource(R.drawable.circle)
                player1.add(clickLocate)

                currentPlay=2



            } else {

                clickView.setImageResource(R.drawable.star)

                player2.add(clickLocate)
                currentPlay = 1


                Handler().postDelayed({

                    if (currentPlay == 1) {
                        if (m == 1 || m == 2 || m == 3 || m == 4 || m == 5) {
                            m += 1

                            autoPlay(gameState)
                        }

                    }
                },500)

            }
            clickView.animate().translationYBy(3000f).setDuration(10)

        }

        for(x in winLocation ){

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

                    win.text = getString(R.string.star)
                    turnChanger = 2
                    turn2.text = ""
                    draw = 0
                }

                if(currentPlay == 2){

                    win.text = getString(R.string.circle)
                    turnChanger = 1
                    turn2.text = ""
                    draw = 0
                }

                gameOver = true

            }

        }

        if(draw == 9){

            win.text = getString(R.string.draw)
            turn2.text = ""
        }

    }

    private fun autoPlay(gameState:ArrayList<Int>){


        var move = 0

        if(first == 1){
            val list = arrayOf(0,2,4,6,8)

            val r = Random()

            val y = list[r.nextInt(list.size)]

            move = y
            first+=1
        }
        else {
         move = findBestMove(gameState)

        }

        val id = move

        val clickVi:ImageView?

        when(id){

            0 -> clickVi = img1
            1 -> clickVi = img2
            2 -> clickVi =img3
            3 -> clickVi =img4
            4 -> clickVi =img5
            5 -> clickVi =img6
            6 -> clickVi =img7
            7 -> clickVi =img8
            8 -> clickVi =img9
            else -> clickVi = img1

        }

        for(x in winLocation ){



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

                    win.text = getString(R.string.star)
                    turnChanger = 2
                    turn2.text = ""
                    draw = 0
                }

                if(currentPlay == 2){

                    win.text = getString(R.string.circle)
                    turnChanger = 1
                    turn2.text = ""
                    draw = 0
                }

                gameOver = true
            }

        }
        playGame(id,clickVi)
    }


  private fun findBestMove(gameState:ArrayList<Int>):Int{

       var bestVal = Integer.MIN_VALUE
       var posi = 0

           for (i in 0..8) {

               // Check if cell is empty
               if (gameState[i] == 10) {

                   gameState[i]=1

                   val moveVal: Int = minMax(gameState, 0, false)

                   gameState[i] = 10

                   if (moveVal > bestVal) {

                       bestVal = moveVal

                       posi = i
                   }

               }

           }
       return posi
    }

    private fun minMax(gameState: ArrayList<Int>, depth:Int, isMax:Boolean):Int {

        val score: Int = evaluate(gameState)

        if (score == 5) return score


        if (score == -5) return score


        if (!isMoveLeft()) return 0


         if (isMax) {
            var best = Integer.MIN_VALUE

            for (i in 0..8) {

                    if (gameState[i] == 10) {

                        gameState[i]=1

                        val good = minMax(gameState, depth + 1, false)

                        gameState[i] = 10

                        best = Math.max(good,best)
                    }
            }
            return best
        } else {
            var best = Integer.MAX_VALUE

            for (i in 0..8) {
                    // Check if cell is empty
                    if (gameState[i] == 10) {

                        gameState[i]=2

                        val score1 = minMax(gameState, depth + 1, true)

                        gameState[i] = 10

                        best = Math.min(best,score1)

                    }
            }
           return best
        }

    }

   private fun evaluate(gameState: ArrayList<Int>):Int {

        for(x in winLocation ){

            if(gameState[x[0]] == gameState[x[1]] && gameState[x[1]] == gameState[x[2]] && gameState[x[0]] != 10){

                if(gameState[x[0]] == 1){
                    return 5
                }

                if(gameState[x[0]] == 2){

                    return -5

                }

            }

        }
        return 0

    }

   private fun isMoveLeft():Boolean{

        for(i in 0..8){

            if(!(player1.contains(i) || player2.contains(i))){

                return true
            }
        }

        return false

    }

}
