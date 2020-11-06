public class CompositePattern {
    public static void main(String[] args) {
        Component winForm = new Component("WINDOW", "窗口");

        Component logo = new Component("Picture", "LOGO图片");
        Component login = new Component("Button", "登录");
        Component register = new Component("Button", "注册");

        Component frame = new Component("Frame", "FRAME1");
        Component username = new Component("Lable", "用户名");
        Component textbox = new Component("TexBox", "文本框");
        Component password = new Component("Lable", "密码");
        Component passwordBox = new Component("PasswordBox", "密码框");
        Component checkbox = new Component("CheckBox", "复选框");
        Component remeber = new Component("TextBox", "记住姓名");
        Component forgot = new Component("LinkLable", "忘记密码");

        winForm.add(logo);
        winForm.add(login);
        winForm.add(register);

        frame.add(username);
        frame.add(textbox);
        frame.add(password);
        frame.add(passwordBox);
        frame.add(checkbox);
        frame.add(remeber);
        frame.add(forgot);

        winForm.add(frame);

        System.out.println(winForm);
        for (Component c : winForm.getComponents()) {
            System.out.println(c);
            for (Component f : c.getComponents()) {
                System.out.println(f);
            }
        }
    }
}
