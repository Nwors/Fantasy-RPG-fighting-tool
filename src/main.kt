
fun main() {
    var krionel = Player(
            "Krionel",
            Stats(3,3,3,3,3),
            Weapon("Hammer", "blunt",3),
            Armor("Chain-mail", "Heavy",3),
            shield = Armor("Basic shield", "Shield",2))

    krionel.updateStats()
    println(krionel.armour)
    krionel.stats.strength -= 3
    krionel.updateStats()
    println(krionel.armour)
}
