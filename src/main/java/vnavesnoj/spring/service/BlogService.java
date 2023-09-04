package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vnavesnoj.spring.database.entity.Blog;
import vnavesnoj.spring.database.repository.BlogBodyRepository;
import vnavesnoj.spring.database.repository.BlogRepository;
import vnavesnoj.spring.dto.BlogCreateEditDto;
import vnavesnoj.spring.dto.BlogInfoReadDto;
import vnavesnoj.spring.dto.BlogReadDto;
import vnavesnoj.spring.mapper.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author vnavesnoj
 * @mail vnavesnoj@gmail.com
 */
@Service
@RequiredArgsConstructor
public class BlogService {

    private final BlogRepository blogRepository;

    private final BlogBodyRepository blogBodyRepository;

    private final Mapper<Blog, BlogInfoReadDto> blogInfoReadMapper;

    private final Mapper<Blog, BlogReadDto> blogReadMapper;

    private final Mapper<BlogCreateEditDto, Blog> blogCreateEditMapper;

    public List<BlogInfoReadDto> findAllBlogInfo() {
        return blogRepository.findAll().stream()
                .map(blogInfoReadMapper::map)
                .toList();
    }

    public Optional<BlogReadDto> findById(Integer id) {
        return blogRepository.findById(id)
                .map(blogReadMapper::map);
    }

    public Optional<BlogReadDto> create(BlogCreateEditDto blogCreateDto) {
        return Optional.of(blogCreateDto)
                .map(blogDto -> {
                    final Blog mappedBlog = blogCreateEditMapper.map(blogDto);
                    mappedBlog.setPublicationTime(LocalDateTime.now());
                    return mappedBlog;
                })
                .map(blog -> {
                    blogBodyRepository.save(blog.getBlogBody());
                    return blogRepository.saveAndFlush(blog);
                })
                .map(blogReadMapper::map);
    }
}
