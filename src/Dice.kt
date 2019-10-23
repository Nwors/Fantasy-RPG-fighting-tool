
class Dice {
    companion object {
        fun roll(dicesCount: Int = 1, numOfFacets: Int = 6): Int {
            var value = 0;
            for (i in 1..dicesCount) {
                value += (1..numOfFacets).random();
            }
            return value
        }
    }
}