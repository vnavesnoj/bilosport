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
import vnavesnoj.spring.dto.BlogCreateEditDto;
import vnavesnoj.spring.dto.BlogInfoReadDto;
import vnavesnoj.spring.dto.PageResponse;
import vnavesnoj.spring.handler.ImageLineHtmlHandler;
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
    public String findById(Model model,
                           @PathVariable Integer id,
                           ImageLineHtmlHandler imageLineHtmlHandler) {
        blogService.findById(id).ifPresentOrElse(
                blog -> model.addAttribute("blog", blog),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
        );
        model.addAttribute("imageHandler", imageLineHtmlHandler);
        return "blog/blog";
    }

    @GetMapping("/{id}/edit")
    public String findByIdToEdit(Model model, @PathVariable Integer id) {
        blogService.findById(id).ifPresentOrElse(
                blog -> model.addAttribute("blog", blog),
                () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                }
        );
        return "blog/blog-edit";
    }

    @PostMapping("/{id}/edit")
    public String update(Model model,
                         @PathVariable Integer id,
                         BlogCreateEditDto blogDto) {
        if (blogService.update(id, blogDto).isPresent()) {
            return "redirect:/news/" + id;
        } else {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/add")
    public String post(Model model, BlogCreateEditDto blogDto) {
        final var blog = blogService.create(blogDto);
        return "redirect:/news/" + blog.getId();
    }

    @PostMapping("/{id}/delete")
    public String delete(Model model, @PathVariable Integer id) {
        if (blogService.delete(id)) {
            return "redirect:/news";
        } else {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
