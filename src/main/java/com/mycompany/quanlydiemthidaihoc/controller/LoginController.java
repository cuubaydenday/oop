package com.mycompany.quanlydiemthidaihoc.controller;

import com.mycompany.quanlydiemthidaihoc.action.CheckLogin;
import com.mycompany.quanlydiemthidaihoc.entity.User;
import com.mycompany.quanlydiemthidaihoc.view.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private CheckLogin checkLogin;

    public LoginController(LoginView view) {
        this.loginView = view;
        this.checkLogin = new CheckLogin();
        view.addLoginListener(new LoginListener());
    }

    public void showLoginView() {
        loginView.setVisible(true);
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            User user = loginView.getUser();
            if ("admin".equals(user.getRole()) && checkLogin.checkAdmin(user)) {
                AdminMainView adminView = new AdminMainView();
                AdminController adminController = new AdminController(adminView);
                adminController.showAdminView();
                loginView.dispose();
            } else if ("student".equals(user.getRole()) && checkLogin.checkStudent(user, user.getUsername())) {
                StudentMainView studentView = new StudentMainView();
                StudentController studentController = new StudentController(studentView);
                studentController.showStudentView();
                loginView.dispose();
            } else {
                loginView.showMessage("Sai thông tin đăng nhập!");
            }
        }
    }
}
