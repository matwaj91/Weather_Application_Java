package weatherApplication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static weatherApplication.AuxiliaryMethods.setFirstCapitalLetter;

class AuxiliaryMethodsTest {

    @Test
    void firstLetterShouldBeCapital() {
        //when
        String firstResult = setFirstCapitalLetter("berlin");
        String secondResult = setFirstCapitalLetter("");

        //then
        assertEquals("Berlin", firstResult);
        assertEquals("", secondResult);
    }
}