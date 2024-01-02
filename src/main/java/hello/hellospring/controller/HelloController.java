package hello.hellospring.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HelloController {
    //이게 웹 상의 주소!
    @GetMapping("hello")
    //model은 {} 내부 변수의 값을 담는? 거라고 함! html에서 ${}내의 변수가 model에서 값을 꺼내는거래
    public String hello(Model model){
        //html에 넣는 변수명으로 data를 주고, 거기의 값으로 반가워를 준 것! hello.html 참조!
        model.addAttribute("data", "반가워.");
        //이게 스프링 내에서 참조할 html 파일 이름!
        return "hello";

    }

    @GetMapping("hello-mvc")
    //이렇게 하면 웹 주소상에서 변수사용 및 값 대입 가능! ex: hello-mvc?name=예지
    public String hellowMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }
}
