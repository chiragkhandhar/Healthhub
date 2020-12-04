import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/WriteReview")
public class WriteReview extends HttpServlet 
{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {		
		response.setContentType("text/html");
		PrintWriter pw = response.getWriter();
        Utilities Utility = new Utilities(request, pw);		
        displayView(request, response);
	}
	
    protected void displayView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {	    
        response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        Utilities utility = new Utilities(request,pw);
        
        if(!utility.isLoggedin())
        {
            HttpSession session = request.getSession(true);				
            session.setAttribute("login_msg", "Please Login to add items to cart");
            response.sendRedirect("Login");
            return;
        }
        String productname = request.getParameter("name");		
        String productType = request.getParameter("type");
        String productMaker = request.getParameter("maker");
        String productPrice = request.getParameter("price");
        HttpSession session = request.getSession(); 

        //get the order product details	on clicking submit the form will be passed to submitorder page	
        User user = utility.getUser();			
        String userName = session.getAttribute("username").toString();
        
        utility.printHtml("Header.html");
        utility.printHtml("LeftNavigationBar.html");

        pw.print("<form name ='WriteReview' action='SubmitReview' method='post'>");
        pw.print("<div id='content'><div class='post'><h2 class='title meta'>");
		pw.print("<a style='font-size: 24px;'>Review</a>");
		pw.print("</h2><div class='entry'>");
        pw.print("<table class='gridtable'>");
		pw.print("<tr><td> Name: </td><td>");
		pw.print(productname);
		pw.print("<input type='hidden' name='productname' value='" + productname + "'>");
		pw.print("</td></tr>");
        pw.print("<tr><td> Type:</td><td>");
        pw.print(productType);
        pw.print("<input type='hidden' name='producttype' value='" + productType + "'>");
		pw.print("</td></tr>");
		pw.print("<tr><td> Price:</td><td>");
        pw.print("$ " + productPrice);
        pw.print("<input type='hidden' name='productprice' value='" + productPrice + "'>");
		pw.print("</td></tr>");		
        pw.print("<tr><td> Category: </td><td>");
        pw.print(productMaker);
		pw.print("<input type='hidden' name='productmaker' value='"+productMaker+"'>");
        pw.print("</td></tr><table>");
		
        pw.print("<table><tr></tr><tr></tr><tr><td> Rating: </td>");
        pw.print("<td>");
        pw.print("<select name='reviewrating'>");
        pw.print("<option value='1' selected>1</option>");
        pw.print("<option value='2'>2</option>");
        pw.print("<option value='3'>3</option>");
        pw.print("<option value='4'>4</option>");
        pw.print("<option value='5'>5</option>");  
        pw.print("</td></tr>");
		
		pw.print("<tr>");
        pw.print("<td> User Address: </td>");
        pw.print("<td> <input type='text' name='address'> </td>");
        pw.print("</tr>");		
    
        pw.print("<tr>");
        pw.print("<td> User Zip Code: </td>");
        pw.print("<td> <input type='text' name='zipcode'> </td>");
        pw.print("</tr>");		


        pw.print("<tr>");
        pw.print("<td> User City: </td>");
        pw.print("<td> <input type='text' name='retailercity'> </td>");
        pw.print("</tr>");							
		
        pw.print("<tr>");
        pw.print("<td> Review Date: </td>");
        pw.print("<td> <input type='date' name='reviewdate'> </td>");
        pw.print("</tr>");				

        pw.print("<tr>");
        pw.print("<td> Your Review: </td>");
        pw.print("<td><textarea name='reviewtext' rows='4' cols='50'> </textarea></td></tr>");
        pw.print("<tr><td colspan='2'><input type='submit' class='btnbuy' name='SubmitReview' value='SubmitReview'></td></tr></table>");

        pw.print("</h2></div></div></div>");		



            
        utility.printHtml("Footer.html");
	   		
		}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	    {
		response.setContentType("text/html");
        PrintWriter pw = response.getWriter();
        displayView(request, response);
	    }
}
