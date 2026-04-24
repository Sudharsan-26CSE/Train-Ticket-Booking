package train;

public class Train {
    private String id;
    private String name;
    private String source;
    private String destination;

    public Train(String id, String name, String source, String destination) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return id + "||" + name + "||" + source + "||" + destination;
    }
}
