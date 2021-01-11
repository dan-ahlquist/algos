import java.io.File

/*
In this programming problem and the next you'll code up the greedy algorithm from the lectures on Huffman coding.

Download the text file below.
huffman.txt

This file describes an instance of the problem. It has the following format:
### Removed by me - 1000 ###[number_of_symbols]
[weight of symbol #1]
[weight of symbol #2]
...

For example, the third line of the file is "6852892," indicating that the weight of the second symbol of the alphabet is
6852892.  (We're using weights instead of frequencies, like in the "A More Complex Example" video.)

Your task in this problem is to run the Huffman coding algorithm from lecture on this data set. What is the maximum
length of a codeword in the resulting Huffman code?

ADVICE: If you're not getting the correct answer, try debugging your algorithm using some small test cases. And then
post them to the discussion forum!
 */

const val filename = "huffman.txt"

fun main() {
    val symbols = readInput(filename)
    println(symbols.firstOrNull())
}

fun readInput(filename: String): List<Symbol> {
    val result = mutableListOf<Symbol>()
    var index = 1
    File(filename).forEachLine { frequency ->
        result.add(Symbol(index.toString(), frequency.toInt()))
        index++
    }
    println("Read in ${result.size} symbols.")
    return result
}
