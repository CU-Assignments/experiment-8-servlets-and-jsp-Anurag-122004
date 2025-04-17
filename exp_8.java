//Login servlet
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String user = req.getParameter("username");
    String pass = req.getParameter("password");

    if ("admin".equals(user) && "1234".equals(pass)) {
      res.getWriter().print("Welcome " + user + "!");
    } else {
      res.getWriter().print("Invalid credentials");
    }
  }
}

//Display Employee List from DB

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
  protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    try {
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
      Statement stmt = con.createStatement();
      ResultSet rs = stmt.executeQuery("SELECT * FROM employees");

      while (rs.next()) {
        res.getWriter().println(rs.getInt("id") + " - " + rs.getString("name") + "<br>");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}


//JSP-based Student Portal (Attendance Form)

<form action="SaveAttendance" method="post">
  Roll No: <input type="text" name="roll"><br>
  Date: <input type="date" name="date"><br>
  Status: <input type="text" name="status"><br>
  <input type="submit" value="Submit">
</form>


  //Servlet (SaveAttendance.java):
  @WebServlet("/SaveAttendance")
public class SaveAttendance extends HttpServlet {
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    String roll = req.getParameter("roll");
    String date = req.getParameter("date");
    String status = req.getParameter("status");

    try {
      Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "");
      PreparedStatement ps = con.prepareStatement("INSERT INTO attendance VALUES (?, ?, ?)");
      ps.setString(1, roll);
      ps.setString(2, date);
      ps.setString(3, status);
      ps.executeUpdate();
      res.getWriter().print("Attendance saved!");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
