package com.thoughtworks.ifoodcompetition.model;

import com.thoughtworks.ifoodcompetition.model.Greeting;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class GreetingTest {

    public static final String CONTENT = "test";
    public static final long ID = 1L;

    @Test
    public void shouldCreateGreetingsWithSuccess(){
        Greeting greeting = new Greeting(ID, CONTENT);
        assertNotNull(greeting);
        assertThat(greeting.getId(), is(ID));
        assertThat(greeting.getContent(), is(CONTENT));
    }

}