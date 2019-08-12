package com.kasiopec.robotcontroller

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.regex.Matcher
import java.util.regex.Pattern

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Setting up example one fields
        btnExOne.setOnClickListener{
            editTextRoomX.setText("5")
            editTextRoomY.setText("5")
            editTextStartX.setText("1")
            editTextStartY.setText("2")
            editTextSide.setText("N")
            editTextPath.setText("RFRFFRFRF")


        }
        //setting up example two fields
        btnExTwo.setOnClickListener{
            editTextRoomX.setText("5")
            editTextRoomY.setText("5")
            editTextStartX.setText("0")
            editTextStartY.setText("0")
            editTextSide.setText("E")
            editTextPath.setText("RFLFFLRF")
        }

        //launching our robot
        btnStart.setOnClickListener{

            // Side at which robot looks at
            val side = editTextSide.text.toString().toUpperCase()
            //Path and actions string we enter for the robot
            val path = editTextPath.text.toString().toUpperCase()
            //Patterns to check for wrong letters in side editText
            val pattern : Pattern = Pattern.compile("[^ENSW]")
            //Pattern for path command
            val pattern2 : Pattern = Pattern.compile("[^LRF]")

            val matcher : Matcher = pattern.matcher(side)
            val mathcer2 : Matcher = pattern2.matcher(path)

            //checking so that side field doesn't contain letters expect world side ones
            if(matcher.find() || side.length > 1){
                Toast.makeText(this, "Wrong direction entered", Toast.LENGTH_SHORT).show()
            //checking for path string, so it only contains predefined movement letters
            }else if(mathcer2.find()){
                Toast.makeText(this, "Wrong control command, check your path string", Toast.LENGTH_SHORT).show()
            }else if (editTextRoomX.text.toString()=="" || editTextRoomY.text.toString()==""){
                Toast.makeText(this, "Please enter size of the field", Toast.LENGTH_SHORT).show()
            }else if (editTextStartX.text.toString() == "" || editTextStartY.text.toString()=="") {
                Toast.makeText(this, "Please enter start position", Toast.LENGTH_SHORT).show()
            }else if (editTextSide.text.toString() == "") {
                Toast.makeText(this, "Please enter side robot looks at", Toast.LENGTH_SHORT).show()
            }else if (editTextPath.text.toString()=="") {
                Toast.makeText(this, "Please enter robot path string", Toast.LENGTH_SHORT).show()
            }else{
                //starting position of the robot X Y coords
                val startX = Integer.parseInt(editTextStartX.text.toString())
                val startY = Integer.parseInt(editTextStartY.text.toString())
                //Setting size of the room, so robot can't go outside
                val roomMaxX = Integer.parseInt(editTextRoomX.text.toString())
                val roomMaxY = Integer.parseInt(editTextRoomY.text.toString())

                //creating robot with all the necessary data if check is OK
                val myRobot = Robot(side, startX, startY, path, roomMaxX, roomMaxY)
                //Showing results in a toast message
                val result = myRobot.move()
                Toast.makeText(this, result, Toast.LENGTH_SHORT).show()
            }

        }
    }
}
