package com.example.ap_project;

//import static org.junit.Assert.*;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class JUNITTest {
//    private Parent root;
//    private Scene scene;
//
//    JUNITTest(Parent root, Scene scene){
//        this.root = root;
//        this.scene = scene;
//    }

//    @Test
//    public void generate_rectangle() {
////        HelloController controller = new HelloController(HelloController.root, HelloController.scene);
//        Rectangle r1 = new Rectangle(100, 500, 150, 300);
//        HelloController.randomRect(r1);
//        Assert.assertTrue(controller.getRectangles().size() == 2);
//        Assert.assertTrue(controller.getRectangles().get(1).getY() == 500);
//    }

    @Test
    public void testSingleton(){
        Username username1 = Username.getInstance();

        username1.setName("TestUser");

        Username username2 = Username.getInstance();

        assertSame(username1, username2);
        assertEquals("TestUser", username2.getName());

    }
}