package com.tao.mapper;

import com.tao.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    //新增一个博客
    void addBlog(Blog blog);

    List<Blog> queryBlogIf(Map<String,String> map);

    void updateBlog(Map<String,String> map);

    List<Blog> queryBlogChoose(Map<String,String> map);

    List<Blog> queryBlogForeach(Map<String,Object> map);
}