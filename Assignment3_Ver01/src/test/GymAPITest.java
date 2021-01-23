
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Tests for GymAPI class")
public class GymAPITest {
    GymAPI gymAPI;
    Member member1;
    Member member2;
    Member member3;
    Trainer trainer1;
    Trainer trainer2;
    Trainer trainer3;

    @BeforeEach
    public void setUp() {
        gymAPI = new GymAPI();
        member1 = new Member("email1", "name1", "address1", "F",
                 1.5f, 100.0f, "Package 1");
        member2 = new Member("email2", "name2", "address2", "F"
                , 1.4f, 100.0f, "Package 1");

        member3 = new Member("email3", "name3", "address3", "M"
                , 1.5f, 100.0f, "Package 1");
//        trainer1 = new Trainer("emailt1", "namet1", "address3", "M","s1");
//        trainer2 = new Trainer("emailt2", "namet2", "address3", "M","s1");
//        trainer3 = new Trainer("emailt3", "namet3", "address3", "M","s1");
    }

    @Nested
    @DisplayName("isValidMemberIndex method test")
    class MemberIndex {

        @Nested
        @DisplayName("Given their are members in the Gym")
        class Valid {

            @BeforeEach
            public void setUp() {
                gymAPI.addMember(member1);
                gymAPI.addMember(member2);
                gymAPI.addMember(member3);
            }

            @DisplayName("When the index passed is at the lower boundry")
            @Test
            public void lower() {
                boolean result = gymAPI.isValidMemberIndex(0);
                assertTrue(result, "0 index is good");
            }

            @DisplayName("When the index passed is at the upper boundry")
            @Test
            public void upper() {
                boolean result = gymAPI.isValidMemberIndex(2);
                assertTrue(result, "2 index is good");
            }
            @DisplayName("When the index passed is out of bounds")
            @Test
            public void out() {
                boolean result = gymAPI.isValidMemberIndex(3);
                assertFalse(result, "0 index is invalid");
            }
        }

        @Nested
        @DisplayName("Given their are no members in the Gym")
        class Empty {

            @DisplayName("When the index passed is zero")
            @Test
            public void lower() {
                boolean result = gymAPI.isValidMemberIndex(0);
                assertFalse(result, "0 index is invalid");
            }
        }
    }

    @Nested
    @DisplayName("numberOfMember method test")
    class NumMember {

        @Nested
        @DisplayName("Given their are members in the Gym")
        class Some {

            @BeforeEach
            public void setUp() {
                gymAPI.addMember(member1);
                gymAPI.addMember(member2);
                gymAPI.addMember(member3);
            }

            @DisplayName("Should return the correct number")
            @Test
            public void correct() {
                int result = gymAPI.numberOfMembers();
                assertEquals(3, result, "Incorrect no. members");
            }
        }

        @Nested
        @DisplayName("Given their are no members in the Gym")
        class None {

            @DisplayName("Should return zeror")
            @Test
            public void correct() {
                int result = gymAPI.numberOfMembers();
                assertEquals(0, result, "Incorrect no. members");
            }
        }
    }

    @Nested
    @DisplayName("numberOfTrainers method test")
    class NumTraimers {

        @Nested
        @DisplayName("Given their are trainers in the Gym")
        class Some {

            @BeforeEach
            public void setUp() {
                gymAPI.addTrainer(trainer1);
                gymAPI.addTrainer(trainer2);
                gymAPI.addTrainer(trainer3);
            }

            @DisplayName("Should return the correct number")
            @Test
            public void correct() {
                int result = gymAPI.numberOfTrainers();
                assertEquals(3, result, "Incorrect no. trainers");
            }
        }

        @Nested
        @DisplayName("Given their are no trainers in the Gym")
        class None {

            @DisplayName("Should return zeror")
            @Test
            public void correct() {
                int result = gymAPI.numberOfTrainers();
                assertEquals(0, result, "Incorrect no. trainers");
            }
        }
    }

    @Nested
    @DisplayName("isValidTrainerIndex method test")
    class TrainerIndex {

        @Nested
        @DisplayName("Given their are trainers in the Gym")
        class Valid {

            @BeforeEach
            public void setUp() {
                gymAPI.addTrainer(trainer1);
                gymAPI.addTrainer(trainer2);
                gymAPI.addTrainer(trainer3);
            }

            @DisplayName("When the index passed is at the lower boundry")
            @Test
            public void lower() {
                boolean result = gymAPI.isValidTrainerIndex(0);
                assertTrue(result, "0 index is good");
            }

            @DisplayName("When the index passed is at the upper boundry")
            @Test
            public void upper() {
                boolean result = gymAPI.isValidTrainerIndex(2);
                assertTrue(result, "2 index is good");
            }
            @DisplayName("When the index passed is out of bounds")
            @Test
            public void out() {
                boolean result = gymAPI.isValidTrainerIndex(3);
                assertFalse(result, "Index is invalid");
            }
        }

        @Nested
        @DisplayName("Given their are no trainers in the Gym")
        class Empty {

            @DisplayName("When the index passed is zero")
            @Test
            public void lower() {
                boolean result = gymAPI.isValidTrainerIndex(0);
                assertFalse(result, "0 index is invalid");
            }
        }
    }
}