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
        Assertions.assertEquals(0,a.getActorId());
    }
    @Test
    public void test_actor_get_first_name(){
        Assertions.assertEquals("Foo",a.getFirstName());
    }
    @Test
    public void test_actor_get_last_name(){
        Assertions.assertEquals("Bar",a.getLastName());
    }
    @Test
    public void test_actor_set_first_name(){
        Actor a = new Actor();
        a.setFirstName("Foo");
        Assertions.assertEquals("Foo",a.getFirstName());
    }
    @Test
    public void test_actor_set_last_name(){
        Actor a = new Actor();
        a.setLastName("Bar");
        Assertions.assertEquals("Bar",a.getLastName());
    }
    @Test
    public void test_actor_set_id(){
        Actor a = new Actor();
        a.setActorId(1);
        Assertions.assertEquals(1,a.getActorId());
    }
}
