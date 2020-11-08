/**
 * 类加载机制测试
 * <p>
 * jvm有严格的规定（五种情况）：
 * <p>
 * 1.遇到new，getstatic，putstatic，invokestatic这4条字节码指令时，假如类还没进行初始化，
 * 则马上对其进行初始化工作。
 * 其实就是3种情况：用new实例化一个类时、读取或者设置类的静态字段时（不包括被final修饰的静态字段，
 * 因为他们已经被塞进常量池了）、以及执行静态方法的时候。
 * <p>
 * 2.使用java.lang.reflect.*的方法对类进行反射调用的时候，
 * 如果类还没有进行过初始化，马上对其进行。
 * <p>
 * 3.初始化一个类的时候，如果他的父亲还没有被初始化，则先去初始化其父亲。
 * <p>
 * 4.当jvm启动时，用户需要指定一个要执行的主类（包含static void main(String[] args)的那个类），
 * 则jvm会先去初始化这个类。
 * 5.用Class.forName(String className);来加载类的时候，也会执行初始化动作。
 * 注意:ClassLoader的loadClass(String className);方法只会加载并编译某类，并不会对其执行初始化。
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("我是main方法，我输出Super的类变量i：" + Sub.i);
        Sub sub = new Sub();
    }
}

class Super {
    {
        System.out.println("我是Super成员块");
    }

    public Super() {
        System.out.println("我是Super构造方法");
    }

    {
        int j = 123;
        System.out.println("我是Super成员块中的变量j：" + j);
    }

    static {
        System.out.println("我是Super静态块");
        i = 123;
    }

    protected static int i = 1;
}

class Sub extends Super {
    static {
        System.out.println("我是Sub静态块");
    }

    public Sub() {
        System.out.println("我是Sub构造方法");
    }

    {
        System.out.println("我是Sub成员块");
    }
}
