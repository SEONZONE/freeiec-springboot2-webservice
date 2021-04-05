package com.seonzone.book.springboot.web;
import com.seonzone.book.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController /* 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 준다.
                    예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다.*/


public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
    /* HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 준다.
    예전에는 @RequestMapping(method = RequestMethod.GET)으로 사용되었다.
    이제 프로젝트의 /hello 로 요청이 오면 문자열 hello를 반환하는 기능을 가지게 된다.*/
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount)
    {
        return new HelloResponseDto(name,amount);
    }

      /* 외부에서 API 로 넘긴 파라미터를 가져오는 어노테이션이다.
      여기서는 외부에서 name(@RequestParam("name"))이란 이름으로 넘긴 파라미터
       를 메소드 파라미터 name(String name)에 저장하게 된다.
                             */


}
