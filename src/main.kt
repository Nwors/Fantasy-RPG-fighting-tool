
fun main() {
    var krionel =  Player(
        "Krionel",
        PlayerStats(3,3,3,3,3),
        equipment = listOf(Armor("Chain-mail","Heavy",5,true),
            Weapon("Club", "Blunt", 3, true),
            Armor("Chain-leggings", "Heavy", 5, true),
            Shield ("TheBarrier", "Shield", 3, false))
    )
    println(krionel.armour())
}
