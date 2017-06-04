/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.WorkTimeOt;
import dao.CompanyDAO;
import dao.EmpDAO;
import dao.WorkTime_OtDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author dinhgiabao28081997
 */
@WebServlet(name = "A09Servlet", urlPatterns = {"/A09Servlet"})
public class A09Servlet extends HttpServlet {

    private EmpDAO emp = new EmpDAO();
    private CompanyDAO com = new CompanyDAO();
    private WorkTime_OtDAO worktimeOT = new WorkTime_OtDAO();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
           HttpSession session = request.getSession(true);
        if (action.equals("getEmp")) {
            String comCode = request.getParameter("comCode");

            request.setAttribute("company", comCode.trim());
            request.setAttribute("allCom", com.getAll());
            request.setAttribute("allEmp", emp.getEmpByComCode(comCode));

            request.getRequestDispatcher("/A09.jsp").forward(request, response);
        }
        if (action.equals("getworkTime_OT")) {
            String empCode = request.getParameter("employee");
            String dateFrom = request.getParameter("dateFrom").replace("-", "");
            String dateTo = request.getParameter("dateTo").replace("-", "");
            request.setAttribute("empCode", session.getAttribute("Emp_Code"));
            request.setAttribute("company", emp.getEmployee(session.getAttribute("Emp_Code").toString()).getComCode().trim());
            request.setAttribute("allCom", com.getAll());
            request.setAttribute("allEmp", emp.getEmpByComCode(emp.getEmployee(session.getAttribute("Emp_Code").toString()).getComCode()));
            request.setAttribute("listOT", worktimeOT.getWorkTime_Ot(empCode,dateFrom,dateTo));
          
            request.getRequestDispatcher("/A09.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
