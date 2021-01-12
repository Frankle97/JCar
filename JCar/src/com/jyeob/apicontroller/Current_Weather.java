package com.jyeob.apicontroller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class Current_Weather
 */
@WebServlet("/Current_Weather")
public class Current_Weather extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Current_Weather() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			String url = "";
			url = "http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=1168064000";
			DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = docBuilder.parse(url);
			String weather = "";
			NodeList dataList = doc.getElementsByTagName("data");
			for (int i=0; i<dataList.getLength(); i++) {
				Node data = dataList.item(i);
				if (data.getNodeType() == Element.ELEMENT_NODE) {
					NodeList subdataList = data.getChildNodes();
					for (int sub = 0; sub < subdataList.getLength(); sub++) {
					Node son = subdataList.item(sub);
					String nodeName = son.getNodeName();
					String nodeValue = son.getTextContent();
						if (!nodeName.equals("#text")) {
							if (nodeName.equals("wfKor")) {
								if (i==0) {
								 weather = nodeValue;
								}
							}
						}
					}
				}
			}
			JsonObject obj = new JsonObject();
			obj.addProperty("weather", weather);
			Gson gson = new Gson();
			out.println(gson.toJson(obj));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
