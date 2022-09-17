package servlet.user;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;
import pojo.User;
import service.user.UserService;
import service.user.UserServiceImpl;
import tools.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        System.out.println(method);
        if (null != method && method.equals("savepwd")){
            this.updatePwd(req,resp);
        }else if(null != method && method.equals("pwdmodify")) {
            this.checkOldPwd(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }


    private void updatePwd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Object object = req.getSession().getAttribute(Constants.USER_SESSION);
        String newPassword = req.getParameter("newpassword");
        if (null != object && !StringUtils.isNullOrEmpty(newPassword)){
            UserService userService = new UserServiceImpl();
            String userCode = ((User) object).getUserCode();
            boolean flag = userService.updatePwd(userCode, newPassword);
            if (flag) {
                req.setAttribute(Constants.SYS_MESSAGE,"修改密码成功,请退出并使用新密码重新登录！");
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else{
                req.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
            }
        }else{
            req.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
        }
        req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
    }

    private void checkOldPwd(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Object object = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldPassword = req.getParameter("oldpassword");
        Map<String, String> resultMap = new HashMap<String, String>();
        if (null == object){
            resultMap.put("result","sessionerror");
        } else if (StringUtils.isNullOrEmpty(oldPassword)){
            resultMap.put("result","error");
        } else {
            String userPassword = ((User) object).getUserPassword();
            if (oldPassword.equals(userPassword)){
                resultMap.put("result","true");
            } else {
                resultMap.put("result","false");
            }
        }
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.write(JSONArray.toJSONString(resultMap));
        writer.flush();
        writer.close();
    }


}
