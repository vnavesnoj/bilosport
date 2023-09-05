package vnavesnoj.spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import vnavesnoj.spring.dto.*;
import vnavesnoj.spring.mapper.Mapper;
import vnavesnoj.spring.service.BlogService;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Controller
@RequestMapping("/news")
@RequiredArgsConstructor
public class BlogController {

    private final BlogService blogService;

    private final Mapper<BlogReadDto, BlogViewReadDto> blogViewReadMapper;

    @GetMapping
    public String findAllBlogInfo(Model model,
                                  Pageable pageable) {
        final Page<BlogInfoReadDto> posts = blogService.findAllBlogInfoSortedByDate(pageable);
        model.addAttribute("posts", PageResponse.of(posts));
        return "blog/blogs";
    }

    @GetMapping("/add")
    public String add(Model model) {
        return "blog/blog-add";
    }

    @GetMapping("/{id}")
    public String findById(Model model, @PathVariable Integer id) {
        blogService.findById(id).ifPresentOrElse(
                blog -> model.addAttribute("blog", blogViewReadMapper.map(blog)),
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
