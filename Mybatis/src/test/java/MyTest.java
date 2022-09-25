import com.tao.mapper.BlogMapper;
import com.tao.mapper.StudentMapper;
import com.tao.mapper.TeacherMapper;
import com.tao.pojo.Blog;
import com.tao.pojo.Student;
import com.tao.pojo.Teacher;
import com.tao.utils.MybatisUtils;
import com.tao.utils.UUIDUtils;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    @Test
    public void asd(){
        SqlSession session = MybatisUtils.getSession();
        StudentMapper mapper = session.getMapper(StudentMapper.class);
        List<Student> students = mapper.getStudents();
        for (Student student : students) {
            System.out.println(student);

        }
        session.close();
    }
    @Test
    public void asasd(){
        SqlSession session = MybatisUtils.getSession();
        TeacherMapper mapper = session.getMapper(TeacherMapper.class);
        Teacher teacher = mapper.getTeacher(1);
        System.out.println(teacher);
        session.close();
    }
    @Test
    public void addBlog(){
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setAuthor("袁涛");
        blog.setId(UUIDUtils.getUUID());
        blog.setTitle("今天哈哈哈");
        blog.setViews(123465);
        blog.setCreateTime(new Date());
        mapper.addBlog(blog);

        blog.setId(UUIDUtils.getUUID());
        blog.setTitle("Java如此简单");
        mapper.addBlog(blog);

        blog.setId(UUIDUtils.getUUID());
        blog.setTitle("vue");
        mapper.addBlog(blog);
        blog.setId(UUIDUtils.getUUID());
        blog.setTitle("qq");
        mapper.addBlog(blog);
        session.commit();
        session.close();
    }
    @Test
    public void queryBlogIf(){
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("author","袁涛");
        List<Blog> blogs = mapper.queryBlogIf(hashMap);
        for (Blog blog : blogs) {
            System.out.println(blog.toString());
        }
        session.close();
    }
    @Test
    public void testUpdateBlog(){
        SqlSession session = MybatisUtils.getSession();
        BlogMapper mapper = session.getMapper(BlogMapper.class);
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title","动态SQL");
        map.put("author","秦疆");
        map.put("id","9c41caa975a447c9ac33e8119b3f22e0");
        mapper.updateBlog(map);
        session.commit();
        session.close();
    }
}
