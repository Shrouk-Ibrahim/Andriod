class BankAccountProject {
    var accountBalance = (0..1000).random() // Initial random account balance between 0 and 1000.

    fun start() {
        // Start of the banking system program loop.
        while (true) {
            println("\n-------------------------------")
            println("Welcome to your Banking System")
            println("-------------------------------")
            println("What type of account would you like to create?")
            println("1 - Debit account")
            println("2 - Credit account")
            println("3 - Checking account")
            print("Choose an option (1, 2, or 3): ")

            // Variable to capture the user’s choice of account type.
            var userChoice: Int? = null
            var retryCount = 0
            // Loop to ensure valid input for account type.
            while (userChoice == null || userChoice !in 1..3) {
                if (retryCount >= 3) {
                    // If there are 3 invalid attempts, exit the program.
                    println("Too many invalid attempts. Exiting program.")
                    return
                }
                // Read user input and convert it to integer.
                userChoice = readLine()?.toIntOrNull()
                // Check if the input is valid (1, 2, or 3).
                if (userChoice == null || userChoice !in 1..3) {
                    println("Invalid input. Please choose 1, 2, or 3.")
                    retryCount++ // Increment retry count for invalid inputs.
                }
            }

            // Determine the type of account based on user choice.
            val accountType = when (userChoice) {
                1 -> "Debit"
                2 -> "Credit"
                3 -> "Checking"
                else -> "Unknown" // Default case if something goes wrong.
            }

            println("\nYou have created a $accountType account.")
            println("-------------------------------")
            println("The current balance is $accountBalance dollars.")
            println("-------------------------------")

            var continueOperations = true
            // Loop for banking operations (deposit or withdrawal).
            while (continueOperations) {
                println("Would you like to make a deposit or withdrawal? (deposit/withdraw):")
                var operation: String? = readLine()?.lowercase() // Read the operation choice from user input.

                if (operation != "deposit" && operation != "withdraw") {
                    println("Invalid operation! Please enter 'deposit' or 'withdraw'.\n")
                    continue
                }

                print("Enter amount: ")
                // Read the amount for deposit or withdrawal.
                val money: Int? = readLine()?.toIntOrNull()

                // Validate the amount to be positive and not null.
                if (money == null || money <= 0) {
                    println("Invalid amount! Please enter a valid positive number.\n")
                    continue
                }

                // Perform operation based on user input (deposit or withdraw).
                when (operation) {
                    "deposit" -> {
                        println("\nYou are depositing $money dollars.")
                        creditDeposit(money) // Call credit deposit method for deposit.
                    }
                    "withdraw" -> {
                        println("\nYou are withdrawing $money dollars.")
                        debitWithdraw(money) // Call debit withdraw method for withdrawal.
                    }
                    else -> {
                        println("Invalid input or amount! Please try again.\n")
                    }
                }

                println("\nWould you like to make another operation? (yes/no):")
                // Ask user if they want to perform another operation.
                val continueChoice = readLine()?.lowercase()
                continueOperations = continueChoice == "yes" // Continue if the user answers "yes".
            }

            println("\nWould you like to create another account? (yes/no):")
            val exitChoice = readLine()?.lowercase() // Ask if user wants to create another account.
            if (exitChoice == "no") {
                // Exit message if user chooses "no".
                println("\nThank you for using the banking system!")
                break // Exit the main loop and terminate the program.
            }
        }
    }

    // Method to handle withdrawals from a debit account.
    fun debitWithdraw(amount: Int): Int {
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!") // Cannot withdraw if balance is zero.
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The current balance is $accountBalance dollars.")
            return 0 // Insufficient funds for the withdrawal.
        } else {
            accountBalance -= amount // Subtract withdrawal amount from balance.
            println("You successfully withdrew $amount dollars. The new balance is $accountBalance dollars.")
            return amount
        }
    }

    // Method to handle deposits to a credit account.
    fun creditDeposit(amount: Int): Int {
        if (accountBalance == 0) {
            println("This account is completely paid off! No deposit needed!") // No deposit needed if account is paid off.
            return 0
        } else if (amount == -accountBalance) {
            accountBalance = 0 // If deposit equals the negative balance, account is fully paid off.
            println("You have fully paid off this account! The balance is now $accountBalance dollars.")
            return amount
        } else {
            accountBalance += amount // Add deposit amount to balance.
            println("You successfully deposited $amount dollars. The new balance is $accountBalance dollars.")
            return amount
        }
    }
}
