package Member.Control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Member.domain.Member;
import Member.service.MemberService;

/**
 * Servlet implementation class MemberContoller
 */
@WebServlet("/member/mController")
public class MemberContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String choiceXword = null;
    public MemberContoller() {
        super();
  
    }


	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String memberMessage = request.getParameter("message");
		String checked = request.getParameter("checked");
		if(memberMessage != null) {
			memberMessage.trim();
			switch(memberMessage) {
				case "index": index(request , response); break;
				case "emailCheck" :	 emailCheck(request , response); break; 
				case "signup" :  signup(request , response); break;
				case "signform" :    signForm(request , response); break;
				case "login" : 	 
					String signCheck = request.getParameter("sign");
					if(signCheck.equals("loginCheck")) {
						login(request , response); break;
					} else if(signCheck.equals("signupCheck")) {
						signForm(request , response); break;
					}else if(signCheck.equals("index")) {
						index(request , response); break;
					}
				case "logout" : 	 logout(request , response); break;
				case "loginform" :   loginForm(request , response); break;
				case "memberManagement" : memberManagementList(request , response); break;
				case "myPage" : myPage(request , response); break;
				case "list" : list(request , response); break; 
				case "memberManagementChange" : {
										if(checked.equals("����")){
											 memberUpdate(request, response); break;
										} else if(checked.equals("ȸ���˻�")) {
											System.out.println("�� �� 2����"); 
											memberManagementSearch(request , response); break;
										} else if(checked.equals("��Ģ��г��Ӻ����ϱ�")) {
											XwordSerch(request, response); break;
										} else if(checked.equals("��Ģ���߰�")) {
											XwordInsert(request, response); break;
										}
				}
				case "memberManagementSearch" : {
										if(checked.equals("����")){
											 memberUpdate(request, response); break;
										} else if(checked.equals("ȸ���˻�")) {
											memberManagementSearch(request , response); break;
										} else if(checked.equals("��Ģ��г��Ӻ����ϱ�")) {
											XwordSerch(request, response); break;
										} else if(checked.equals("��Ģ���߰�")) {
											XwordInsert(request , response); break;
										}
				}
				case "memberManagementFindform" : memberManagementInfoForm(request , response); break;
				case "XwordSerch" :  XwordSerch(request, response); break;
				case "memberUpdateUserAdmin" :  memberUpdateAdmin(request, response); break;
				case "myPageUpdate" : myPageUpdate(request , response); break;			
				case "XwordInsert" : XwordInsert(request , response); break;
			}
		} else {
			loginForm(request , response);
		}
	}
	
	public void XwordInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		String Xword = request.getParameter("XwordInsert");
		boolean flag = service.XWordInserts(Xword);
		
		memberManagementList(request , response);
	}
	public void index(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../");
	}
	
	public void XwordSerch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		boolean flag = service.updateMemberNickNameXSelect();
		request.setAttribute("flag", flag);
		
		String view = "../Member/nickChange.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
		
	}
	public void myPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		HttpSession session = request.getSession();
		String email = session.getAttribute("Member_Email").toString();
		Member MemberInfoE = service.MemberInfo(email);
		String MemberLoc = MemberInfoE.getMemLoc();
		String MemberLocq[] = MemberLoc.split(" ");
		String MemberPostNumber = MemberLocq[0];
		MemberPostNumber.trim();
		String MemberAddress = "";
		String MemberUpAddress = "";
		if(MemberLocq.length==5) {
			for(int i=1;i<5;i++) {
					MemberAddress +=  MemberLocq[i] + " ";
				}
				MemberAddress.trim();
					if(MemberLocq.length <=5) {
						for(int i=5;i<MemberLocq.length;i++) {
							MemberUpAddress +=  MemberLocq[i] + " ";
						}
					} else {
						int splitLength = MemberLocq.length;
						MemberUpAddress = MemberLocq[splitLength];
					}
		} else if(MemberLocq.length == 4){ 
			for(int i=1;i<4;i++) {
				MemberAddress +=  MemberLocq[i] + " ";
			}
			MemberAddress.trim();
				if(MemberLocq.length <=4) {
					for(int i=4;i<MemberLocq.length;i++) {
						MemberUpAddress +=  MemberLocq[i] + " ";
					}
				} else {
					int splitLength = MemberLocq.length;
					MemberUpAddress = MemberLocq[splitLength];
				}
		} else {
			for(int i=1;i<MemberLocq.length;i++) {
				MemberAddress +=  MemberLocq[i] + " ";
			}
		}
		MemberUpAddress.trim();
		request.setAttribute("MemberInfoz", MemberInfoE);
		request.setAttribute("MemberLocPostNumber", MemberPostNumber);
		request.setAttribute("MemberLocAddress", MemberAddress);
		request.setAttribute("MemberLocUpAddress", MemberUpAddress);
		
		String view = "../Member/myPage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	
	public void  memberManagementInfoForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		String email = request.getParameter("email");
		Member MemberInfoE = service.MemberInfo(email);
		String MemberLoc = MemberInfoE.getMemLoc();
		String MemberLocq[] = MemberLoc.split(" ");
		String MemberPostNumber = MemberLocq[0];
		MemberPostNumber.trim();
		String MemberAddress = "";
		String MemberUpAddress = "";
		if(MemberLocq.length==5) {
			for(int i=1;i<5;i++) {
					MemberAddress +=  MemberLocq[i] + " ";
				}
				MemberAddress.trim();
					if(MemberLocq.length <=5) {
						for(int i=5;i<MemberLocq.length;i++) {
							MemberUpAddress +=  MemberLocq[i] + " ";
						}
					} else {
						int splitLength = MemberLocq.length;
						MemberUpAddress = MemberLocq[splitLength];
					}
		} else if(MemberLocq.length == 4){ 
			for(int i=1;i<4;i++) {
				MemberAddress +=  MemberLocq[i] + " ";
			}
			MemberAddress.trim();
				if(MemberLocq.length <=4) {
					for(int i=4;i<MemberLocq.length;i++) {
						MemberUpAddress +=  MemberLocq[i] + " ";
					}
				} else {
					int splitLength = MemberLocq.length;
					MemberUpAddress = MemberLocq[splitLength];
				}
		} else {
			for(int i=1;i<MemberLocq.length;i++) {
				MemberAddress +=  MemberLocq[i] + " ";
			}
		}
		MemberUpAddress.trim();
		request.setAttribute("MemberInfoz", MemberInfoE);
		request.setAttribute("MemberLocPostNumber", MemberPostNumber);
		request.setAttribute("MemberLocAddress", MemberAddress);
		request.setAttribute("MemberLocUpAddress", MemberUpAddress);
		
		String view = "../Member/MemberInfo.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	public void memberUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		response.setCharacterEncoding("utf-8");
		String email = request.getParameter("fnames");
		String category = request.getParameter("fname"); 
		String content = request.getParameter("memberInfoChange");
		boolean flag = service.updateMember(email , category , content);
		request.setAttribute("flag", flag);
		
		String view = "../Member/updateMember.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	public void myPageUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();  //postNumber 
		response.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String categoryChoicePwd = request.getParameter("pwd");
		String categoryChoiceNick = request.getParameter("nick");
		Integer categoryChoicePhone = Integer.parseInt(request.getParameter("phone"));
		Integer categoryChoiceLisence = Integer.parseInt(request.getParameter("lisence"));
		String categoryLoc1 = request.getParameter("postNumber");
		String categoryLoc2 = request.getParameter("Address");
		String categoryLoc3 = request.getParameter("upAddress");
		String categoryLoc = categoryLoc1 + categoryLoc2  + categoryLoc3;
		System.out.println(email +"  "+ categoryChoiceNick +"  "+ categoryChoicePwd);
		boolean flag = service.updateMemberAdmin(email, categoryChoicePwd , categoryChoiceNick , categoryChoicePhone , categoryChoiceLisence , categoryLoc);
		request.setAttribute("flag" , flag);
		
		String view = "../Member/myPageUpdateClear.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	//  ȸ������ �� ������ ������Ʈ
	public void memberUpdateAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();  //postNumber 
		response.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String categoryChoicePwd = request.getParameter("pwd");
		String categoryChoiceNick = request.getParameter("nick");
		Integer categoryChoicePhone = Integer.parseInt(request.getParameter("phone"));
		Integer categoryChoiceLisence = Integer.parseInt(request.getParameter("lisence"));
		String categoryLoc1 = request.getParameter("postNumber");
		String categoryLoc2 = request.getParameter("Address");
		String categoryLoc3 = request.getParameter("upAddress");
		String categoryLoc = categoryLoc1 + categoryLoc2  + categoryLoc3;
		System.out.println(email +"  "+ categoryChoiceNick +"  "+ categoryChoicePwd);
		boolean flag = service.updateMemberAdmin(email, categoryChoicePwd , categoryChoiceNick , categoryChoicePhone , categoryChoiceLisence , categoryLoc);
		request.setAttribute("flag" , flag);
		
		String view = "../Member/memberPage.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	public void memberManagementSearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		response.setCharacterEncoding("utf-8");
		Integer category = Integer.parseInt(request.getParameter("Search"));
		String values = request.getParameter("memberInfoSearch");
		ArrayList<Member> list = service.MemberFind(category ,values);
		request.setAttribute("searchList", list);
		
		ArrayList<String> listXword = service.XWordSelect();
		request.setAttribute("Xwordlist", listXword );
		
		String view = "../Member/MemberListManagementSearch.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	public void memberManagementList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		response.setCharacterEncoding("utf-8");
		ArrayList<Member> list = service.memberList();
		ArrayList<String> listXword = service.XWordSelect();
		
		request.setAttribute("Xwordlist", listXword );
		request.setAttribute("allList", list);
		
		String view = "../Member/MemberListManagement.jsp";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	public void emailCheck(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		response.setCharacterEncoding("UTF-8");
		String email = request.getParameter("email");
		PrintWriter script = response.getWriter();
		boolean flag = service.duplicateMember(email);
		if(flag) {
			script.print("not-usable");
			script.close();
		} else if(!flag){
			script.print("1");	
			script.close();
		}
		//request.setAttribute("flag", flag);
		////String view = "../Member/singup.jsp";
		////RequestDispatcher rd = request.getRequestDispatcher(view);
		//rd.forward(request, response);
	}
	public void list(HttpServletRequest request ,HttpServletResponse response ) throws ServletException, IOException {
		response.sendRedirect("../Member/list.jsp");
	}
	
	public void loginForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../Member/login.jsp");
	}
	
	public void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		// �α׾ƿ� ���� 
		Enumeration enum_SessionCheck = session.getAttributeNames();
		while(enum_SessionCheck.hasMoreElements()) {
			String session_name = enum_SessionCheck.nextElement().toString();
			String session_value = session.getAttribute(session_name).toString();
			System.out.println("sessionInvalidate before ���Ǹ� : " + session_name + "���� �� :" + session_value );
		}
		
		
		session.invalidate();
		
	    if (request.isRequestedSessionIdValid() == true) {
	        System.out.println("������ ���� ���� �մϴ�.");
	    } else {
	    	System.out.println("������ ���� �Ǿ����ϴ�.");
	    }
	    
		response.sendRedirect("../Member/login.jsp");
	}
	
	public void signForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("../Member/singup.jsp");
	}
	
	public void signup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		request.setCharacterEncoding("utf-8");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("name");
		String birthStr = request.getParameter("birth").trim();
		Date birth = null;
		if(birthStr == null || birthStr.equals("") || birthStr.equals(" ")) {
			response.sendRedirect("mController?message=signForm");
			try {
				birth = java.sql.Date.valueOf(birthStr);
			}catch(Exception e) {
			}
		}
		
		String nick = request.getParameter("nick");
		String phone = request.getParameter("phone");
		Date anni = java.sql.Date.valueOf("8888-01-01");
		System.out.print(request.getParameter("anni").toString());
		request.getParameter("anni").trim();
		if(request.getParameter("anni").equals(" ") || request.getParameter("anni").equals("")) {
			try {
				anni = java.sql.Date.valueOf("8888-01-01");
			}catch(Exception e) {
				throw new IllegalArgumentException(e);
			}
		} else {
			anni = java.sql.Date.valueOf(request.getParameter("anni"));
		}
		String postNumber =  request.getParameter("postNumber");
		String addressed = request.getParameter("Address");
		String upAddress = request.getParameter("upAddress");
		String address = postNumber + " " + addressed + " " + upAddress;
		int gender = Integer.parseInt(request.getParameter("gender"));
		int couple = Integer.parseInt(request.getParameter("couple"));
		int license = Integer.parseInt(request.getParameter("license"));
		Member m = new Member(email, -1 , name, birth, pwd, null , nick, phone, address, gender, anni, couple, license , 0 );
		boolean flag = service.saveService(m);
		
		request.setAttribute("flag", flag);
		
		String view = "mController?message=index";
		RequestDispatcher rd = request.getRequestDispatcher(view);
		rd.forward(request, response);
	}
	
	public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MemberService service = MemberService.getInstance();
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		response.setCharacterEncoding("utf-8");
		if(email == null || email.equals("") || pwd == null || pwd.equals("")) {
			if(email == null || email.equals("")) {
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert(\"email Check\");");
				pw.print("location.href='../Member/login.jsp'");
				pw.print("</script>");
				pw.close();
				
			} else if (pwd == null || pwd.equals("")) {
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert(\"password Check\");");
				pw.print("location.href='location.href='../Member/login.jsp'");
				pw.print("</script>");
				pw.close();
				
			}
		} else {
			ArrayList<Member> list = service.loginMember(email, pwd);
			HttpSession session = request.getSession();
			if(list != null) {
				for(Member sessionIn : list) {
					String sessionEmail = sessionIn.getEmail();
					String sessionGrade = sessionIn.getGrade();
					long sessionNumber = sessionIn.getMemNumber();
					String sessionName = sessionIn.getMemName();	
					Date sessionBirth = sessionIn.getBirth();
					Date sessionAnni = sessionIn.getAnni();
					String sessionNick = sessionIn.getNick();
					String sessionPhone = sessionIn.getMemPhone();
					int sessionGender = sessionIn.getGender();
					String sessionLoc = sessionIn.getMemLoc();
					int sessionCouple = sessionIn.getCouple();
					int sessionLicense = sessionIn.getLicense();
					int sessionorderCount = sessionIn.getOrderCount();
					
					session.setAttribute("Member_Email" , sessionEmail);
					session.setAttribute("Member_Number" , sessionNumber);
					session.setAttribute("Member_Nick" , sessionNick);
					session.setAttribute("Member_Grade" , sessionGrade);
					session.setAttribute("Member_Name" , sessionName);
					session.setAttribute("Member_Birth" , sessionBirth);
					session.setAttribute("Member_Phone" , sessionPhone);
					session.setAttribute("Member_Loc" , sessionLoc);
					session.setAttribute("Member_Gender" , sessionGender);
					session.setAttribute("Member_Anni" , sessionAnni);
					session.setAttribute("Member_Couple" , sessionCouple);
					session.setAttribute("Member_License" , sessionLicense);
					session.setAttribute("Member_OrderCount" , sessionorderCount);
					//boolean flag = true;
					//request.setAttribute("flag", flag);
					
					
					String view = "../Member/list.jsp";
					RequestDispatcher rd = request.getRequestDispatcher(view);
					rd.forward(request, response);
				}
			} else {
				PrintWriter pw = response.getWriter();
				pw.print("<script>");
				pw.print("alert(\"login fail\");");
				pw.print("location.href='../Member/login.jsp''");
				pw.print("</script>");
				pw.close();
			}
		}
	}

}

