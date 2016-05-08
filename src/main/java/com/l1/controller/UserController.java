package com.l1.controller;

import com.l1.entity.User;
import com.l1.service.UserService;
import com.l1.util.StringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户Controller层
 *
 * @author hongxp
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping
    public String showPage() {
        return "userManage";
    }

    /**
     * 用户登录
     *
     * @param user
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/login")
    public String login(User user, HttpServletRequest request) throws Exception {
        User resultUser = userService.login(user);
        if (resultUser == null) {
            request.setAttribute("user", user);
            request.setAttribute("errorMsg", "用户名或密码错误！");
            return "login";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("currentUser", resultUser);
            return "redirect:/main";
        }
    }

    /**
     * 分页条件查询用户
     *
     * @param page
     * @param rows
     * @param s_user
     * @return
     * @throws Exception
     */
    @RequestMapping("/list")
    @ResponseBody
    public Map<String, Object> list(@RequestParam(value = "page", required = false) String page,
                                    @RequestParam(value = "rows", required = false) String rows, User s_user)
            throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", StringUtil.formatLike(s_user.getUserName()));
        map.put("start", page);
        map.put("size", rows);

        List<User> userList = userService.find(map);
        Long total = userService.getTotal(map);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("rows", userList);
        result.put("total", total);

        return result;
    }

    /**
     * 获取客户经理信息 下拉框数据用到
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/customerManagerComboList")
    @ResponseBody
    public List<User> customerManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("roleName", "客户经理");
        List<User> userList = userService.find(map);

        return userList;
    }

    /**
     * 添加或者修改用户
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> save(User user) throws Exception {
        int resultTotal = 0; // 操作的记录条数
        if (user.getId() == null) {
            resultTotal = userService.add(user);
        } else {
            resultTotal = userService.update(user);
        }
        Map<String, Object> result = new HashMap<String, Object>();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }

        return result;
    }

    /**
     * 删除用户
     *
     * @param ids
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "ids") String ids) throws Exception {
        String[] idsStr = ids.split(",");
        for (int i = 0; i < idsStr.length; i++) {
            userService.delete(Integer.parseInt(idsStr[i]));
        }
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("success", true);
        return result;
    }

    /**
     * 修改用户密码
     *
     * @return
     * @throws Exception
     */
    @RequestMapping("/modifyPassword")
    @ResponseBody
    public Map<String, Object> modifyPassword(Integer id, String newPassword) throws Exception {
        User user = new User();
        user.setId(id);
        user.setPassword(newPassword);
        int resultTotal = userService.update(user);
        Map<String, Object> result = new HashMap<String, Object>();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        return result;
    }

    /**
     * 用户注销
     *
     * @param session
     * @return
     * @throws Exception
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) throws Exception {
        session.invalidate();
        return "redirect:/login.jsp";
    }

    @RequestMapping("/comboList")
    @ResponseBody
    public List<User> itemManagerComboList() throws Exception {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("stat", "1");
        List<User> usersList = userService.findForCombo(map);


//TODO 过滤?
// 		config.setJsonPropertyFilter(new PropertyFilter() {
//			@Override
//			public boolean apply(Object source, String name, Object value) {
//				if ("id".equals(name) || "text".equals(name)) {
//					return false;
//				} else {
//					return true;
//				}
//			}
//		});

        return usersList;
    }
}
