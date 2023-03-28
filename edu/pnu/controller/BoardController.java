package edu.pnu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pnu.domain.Person;
import edu.pnu.service.BoardService;

@RestController
public class BoardController {
	// 하드코딩
	// BoardService boardService = new BoardService();
	
	// di(으존성 주입) 방식
	// 변수에 넣는 형태.
	@Autowired
	BoardService boardService;
	
	public BoardController() {
		System.out.println("===> BoardController 생성");
	}
	// get 호출
	@GetMapping("/hello")
	public String hello(String name) {
		return "Hello : " + name;
	}
	// post 호출
	@PostMapping("/hello")
	public String hello1(String name) {
		return "Hello Post : " + name;
	}
	// 객체를 반환하는 get 호출.
	@GetMapping("/getPerson")
	public Person getPerson() {
		return new Person("홍길동", 2000, "백수", "웹서핑");
	}
	// 객체들의 리스트를 반환하는 get 호출.
	@GetMapping("/getPersons")
	public List<Person> getPersons() {
		return boardService.getPersons();
	}
	
}
