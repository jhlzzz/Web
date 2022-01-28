<%@ page contentType="text/html;charset=utf-8"%>
	<jsp:useBean id="addrDAO" class="hak.mv.model.AddrDAO" scope="application"/>

<%!
	private int getSeq(HttpServletRequest request){
        int seq = -1;
		String seqStr = request.getParameter("seq");
		if(seqStr != null){
			seqStr = seqStr.trim();
			if(seqStr.length() != 0){
				try{
					seq = Integer.parseInt(seqStr); 
					return seq;
				}catch(NumberFormatException ne){
				}
			}
		}
		return seq;
	}
%>
<%   
	int seq = getSeq(request);
	if(seq != -1){
		addrDAO.delete(seq);
	}
	response.sendRedirect("list.jsp");
%>