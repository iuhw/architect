import java.util.ArrayList;
import java.util.List;

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
