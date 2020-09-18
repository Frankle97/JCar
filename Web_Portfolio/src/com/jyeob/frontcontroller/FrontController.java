package com.jyeob.frontcontroller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jyeob.boardcontroller.NoticeDelete;
import com.jyeob.boardcontroller.NoticeDetail;
import com.jyeob.boardcontroller.NoticeEdit;
import com.jyeob.boardcontroller.NoticeList;
import com.jyeob.boardcontroller.NoticeListSearch;
import com.jyeob.boardcontroller.NoticeListSearchOption;
import com.jyeob.boardcontroller.NoticeWrite;
import com.jyeob.boardcontroller.ReviewDetail;
import com.jyeob.boardcontroller.ReviewEdit;
import com.jyeob.boardcontroller.ReviewList;
import com.jyeob.boardcontroller.ReviewRemove;
import com.jyeob.boardcontroller.ReviewWrite;
import com.jyeob.boardcontroller.SendEmail;
import com.jyeob.carcontroller.AddSellerList;
import com.jyeob.carcontroller.BuyCar;
import com.jyeob.carcontroller.CarDetail;
import com.jyeob.carcontroller.CarListRemove;
import com.jyeob.membercontroller.Action;
import com.jyeob.membercontroller.AddListFinal;
import com.jyeob.membercontroller.AddListFinal_Admin;
import com.jyeob.membercontroller.CheckAcs;
import com.jyeob.membercontroller.JoinAction;
import com.jyeob.membercontroller.LoginAction;
import com.jyeob.membercontroller.LogoutAction;
import com.jyeob.membercontroller.main;
import com.jyeob.membercontroller.mypageBringSell;
import com.jyeob.membercontroller.mypageOrderSell;
import com.jyeob.membercontroller.mypageOrderSellCancel;
import com.jyeob.membercontroller.mypageReleaseSell;
import com.jyeob.membercontroller.mypageRemoveSell;
import com.jyeob.membercontroller.userChangeInfoAction;
import com.jyeob.membercontroller.userChangePWAction;
import com.jyeob.membercontroller.userFindIDAction;
import com.jyeob.membercontroller.userFindPWAction;
import com.jyeob.membercontroller.userList;
import com.jyeob.membercontroller.userRemove;
import com.jyeob.membercontroller.userRemoveForAdmin;

/**
 * Servlet implementation class frontcontroller
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		action(request,response);
	}
	private void action(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String path = request.getRequestURI().substring(request.getContextPath().length());
		
		Action comm = null;
		if (path.equals("/joinTerms.do")) {
			request.getRequestDispatcher("/member/joinTerms.jsp").forward(request, response);
		} else if (path.equals("/joinClient.do")) {
			request.getRequestDispatcher("/member/joinClient.jsp").forward(request, response);
		} else if (path.equals("/joinAct.do")) {
			comm = new JoinAction();
			comm.execute(request, response);
		} else if (path.equals("/joinCom.do")) {
			request.getRequestDispatcher("/member/joinCom.jsp").forward(request, response);
		} else if (path.equals("/findIDView.do")) {
			request.getRequestDispatcher("/member/userFindID.jsp").forward(request, response);
		} else if (path.equals("/findID_suc.do")) {
			request.getRequestDispatcher("/member/userFindID_Success.jsp").forward(request, response);
		} else if (path.equals("/findID.do")) {
			comm = new userFindIDAction();
			comm.execute(request, response);
		} else if (path.equals("/findPWView.do")) {
			request.getRequestDispatcher("/member/userFindPW.jsp").forward(request, response);
		} else if (path.equals("/findPW.do")) {
			comm = new userFindPWAction();
			comm.execute(request, response);
		} else if (path.equals("/findPW_suc.do")) {
			request.getRequestDispatcher("/member/userFindPW_Success.jsp").forward(request, response);
		} else if (path.equals("/changePW.do")) {
			comm = new userChangePWAction();
			comm.execute(request, response);
		} else if (path.equals("/loginView.do")) {
			request.getRequestDispatcher("/member/userLogin.jsp").forward(request, response);
		} else if (path.equals("/login.do")) {
			comm = new LoginAction();
			comm.execute(request, response);
		} else if (path.equals("/myPageView.do")) {
			request.getRequestDispatcher("/member/userMypage.jsp").forward(request, response);
		} else if (path.equals("/logout.do")) {
			comm = new LogoutAction();
			comm.execute(request, response);
		} else if (path.equals("/editInfo.do")) {
			request.getRequestDispatcher("/member/userEditInfo.jsp").forward(request, response);
		} else if (path.equals("/CheckAcs.do")) {
			comm = new CheckAcs();
			comm.execute(request, response);
		} else if (path.equals("/changeMyInfo.do")) {
			request.getRequestDispatcher("/member/changeMyInfo.jsp").forward(request, response);
		} else if (path.equals("/changeInfo.do")) {
			comm = new userChangeInfoAction();
			comm.execute(request, response);
		} else if (path.equals("/showNotice.do")) {
			comm = new NoticeList();
			comm.execute(request, response);
		} else if (path.equals("/writeNotice.do")) {
			comm = new NoticeWrite();
			comm.execute(request, response);
		} else if (path.equals("/car.do")) {
			comm = new main();
			comm.execute(request, response);
		} else if (path.equals("/detailNotice.do")) {
			comm = new NoticeDetail();
			comm.execute(request, response);
		} else if (path.equals("/userRemoveForm.do")) {
			request.getRequestDispatcher("/member/userRemove.jsp").forward(request, response);
		} else if (path.equals("/removeAccount.do")) {
			comm = new userRemove();
			comm.execute(request, response);
		} else if (path.equals("/writeNoticeView.do")) {
			request.getRequestDispatcher("/boardNotice/noticeBoard_Write.jsp").forward(request, response);
		} else if (path.equals("/deleteNotice.do")) {
			comm = new NoticeDelete();
			comm.execute(request, response);
		} else if (path.equals("/editNoticeView.do")) {
			request.getRequestDispatcher("/boardNotice/noticeBoard_Edit.jsp").forward(request, response);
		} else if (path.equals("/editNotice.do")) {
			comm = new NoticeEdit();
			comm.execute(request, response);
		} else if (path.equals("/showReview.do")) {
			comm = new ReviewList();
			comm.execute(request, response);
		} else if (path.equals("/detailReview.do")) {
			comm = new ReviewDetail();
			comm.execute(request, response);
		} else if (path.equals("/editReviewForm.do")) {
			request.getRequestDispatcher("/boardReview/reviewBoard_Edit.jsp").forward(request, response);
		} else if (path.equals("/editReview.do")) {	
			comm = new ReviewEdit();
			comm.execute(request, response);
		} else if (path.equals("/noticeSearch.do")) {
			comm = new NoticeListSearch();
			comm.execute(request, response);
		} else if (path.equals("/noticeSearchS.do")) {
			comm = new NoticeListSearchOption();
			comm.execute(request, response);
		} else if (path.equals("/adminPage.do")) {
			comm = new userList();
			comm.execute(request, response);
		} else if (path.equals("/adminEditUser.do")) {
			request.getRequestDispatcher("/admin/adminEditUserInfo.jsp").forward(request, response);
		} else if (path.equals("/userRemoveForAdmin.do")) {
			comm = new userRemoveForAdmin();
			comm.execute(request, response);
		} else if (path.equals("/carList.do")) {
			request.getRequestDispatcher("/buyCar/carList.jsp").forward(request, response);
		} else if (path.equals("/sendEmailView.do")) {
			request.getRequestDispatcher("/admin/send_mail.jsp").forward(request, response);
		} else if (path.equals("/sendEmail.do")) {
			comm = new SendEmail();
			comm.execute(request, response);
		} else if (path.equals("/joinSelect.do")) {
			request.getRequestDispatcher("/member/joinSelect.jsp").forward(request, response);
		} else if (path.equals("/carListSearch.do")) {
			request.getRequestDispatcher("/buyCar/carList.jsp").forward(request, response);	
		} else if (path.equals("/requestSellCar.do")) {
			request.getRequestDispatcher("/sellCar/addSellCar_ForCitizen.jsp").forward(request, response);
		} else if (path.equals("/requestSellCarAdd.do")) {
			comm = new AddSellerList();
			comm.execute(request, response);
		} else if (path.equals("/myPageOrderSell.do")) {
			comm = new mypageOrderSell();
			comm.execute(request, response);
		} else if (path.equals("/myPageOrderSellCancel.do")) {
			comm = new mypageOrderSellCancel();
			comm.execute(request, response);
		} else if (path.equals("/sellerMyPage.do")) {
			request.getRequestDispatcher("/member/sellerMyPagePosition.jsp").forward(request, response);
		} else if (path.equals("/sellerMyPageList.do")) {
			request.getRequestDispatcher("/member/sellerMyPageList.jsp").forward(request, response);
		} else if (path.equals("/mypageBringSell.do")) {
			comm = new mypageBringSell();
			comm.execute(request, response);
		} else if (path.equals("/mypageAddList.do")) {
			request.getRequestDispatcher("/sellCar/addSellCarList.jsp").forward(request, response);
		} else if (path.equals("/mypageReleaseSell.do")){
			comm = new mypageReleaseSell();
			comm.execute(request, response);
		} else if (path.equals("/addListFinal.do")) {
			comm = new AddListFinal(); 
			comm.execute(request, response);
		} else if (path.equals("/adminSellerList.do")) {
			request.getRequestDispatcher("/admin/adminSellerPageList.jsp").forward(request, response);
		} else if (path.equals("/mypageRemoveSell.do")) {
			comm = new mypageRemoveSell();
			comm.execute(request, response);
		} else if (path.equals("/detailCar.do")) {
			comm = new CarDetail();
			comm.execute(request, response);
		} else if (path.equals("/buyCar.do")) {
			comm = new BuyCar();
			comm.execute(request, response);
		} else if (path.equals("/addListFinal_Admin.do")) {
			comm = new AddListFinal_Admin(); 
			comm.execute(request, response);
		} else if (path.equals("/addListFinal_Admin_Page.do")) {
			request.getRequestDispatcher("/sellCar/addSellCarList_forAdmin.jsp").forward(request, response);
		} else if (path.equals("/review_write.do")) {
			request.getRequestDispatcher("/boardReview/reviewBoard_Write.jsp").forward(request, response);
		} else if (path.equals("/writeReview.do")) {
			comm = new ReviewWrite();
			comm.execute(request, response);
		} else if (path.equals("/removeReview.do")) {
			comm = new ReviewRemove();
			comm.execute(request, response);
		} else if (path.equals("/removeCarlist.do")) {
			comm = new CarListRemove();
			comm.execute(request, response);
		}
	}

}
