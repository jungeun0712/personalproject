package action.board;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;

import action.ActionForward;
import model.Board;
import model.BoardDao;
import model.Comment;
import model.CommentDao;
import model.Icon;
import model.IconDao;
import model.Member;
import model.MemberDao;
import model.Question;
import model.QuestionDao;
import model.Warning;
import model.WarningDao;

public class BoardAllAction {
	private BoardDao dao = new BoardDao();
	private MemberDao mdao = new MemberDao();
	private CommentDao cdao = new CommentDao();
	private QuestionDao qdao = new QuestionDao();
	private WarningDao wdao = new WarningDao();
	private IconDao idao = new IconDao();
	protected String login;  
	protected String id;
	
	//�α���
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) {
		String msg = "�ش� id�� �������� �ʽ��ϴ�.";
		String url = "loginForm.do";
		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		Member mem = new MemberDao().selectOne(id);
		if(mem != null) {
			if(pass.equals(mem.getPass())) {
				request.getSession().setAttribute("login",id);
				msg = mem.getName() +"���� �α��� �ϼ̽��ϴ�.";
				url = "../board/list3.do?type=3";
			} else {
				msg = "��й�ȣ�� Ʋ���ϴ�.";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	//ȸ������
	public ActionForward join(HttpServletRequest request, HttpServletResponse response) {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setTel(request.getParameter("tel"));
		mem.setEmail(request.getParameter("email"));
		String msg = "ȸ������ ����";
		String url = "joinForm.do";
		if(mdao.insert(mem) > 0) {
			msg = "ȸ������ ����";
			url = "loginForm.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	//����
	public ActionForward main(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
		id = request.getParameter("id");
		if(login == null || login.trim().equals("")) {
			request.setAttribute("msg", "�α��� �� �ŷ��ϼ���");
			request.setAttribute("url", "loginForm.do");
			return new ActionForward(false,"../alert.jsp");
		}
		return new ActionForward();
	}

	public ActionForward bmain(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
		id = request.getParameter("id");
		if(login == null || login.trim().equals("")) {
			request.setAttribute("msg", "�α��� �� �ŷ��ϼ���");
			request.setAttribute("url", "loginForm.do");
			return new ActionForward(false,"../alert.jsp");
		}
		return new ActionForward();
	}
	//�α׾ƿ�
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) {
		String msg = "�α׾ƿ��Ǿ����ϴ�.";
		String url = "loginForm.do";
		request.getSession().invalidate();
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false,"../alert.jsp");
	}
	//��й�ȣ ã��
	public ActionForward pw(HttpServletRequest request, HttpServletResponse response) {
	MemberDao dao = new MemberDao();
	String id = request.getParameter("id");
	String name = request.getParameter("name");
	String pw = dao.pwSearch(id,name);
	if(pw != null) {  //��й�ȣ ã�� ����
		request.setAttribute("pass", pw.substring(2,pw.length()));
		return new ActionForward();
	} else {   //��й�ȣ ã�� ����
	  request.setAttribute("msg", "������ �´� ��й�ȣ�� ã�� �� �����ϴ�.");
	  request.setAttribute("url", "pwForm.do");
	  return new ActionForward(false,"../alert.jsp");
		}
	}
	//���̵� ã��
	public ActionForward id(HttpServletRequest request, HttpServletResponse response) {
		MemberDao dao = new MemberDao();
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String id = dao.idSearch(name,email);
		String msg = null;
		String url = null;
		if(id != null) { //id ã�� ����
			id = id.substring(0,id.length()-2);
			request.setAttribute("id", id);
			return new ActionForward();
		}else { //id ã�� ����
			msg = "�ش� ������ �´� ���̵� �����ϴ�";
			url = "idForm.do";
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
		 return new ActionForward(false,"../alert.jsp");
		}
	}
	
	//ȸ����������
	public ActionForward meminfo(HttpServletRequest request, HttpServletResponse response) {
		id = request.getParameter("id");
		Member info = new MemberDao().selectOne(id);
		request.setAttribute("info", info);
		return new ActionForward();
	}
	
	//ȸ����������
	public ActionForward memupdate(HttpServletRequest request, HttpServletResponse response) {
		Member mem = new Member();
		mem.setId(request.getParameter("id"));
		mem.setPass(request.getParameter("pass"));
		mem.setName(request.getParameter("name"));
		mem.setTel(request.getParameter("tel"));
		mem.setEmail(request.getParameter("email"));
		MemberDao dao = new MemberDao();
		Member dbmem = dao.selectOne(mem.getId());
		String msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
		String url = "updateForm.do?id="+mem.getId();
		if(login.equals("admin") || mem.getPass().equals(dbmem.getPass())) {
			if(dao.update(mem) > 0) {
				  msg = "�����Ϸ�";
				  url = "meminfo.do?id="+mem.getId();
			  } else {
				  msg = "��������";
				  url = "main.do";
			  }
		}
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		return new ActionForward(false,"../alert.jsp");
	}
	
	//ȸ�� ��й�ȣ �����ϱ�
	public ActionForward password(HttpServletRequest request, HttpServletResponse response) {
		 String login = (String)request.getSession().getAttribute("login");
			boolean  opener = false;
			boolean  closer = false;
			String  msg="��й�ȣ ���� �Դϴ�. Ȯ���ϼ���.";
			String  url = "passwordForm.do";
			if(login == null || login.trim().equals("")) {  //�α׾ƿ� ����
				opener = true;
			    closer = true;
				msg = "�α��� �ϼ���.";
				url = "loginForm.do";
			} else { //�α��� ����
			  String pass= request.getParameter("pass");
			  String chgpass= request.getParameter("chgpass");
			  MemberDao dao = new MemberDao();
			  Member mem = dao.selectOne(login);
			  if(pass.equals(mem.getPass())) {//�Էµ� ��й�ȣ�� db�� ����� ��й�ȣ�� ���� ���
				  closer = true;
				  if(dao.updatePass(login,chgpass) > 0) {
					  opener = true;
					  msg="��й�ȣ�� ����Ǿ����ϴ�.";
					  url = "meminfo.do?id=" + login;
				  } else {
					  msg="��й�ȣ ����� ������ �߻� �߽��ϴ�.";
				  }
			  }
			}
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			request.setAttribute("closer", closer);
			request.setAttribute("opener", opener);
			return new ActionForward();
		}
	
	//ȸ����Ϻ��� (�����ڸ�)
	public ActionForward memlist(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
	    if(login==null||login.trim().equals("")){
	    	request.setAttribute("msg", "�α����� �ʿ��մϴ�.");
	    	request.setAttribute("url", "loginForm.do");
	    	return new ActionForward(false,"../alert.jsp");
	    } else if(!login.equals("admin")){
	    	request.setAttribute("msg", "�����ڸ� ������ �ŷ� �Դϴ�.");
	    	request.setAttribute("url", "../board/list3.do?type=3");
	    	return new ActionForward(false,"../alert.jsp");
	    }
	    MemberDao dao = new MemberDao();
		request.setAttribute("list", dao.list());
		return new ActionForward();
	}
	
	//ȸ��Ż���ϱ�
	public ActionForward memdelete(HttpServletRequest request, HttpServletResponse response) {
		id=request.getParameter("id");
		if(id.equals("admin")) {
			request.setAttribute("msg","�����ڴ� Ż�� ���� �ʽ��ϴ�.");
			request.setAttribute("url", "list.do");
			return new ActionForward(false,"../alert.jsp");
		}
		String pass = request.getParameter("pass");
	    MemberDao dao = new MemberDao();
		Member mem = dao.selectOne(id); //db ���� ��ȸ
		String msg = id + "���� ��й�ȣ�� Ʋ���ϴ�.";
        String url = "deleteForm.do?id=" + id;
        //Ż�� ��� ����
		if(login.equals("admin") || pass.equals(mem.getPass())) {
		   if(dao.delete(id) > 0) { 
			 if(login.equals("admin")) {  //�������� ���
				msg =id + " ����ڸ� ���� Ż�� ����";
			    url = "list.do";
			 } else {  //�Ϲݻ������ ���
				msg =id + "����  ȸ�� Ż�� �Ϸ�Ǿ����ϴ�.";
			    url = "loginForm.do";
			    request.getSession().invalidate();
			 }
		   } else {  //���� ����
			 msg = id +"���� Ż��� ���� �߻�.";
			 if(login.equals("admin")) {  //�������� ���
				url = "list.do";
			 } else {  //�Ϲݻ������ ���
				url = "meminfo.do?id="+id;
			 }
		   }
	   }
		request.setAttribute("msg",msg);
		request.setAttribute("url",url);
		return new ActionForward(false,"../alert.jsp");
    }
	/**
	 * 1. multipart/form-data Ÿ���� �����̹Ƿ� 
	 *    MultipartRequest ��ü ���� 
	 * 2. �Ķ���� ����* model.Board ��ü ����. 
	 * 3. �Խù� ��ȣ num ���� ��ϵ� num�� �ִ밪�� ��ȸ. 
	 *    �ִ밪 +1 ��ϵ� �Խù��� ��ȣ.
	 *    db���� maxnum �� ���ؼ� +1 ������ num �����ϱ� 
	 * 4. board ������ db�� ����ϱ�. 
	 *    ��ϼ��� : �޼��� ���.list.do ������ �̵� 
	 *    ��� ���� : �޼��� ���. writeForm.me ������ �̵�
	 */
	//�ϱ���Խ��Ǿ���
	public ActionForward write(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		type = 1;
		Member write = new MemberDao().selectOne(id);
		request.setAttribute("write", write);
		String msg = "�Խù� ��� ����";
		String url = "writeForm.do?id="+id+"&type="+1;
		String path = request.getServletContext().getRealPath("/") + "model2/board/file/";
		try {
			File f = new File(path);
			if (!f.exists())
				f.mkdirs();
			MultipartRequest multi = new MultipartRequest(request, path, 10 * 1024 * 1024, "euc-kr");
			Board b = new Board();
			b.setId(multi.getParameter("id"));
			b.setType(Integer.parseInt(multi.getParameter("type")));
			b.setCheckpass(multi.getParameter("checkpass"));
			b.setSubject(multi.getParameter("subject"));
			b.setContent(multi.getParameter("content"));
			b.setFile1(multi.getFilesystemName("file1"));
			int num = dao.maxnum();
			b.setNum(++num);
			b.setGrp(num);
			if (dao.insert(b)) {
				msg = "�Խù� ��� ����";
				url = "list.do?id="+id+"&type="+1;
			}
		} catch (IOException e) {
			throw new ServletException(e);
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}

	/**
	 * 1. ���������� 10���� �Խù��� ����ϱ�. 
	 *    pageNum �Ķ���Ͱ��� ���� => ���� ���� 1�� �����ϱ�. 
	 *  2. �ֱ� ��ϵ� �Խù��� ���� ���� ��ġ��. 
	 *  3. ȭ�鿡 �ʿ��� ������ �Ӽ����� ���.
	 */
	//�ϱ���Խ��Ǹ�Ϻ���
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("-------------------" + id);
		int limit = 10;
		int pageNum = 1;
		type = 1;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = dao.boardCount(column,find, id, type);
		List<Board> list = dao.list(pageNum, limit,column,find, id,type);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardcnt", boardcnt);
		String today = 
		   new SimpleDateFormat("yyyyMMdd").format(new Date());
		request.setAttribute("today", today);
		return new ActionForward();
	}
	
	//�ϱ��� �󼼺���
	public ActionForward info(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		Board b = dao.selectOne(num, id, type);
		// request.getRequestURI() :
		// /jspstudy2/model2/board/info.do
		if (request.getRequestURI().contains("/board/info.do")) {
			dao.readcntadd(num);
		}
		request.setAttribute("b", b);
		return new ActionForward();
	}
	
	/*
	 * CKEDITOR���� �̹����� �Խ��ǳ��뿡 �߰��ϱ�
	 */
	public ActionForward imgupload
	   (HttpServletRequest request, HttpServletResponse response)
			   throws IOException {
		String path = request.getServletContext().getRealPath("/")
				+ "model2/board/imgfile/";
		File f = new File(path);
		if (!f.exists())	f.mkdirs();
		MultipartRequest multi = new MultipartRequest
				   (request, path, 10 * 1024 * 1024, "euc-kr");
		String fileName = multi.getFilesystemName("upload");
		request.setAttribute("fileName", fileName);
		request.setAttribute("CKEditorFuncNum",
				request.getParameter("CKEditorFuncNum"));
		return new ActionForward(false, "ckeditor.jsp");
	}	
	/**
	 * 1. �Ķ������������ Board ��ü ����. 
	 * 2. ��й�ȣ ����
	 *    ��й�ȣ ��ġ �ϴ� ��� 
	 *       ���� �Ķ������ �������� �ش� �Խù��� �����������ϱ�. 
	 *       ÷�������� ������ ���� ��� file2 �Ķ������ ������ �ٽ� �����ϱ� 
	 *    ��й�ȣ ����ġ 
	 *       ��й�ȣ ���� �޼��� ����ϰ�,updateForm.do�� ������ �̵� 
	 * 3. �������� :
	 *     �������� �޽��� ��� �� info.do ������ �̵� 
	 *    �������� :
	 *     �������� �޽��� ��� �� updateForm.do ������ �̵�
	 */
	//�ϱ��� ����
	public ActionForward update(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		String path = request.getServletContext().getRealPath("/")
				+ "model2/board/file/";
		MultipartRequest multi = new MultipartRequest
				(request, path, 10 * 1024 * 1024, "euc-kr");
		Board board = new Board();
		board.setId(multi.getParameter("id"));
		board.setNum(Integer.parseInt(multi.getParameter("num")));
		board.setType(Integer.parseInt(multi.getParameter("type")));
		board.setCheckpass(multi.getParameter("checkpass"));
		board.setSubject(multi.getParameter("subject"));
		board.setContent(multi.getParameter("content"));
		board.setFile1(multi.getFilesystemName("file1"));
		//÷������ ������ �ȵ� ���
		if (board.getFile1() == null || 
				                board.getFile1().equals("")) {
			board.setFile1(multi.getParameter("file2"));
		}
		Board dbBoard = dao.selectOne(board.getNum(),board.getId(),board.getType());
		String msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
		String url = "updateForm.do?num=" + board.getNum()+"&id="+board.getId()+"&type="+1;
		if (board.getCheckpass().equals(dbBoard.getCheckpass())) {
			if (dao.update(board)) {
				msg = "�Խù� ���� �Ϸ�";
				url = "info.do?num=" + board.getNum()+"&id="+board.getId()+"&type="+1;
			} else {
				msg = "�Խù� ���� ����";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
/**
   1. num,pass �Ķ���͸� ������ ����.
   2. �Էµ� ��й�ȣ�� db ��й�ȣ ����
            Ʋ����� : ��й�ȣ ���� �޽��� ���, deleteForm.do ������ �̵�
   3. ��й�ȣ�� �´� ��� : �Խù� ����.
           ���� ���� : ���� ���� �޽��� ���, list.do ������ �̵�
           ���� ���� : ���� ���� �޽��� ���, info.do ������ �̵�
 */
	//�ϱ��� ����
	public ActionForward delete(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int type = Integer.parseInt(request.getParameter("type"));
		String id = request.getParameter("id"); 
		String pass = request.getParameter("pass"); 
		String msg = "�Խñ��� ��й�ȣ�� Ʋ�Ƚ��ϴ�";
		String url = "deleteForm.do?num=" + num + "&id=" + id + "&type=" + 1;
		Board board = dao.selectOne(num, id, type);
		if(board == null) {
			msg = "���� �Խñ��Դϴ�.";
			url = "list.do?num=" + num + "&id=" + id + "&type=" + 1;
		} else {
		  if (pass.equals(board.getCheckpass())) {
			if (dao.delete(num)) {
				msg = "�Խñ� ���� ����";
				url = "list.do?num=" + num + "&id=" + id + "&type=" + 1;
			} else {
				msg = "�Խñ� ���� ����";
				url = "info.do?num=" + num + "&id=" + id + "&type=" + 1;
			}
		  }
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//Q&A �ϱ��� ��Ϻ���
	public ActionForward list2(HttpServletRequest request, HttpServletResponse response) {
		String id=request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("-------------------" + id);
		int limit = 10;
		int pageNum = 1;
		type=2;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = dao.boardCount(column,find, id, type);
		List<Board> list = dao.list(pageNum, limit,column,find, id, type);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		String subject = dao.selectOne3();
		System.out.println(subject);
		System.out.println(subject);
		System.out.println(subject);
		System.out.println(subject);
		System.out.println(subject);
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("subject", subject);
		String today = 
		   new SimpleDateFormat("yyyyMMdd").format(new Date());
		request.setAttribute("today", today);
		return new ActionForward();
	}
	//Q&A �ϱ��� �۾���
	public ActionForward write2(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		type = 2;
		Member write = new MemberDao().selectOne(id);
		request.setAttribute("write2", write);
		String msg = "�Խù� ��� ����";
		String url = "writeForm2.do?id="+id+"&type="+2;
		Board b = new Board();
		b.setId(request.getParameter("id"));
		b.setType(Integer.parseInt(request.getParameter("type")));
		b.setCheckpass(request.getParameter("checkpass"));
		b.setSubject(request.getParameter("subject"));
		b.setContent(request.getParameter("content"));
		int num = dao.maxnum();
		b.setNum(++num);
		b.setGrp(num);
		if (dao.insert(b)) {
			msg = "�Խù� ��� ����";
			url = "list2.do?id="+id+"&type="+2;
		} 
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//Q&A �ϱ��� �󼼺���
	public ActionForward info2(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		Board b = dao.selectOne(num, id, type);
		String subject = dao.selectOne3();
		System.out.println(subject);
		System.out.println(subject);
		System.out.println(subject);
		// request.getRequestURI() :
		// /jspstudy2/model2/board/info.do
		if (request.getRequestURI().contains("/board/info2.do")) {
			dao.readcntadd(num);
		}
		request.setAttribute("b", b);
		request.setAttribute("subject", subject);
		return new ActionForward();
	}
	//Q&A �ϱ��� ����
	public ActionForward update2(HttpServletRequest request, 
	HttpServletResponse response) throws IOException {
		Board board = new Board();
		board.setId(request.getParameter("id"));
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setType(Integer.parseInt(request.getParameter("type")));
		board.setCheckpass(request.getParameter("checkpass"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		Board dbBoard = dao.selectOne(board.getNum(),board.getId(),board.getType());
		String msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
		String url = "updateForm2.do?num=" + board.getNum()+"&id="+board.getId()+"&type="+2;
		if (board.getCheckpass().equals(dbBoard.getCheckpass())) {
			if (dao.update(board)) {
				msg = "�Խù� ���� �Ϸ�";
				url = "info2.do?num=" + board.getNum()+"&id="+board.getId()+"&type="+2;
			} else {
				msg = "�Խù� ���� ����";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//Q&A �ϱ��� ����
	public ActionForward delete2(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int type = Integer.parseInt(request.getParameter("type"));
		String id = request.getParameter("id"); 
		String pass = request.getParameter("pass"); 
		String msg = "�Խñ��� ��й�ȣ�� Ʋ�Ƚ��ϴ�";
		String url = "deleteForm2.do?num=" + num + "&id=" + id + "&type=" + 2;
		Board board = dao.selectOne(num, id, type);
		if(board == null) {
			msg = "���� �Խñ��Դϴ�.";
			url = "list2.do?num=" + num + "&id=" + id + "&type=" + 2;
		} else {
		  if (pass.equals(board.getCheckpass())) {
			if (dao.delete(num)) {
				msg = "�Խñ� ���� ����";
				url = "list2.do?num=" + num + "&id=" + id + "&type=" + 2;
			} else {
				msg = "�Խñ� ���� ����";
				url = "info2.do?num=" + num + "&id=" + id + "&type=" + 2;
			}
		  }
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//��ΰԽ��� ��Ϻ���
	public ActionForward list3(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		System.out.println("-------------------" + id);
		int type = Integer.parseInt(request.getParameter("type"));
		int limit = 10;
		int pageNum = 1;
		type = 3;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = dao.boardCount2(column,find, type);
		List<Board> list = dao.list2(pageNum, limit,column,find, type);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		String today = 
		   new SimpleDateFormat("yyyyMMdd").format(new Date());
		request.setAttribute("today", today);
		return new ActionForward();
	}
	//��ΰԽ��� �۾���
	public ActionForward write3(HttpServletRequest request, HttpServletResponse response)  throws ServletException, IOException{
		id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		type = 3;
		String msg = "�Խù� ��� ����";
		String url = "writeForm3.do?type="+3;
		Board b = new Board();
		b.setId(request.getParameter("id"));
		b.setType(Integer.parseInt(request.getParameter("type")));
		b.setKeyword(Integer.parseInt(request.getParameter("keyword")));
		b.setCheckpass(request.getParameter("checkpass"));
		b.setSubject(request.getParameter("subject"));
		b.setContent(request.getParameter("content"));
		int num = dao.maxnum();
		b.setNum(++num);
		b.setGrp(num);
		if (dao.insert(b)) {
			msg = "�Խù� ��� ����";
			url = "list3.do?type="+3;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//��� �Խ��� �󼼺���
	public ActionForward info3(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		String id = request.getParameter("id");
		int type = Integer.parseInt(request.getParameter("type"));
		Board b = dao.selectOne2(num, type);
		List<Comment> c = cdao.list(num);
		if (request.getRequestURI().contains("/board/info3.do")) {
			dao.readcntadd(num);
		}
		request.setAttribute("b", b);
		request.setAttribute("c", c);
		return new ActionForward();
	}
	//��� �Խ��� ����
	public ActionForward update3(HttpServletRequest request, 
	HttpServletResponse response) throws IOException {
		Board board = new Board();
		board.setId(request.getParameter("id"));
		board.setNum(Integer.parseInt(request.getParameter("num")));
		board.setType(Integer.parseInt(request.getParameter("type")));
		board.setKeyword(Integer.parseInt(request.getParameter("keyword")));
		board.setCheckpass(request.getParameter("checkpass"));
		board.setSubject(request.getParameter("subject"));
		board.setContent(request.getParameter("content"));
		Board dbBoard = dao.selectOne2(board.getNum(),board.getType());
		String msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
		String url = "updateForm3.do?num=" + board.getNum()+"&type="+3;
		if (board.getCheckpass().equals(dbBoard.getCheckpass())) {
			if (dao.update(board)) {
				msg = "�Խù� ���� �Ϸ�";
				url = "info3.do?num=" + board.getNum()+"&type="+3;
			} else {
				msg = "�Խù� ���� ����";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//��� �Խ��� ����
	public ActionForward delete3(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		int type = Integer.parseInt(request.getParameter("type"));
		String id = request.getParameter("id"); 
		String pass = request.getParameter("pass"); 
		String msg = "�Խñ��� ��й�ȣ�� Ʋ�Ƚ��ϴ�";
		String url = "deleteForm3.do?num=" + num + "&type=" + 3;
		Board board = dao.selectOne2(num, type);
		if(board == null) {
			msg = "���� �Խñ��Դϴ�.";
			url = "list3.do?num=" + num + "&type=" + 3;
		} else {
		  if (pass.equals(board.getCheckpass())) {
			if (dao.delete(num)) {
				msg = "�Խñ� ���� ����";
				url = "list3.do?num=" + num + "&type=" + 3;
			} else {
				msg = "�Խñ� ���� ����";
				url = "info3.do?num=" + num + "&type=" + 3;
			}
		  }
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	/**
	 * 1. �Ķ���� ���� Board ��ü�� �����ϱ� 
	 *    �������� : num, grp, grplevel, grpstep 
	 *    ������� : name,pass, subject, content => ������� 
	 * 2. ���� grp ���� ����ϴ� �Խù����� grpstep ���� 1 ���� �ϱ�.
	 *     void BoardDao.grpStepAdd(grp,grpstep)
	 * 3. Board ��ü�� db�� insert �ϱ�. 
	 *    num : maxnum + 1 
	 *    grp : ���۰� ����. 
	 *    grplevel : ���� + 1 
	 *    grpstop : ���� + 1
	 */
	//��� �Խ��� ��۾���
	public ActionForward reply(HttpServletRequest request, HttpServletResponse response) {
		Board b = new Board();
		Comment c = new Comment();
		b.setType(Integer.parseInt(request.getParameter("type")));
		c.setId(request.getParameter("id"));
		c.setNum(Integer.parseInt(request.getParameter("num")));
		c.setComment(request.getParameter("comment"));
		int seq = cdao.maxseq();
		String url = "info3.do?type="+3+"&num="+c.getNum();
		c.setSeq(++seq);
		if (cdao.insert(c)) {
			url = "info3.do?type="+3+"&num="+c.getNum();
		}
		request.setAttribute("url", url);
		return new ActionForward(false, "../url.jsp");
	}
	
	//��ΰԽ��� ��� ���
//	public ActionForward list4(HttpServletRequest request, HttpServletResponse response) {
//		int seq = Integer.parseInt(request.getParameter("seq"));
//		int num = Integer.parseInt(request.getParameter("num"));
//		String column = request.getParameter("column");
//		String find = request.getParameter("find");
//		if(column != null && column.trim().equals("")) 
//			 column = null;
//		if(find != null && find.trim().equals("")) 
//			find = null;
//		if(column == null || find == null) {
//			column = null;
//			find = null;
//		}
//		int boardcnt = cdao.boardCount(column,find, seq ,num);
//		List<Comment> list = cdao.list(column,find ,seq ,num);
//		request.setAttribute("boardcnt", boardcnt);
//		request.setAttribute("list", list);
//		return new ActionForward();
//	}
	
	//Q&A ���� ���� ���
	public ActionForward list5(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
	    if(login==null||login.trim().equals("")){
	    	request.setAttribute("msg", "�α����� �ʿ��մϴ�.");
	    	request.setAttribute("url", "loginForm.do");
	    	return new ActionForward(false,"../alert.jsp");
	    } else if(!login.equals("admin")){
	    	request.setAttribute("msg", "�����ڸ� ������ �ŷ� �Դϴ�.");
	    	request.setAttribute("url", "../board/list3.do?type=3");
	    	return new ActionForward(false,"../alert.jsp");
	    }
		int limit = 10;
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = qdao.boardCount(column,find);
		List<Question> list = qdao.list(pageNum, limit,column,find);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		return new ActionForward();
	}
	//Q&A ���� ����
	public ActionForward write5(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		String msg = "�Խù� ��� ����";
		String url = "writeForm5.do";
		Question q = new Question();
		q.setQuestion(request.getParameter("question"));
		q.setPw(request.getParameter("pw"));
		q.setToday(request.getParameter("today"));
		int num = qdao.maxnum();
		q.setNum(++num);
		if (qdao.insert(q)) {
			msg = "�Խù� ��� ����";
			url = "list5.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//Q&A ���� �� ����
	public ActionForward info5(HttpServletRequest request, HttpServletResponse response) {
		int num = Integer.parseInt(request.getParameter("num"));
		Question q = new QuestionDao().selectOne(num);
		request.setAttribute("q", q);
		return new ActionForward();
	}
	//Q&A ���� ����
	public ActionForward update5(HttpServletRequest request, 
	HttpServletResponse response) throws IOException {
		Question q = new Question();
		q.setNum(Integer.parseInt(request.getParameter("num")));
		q.setPw(request.getParameter("pw"));
		q.setQuestion(request.getParameter("question"));
		q.setToday(request.getParameter("today"));
		Question qboard = qdao.selectOne(q.getNum());
		String msg = "��й�ȣ�� Ʋ�Ƚ��ϴ�.";
		String url = "updateForm5.do?num=" + q.getNum();
		if (q.getPw().equals(qboard.getPw())) {
			if (qdao.update(q)) {
				msg = "�Խù� ���� �Ϸ�";
				url = "info5.do?num=" + q.getNum();
			} else {
				msg = "�Խù� ���� ����";
			}
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	//�Ű��ϱ�
	public ActionForward warn(HttpServletRequest request, HttpServletResponse response) {
		Warning w = new Warning();
		w.setNum(Integer.parseInt(request.getParameter("num")));
		w.setSeq(Integer.parseInt(request.getParameter("seq")));
		w.setId(request.getParameter("id"));
		w.setWarnid(request.getParameter("warnid"));
		w.setReason(request.getParameter("reason"));
		String msg = "�Ű� ��� ����";
		String url = "warnwrite.do?seq="+w.getSeq()+"&num="+w.getNum()+"&warnid="+w.getWarnid();
		if (wdao.insert(w)) {
			msg = "�Ű� ��� ����";
			url = "warnForm.do";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	public ActionForward warnForm(HttpServletRequest request, HttpServletResponse response) {
		login = (String)request.getSession().getAttribute("login");
	    if(login==null||login.trim().equals("")){
	    	request.setAttribute("msg", "�α����� �ʿ��մϴ�.");
	    	request.setAttribute("url", "loginForm.do");
	    	return new ActionForward(false,"../alert.jsp");
	    } else if(!login.equals("admin")){
	    	request.setAttribute("msg", "�����ڸ� ������ �ŷ� �Դϴ�.");
	    	request.setAttribute("url", "../board/list3.do?type=3");
	    	return new ActionForward(false,"../alert.jsp");
	    }
		int limit = 10;
		int pageNum = 1;
		try {
			pageNum = Integer.parseInt
					       (request.getParameter("pageNum"));
		} catch (NumberFormatException e) {}
		String column = request.getParameter("column");
		String find = request.getParameter("find");
		if(column != null && column.trim().equals("")) 
			 column = null;
		if(find != null && find.trim().equals("")) 
			find = null;
		if(column == null || find == null) {
			column = null;
			find = null;
		}
		int boardcnt = wdao.boardCount(column,find);
		List<Warning> list = wdao.list(pageNum, limit,column,find);
		int maxpage = (int) ((double) boardcnt / limit + 0.95);
		int startpage = ((int) (pageNum / 10.0 + 0.9) - 1) * 10 + 1;
		int endpage = startpage + 9;
		if (endpage > maxpage)
			endpage = maxpage;
		int boardnum = boardcnt - (pageNum - 1) * limit;
		request.setAttribute("boardcnt", boardcnt);
		request.setAttribute("list", list);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("boardnum", boardnum);
		request.setAttribute("pageNum", pageNum);
		return new ActionForward();
	}
	//�Ű� �Ϸ� ��� ����
	public ActionForward warndelete(HttpServletRequest request, 
			HttpServletResponse response) throws IOException {
		int seq = Integer.parseInt(request.getParameter("seq"));
		String msg = "�Ű� �Ϸ� ��� ���� ����";
		String url = "warnForm.do";
		Warning w = wdao.selectOne(seq);
		if(w == null) {
			msg = "���� �Խñ��Դϴ�.";
			url = "warnForm.do";
		} else {
			if (wdao.delete(seq)) {
				msg = "�Ű� �Ϸ� ��� ���� ����";
				url = "warnForm.do";
			} 
		  }
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		return new ActionForward(false, "../alert.jsp");
	}
	
	public ActionForward infocalForm(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		return new ActionForward();
	}
	//�⼮üũ ���� ����
	public ActionForward infocal(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		boolean opener = false;
		boolean closer = true;
		login = (String)request.getSession().getAttribute("login");
		String msg = "�ٽ� �Է����ּ���";
		String url = "infocalForm.do";
		Icon i = new Icon();
		i.setId(login);
		i.setIcon(Integer.parseInt(request.getParameter("icon")));
		if (idao.insert(i)) {
			msg = "�Խù� ��� ����";
			url = "cal.do";
			opener = true;
		}
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		request.setAttribute("opener", opener);
		request.setAttribute("closer", closer);
		return new ActionForward(false, "infoCal.jsp");
	}
}
