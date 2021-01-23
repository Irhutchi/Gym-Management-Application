# Gym-Management-Application
A repository created to store files associated console-based Gym Management application that demonstrates CRUD functionality.
IntelliJ IDE used to develope application.

## Description

The components of the architecture are:
<table border="0">
 <tr>
    <td><b style="font-size:30px">Component</b></td>
    <td><b style="font-size:30px">Description</b></td>
 </tr>
 <tr>
    <td>MenuController<br>
        Gym API<br>
        Utility<br>
        DataModel
    </td>
    <td>This class provides a console-based user interface to the application's feature set.<br>
        This class implements the application's 'business logic'.<br>
        Reusable parts of the 'business logic' are delegated to this class.<br>
        This contains classes based on the problem domain, e.g. Member, Trainer
    </td>
 </tr>
</table>

![](https://github.com/Irhutchi/Gym-Management-Application/blob/master/imgs/archictecture.jpg)

### Data Model

The apps data model consists of the following entities. 

* Premium Member
* Student Member
* Trainer
* Assessment

A visual representation of the data model is shown below:

![](https://github.com/Irhutchi/Gym-Management-Application/blob/master/imgs/dataModel.jpg)

### Gym API
This class operates between the model classes and the menu driver class (see later). It stores:

* an ArrayList of **Member**
* an ArrayList of **Trainer**

### Utility Class
A utility class (or helper class) is a "structure" that has only static methods and encapsulates no state (fields). 
Typically the functionality of the methods is reusable across a range of applications. 

### MenuConroller Class
The menu driver class (MenuController) uses the console I/O to interact with the user. 
It should create an instance of the GymApi class and allow the user to navigate (a subset of) the system's features through a series of menus. 
The following processing is required in this *menu system*:

1. On app start-up, gym data loads automatically (trainers and members) from an XML file.
2. User is prompted to login(l) or register (r).
3. User is ask to if they are a member(m) or a trainer(t). <br>
  * If the user selected to register, ask them to enter the required details for the member/trainer. If they enter an email that is already used in the system (for either trainers/members), 
  print out "invalid email" and ask them to enter a new one.<br>
  * If the user selects login, verify that the email entered is stored in the appropriate arraylist i.e. the members or trainers list. If the email doesn’t exist, print out “access denied” to the console and exit the program.<br>
4. Once logged in,the appropriate menu is displayes (trainer or member).

### Unit Tests
A full suite of unit test will are provided (WIT) to prove the entire API and utility classes. 
Unit tests were not developed by the author as part of this project but provided to run tests and fix any logic errors it reports in the code.

## Author

**Ian Hutchinson**  
20048122@mail.wit.ie


## License

This project is licensed under the MIT  License - see the LICENSE.md file for details
