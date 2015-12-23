package com.myproject.testyourself.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.testyourself.model.UserDetails;
import com.myproject.testyourself.services.FBConnection;
import com.myproject.testyourself.services.FBGraph;
import com.myproject.testyourself.services.UserDetailsService;

@Controller
public class HelloController extends HttpServlet {

	@Autowired
	private UserDetailsService userService;

	private static final long serialVersionUID = 1L;
	private String code = "";
	private String accessToken = "";

	@RequestMapping("/welcome")
	public ModelAndView helloworld(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ModelAndView modelandview = new ModelAndView("HelloPage");
		return modelandview;

	}

	@RequestMapping("/signin")
	public ModelAndView signin() {

		ModelAndView modelandview = new ModelAndView("HelloPage");
		return modelandview;

	}

	@RequestMapping("/login")
	public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		code = req.getParameter("code");
		if (code == null || code.equals("")) {
			resp.sendRedirect("/TESTYourself/welcome");
		} else {
			FBConnection fbConnection = new FBConnection();
			accessToken = fbConnection.getAccessToken(code);

			FBGraph fbGraph = new FBGraph(accessToken);
			String graph = fbGraph.getFBGraph();
			Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

			req.getSession().setAttribute("user", fbProfileData.get("id"));
			
			try {
				UserDetails user = new UserDetails();
				user.setUserId(Long.parseLong(fbProfileData.get("id")));
				user.setUserName(fbProfileData.get("name"));
				user.setUserEmail(fbProfileData.get("email"));
				user.setUserSource("Facebook");

				userService.addUser(user);
			} catch (DataIntegrityViolationException e) {
				System.out.println("User Existed");
			
			} catch (Exception e) {
				System.out.println(e);
			}

			resp.sendRedirect("/TESTYourself/user=" + fbProfileData.get("id") + "/dashboard");
		}
	}

	@RequestMapping("/user={userId}/dashboard")
	public ModelAndView login(@PathVariable("userId") String Id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if(req.getSession().getAttribute("user") == null) {
			
		}
		
		code = null;

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

		ModelAndView modelandview = new ModelAndView("UserHome");
		modelandview.addObject("fbname", fbProfileData.get("name"));
		modelandview.addObject("email", fbProfileData.get("email"));
		modelandview.addObject("id", fbProfileData.get("id"));

		return modelandview;

	}

	@RequestMapping("/user={userId}/profile")
	public ModelAndView profile(@PathVariable("userId") String Id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		ModelAndView modelandview = new ModelAndView("UserProfile");

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		
		String age = fbProfileData.get("age_range");
		if(age.contains("min")) {
			int pos = age.indexOf("}");
			age = age.substring(pos-2, pos);
		}

		String gender = fbProfileData.get("gender");
		
		modelandview.addObject("fbname", fbProfileData.get("name"));
		modelandview.addObject("email", fbProfileData.get("email"));
		modelandview.addObject("id", fbProfileData.get("id"));	
		modelandview.addObject("gender", gender.substring(0,1).toUpperCase() + gender.substring(1));
		modelandview.addObject("age_range", age);

		return modelandview;
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String logoutUrl = "https://www.facebook.com/logout.php?next=" + URLEncoder.encode("http://localhost:8080/login/", "UTF-8") + "&access_token=" + accessToken;
		req.getSession().invalidate();
		resp.sendRedirect(logoutUrl);
		
	}
}
