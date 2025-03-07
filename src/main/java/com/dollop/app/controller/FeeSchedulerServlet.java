package com.dollop.app.controller;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.dollop.app.bean.Student;
import com.dollop.app.service.IAdminService;
import com.dollop.app.service.impl.AdminServiceImpl;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;


@WebServlet(name = "FeeSchedulerServlet", loadOnStartup = 1)
public class FeeSchedulerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private IAdminService adminService = null;
    private ScheduledExecutorService scheduler;
    
    public FeeSchedulerServlet() {
        super();
        
        
    }

    @Override
    public void init() {
    	this.adminService = new AdminServiceImpl();
        scheduler = Executors.newSingleThreadScheduledExecutor();

         // Schedule fee generation every 24 hours
         scheduler.scheduleAtFixedRate(this::generateFees, 0, 24, TimeUnit.HOURS);
    }

    private void generateFees() {
        try{
            List<Student> students = adminService.getAllStudents();
            YearMonth currentMonth = YearMonth.now();
            String monthName = currentMonth.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM"));

            for (Student student : students) {
                LocalDate joiningDate = student.getJoining_date().toLocalDateTime().toLocalDate();;
                YearMonth firstEligibleMonth = YearMonth.from(joiningDate).plusMonths(1);

                if (!currentMonth.isBefore(firstEligibleMonth)) { // Check if one month is completed
                    if (!adminService.isFeeGenerated(student.getId(), monthName)) {
                        adminService.generateMonthlyFee(student.getId(), adminService.getRoomByRoomId(student.getRoomId()).getPayment(), monthName);  
                        System.out.println("Fee generated for " + student.getName() + " for " + monthName);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void destroy() {
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdown();
        }
    }

}
