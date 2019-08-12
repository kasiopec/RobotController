package com.kasiopec.robotcontroller

class Robot (side: String, x: Int, y: Int, val path: String, val maxX: Int, val maxY: Int){
    //initializing values
    var robotSide = side
    var posX = x
    var posY = y
    var result : String = ""

    init{
    }
    //method to move robot
    fun move(): String{
        //for loop to loop through path string
        outer@ for(letter in path.toUpperCase()){
            //checking for path letters
            when(letter){
                'R'-> turnRight(robotSide)
                'L' -> turnLeft(robotSide)
                //in case stepForward returns false we are breaking the loop and sending
                // notification that we reached the end of the field
                'F' -> if(!stepForward()){
                    result = "Robot out of power, check your path string"
                    break@outer
                }
            }
            result = "Robot at: $posX $posY $robotSide"

        }
        return result
    }


    //method for making a step forward with field boundaries check
    private fun stepForward(): Boolean{
        if(robotSide == "E" && posX<maxX){
            posX +=1
        }else if(robotSide == "W" && posX>0){
            posX -=1
        }else if(robotSide == "N" && posY>0){
            posY -=1
        }else if (robotSide == "S" && posY<maxY){
            posY +=1
        }else{
            return false
        }
        return true
    }
    //method to make left turn
    private fun turnLeft(s: String){
        when(s){
            "N" -> robotSide = "W"
            "W" -> robotSide = "S"
            "S" -> robotSide = "E"
            "E" -> robotSide = "N"

        }
    }
    //method to make right turn
    private fun turnRight(s: String){
        when(s){
            "N" -> robotSide = "E"
            "E" -> robotSide = "S"
            "S" -> robotSide = "W"
            "W" -> robotSide = "N"
        }
    }



}

