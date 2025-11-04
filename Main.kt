import kotlin.random.Random

fun main(){
    var balance=10000              // initial player balance
    val cash=balance               // store initial balance for final comparison

    var totalGames=0               // track number of games played
    var totalWin=0                 // track wins
    var totalLoss=0                // track losses
    var highestBalance=balance     // track highest balance reached
    var lowestBalance=balance      // track lowest balance reached
    var jackpotWon=0               // track jackpot count

    while(true){                   // main game loop
        println("\n1.play\n2.exit")
        println("choice:")
        val choice=readLine()!!.toInt()

        if(choice==1){             // player chooses to play a game

            totalGames++           // increment total games

            var guess=0            // initialize guess variable

            println("\ndifficulty(1.easy 2.medium 3.hard)")
            val diff=readLine()!!.toInt()   // select difficulty level

            println("place your bet:")
            val bet=readLine()!!.toInt()    // user enters bet amount

            if(bet>balance){                // check if bet exceeds balance
                println("you can't bet more than your balance....")
                continue
            }

            balance=balance-bet             // deduct bet from balance

            if(balance<lowestBalance){       // update lowest balance
                lowestBalance=balance
            }

            // difficulty range handling
            val low: Int
            val high: Int

            if (diff == 1) {                // EASY MODE: guess range 1–3
                low = 1
                high = 4
                println("you chose EASY MODE (enter your guess between 1 and 3):")
                println("place your guess number:")
                guess=readLine()!!.toInt()

                if(guess<low || guess>=high ){   // validate guess
                    println("guess is out of range!!!")
                    continue
                }

            } else if (diff == 2) {         // MEDIUM MODE: guess range 1–10
                low = 1
                high = 11
                println("you chose MEDIUM MODE (enter your guess between 1 and 10):")
                println("place your guess number:")
                guess=readLine()!!.toInt()

                if(guess<low || guess>=high ){
                    println("guess is out of range!!!")
                    continue
                }

            } else if (diff == 3) {         // HARD MODE: guess range 1–50
                low = 1
                high = 51
                println("you chose HARD MODE (enter your guess between 1 and 50):")
                println("place your guess number:")
                guess=readLine()!!.toInt()

                if(guess<low || guess>=high ){
                    println("guess is out of range!!!")
                    continue
                }

            } else {                        // invalid difficulty
                println("\ndefault is easy....")
                low = 1
                high = 4
            }

            val result = Random.nextInt(low, high)   // generate random number for result

            if(guess==result){                       // WINNING CONDITION
                println("you won the game!!!")

                balance=balance+bet*2                // award winnings
                println("you final balance${balance}")

                if(balance>highestBalance){          // update highest balance
                    highestBalance=balance
                }

                totalWin++                           // increment win count

                // jackpot feature (1% chance)
                val jackpot= Random.nextInt(1,101)
                if(jackpot==1){
                    println("congratulation!!! you have won  the JACKPOT...")
                    jackpotWon++
                    println("you earn 5 times your bet more")

                    balance=balance+bet*5            // add jackpot bonus
                    if(balance>highestBalance){
                        highestBalance=balance
                    }
                }

            } else {                                 // LOSING CONDITION
                println("the result is ${result}")
                println("your guess is ${guess}")
                println("you lost the game!!!")
                totalLoss++
                println("you final balance${balance}")
            }
        }

        if(choice==2){                               // exit game
            break
        }

        if(balance==0){                              // balance empty → offer recharge
            println("your are out of cash!!!")
            println("Do you wish to add amount?(1.yes OR 2.no)")
            val addMon=readLine()!!.toInt()

            if(addMon==1){                           // refill money
                println("how much you want to add:")
                val amount=readLine()!!.toInt()
                balance+=amount
            }

            if(addMon==2){                           // exit game
                break
            }
        }
    }

    // final summary printed after exiting loop
    println("your final standing ${balance}")

    if(cash>balance){
        println("you lost ${cash-balance} amount")
    }
    else{
        println("you earned ${balance- cash} amount")
    }

    //  game statistics
    println("Total Games Played:${totalGames}")
    println("Total Wins : ${totalWin}")
    println("Total Losses : ${totalLoss}")
    println("Total Jackpot Won : ${jackpotWon}")
    println("Highest Balance : ${highestBalance}")
    println("Lowest Balance : ${lowestBalance}")
}
