package gold

import java.io.BufferedReader
import java.io.InputStreamReader
import kotlin.math.max

var n: Int = 0
lateinit var playersInningResult: Array<List<Int>>
var ans = 0
val playerList = Array<Int>(10) {0}
val v = Array(10) { false }

fun baseball(): Int {
    var score = 0
    var curPlayer = 1
    for (i in 0 until n) {
        var outCount = 0
        var base = Array(4) { false }
        //타순
        while (outCount < 3) {
            when (playersInningResult[i][playerList[curPlayer]-1]) {
                0 -> outCount++
                1 -> {
                    if (base[3]) {
                        score++
                        base[3] = false
                    }
                    if (base[2]) {
                        base[3] = true
                        base[2] = false
                    }
                    if (base[1]) {
                        base[2] = true
                        base[1] = false
                    }
                    base[1] = true
                }

                2 -> {
                    if (base[3]) {
                        score++
                        base[3] = false
                    }
                    if (base[2]) {
                        score++
                        base[2] = false
                    }
                    if (base[1]) {
                        base[3] = true
                        base[1] = false
                    }
                    base[2] = true
                }

                3 -> {
                    if (base[3]) {
                        score++
                        base[3] = false
                    }
                    if (base[2]) {
                        score++
                        base[2] = false
                    }
                    if (base[1]) {
                        score++
                        base[1] = false
                    }
                    base[3] = true
                }

                4 -> {
                    if (base[3]) {
                        score++
                        base[3] = false
                    }
                    if (base[2]) {
                        score++
                        base[2] = false
                    }
                    if (base[1]) {
                        score++
                        base[1] = false
                    }
                    score++
                }
            }
            if (curPlayer == 9) curPlayer = 1
            else curPlayer++
        }
    }
    return score
}

fun dfs(p: Int) {
    if (p == 10) {
        ans = max(ans, baseball())
        return
    }

    for (i in 1..9) {
        if (!v[i]) {
            playerList[i] = p;
            v[i] = true
            dfs(p+1)
            v[i] = false;
            playerList[i] = 0;
        }
    }

}

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
    n = readLine()!!.toInt()
    playersInningResult = Array(n) {
        readLine()!!.split(" ").map { it.toInt() }
    }
    playerList[4] = 1
    v[4] = true
    dfs(2)

    print(ans)
}