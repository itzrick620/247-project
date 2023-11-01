import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Represents a comment associated with a project.
 */
public class Comment {

    private final UUID id; // Unique identifier for the comment
    private final String userInput; // The text content of the comment
    private final User user; // The user who posted the comment
    private Date date; // The date and time when the comment was posted
    private final ArrayList<Comment> replies; // Replies to this comment

    /**
     * Constructs a new Comment object with the given user input and user.
     *
     * @param userInput the text content of the comment
     * @param user       the user who posted the comment
     */
    public Comment(String userInput, User user) {
        this.id = UUID.randomUUID();
        this.userInput = userInput;
        this.user = user;
        this.date = new Date();
        this.replies = new ArrayList<>();
    }

    /**
     * Constructs a new Comment object with the given user input, user, date, and replies.
     *
     * @param userInput the text content of the comment
     * @param user       the user who posted the comment
     * @param date       the date and time when the comment was posted
     * @param replies    replies to this comment
     */
    public Comment(String userInput, User user, Date date, ArrayList<Comment> replies) {
        this.id = UUID.randomUUID();
        this.userInput = userInput;
        this.user = user;
        this.date = new Date(date.getTime());
        this.replies = new ArrayList<>(replies);
    }

    /**
     * Gets the unique identifier of the comment.
     *
     * @return the UUID of the comment
     */
    public UUID getId() {
        return id;
    }

    /**
     * Gets the date and time when the comment was posted.
     *
     * @return the date and time of the comment
     */
    public Date getDate() {
        return new Date(date.getTime());
    }

    /**
     * Gets the user who posted the comment.
     *
     * @return the user who posted the comment
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets the date and time when the comment was posted.
     *
     * @param date the date and time of the comment
     */
    public void setDate(Date date) {
        this.date = new Date(date.getTime());
    }

    /**
     * Sets the date and time of the comment from a string representation.
     *
     * @param dateString the date and time of the comment as a string
     * @throws ParseException if the date string cannot be parsed
     */
    public void setDate(String dateString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        this.date = dateFormat.parse(dateString);
    }

    /**
     * Retrieves the user input associated with this comment.
     *
     * @return A string representing the user's text input for this comment.
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * Adds a reply to this comment.
     *
     * @param reply the reply to add
     */
    public void addReply(Comment reply) {
        replies.add(reply);
    }

    /**
     * Retrieves the replies to this comment.
     *
     * @return an ArrayList of Comment objects representing the replies
     */
    public ArrayList<Comment> getReplies() {
        return new ArrayList<>(replies);
    }
}
