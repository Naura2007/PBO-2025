public class Review {
    private String reviewer;
    private String comment;
    private int rating; // 1 - 5
    private int voteBalance;

    public Review(String reviewer, String comment, int rating) {
        this.reviewer = reviewer;
        this.comment = comment;
        this.rating = rating;
        this.voteBalance = 0;
    }

    public String getReviewer() {
        return reviewer;
    }

    public int getRating() {
        return rating;
    }

    public int getVoteBalance() {
        return voteBalance;
    }

    public void upvote() {
        voteBalance++;
    }

    public void downvote() {
        voteBalance--;
    }

    public String getFullDetails() {
        return "Reviewer: " + reviewer + "\n"
            + "Rating: " + rating + "/5\n"
            + "Helpfulness Balance: " + voteBalance + "\n"
            + "Comment: " + comment;
    }
}
