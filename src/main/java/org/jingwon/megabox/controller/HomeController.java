package org.jingwon.megabox.controller;

import org.jingwon.megabox.service.MovieServiceImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/")
@AllArgsConstructor
public class HomeController {
	//무조건 service를 @setter  or  @AllArgsConstructor을 해줘야됨  즉 생성자를 생성해주지 않으면 model 에 attribute 해줄때 service의 메서드를 사용할수 없음 ( 생성자가 없으니까 )
	private MovieServiceImpl service;
	
	@RequestMapping("/main.do")
	public String main(Model model) {
		log.info("controller : main.do 요청");
		model.addAttribute("list", service.mainPageMovieList());
		model.addAttribute("list2", service.mainPageCurationList());
		model.addAttribute("curation", service.mainPageCuration());
		
		return "main";
	}
	@RequestMapping("/movie.do")
	public String movie(Model model, String type, String search, boolean check) {
		log.info("type : "+type + "," + "search : " + search);
		model.addAttribute("list", service.getMovieList(type, search, check));
		return "movie";
	}
	@RequestMapping("/movie-detail.do")
	public String movieDetail(Model model, int seq) {
		model.addAttribute("vo", service.getMovie(seq));
		return "detail";
	}
}
