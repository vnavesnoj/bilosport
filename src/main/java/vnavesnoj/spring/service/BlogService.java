package vnavesnoj.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
@Transactional(readOnly = true)
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

    public Page<BlogInfoReadDto> findAllBlogInfoSortedByDate(Pageable pageable) {
        final var pageRequest = PageRequest.of(
                pageable.getPageNumber(),
                pageable.getPageSize(),
                Sort.by("publicationTime").descending());
        return blogRepository.findAll(pageRequest)
                .map(blogInfoReadMapper::map);
    }

    public Optional<BlogReadDto> findById(Integer id) {
        return blogRepository.findById(id)
                .map(blogReadMapper::map);
    }

    @Transactional
    public BlogReadDto create(BlogCreateEditDto blogCreateDto) {
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
                .map(blogReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    public Optional<BlogReadDto> update(Integer id, BlogCreateEditDto blogEditDto) {
        return blogRepository.findById(id)
                .map(entity -> blogCreateEditMapper.map(blogEditDto, entity))
                .map(blogRepository::saveAndFlush)
                .map(blogReadMapper::map);
    }

    @Transactional
    public boolean delete(Integer id) {
        return blogRepository.findById(id)
                .map(blog -> {
                    blogRepository.delete(blog);
                    blogRepository.flush();
                    return true;
                })
                .orElse(false);
    }

}
