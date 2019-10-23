
fun main() {
    var krionel = Player(
            "Krionel",
            Stats(3,3,3,3,3),
            Weapon("blunt",3),
            Armor("Heavy",3))

    krionel.updateStats()
    println(krionel.armored)
    krionel.stats.strength -= 3
    krionel.updateStats()
    println(krionel.armored)
}
