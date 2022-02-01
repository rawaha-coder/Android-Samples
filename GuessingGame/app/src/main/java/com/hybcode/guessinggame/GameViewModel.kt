package com.hybcode.guessinggame

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel: ViewModel() {

    private val words = listOf("Android", "Activity", "Fragment", "Navigation", "Binding")
    private val secretWord = words.random().uppercase()
    private var correctGuesses = ""

    //var secretWordDisplay = ""
    val secretWordDisplay = MutableLiveData<String>()

    //var incorrectGuesses = ""
    val incorrectGuesses = MutableLiveData<String>("")

    //var livesLeft = 8
    val livesLeft = MutableLiveData<Int>(8)

    init {
        secretWordDisplay.value = deriveSecretWordDisplay()
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
                secretWordDisplay.value = deriveSecretWordDisplay()
            }else{
                incorrectGuesses.value += guess
                livesLeft.value = livesLeft.value?.minus(1)
            }
        }
    }

    fun isWon() = secretWord.equals(secretWordDisplay.value, true)

    fun isLose() = livesLeft.value ?: 0 <= 0

    fun wonLoseMessage(): String {
        var message = ""
        if(isWon()) message = "You won!"
        else if (isLose()) message = "You lose!"
        message += " The word was $secretWord."
        return message
    }
}