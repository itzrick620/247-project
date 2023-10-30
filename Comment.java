import java.util.ArrayList;
import java.util.Date;

/**
 * The Comment class represents a comment with various properties, including user input, user information, date, and replies.
 */
public class Comment {
    private String userInput;
    private User user;
    private Date date;
    ArrayList<Comment> replies;

    /**
     * Constructs a new Comment object with the given user input and user information.
     * The date is automatically set to the current date, and there are no initial replies.
     *
     * @param userInput The text of the comment provided by the user.
     * @param user     The user who posted the comment.
     */
    public Comment(String userInput, User user) {
        this.userInput = userInput;
        this.user = user;
        this.date = new Date();
        this.replies = new ArrayList<>();
    }

    /**
     * Constructs a new Comment object with the given user input, user information, date, and replies.
     *
     * @param userInput The text of the comment provided by the user.
     * @param user     The user who posted the comment.
     * @param date     The date and time when the comment was posted.
     * @param replies  A list of replies to this comment.
     */
    public Comment(String userInput, User user, Date date, ArrayList<Comment> replies) {
        this.userInput = userInput;
        this.user = user;
        this.date = date;
        this.replies = replies;
    }

    /**
     * Gets the user input of the comment.
     *
     * @return The text of the comment provided by the user.
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Gets the user who posted the comment.
     *
     * @return The user who posted the comment.
     */
    public User getUser() {
        return user;
    }

    /**
     * Gets the date and time when the comment was posted.
     *
     * @return The date and time of the comment.
     */
    public Date getDate() {
        return date;
    }

    /**
     * Gets a list of replies to this comment.
     *
     * @return A list of comments that are replies to this comment.
     */
    public ArrayList<Comment> getReplies() {
        return replies;
    }

    /**
     * Sets the user input of the comment.
     *
     * @param userInput The text of the comment provided by the user.
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Sets the user who posted the comment.
     *
     * @param user The user who posted the comment.
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Sets the date and time when the comment was posted.
     *
     * @param date The date and time of the comment.
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * Sets a list of replies to this comment.
     *
     * @param replies A list of comments that are replies to this comment.
     */
    public void setReplies(ArrayList<Comment> replies) {
        this.replies = replies;
    }
}

