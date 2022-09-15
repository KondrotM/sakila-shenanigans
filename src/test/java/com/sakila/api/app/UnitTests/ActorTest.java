package com.sakila.api.app.UnitTests;

import com.sakila.api.app.Actor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ActorTest {

    Actor a = new Actor(0,"Foo","Bar");

    @Test
    public void test_actor_initalise(){
        Actor a = new Actor();
        Assertions.assertNotNull(a);
    }
    @Test
    public void test_actor_full_init(){
        Actor a = new Actor(1,"Nicki-James","Shepherd");
        Assertions.assertNotNull(a);
    }
    @Test
    public void test_actor_get_id(){
        Assertions.assertEquals(0,a.getActor_id());
    }
    @Test
    public void test_actor_get_first_name(){
        Assertions.assertEquals("Foo",a.getFirst_name());
    }
    @Test
    public void test_actor_get_last_name(){
        Assertions.assertEquals("Bar",a.getLast_name());
    }
    @Test
    public void test_actor_set_first_name(){
        Actor a = new Actor();
        a.setFirst_name("Foo");
        Assertions.assertEquals("Foo",a.getFirst_name());
    }
    @Test
    public void test_actor_set_last_name(){
        Actor a = new Actor();
        a.setLast_name("Bar");
        Assertions.assertEquals("Bar",a.getLast_name());
    }
    @Test
    public void test_actor_set_id(){
        Actor a = new Actor();
        a.setActor_id(1);
        Assertions.assertEquals(1,a.getActor_id());
    }
}
