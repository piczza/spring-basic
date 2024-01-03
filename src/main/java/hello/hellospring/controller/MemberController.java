package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    //이게 그래서 멤버서비스 가져오는거같은대
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //갯맵핑: 조회할때 씀
    //이걸로 회원가입폼을 연결해주고
    @GetMapping("/members/new")//이걸 입력하면
    public String createForm(){
        return "members/createMemberForm";//이 html을 찾으러감
    }

    //포스트맵핑: 정보를 폼에 넣어서 가져올때 씀.
    //이건 회원가입폼에서 정보 받아오는건가
    @PostMapping("/members/new")
    public String create(MemberForm form){
        //name 가져와서 member에 넣어주기
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        //넣은 정보로 회원가입.
        memberService.join(member);

        return "redirect:/";
    }
}
