package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , View.OnClickListener{

    private var PLAYER = true
    private var turncount = 0

    private var boardStatus=Array(3){IntArray(3)}

    private lateinit var board:Array<Array<Button>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        board= arrayOf(
            arrayOf(bt1,bt2,bt3),
            arrayOf(bt4,bt5,bt6),
            arrayOf(bt7,bt8,bt9),
        )
        for(i in board){
            for (bt1 in i){
bt1.setOnClickListener(this)
            }
        }
        initializeboardstatus()

        bt10.setOnClickListener{
            PLAYER=true
            turncount=0
            initializeboardstatus()
            Tv.text="Player X Turn"
        }
    }

    private fun initializeboardstatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j]=-1
            }
        }
        for(i in board){
            for(button in i){
                button.isEnabled=true
                button.text=""
            }
        }
    }


    override fun onClick(view: View) {
        when(view.id){
            R.id.bt1->{
                updateValue(row=0,col=0,PLAYER)
            }
            R.id.bt2->{
                updateValue(row=0,col=1,PLAYER)
            }
            R.id.bt3->{
                updateValue(row=0,col=2,PLAYER)
            }
            R.id.bt4->{
                updateValue(row=1,col=0,PLAYER)
            }
            R.id.bt5->{
                updateValue(row=1,col=1,PLAYER)
            }
            R.id.bt6->{
                updateValue(row=1,col=2,PLAYER)
            }
            R.id.bt7->{
                updateValue(row=2,col=0,PLAYER)
            }
            R.id.bt8->{
                updateValue(row=2,col=1,PLAYER)
            }
            R.id.bt9->{
                updateValue(row=2,col=2,PLAYER)
            }
        }
        turncount++
        PLAYER=!PLAYER
        if(PLAYER){
            updateDisplay("Player X Turn")
        }
        else{
            updateDisplay("Player O Turn")
        }
        if(turncount==9){
            updateDisplay("Game Draw")
        }
        checkwinner()
    }

    private fun checkwinner() {
        //Horizontal Rows
        for(i in 0..2){
            if(boardStatus[i][0]==boardStatus[i][1] && boardStatus[i][0]==boardStatus[i][2]){
                if(boardStatus[i][0]==1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardStatus[i][0]==0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        //Vertical Winner
        for(i in 0..2){
            if(boardStatus[0][i]==boardStatus[1][i] && boardStatus[0][i]==boardStatus[2][i]){
                if(boardStatus[0][i]==1){
                    updateDisplay("Player X Winner")
                    break
                }else if(boardStatus[0][i]==0){
                    updateDisplay("Player O Winner")
                    break
                }
            }
        }
        //First Diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[0][0] == boardStatus[2][2]){
            if(boardStatus[0][0]==1){
                updateDisplay("Player X Winner")
            }else if(boardStatus[0][0]==0){
                updateDisplay("Player O Winner")
            }
        }
        //Second Diagonal
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[0][2] == boardStatus[2][0]){
            if(boardStatus[0][2]==1){
                updateDisplay("Player X Winner")
            }else if(boardStatus[0][2]==0){
                updateDisplay("Player O Winner")
            }
        }
    }

    private fun updateDisplay(text: String) {
        Tv.text=text
        if(text.contains("Winner")){
            disableButton()
        }
    }
private fun disableButton(){
    for(i in board){
        for(button in i){
            button.isEnabled=false
        }
    }
}

    private fun updateValue(row: Int, col: Int, player: Boolean) {
val text=if(player)"X" else "O"
        val value=if(player) 1 else 0
        board[row][col].apply{
            isEnabled=false
            setText(text)
        }
        boardStatus[row][col]=value

    }
}