package weatherApplication;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static weatherApplication.AuxiliaryMethods.getNameDay;
import static weatherApplication.AuxiliaryMethods.setFirstCapitalLetter;

class AuxiliaryMethodsTest {

    @Test
    void firstLetterShouldBeCapital() {
        //when
        String firstResult = setFirstCapitalLetter("berlin");
        String secondResult = setFirstCapitalLetter("");

        //then
        assertThat(firstResult, equalTo("Berlin"));
        assertThat(secondResult, equalTo(""));
    }

    @Test
    void shouldReturnDayName() {
        //when
        String day = getNameDay("2023-02-02 09:00:00");

        //then
        assertThat("Thursday", is(day));
    }
}