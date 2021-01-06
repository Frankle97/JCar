package com.jyeob.boardcontroller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.membercontroller.Action;

public class SendEmail implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String to=request.getParameter("email");
		
		///////////////////////////////////////////////////
		
			String host = "smtp.naver.com";
			final String user = "tieotdsf1324@naver.com"; // 보내는쪽의 메일설정
			final String password = "@as2053000";  
			///////////////////////////////////////////////////
			Properties props = new Properties();
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.post", "465");
			 Session session = Session.getInstance(props, new  javax.mail.Authenticator() {
			    	protected PasswordAuthentication getPasswordAuthentication() {
			    		return  new PasswordAuthentication(user, password);
			    	}
			    });
			 
		    try {
		        MimeMessage msg = new MimeMessage(session);
		        msg.setFrom(new InternetAddress(user));
		        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		        msg.setSubject(subject);
		        msg.setContent(content, "text/html; charset=euc-kr");
		        Transport.send(msg);
		        out.println("<script>alert('발송에 완료하였습니다.'); location.href='"+request.getContextPath()+"/adminPage.do?id=admin'; </script>");	
		    } catch (Exception e) {
		    	e.printStackTrace();
		    	out.println("<script>alert('발송에 실패하였습니다.'); location.href='"+request.getContextPath()+"/adminPage.do?id=admin'; </script>");
		    }
		   
	}

}
