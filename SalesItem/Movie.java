import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Movie {
    private String title;
    private int year;
    private ArrayList<Review> reviews;

    public Movie(String title, int year) {
        this.title = title;
        this.year = year;
        this.reviews = new ArrayList<Review>();
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public int getNumberOfReviews() {
        return reviews.size();
    }

    public boolean addReview(String reviewer, String text, int rating) {
        if (rating < 1 || rating > 5) {
            return false; // invalid rating
        }
        if (findReviewByReviewer(reviewer) != null) {
            return false; // same reviewer not allowed twice
        }
        reviews.add(new Review(reviewer, text, rating));
        return true;
    }

    public void removeReview(int index) {
        if (index >= 0 && index < reviews.size()) {
            reviews.remove(index);
        }
    }

    public void upvoteReview(int index) {
        if (index >= 0 && index < reviews.size()) {
            reviews.get(index).upvote();
        }
    }

    public void downvoteReview(int index) {
        if (index >= 0 && index < reviews.size()) {
            reviews.get(index).downvote();
        }
    }

    public void showInfo() {
        System.out.println("*** " + title + " (" + year + ") ***");
        System.out.println("Number of reviews: " + reviews.size());
        System.out.println("Customer reviews:");
        for (Review review : reviews) {
            System.out.println("--------------------------------");
            System.out.println(review.getFullDetails());
        }
        System.out.println("================================");
    }

    public Review findMostHelpfulReview() {
        if (reviews.isEmpty()) {
            return null;
        }
        Iterator<Review> it = reviews.iterator();
        Review best = it.next();
        while (it.hasNext()) {
            Review current = it.next();
            if (current.getVoteBalance() > best.getVoteBalance()) {
                best = current;
            }
        }
        return best;
    }

    private Review findReviewByReviewer(String reviewer) {
        for (Review r : reviews) {
            if (r.getReviewer().equals(reviewer)) {
                return r;
            }
        }
        return null;
    }

    // ⬇️ TARUH method main() DI SINI, sebelum tanda "}" terakhir
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan judul film: ");
        String title = input.nextLine();

        System.out.print("Masukkan tahun rilis: ");
        int year = input.nextInt();
        input.nextLine(); // clear buffer

        Movie movie = new Movie(title, year);

        System.out.println("\n=== Tambahkan Review ===");
        String lagi;
        do {
            System.out.print("Nama reviewer: ");
            String reviewer = input.nextLine();

            System.out.print("Komentar: ");
            String comment = input.nextLine();

            System.out.print("Rating (1-5): ");
            int rating = input.nextInt();
            input.nextLine();

            boolean success = movie.addReview(reviewer, comment, rating);
            if (!success) {
                System.out.println("⚠️ Review gagal ditambahkan (duplikat atau rating invalid).");
            }

            System.out.print("Tambah review lagi? (y/n): ");
            lagi = input.nextLine();
        } while (lagi.equalsIgnoreCase("y"));

        System.out.println("\n=== Semua Review ===");
        movie.showInfo();

        System.out.println("\n=== Voting Review ===");
        System.out.print("Masukkan indeks review yang ingin di-upvote (mulai dari 0): ");
        int upvoteIndex = input.nextInt();
        movie.upvoteReview(upvoteIndex);

        System.out.print("Masukkan indeks review yang ingin di-downvote (mulai dari 0): ");
        int downvoteIndex = input.nextInt();
        movie.downvoteReview(downvoteIndex);

        System.out.println("\n=== Setelah Voting ===");
        movie.showInfo();

        Review best = movie.findMostHelpfulReview();
        if (best != null) {
            System.out.println("\nReview paling membantu:");
            System.out.println(best.getFullDetails());
        } else {
            System.out.println("Belum ada review.");
        }

        input.close();
    }
}
