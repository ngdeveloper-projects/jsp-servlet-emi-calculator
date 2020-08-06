package in.javadomain;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculateEMI
 */
@WebServlet("/CalculateEMI")
public class CalculateEMI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalculateEMI() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		   String amount = request.getParameter("amount");
	        Double inter = Double.parseDouble(request.getParameter("rate"));
	        String month = request.getParameter("month");
	 
	        double loanAmount = Double.parseDouble(amount);
	        double rateOfInterest =inter;
	        int numberOfMonths = Integer.parseInt(month);
	 
	          double temp = 1200;           //100*numberofmonths(12))
	          double interestPerMonth = rateOfInterest/temp;
	 
	          double onePlusInterestPerMonth = 1 + interestPerMonth;
	 
	          double powerOfOnePlusInterestPerMonth = Math.pow(onePlusInterestPerMonth,numberOfMonths);
	 
	          double powerofOnePlusInterestPerMonthMinusOne = powerOfOnePlusInterestPerMonth-1;
	 
	          double divides = powerOfOnePlusInterestPerMonth/powerofOnePlusInterestPerMonthMinusOne;
	 
	          double principleMultiplyInterestPerMonth = loanAmount * interestPerMonth;
	 
	          double totalEmi =  principleMultiplyInterestPerMonth*divides;
	 
	          double finalValue = Math.round( totalEmi * 100.0 ) / 100.0;
	 
		 request.setAttribute("emi_payable", finalValue);
		request.getRequestDispatcher("/emi.jsp").forward(request,response);
	}
}
