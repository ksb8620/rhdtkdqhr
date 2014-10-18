package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CustomerDao;
import model.CustomerVo;

public class CustomerServlet extends HttpServlet {       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("euc-kr");
		String command = request.getParameter("command");
		if(command.equals("insert")){
			insert(request, response);
		}else if(command.equals("delete")){
			delete(request, response);
		}else if(command.equals("update")){
			update(request, response);
		}else if(command.equals("allView")){
			getCustomers(request, response);
		}			
	}
	/**
	 * 회원 가입 메소드 
	 * 1. Client가 보내온 데이터를 받아 DAO를 통해 DB에 입력한다.
	 * 2. 처리 결과에 따라 
	 * 		2-1. 입력에 실패하면 error.jsp로 수행을 넘긴다.
	 *		2-2. 정상적으로 입력을 성공했으면 입력한 글내용을 보여주는 JSP(view.jsp)로 이동한다.	         
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void insert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CustomerVo cvo = new CustomerVo(request.getParameter("id").trim(),
										request.getParameter("password").trim(), 
										request.getParameter("name").trim(), 
										request.getParameter("email").trim());
		String url = "error.jsp";	
		try {			
			CustomerDao.insert(cvo);			
			request.setAttribute("cvo", cvo);
			url = "view.jsp";
		} catch (SQLException e) {		
			request.setAttribute("error", "입력 실패");
			e.printStackTrace();
		}
		request.getRequestDispatcher(url).forward(request, response);
	}	
	/**
	 * 회원 탈퇴 즉 삭제 메소드
	 * 1. 탈퇴시켜려 하는 client의 id값으로 삭제한다.
	 * 2. 처리 결과에 따라 
	 * 	2-1. 입력에 실패하면 error.jsp로 수행을 넘긴다.
	 *  2-2. 정상적으로 삭제 성공했으면 글목록을 보여주는 JSP(list.jsp)가 출력 되도록 한다.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";
		try {	
			CustomerDao.delete(request.getParameter("id").trim());	
			request.setAttribute("allList", CustomerDao.getCustomers());
			url = "list.jsp";
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("error", "회원 삭제시 에러");
			url = "error.jsp";
		}		
		request.getRequestDispatcher(url).forward(request, response);
	}	
	/**
	 * 가입된 회원 정보 update 메소드
	 * 1. update.jsp로 호출되는 메소드로 pw와 email값만 업데이트 가능하다.
	 * 2. 처리 결과에 따라 
		* 	2-1. 입력에 실패하면 error.html로 수행을 넘긴다.
		*  2-2. 정상적으로 삭제 성공했으면 단순 성공 메세지가 출력되는 JSP(updateSuccess.jsp)가 실행 되도록 한다.
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";				
		HttpSession session = request.getSession();
		CustomerVo cvo = (CustomerVo)session.getAttribute("cvo");		
		cvo.setPassword(request.getParameter("password").trim());
		cvo.setEmail(request.getParameter("email").trim());	
		try{				
			CustomerDao.update(cvo);			
			url = "updateSuccess.jsp";
		}catch(SQLException e){
			e.printStackTrace();
			request.setAttribute("error", "업데이트 실패");	
			url = "error.jsp";
		}
		 request.getRequestDispatcher(url).forward(request, response);
	}
	/**
	 * 가입된 모든 회원 정보 검색 메소드
	 * 1. 다수의 jsp로 호출되는 메소드로 모든 고객 정보를 DAO로 부터 받아온다
	 * 2. 처리 결과에 따라 
		* 	2-1. 입력에 실패하면 error.jsp로 수행을 넘긴다.
		*  2-2. 정상적으로 Dao로부터 받아온 값이 있다면 JSP(list.jsp)가 실행되도록 한다
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void getCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "error.jsp";		
		try {		
			request.setAttribute("allList", CustomerDao.getCustomers());
			url = "list.jsp";
		} catch (SQLException e) {		
			e.printStackTrace();
			request.setAttribute("error", "모든 고객 검색 실패");
		}
		 request.getRequestDispatcher(url).forward(request, response);
	}
}
