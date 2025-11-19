package com.project.blogs.mapper;

import com.project.blogs.core.dto.SlugUtil;
import com.project.blogs.dto.post_dto.request.CreatePostRequestDto;
import com.project.blogs.dto.post_dto.request.UpdatePostRequestDto;
import com.project.blogs.dto.post_dto.response.ListResponseDto;
import com.project.blogs.dto.post_dto.response.ViewPostResponseDto;
import com.project.blogs.entity.Post;
import com.project.blogs.entity.User;
import com.project.blogs.exception.NotFoundException;
import com.project.blogs.repo.PostRepo;
import com.project.blogs.repo.UserRepo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class PostMapper{

    @Autowired
    protected UserRepo userRepo;

    @Autowired
    protected PostRepo postRepo;

    private String makeSlugUnique(String slug) {
        String uniqueSlug = slug;
        int count = 1;
        while (postRepo.existsBySlug(uniqueSlug)) {
            uniqueSlug = slug + "-" + count;
            count++;
        }
        return uniqueSlug;
    }


    public Post savePost(CreatePostRequestDto postRequestDto) {
        User author = userRepo.findById(postRequestDto.getAuthorId())
                .orElseThrow(() -> new NotFoundException("Author not found"));

        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setAuthor(author);
        String slug = SlugUtil.toSlug(postRequestDto.getTitle());

        slug = makeSlugUnique(slug);

        post.setSlug(slug);
        return post;
    }

    @Mapping(target = "author", source = "author.username")
    public abstract ListResponseDto entityToResponse(Post post);
    public List<ListResponseDto> listAllPost(Page<Post> posts)  {
        return posts.getContent().stream().map(this::entityToResponse).collect(Collectors.toList());
    }

    public abstract ViewPostResponseDto viewPost(Post post);

    public Post updatePost(Post post, UpdatePostRequestDto updatePostRequestDto) {
        post.setTitle(updatePostRequestDto.getTitle());
        post.setContent(updatePostRequestDto.getContent());
        return post;
    }

    public Post deletePost(Post post) {
        post.setIsDeleted(true);
        return post;
    }
}
