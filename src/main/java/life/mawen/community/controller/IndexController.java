package life.mawen.community.controller;


import life.mawen.community.dto.PaginationDTO;
import life.mawen.community.dto.QuestionDTO;
import life.mawen.community.mapper.QuestionMapper;
import life.mawen.community.mapper.UserMapper;
import life.mawen.community.model.Question;
import life.mawen.community.model.User;
import life.mawen.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;

//    @Autowired
//    private HotTagCache hotTagCache;

    @GetMapping("/")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "1") Integer page,
                        @RequestParam(name = "size", defaultValue = "10") Integer size,
                        @RequestParam(name = "search", required = false) String search,
                        @RequestParam(name = "tag", required = false) String tag,
                        @RequestParam(name = "sort", required = false) String sort) {
        PaginationDTO pagination = questionService.list(search,  page, size);
        //List<String> tags = hotTagCache.getHots();
        model.addAttribute("pagination", pagination);
        model.addAttribute("search", search);
        //model.addAttribute("tag", tag);
       // model.addAttribute("tags", tags);
        //model.addAttribute("sort", sort);
        return "index";
    }
}
