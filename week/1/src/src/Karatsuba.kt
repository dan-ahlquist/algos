import java.lang.IllegalArgumentException
import java.lang.Integer.max

class Karatsuba: Multiplier {

    override fun multiply(x: String, y: String): String {
        if(x.length > 64 || y.length > 64) throw IllegalArgumentException("Too long input string length. x->${x.length} y->${y.length}")

        val (x_, y_) = format(x, y)

        if(x_.length != y_.length) throw IllegalArgumentException("Unequal input string length. x->${x_.length} y->${y_.length}")

        // base case: one-digit numbers
        if(x_.length <= 1 && y_.length <= 1) {
            return (x_.toInt() * y_.toInt()).toString()
        }

        val a = firstHalf(x_)
        val b = secondHalf(x_)
        val c = firstHalf(y_)
        val d = secondHalf(y_)

        val p = a.toBigInteger() + b.toBigInteger()
        val q = c.toBigInteger() + d.toBigInteger()

        val ac = multiply(a, c)
        val bd = multiply(b, d)
        val pq = multiply(p.toString(), q.toString())

        val adbc = pq.toBigInteger() - ac.toBigInteger() - bd.toBigInteger()

        val n = x_.length
        val t = ac.timesPowerOfTen(n).toBigInteger()
        val u = adbc.toString().timesPowerOfTen(n/2).toBigInteger()
        val v = bd.toBigInteger()

        return (t+u+v).toString()
    }

    private fun format(x: String, y: String): Pair<String, String> {
        val greaterLength = max(x.length, y.length)
        val desiredLength = nextPowerOf2(greaterLength)

        val x_ = x.padStart(desiredLength, '0')
        val y_ = y.padStart(desiredLength, '0')

        return Pair(x_, y_)
    }

    private fun nextPowerOf2(n: Int): Int {
        val powerOf2Lengths = listOf(1,2,4,8,16,32,64)
        for (result in powerOf2Lengths) {
            if (result >= n) return result
        }
        return 64
    }

    private fun firstHalf(n: String): String {
        val m = n.length / 2
        return n.substring(0, m)
    }

    private fun secondHalf(n: String): String {
        val m = n.length / 2
        return n.substring(m) // end is implied
    }
}

fun String.timesPowerOfTen(n: Int): String {
    return this + "0".repeat(n)
}