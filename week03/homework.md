1. 请在草稿纸上手写一个单例模式的实现代码，拍照提交作业。[照片](WechatIMG36.jpeg)
2. 请用组合设计模式编写程序，打印输出图 1 的窗口，窗口组件的树结构如图 2 所示，打印输出示例参考图 3，[示例图](java/week03_homework.png)。
```java
public class Component {
    private String name;
    private String value;
    private List<Component> components;

    public Component(String name, String value) {
        this.name = name;
        this.value = value;
        components = new ArrayList<>();
    }

    public void add(Component component) {
        this.components.add(component);
    }

    public List<Component> getComponents() {
        return this.components;
    }

    @Override
    public String toString() {
        return "Component{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}

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
```