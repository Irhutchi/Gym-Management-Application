
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

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
        trainer1 = new Trainer("emailt1", "namet1", "address3", "M","s1");
        trainer2 = new Trainer("emailt2", "namet2", "address3", "M","s1");
        trainer3 = new Trainer("emailt3", "namet3", "address3", "M","s1");
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

    @Nested
    @DisplayName("searchMemberByEmail method test")
    class SearchMemberEmail {

        @Nested
        @DisplayName("Given their are members in the Gym")
        class Some {

            @BeforeEach
            public void setUp() {
                gymAPI.addMember(member1);
                gymAPI.addMember(member2);
                gymAPI.addMember(member3);
            }

            @DisplayName("When the email has a match in the array")
            @Test
            public void match() {
                Member result = gymAPI.searchMembersByEmail("email2");
                assertEquals("name2",result.getName(), "Should have found match");
            }

            @DisplayName("When the email has no match in the array")
            @Test
            public void nomatch() {
                Member result = gymAPI.searchMembersByEmail("email22");
                assertNull(result, "Should return null");
            }
        }

        @Nested
        @DisplayName("Given their are no members in the Gym")
        class Empty {

            @DisplayName("Then any email should return null")
            @Test
            public void nomatch() {
                Member result = gymAPI.searchMembersByEmail("email22");
                assertNull(result, "Should return null");
            }
        }
    }

    @Nested
    @DisplayName("searchMemberByName method test")
    class SearchMemberName {

        @Nested
        @DisplayName("Given their are members in the Gym")
        class Some {

            @BeforeEach
            public void setUp() {
                gymAPI.addMember(member1);
                gymAPI.addMember(member2);
                gymAPI.addMember(member3);
            }

            @DisplayName("When the (partial) name has some matchs in the array")
            @Test
            public void matchs() {
                member3.setName("name12");
                ArrayList<String> result = gymAPI.searchMembersByName ("2");
                assertEquals(2, result.size(), "Should have found matchs");
                assertEquals("name2", result.get(0), "Should have found name2");
                assertEquals("name12", result.get(1), "Should have found name12");
            }
            @DisplayName("When the (full) name has an exact match in the array")
            @Test
            public void amatch() {
                ArrayList<String> result = gymAPI.searchMembersByName ("name3");
                assertEquals(1, result.size(), "Should have found a match");
                assertEquals("name3", result.get(0), "Should have found name3");
            }

            @DisplayName("When the (partial) name has no matchs in the array")
            @Test
            public void nomatch() {
                ArrayList<String> result = gymAPI.searchMembersByName ("name4");
                assertEquals(0, result.size(), "Should have found nomatchs");
            }
        }

        @Nested
        @DisplayName("Given their are no members in the Gym")
        class Empty {
            @DisplayName("Then any name name will return empty array")
            @Test
            public void nomatch() {
                ArrayList<String> result = gymAPI.searchMembersByName ("name4");
                assertEquals(0, result.size(), "Should have found no matchs");
            }

        }
    }

    @Nested
    @DisplayName("searchTrainerByEmail method test")
    class SearchTrainerEmail {

        @Nested
        @DisplayName("Given their are trainers in the Gym")
        class Some {
            @BeforeEach
            public void setUp() {
                gymAPI.addTrainer(trainer1);
                gymAPI.addTrainer(trainer2);
                gymAPI.addTrainer(trainer3);
            }

            @DisplayName("When the email has a match in the array")
            @Test
            public void match() {
                Trainer result = gymAPI.searchTrainersByEmail("emailt2");
                assertEquals("namet2",result.getName(), "Should have found match");
            }

            @DisplayName("When the email has no match in the array")
            @Test
            public void nomatch() {
                Trainer result = gymAPI.searchTrainersByEmail("email22");
                assertNull(result, "Should return null");
            }
        }

        @Nested
        @DisplayName("Given their are no trainers in the Gym")
        class Empty {

            @DisplayName("Then any email should return null")
            @Test
            public void nomatch() {
                Trainer result = gymAPI.searchTrainersByEmail("email22");
                assertNull(result, "Should return null");
            }
        }
    }

    @Nested
    @DisplayName("searchTrainerByName method test")
    class SearchTrainerName {

        @Nested
        @DisplayName("Given their are trainers in the Gym")
        class Some {

            @BeforeEach
            public void setUp() {
                gymAPI.addTrainer(trainer1);
                gymAPI.addTrainer(trainer2);
                gymAPI.addTrainer(trainer3);
            }

            @DisplayName("When the (partial) name has some matchs in the array")
            @Test
            public void matchs() {
                trainer3.setName("namet12");
                ArrayList<String> result = gymAPI.searchTrainersByName  ("2");
                assertEquals(2, result.size(), "Should have found matchs");
                assertEquals("namet2", result.get(0), "Should have found namet2");
                assertEquals("namet12", result.get(1), "Should have found namet12");
            }
            @DisplayName("When the (full) name has an exact match in the array")
            @Test
            public void amatch() {
                ArrayList<String> result = gymAPI.searchTrainersByName ("namet3");
                assertEquals(1, result.size(), "Should have found a match");
                assertEquals("namet3", result.get(0), "Should have found namet3");
            }

            @DisplayName("When the (partial) name has no matchs in the array")
            @Test
            public void nomatch() {
                ArrayList<String> result = gymAPI.searchTrainersByName ("name4");
                assertEquals(0, result.size(), "Should have found nomatchs");
            }
        }

        @Nested
        @DisplayName("Given their are no trainers in the Gym")
        class Empty {
            @DisplayName("Then any name name will return empty array")
            @Test
            public void nomatch() {
                ArrayList<String> result = gymAPI.searchTrainersByName ("name4");
                assertEquals(0, result.size(), "Should have found no matchs");
            }

        }
    }
}
