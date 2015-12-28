package com.myproject.testyourself.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.testyourself.dao.ExamDetailsDao;
import com.myproject.testyourself.model.ExamDetails;
import com.myproject.testyourself.model.UserDetails;
import com.myproject.testyourself.services.ExamDetailsService;
import com.myproject.testyourself.services.FBConnection;
import com.myproject.testyourself.services.FBGraph;
import com.myproject.testyourself.services.UserDetailsService;

@Controller
public class HelloController extends HttpServlet {

	@Autowired
	private UserDetailsService userService;
	@Autowired
	private ExamDetailsService examService;

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
			req.getSession().invalidate();

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

	@RequestMapping("/user={userId}/create")
	public ModelAndView createExam(@PathVariable("userId") String Id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

		ModelAndView modelandview = new ModelAndView("UserCreateExam");
		modelandview.addObject("fbname", fbProfileData.get("name"));
		modelandview.addObject("email", fbProfileData.get("email"));
		modelandview.addObject("id", fbProfileData.get("id"));

		return modelandview;

	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/user={userId}/create/submit", method = RequestMethod.POST)
	public ModelAndView submitExam(@PathVariable("userId") String Id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			String startDate = req.getParameter("startDate");
			DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
			Date s_date = null;
			try {
				s_date = df.parse(startDate);
			} catch (ParseException e) {
				System.out.println(e);
			}
			
			String endDate = req.getParameter("endDate");
			DateFormat df1 = new SimpleDateFormat("dd-mm-yyyy");
			Date e_date = null;
			try {
				e_date = df1.parse(endDate);
			} catch (ParseException e) {
				System.out.println(e);
			}
			
			String duration = req.getAttribute("Hour") + "-" + req.getAttribute("Minute");
			
			ExamDetails exam = new ExamDetails();
			exam.setExamName(req.getParameter("examName"));
			exam.setExamDuration(duration);
			exam.setExamStart(s_date);
			exam.setExamEnd(e_date);
			exam.setUserId(Long.parseLong(Id));
			
			examService.addExam(exam);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

		ModelAndView modelandview = new ModelAndView("UserHome");
		modelandview.addObject("status", "ExamCreated");
		modelandview.addObject("fbname", fbProfileData.get("name"));
		modelandview.addObject("email", fbProfileData.get("email"));
		modelandview.addObject("id", fbProfileData.get("id"));

		return modelandview;

	}

	@RequestMapping("/user={userId}/dashboard")
	public ModelAndView login(@PathVariable("userId") String Id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (code == null || code.equals("")) {
			resp.sendRedirect("/TESTYourself/welcome");
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

	@RequestMapping("/user={userId}/exams")
	public ModelAndView exams(@PathVariable("userId") String Id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (code == null || code.equals("")) {
			resp.sendRedirect("/TESTYourself/welcome");
		}

		code = null;

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

		ModelAndView modelandview = new ModelAndView("UserExams");
		modelandview.addObject("fbname", fbProfileData.get("name"));
		modelandview.addObject("email", fbProfileData.get("email"));
		modelandview.addObject("id", fbProfileData.get("id"));

		return modelandview;

	}

	@RequestMapping("/user={userId}/profile")
	public ModelAndView profile(@PathVariable("userId") String Id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		if (code == null || code.equals("")) {
			resp.sendRedirect("/TESTYourself/welcome");
		}
		
		ModelAndView modelandview = new ModelAndView("UserProfile");

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

		String age = fbProfileData.get("age_range");
		if (age.contains("min")) {
			int pos = age.indexOf("}");
			age = age.substring(pos - 2, pos);
		}

		String gender = fbProfileData.get("gender");

		modelandview.addObject("fbname", fbProfileData.get("name"));
		modelandview.addObject("email", fbProfileData.get("email"));
		modelandview.addObject("id", fbProfileData.get("id"));
		modelandview.addObject("gender", gender.substring(0, 1).toUpperCase() + gender.substring(1));
		modelandview.addObject("age_range", age);
		modelandview.addObject("link", fbProfileData.get("link"));
		modelandview.addObject("locale", fbProfileData.get("locale"));

		return modelandview;
	}

	@RequestMapping("/logout")
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String logoutUrl = "https://www.facebook.com/logout.php?next="
				+ URLEncoder.encode("http://localhost:8080/login/", "UTF-8") + "&access_token=" + accessToken;
		req.getSession().invalidate();
		resp.sendRedirect(logoutUrl);

	}
}
