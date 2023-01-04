package machine

class CoffeeMachine(
    private var water : Int = 400,
    private var milk : Int = 540,
    private var beans : Int = 120,
    private var cups : Int = 9,
    private var cash : Int = 550) {

    var isRunning: Boolean = false;

    fun printState() {
        println("The coffee machine has:\n" +
                "$water ml of water\n" +
                "$milk ml of milk\n" +
                "$beans g of coffee beans\n" +
                "$cups disposable cups\n" +
                "\$$cash of money")
    }

    fun printActions() {
        isRunning = true
        while (isRunning) {
            println("Write action (buy, fill, take, remaining, exit):")
            when(readln()) {
                "buy" -> buyAction()
                "fill" -> fillAction()
                "take" -> takeAction()
                "remaining" -> printState()
                "exit" -> isRunning = false
            }
        }
    }


    private fun buyAction() {
        println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ")
        when(readln()) {
            "1" -> {
                checkResources(250, needBeans = 16, addCash = 4)
            }
            "2" -> {
                checkResources(350,75,20,7)
            }
            "3" -> {
                checkResources(200,100,12,6)
            }
            "back" -> return
        }
    }

    private fun checkResources(needWater: Int,
                               needMilk: Int = 0,
                               needBeans: Int,
                               addCash: Int,
                               needCups: Int = 1) {
        if(water >= needWater &&
            milk >= needMilk &&
            beans >= needBeans &&
            cups >= needCups) {
            println("I have enough resources, making you a coffee!")
            this.water -= needWater
            this.milk -= needMilk
            this.beans -= needBeans
            this.cash += addCash
            this.cups -= needCups
        } else if (water < needWater) {
            println("Sorry, not enough water!")
        } else if (milk < needMilk) {
            println("Sorry, not enough milk!")
        } else if (beans < needBeans) {
            println("Sorry, not enough coffee beans!")
        } else if (cups < needCups) {
            println("Sorry, not enough cups!")
        } else {
            return
        }
    }

    private fun fillAction() {
        println("Write how many ml of water you want to add:")
        val addWater = readln().toInt()
        this.water += addWater
        println("Write how many ml of milk you want to add:")
        val addMilk = readln().toInt()
        this.milk += addMilk
        println("Write how many grams of coffee beans you want to add:")
        val addBeans = readln().toInt()
        this.beans += addBeans
        println("Write how many disposable cups you want to add:")
        val addCups = readln().toInt()
        this.cups += addCups
    }

    private fun takeAction() {
        println("I gave you \$${this.cash}")
        this.cash -= this.cash
    }
}

fun main() {
    val machine = CoffeeMachine()
    machine.printActions()
}







