package com.example.test_spring_boot;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class MainControllerTest
{
    MainController mainController;

    @MockBean
    MainController mockController;

    @MockBean
    Model model;

    @Before
    public void serUP()
    {
        mainController=new MainController();
    }

    @After
    public void tearDown()
    {
        mainController=null;
    }

    @Test
    public void home()
    {
        assertEquals("index",mainController.home());
    }

    @Test
    public void test1()
    {
        assertEquals("test",mainController.test(model));
    }

    @Test
    public void getString()
    {
        assertEquals("TEST SPRING BOOT WITH GOOGLE APP ENGINE!",
                mainController.getString());
    }

    @Test
    public void time()
    {
        assertEquals("time",mainController.time());
    }

    @Test
    public void date()
    {
        assertEquals("date",mainController.date(model));
    }

    @Test
    public void getDate()
    {
        String date=new SimpleDateFormat("dd.MM.yyyy").format(new Date());
        when(mockController.date(model)).thenReturn(date);
        assertEquals(date,mockController.date(model));
    }

    @Test
    public void input()
    {
        ModelAndView modelAndView=mainController.modelInput;
        when(mockController.input()).thenReturn(modelAndView);
        assertEquals(modelAndView,mainController.input());
        assertNotNull(modelAndView);
    }

    @Test
    public void getEnteredGetData()
    {
        String data="DDDd 1223 а_-+";
        assertEquals("entered_data",
                mainController.getEnteredPostData(data,model));
    }

    @Test
    public void getEnteredPostData()
    {
        String data="DDDd 1223 а_-+";
        assertEquals("entered_data",
                mainController.getEnteredPostData(data,model));
    }

    @Test
    public void getMessageWithData()
    {
        String data="DDDd 1223 а_-+";
        assertEquals("You entered:"+data,mainController.getMessageWithData(data));

        String second_data="";
        assertEquals("Data is empty",mainController.getMessageWithData(second_data));

        String third_data=null;
        assertEquals("null",mainController.getMessageWithData(third_data));
    }

    @Test
    public void say_hello()
    {
        ModelAndView modelAndView=mainController.modelHello;
        assertEquals(modelAndView,mainController.say_hello(model));
        assertNotNull(modelAndView);
    }

    @Test
    public void hello()
    {
        String name="Andrei";
        ModelAndView modelAndView=mainController.modelHello;
        when(mockController.hello(name,model)).thenReturn(modelAndView);
        assertEquals(modelAndView,mockController.hello(name,model));
        assertNotNull(modelAndView);

        String second_name="  ";
        when(mockController.hello(second_name,model)).thenReturn(modelAndView);
        assertEquals(modelAndView,mockController.hello(second_name,model));
        assertNotNull(modelAndView);
    }

    @Test
    public void getMessageWithName()
    {
        String name="Andrei";
        assertEquals("Hello,"+name+"!!!",
                mainController.getMessageWithName(name));

        String second_name="  ";
        assertEquals("Entered name has only spaces",
                mainController.getMessageWithName(second_name));
    }
}