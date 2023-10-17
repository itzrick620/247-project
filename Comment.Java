import java.util.ArrayList;
import java.util.Date;

public class Comment {
    private String userInput;
    private User user;
    private Date date;
    ArrayList<Comment> replies;

    public void Comment(String userInput, User user) {}

    public void Comment(String userInput, User user, Date date, ArrayList<Comment> replies) {}
}