package com.hybcode.guessinggame

import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private val words = listOf("Android", "Activity", "Fragment", "Navigation", "Binding")
    private val secretWord = words.random().uppercase()
    var secretWordDisplay = ""
    private var correctGuesses = ""
    var incorrectGuesses = ""
    var livesLeft = 8

    init {
        secretWordDisplay = deriveSecretWordDisplay()
    }

    private fun deriveSecretWordDisplay(): String {
        var display = ""
        secretWord.forEach {
            display += checkLetter(it.toString())
        }
        return display
    }

    private fun checkLetter(letter: String) = when (correctGuesses.contains(letter)) {
        true -> letter
        false -> "_"
    }

    fun makeGuess(guess: String) {
        if (guess.length == 1){
            if (secretWord.contains(guess)){
                correctGuesses += guess
                secretWordDisplay = deriveSecretWordDisplay()
            }else{
                incorrectGuesses += guess
                livesLeft--
            }
        }
    }

    fun isWon() = secretWord.equals(secretWordDisplay, true)

    fun isLose() = livesLeft <= 0

    fun wonLoseMessage(): String {
        var message = ""
        if(isWon()) message = "You won!"
        else if (isLose()) message = "You lose!"
        message += " The word was $secretWord."
        return message
    }
}