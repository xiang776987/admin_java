package com.chinasoft.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chinasoft.bean.Lines;
import com.chinasoft.dao.HotelDAO;
import com.chinasoft.dao.LineTypeDAO;
import com.chinasoft.dao.LinesDAO;
import com.chinasoft.dao.OutTypeDAO;

public class AddLine extends HttpServlet {

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out=response.getWriter();
		Lines line=new Lines();
		LinesDAO ld=new LinesDAO();
		LineTypeDAO ltd=new LineTypeDAO();
		HotelDAO hd=new HotelDAO();
		OutTypeDAO otd=new OutTypeDAO();
		line.setBeforePrice(Double.parseDouble(request.getParameter("bprice_add")));
		line.setNowPrice(Double.parseDouble(request.getParameter("nprice_add")));
		line.setLineName(request.getParameter("linename_add"));
		line.setOutTypeID(otd.getOutTypeID(request.getParameter("outtype_add")));
		line.setLineTypeID(ltd.getTypeID(request.getParameter("linetype_add")));
		line.setCityID(hd.getCityID(request.getParameter("hotel_add")));
		line.setHotelID(hd.getHotelID(request.getParameter("hotel_add")));
		line.setPicUrl(request.getParameter("picurl"));
		int a=ld.getInsertLine(line);
		if(a>0){
			out.println("新增成功，1秒后返回管理界面");
			response.setHeader("Refresh", "1,URL=GetLine");
		}else{
			out.println("新增失败，1秒后返回编辑界面");
			response.setHeader("Refresh", "1,URL=editline.jsp");
		}
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
