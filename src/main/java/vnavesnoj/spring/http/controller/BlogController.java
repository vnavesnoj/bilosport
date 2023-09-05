package vnavesnoj.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import vnavesnoj.spring.dto.BlogCreateEditDto;
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

    @GetMapping("/add")
    public String add(Model model) {
        return "blog/blog-add";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Integer id) {
        blogService.findById(id).ifPresentOrElse(
                blog -> model.addAttribute("blog", blog),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
        );
        return "blog/blog";
    }

    @PostMapping("/add")
    public String post(Model model, BlogCreateEditDto blogDto) {
        final var blog = blogService.create(blogDto);
        return "redirect:/news/" + blog.getId();
    }
}
