package com.myproject.testyourself.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.myproject.testyourself.services.FBConnection;
import com.myproject.testyourself.services.FBGraph;

@Controller
public class HelloController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String code = "";

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
			String accessToken = fbConnection.getAccessToken(code);

			FBGraph fbGraph = new FBGraph(accessToken);
			String graph = fbGraph.getFBGraph();
			Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
			
			HttpSession session = req.getSession();
			session.setAttribute("email", fbProfileData.get("email"));

			resp.sendRedirect("/TESTYourself/user/" + fbProfileData.get("id"));
		}
	}

	@RequestMapping("/user/{userId}")
	public ModelAndView login(@PathVariable("userId") String Id, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);

		System.out.println(accessToken);

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

}
