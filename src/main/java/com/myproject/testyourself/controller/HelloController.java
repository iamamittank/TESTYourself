package com.myproject.testyourself.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	public ModelAndView helloworld() {

		ModelAndView modelandview = new ModelAndView("HelloPage");
		return modelandview;

	}

	@RequestMapping("/signin")
	public ModelAndView signin() {

		ModelAndView modelandview = new ModelAndView("HelloPage");
		return modelandview;

	}

	@RequestMapping("/login")
	public void service(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		code = req.getParameter("code");
		if (code == null || code.equals("")) {
			throw new RuntimeException(
					"ERROR: Didn't get code parameter in callback.");
		}
		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);
		resp.sendRedirect("/TESTYourself/user/"+ fbProfileData.get("id"));
	}
	
	@RequestMapping("/user/{userId}")
	public ModelAndView login(@PathVariable("userId") String Id) {

		FBConnection fbConnection = new FBConnection();
		String accessToken = fbConnection.getAccessToken(code);
		
		code = null;

		FBGraph fbGraph = new FBGraph(accessToken);
		String graph = fbGraph.getFBGraph();
		Map<String, String> fbProfileData = fbGraph.getGraphData(graph);

		ModelAndView modelandview = new ModelAndView("UserHome");
		//modelandview.addAllObjects(fbProfileData);
		modelandview.addObject("fbname",fbProfileData.get("id"));
		return modelandview;

	}


}
