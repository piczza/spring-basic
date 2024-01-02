package hello.hellospring.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HelloController {
    //이게 웹 상의 주소!
    //정적 방식
    @GetMapping("hello")
    //model은 {} 내부 변수의 값을 담는? 거라고 함! html에서 ${}내의 변수가 model에서 값을 꺼내는거래
    public String hello(Model model){
        //html에 넣는 변수명으로 data를 주고, 거기의 값으로 반가워를 준 것! hello.html 참조!
        model.addAttribute("data", "반가워.");
        //이게 스프링 내에서 참조할 html 파일 이름!
        return "hello";

    }

    //MVC 방식
    @GetMapping("hello-mvc")
    //이렇게 하면 웹 주소상에서 변수사용 및 값 대입 가능! ex: hello-mvc?name=예지
    public String hellowMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    //API 방식: 기초
    @GetMapping("hello-string")
    //html의 <body>가 아니라, http의 body부분의 데이터를 직접 넣어주겠다는 뜻
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    //API 방식: 보통 이렇게 씀. (제이슨방식)
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi (@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
