Name: Ian Hutchinson
Student Number: 20048122


Which level unit tests succeed completely:

Member Tests = pass level and very good level tests passing.
GymAPI Tests = All pass level test completed.

Which level unit tests succeed partially:

GymAPI Tests = Very good -24 passing tests.
              2 fail - searchByMemberName & searchByTrainerName partial(name) test failing

Self reflection - Grading Spectrum Level:
Pass (40-49)
Data Model: Basic implementation (Person, Member and Trainer).
    -Basic inheritance design implemented in this project. for example, the member and trainer inherit all the fields
     (email, name, address and gender) of the Person-superclass. Member and trainer both have common fields avoiding
     code duplication. They also have their own unique fields, differentiating themselves. The Student member and
     premium member inherit all of the member fields. Student member contains two unique fields (student id and college name).
    -These details are used to when validating student gym package.
    -Encapsulation is used to protect class fields to be accessed outside of the class without 'permission'.. Mutators
     are utilised to achieve set and get values.

API: Add members/trainers, numberofMembers/Trainers methods, isValidMember/TrainerIndex methods, basic addMember/Trainer.
    - registerMember():- Location -> MenuController -> Line 185-277
    - registerTrainer();- Location -> MenuController -> Line 410-432
      Add member and trainers happen during registration. The menu prompts for member or trainer on start up, they can
      choose to register at that point and their details are saved to the relevant xml file.
    - numberOfMembers(); Location -> GymAPI -> Line 106-112
      trainer has the ability to check how many members are present in the member arraylist at anytime
    - isValidMemberIndex() Location -> GymAPI -> Line 129-137
    - isValidTrainerIndex() Location -> GymAPI -> Line 143-151

Good (50-59)
    - XML Persistence:
      On app start-up, trainers and members automatically load the gym data from their respective XML file
      On exiting the program, trainers and members automatically save the gym data to their respective XML file
      Both are wrapped in a try-catch statement, if an exception occurs it will throw an error and the user will see it
      in the console as a message.
      The member and trainer load/save methods are located in GymAPI -> Line 216-317
      The load methods are called in the MenuController constructor -> Line 29-34
      The save methods are called in the startSystem() when the user decides to quit the program -> Line 114-128

    - Menu: Login, Member sub-menu, (Member) View/update profile:
      The welcomeMenu() is called from from main(). User is prompted, are they a member(m) or trainer(t). This welcomeMenu()
      reads in 'userChoice' of type String from the user. They have decided whether to continue as a member or trainer.
      If anything other than 'm' or 't' is read in it will throw an error 'invalid entry'. The menu will re-run from
      the beginning.
      Next, the welcomeMenu() will read in 'loginOrRegisterChoice' of type String from the user.
      A nest if else condition ->MenuController ->Line 65-81, will concatenating two strings from user to return login
      or register method based on the switch case option, startSystem.
      The login method in MenuController ->Line 150-182, asks the user to enter their (member or trainers) email.'userEmail'
      is used to check if the member/trainer object email matches the user email entered. if user input doesn't match,
      return null. 'gymLogin' takes 'ml' or 'tl' to distinguishes which  menu to return. If it finds member, display member
      menu and same for trainer.

Very Good (60-69)
    - API: Various Search methods (by email and name)
      Search by name and email methods for both member and trainer are stored in GymAPI.
      Search by email is used in several instances. One instance is registerMember() Line ->194. When a user wants to
      register as a member it checks for null. if that email is already associated with a member stored, print to the console
      'Member already exists!!'. Otherwise the email is not taken and the user can continue filling in their details to
      complete registration. the searchByMember/TrainerEmail is called from GymAPI class. It is used in several other
      instances in member and trainer menus.

    - Menu: Trainer sub-menu, Add/List/Search members,
      A trainer has several abilities handled by switch case. Each option can be accessed via 'choice' of type int.
      Location ->MenuController -> Line 348-364

A statement of how much of the application specification you implemented:
    - This project has completed all aspects contained in the pass, good and some of very good levels of the grading spectrum.
      The application allows access to trainer menu and member menu via registration or members with their information already stored.
      Validation of personal details such as ensuring:
        - person's name does not exceed 30 characters ->Person Class -> Line 52-59
        - Ensures member's weight entered is between 35 & 250, if value outside of this range default to 100
          ->Member Class -> Line 55-61
        - Ensures member's height entered is between 1 & 3 inclusive else default to 1.5 ->Member Class ->Line 67-73.
      Other validation occurs in when reading in values from the user in the menu options. This true for entries that require
      ints, float or double entries for example weight. The user is ask to input a float which is nested in a try-catch
      and wrapped in a while loop. if'goodInput' is true input is taken else if user enters a character it will return the
      same question again until it receives a good input.
      The gym packages available to the user is contains in a hashMap of type String, String. The key value is package
      name. and the value is the details contained in that package. During member registration the last input from the user
      is to ask them to choose a package which is stored in a variable 'packageChoice. The list of packages is displayed
      first using a for loop  ->MenuController -> Line234 iterating over set of all key-value pairs and printing them
      to the console. User enters choice of package they would like to avail of.
        Using a nested if statement the containsKey() checks user input contains/ matches package choice entered is contained
      in the package hashmap. If the user chooses 'WIT' package they are prompted with a query 'Are you a student (y/n)''
      if they choose no or enter an invalid entry, a message will print to the console inform them this package is only
      available for students. If the enter'y' and input the correct key 'WIT' it calls the Member constructor to build a member
      object in memory with the details entered by the user. This person's details are then save as a student member in the
      member xml file when they exit the program.
        If the user choice (chosenPackage) equals 'Package 1' calls the Member constructor to build a member object in memory
      with the details entered by the user. This person's details are then save as a premium member in the member
      xml file when they exit the program.
        Else  if the user choice (chosenPackage) equals 'Package 2 or 3' it calls the Member constructor to build a member
      object with the details entered by the user. This person's details are then save as a just a member in the member
      xml file when they exit the program.


Known bugs/problems :
    - bug is contained in the welcomeMenu(). The user is given the choice to exit the program by entering a string
     'quit'. If on the first option "Are you  a member(m) or a trainer(t)" it will not exit if quit is typed here as the
      the program concatenates to strings entered so doesn't accept quit at this point.
    - when logging in as trainer and you get to the point of email entry, if you just press enter key without any string
      string input it still allows access to the trainer menu.
    - By using equalsIgnoreCase() for taking in 'userChoice' & 'loginOrRegisterChoice' in welcomeMenu I was expecting the
      program to accept either upper and lower case strings but only accepts lower case input anything other than that
      returns an invalid entry.
      I have tried different approaches in trying to resolve this including:
                                                   String userChoice = input.nextLine().toLowerCase();
                                                   String loginOrRegisterChoice = input.nextLine().toLowerCase();
      But still didn't make head way with it. I suspect my problem lay in me switch case statement.
    - bug contained in the Scanner class that doesn't read a newline character in the input by hit enter afer an
      Scanner input.nextInt() or input.nextFloat(). The workaround is to include a dummy input.nextLine() after ints or
      floats etc. Not after a input.nextLine() itself.
    - In the runTrainer() switch case option 2 - list all members. I am attempting to call the listMembers method from
      GymAPI and print the lost of members to the console but is not working.


Any sources referred to during the development of the assignment:
- Baeldung. 2020. A Guide to Java HashMap | Baeldung. [ONLINE] Available at: https://www.baeldung.com/java-hashmap. [Accessed 02 June 2020].