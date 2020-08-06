package com.example.test_spring_boot;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class of Controller what handles mapping.
 */
@Controller
public class MainController
{
    /**
     * Object of class ModelAndView. It's used to open hello.html.
     */
    ModelAndView modelHello=new ModelAndView("hello.html");
    /**
     * Object of class ModelAndView. It's used to open input.html.
     */
    ModelAndView modelInput=new ModelAndView("input.html");

    /**
     * Main page.
     *
     * @return name of html page.
     */
    @GetMapping("/")
    public String home()
    {
        return "index";
    }

    /**
     * Test page.
     *
     * @param model object of interface Model what is used to add attribute.
     * @return name of html page.
     */
    @GetMapping("/test")
    public String test(Model model)
    {
        model.addAttribute("message",getString());
        return "test";
    }

    /**
     * Get string for test page.
     *
     * @return string.
     */
    public String getString()
    {
        return "TEST SPRING BOOT WITH GOOGLE APP ENGINE!";
    }

    /**
     * Page what outputs time.
     *
     * @return name of html page.
     */
    @GetMapping("/time")
    public String time()
    {
        return "time";
    }

    /**
     * Page what outputs date.
     *
     * @param model object of interface Model what is used to add attribute.
     * @return date.
     */
    @GetMapping("/date")
    public String date(Model model)
    {
        model.addAttribute("date",getDate());
        return "date";
    }

    /**
     * Get date.
     *
     * @return date.
     */
    public String getDate()
    {
        SimpleDateFormat formatDate=new SimpleDateFormat("dd.MM.yyyy");
        return formatDate.format(new Date());
    }

    /**
     * Page with form for input data.
     *
     * @return object of class ModelAndView with View name "input.html".
     */
    @GetMapping("/input")
    public ModelAndView input()
    {
        return modelInput;
    }

    /**
     * Get method of handling data.
     *
     * @param data data what was inputted in page with form for input data.
     * @param model object of interface Model what is used to add attribute.
     * @return name of html page.
     */
    @GetMapping("/entered_get")
    public String getEnteredGetData(@RequestParam String data,Model model)
    {
        model.addAttribute("message",getMessageWithData(data));
        return "entered_data";
    }

    /**
     * Post method of handling data.
     *
     * @param data data what was inputted in page with form for input date.
     * @param model object of interface Model what is used to add attribute.
     * @return name of html page.
     */
    @PostMapping("/entered_post")
    public String getEnteredPostData(@RequestParam String data,Model model)
    {
        model.addAttribute("message",getMessageWithData(data));
        return "entered_data";
    }

    /**
     * Get message.
     *
     * @param data data what it's necessary to check.
     * @return message with data.
     */
    public String getMessageWithData(String data)
    {
        String message;
        if(data!=null)
        {
            if(!data.equals(""))
            {
                message="You entered:"+data;
            }

            else
            {
                message="Data is empty";
            }
        }

        else
        {
            message="null";
        }

        return message;
    }

    /**
     * Page with form for input name.
     *
     * @return object modelHello.
     */
    @RequestMapping("/say_hello")
    public ModelAndView say_hello(Model model)
    {
        model.addAttribute("title","Hello!");
        return modelHello;
    }


    /**
     * This page with added message.
     *
     * @param name name what was inputted in page with form for input name.
     * @param model object of interface Model what is used to add attribute.
     * @return object of class ModelAndView with View name "hello.html".
     */
    @RequestMapping(value="/hello",method=RequestMethod.GET)
    public ModelAndView hello(@RequestParam(value="name",defaultValue="Unnamed")
                              @NonNull String name,
                              Model model)
    {
        String message=getMessageWithName(name);
        model.addAttribute("message",getMessageWithName(name));

        if(!message.equals("Entered name has only spaces"))
        {
            model.addAttribute("title","Hello,"+name+"!");
        }

        else
        {
            model.addAttribute("title","Hello!");
        }

        return modelHello;
    }

    /**
     * Get message.
     *
     * @param name name what it's necessary to check.
     * @return message with name.
     */
    public String getMessageWithName(String name)
    {
        String message;
        Pattern pattern=Pattern.compile(" ");
        Matcher matcher=pattern.matcher(name);
        List<String> stringList=new ArrayList<>();

        while(matcher.find())
        {
            stringList.add(matcher.group());
        }

        if(name.length()!=stringList.size())
        {
            message="Hello,"+name+"!!!";
        }

        else
        {
            message="Entered name has only spaces";
        }

        return message;
    }
}