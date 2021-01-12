package com.jyeob.boardcontroller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.dao.BoardDao;
import com.jyeob.dto.BoardDto;
import com.jyeob.dto.PagingDto;
import com.jyeob.membercontroller.Action;

public class NoticeListSearchOption implements Action{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		BoardDao dao = new BoardDao();
		ArrayList<BoardDto> list = null;
		String category = request.getParameter("category");
		String tab = request.getParameter("tab");
		String search = request.getParameter("search");
		int no = Integer.parseInt(request.getParameter("no"));
	
		if (category.equals("전체공지")) {
			if (tab.equals("제목")) {
				int pageTotal = dao.listCntSearchAnyTitle(search);
				int onepageLimit = 10;
				int pageAll = (int) Math.ceil(pageTotal / (float)onepageLimit);
				int pstartno = 0;
				if (request.getParameter("no") != null) {
					pstartno = no;
				}
				list = dao.list10SearchAnyTitle(pstartno, search);
				int bottomList = 10;
				int bottom_current = (int)Math.ceil((pstartno + 1) / (float)onepageLimit);
				int bottom_start = (int)Math.floor(((bottom_current - 1) / (float)bottomList)) * bottomList + 1;

				int bottom_end = bottom_start + bottomList - 1;
				if (pageAll < bottom_end) {
					bottom_end = pageAll;
				} 
				
				request.setAttribute("chk", 2);
				request.setAttribute("category", category);
				request.setAttribute("tab", tab);
				request.setAttribute("cnt", pageTotal);
				request.setAttribute("search", search);
				request.setAttribute("list", new PagingDto(pageTotal, onepageLimit, pageAll, pstartno, bottomList, bottom_current, bottom_start, bottom_end, list));
				request.getRequestDispatcher("boardNotice/noticeBoard.jsp").forward(request, response);
				
			} else if (tab.equals("내용")) {
				int pageTotal = dao.listCntSearchAnyContent(search);
				int onepageLimit = 10;
				int pageAll = (int) Math.ceil(pageTotal/(float)onepageLimit);
				int pstartno = 0;
				if (request.getParameter("no") != null) {
					pstartno = no;
				}
				list = dao.list10SearchAnyContent(pstartno, search);
				int bottomList = 10;
				int bottom_current = (int)Math.ceil((pstartno + 1) / (float)onepageLimit);
				int bottom_start = (int)Math.floor(((bottom_current - 1) / (float)bottomList)) * bottomList + 1;
				int bottom_end = bottom_start + bottomList - 1;
				if (pageAll < bottom_end) {
					bottom_end = pageAll;
				} 
					
				request.setAttribute("chk", 2);
				request.setAttribute("category", category);
				request.setAttribute("tab", tab);
				request.setAttribute("cnt", pageTotal);
				request.setAttribute("search", search);
				request.setAttribute("list", new PagingDto(pageTotal, onepageLimit, pageAll, pstartno, bottomList, bottom_current, bottom_start, bottom_end, list));
					request.getRequestDispatcher("boardNotice/noticeBoard.jsp").forward(request, response);
				} else if (tab.equals("전체")) {
					int pageTotal = dao.listCntSearchAnyAll(search);
					int onepageLimit = 10;
					int pageAll = (int) Math.ceil(pageTotal / (float)onepageLimit);
					int pstartno = 0;
					if (request.getParameter("no") != null) {
						pstartno = no;
					}
					list = dao.list10SearchAnyAll(pstartno, search);
					int bottomList = 10;
					int bottom_current = (int)Math.ceil((pstartno + 1) / (float)onepageLimit);
					int bottom_start = (int)Math.floor(((bottom_current-1) / (float)bottomList)) * bottomList + 1;
					int bottom_end = bottom_start + bottomList - 1;
					if (pageAll < bottom_end) {
						bottom_end = pageAll;
					} 
						
					request.setAttribute("chk", 2);
					request.setAttribute("category", category);
					request.setAttribute("tab", tab);
					request.setAttribute("cnt", pageTotal);
					request.setAttribute("search", search);
					request.setAttribute("list", new PagingDto(pageTotal, onepageLimit, pageAll, pstartno, bottomList, bottom_current, bottom_start, bottom_end, list));
					request.getRequestDispatcher("boardNotice/noticeBoard.jsp").forward(request, response);
				}
				} else {
					if (tab.equals("제목")) {
						int pageTotal = dao.listCntSearchTitle(category, search);
						int onepageLimit = 10;
						int pageAll = (int) Math.ceil(pageTotal / (float)onepageLimit);
						int pstartno = 0;
						if (request.getParameter("no") != null) {
							pstartno = no;
						}
						list = dao.list10SearchTitle(pstartno, category, search);
						int bottomList = 10;
						int bottom_current = (int)Math.ceil((pstartno + 1) / (float)onepageLimit);
						int bottom_start = (int)Math.floor(((bottom_current - 1) / (float)bottomList)) * bottomList + 1;

						int bottom_end = bottom_start + bottomList - 1;
						if (pageAll < bottom_end) {
							bottom_end = pageAll;
						} 
				
						request.setAttribute("chk", 2);
						request.setAttribute("category", category);
						request.setAttribute("tab", tab);
						request.setAttribute("cnt", pageTotal);
						request.setAttribute("search", search);
						request.setAttribute("list", new PagingDto(pageTotal, onepageLimit, pageAll, pstartno, bottomList, bottom_current, bottom_start, bottom_end, list));
						request.getRequestDispatcher("boardNotice/noticeBoard.jsp").forward(request, response);
				} else if (tab.equals("내용")) {
					int pageTotal = dao.listCntSearchContent(category, search);
					int onepageLimit = 10;
					int pageAll = (int) Math.ceil(pageTotal / (float)onepageLimit);
					int pstartno = 0;
					if (request.getParameter("no") != null) {
						pstartno = no;
					}
					list = dao.list10SearchContent(pstartno, category, search);
					int bottomList = 10;
					int bottom_current = (int)Math.ceil((pstartno + 1) / (float)onepageLimit);
					int bottom_start = (int)Math.floor(((bottom_current - 1)/(float)bottomList)) * bottomList + 1;

					int bottom_end = bottom_start + bottomList - 1;
					if (pageAll < bottom_end) {
						bottom_end = pageAll;
					} 
					
					request.setAttribute("chk", 2);
					request.setAttribute("category", category);
					request.setAttribute("tab", tab);
					request.setAttribute("cnt", pageTotal);
					request.setAttribute("search", search);
					request.setAttribute("list", new PagingDto(pageTotal, onepageLimit, pageAll, pstartno, bottomList, bottom_current, bottom_start, bottom_end, list));
					request.getRequestDispatcher("boardNotice/noticeBoard.jsp").forward(request, response);
					} else if (tab.equals("전체")) {
						int pageTotal = dao.listCntSearchAll(category, search);
						int onepageLimit = 10;
						int pageAll = (int) Math.ceil(pageTotal / (float)onepageLimit);
						int pstartno = 0;
						if (request.getParameter("no") != null) {
							pstartno = no;
						}
						list = dao.list10SearchAll(pstartno, category, search);
						int bottomList = 10;
						int bottom_current = (int)Math.ceil((pstartno+1) / (float)onepageLimit);
						int bottom_start = (int)Math.floor(((bottom_current - 1) / (float)bottomList)) * bottomList + 1;

						int bottom_end = bottom_start + bottomList - 1;
						if (pageAll < bottom_end) {
							bottom_end = pageAll;
						} 
						
						request.setAttribute("chk", 2);
						request.setAttribute("category", category);
						request.setAttribute("tab", tab);
						request.setAttribute("cnt", pageTotal);
						request.setAttribute("search", search);
						request.setAttribute("list", new PagingDto(pageTotal, onepageLimit, pageAll, pstartno, bottomList, bottom_current, bottom_start, bottom_end, list));
						request.getRequestDispatcher("boardNotice/noticeBoard.jsp").forward(request, response);
					}
		}
	}

}
