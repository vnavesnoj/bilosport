package vnavesnoj.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import vnavesnoj.spring.dto.BlogInfoReadDto;
import vnavesnoj.spring.service.BlogService;

import java.util.List;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    @GetMapping
    public String findAllBlogInfo(Model model) {
        final List<BlogInfoReadDto> posts = blogService.findAllBlogInfo();
        model.addAttribute("posts", posts);
        return "blog/blogs";
    }

    @GetMapping("news/add")
    public String add(Model model) {
        return "blog/blog-add";
    }
}
