package hello;

public class Greeting {

    private long id;
    private String content;

    public Greeting(long id, String content) {
       this.id = id;
       this.content = content;
    }

    public long getId() {
        return 1;
    }

    public String getContent() {
        return this.content;
    }
}
