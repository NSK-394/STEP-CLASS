public class BookIssue {
    private String title;
    private String borrowerName;
    private int daysOverdue;

    public BookIssue(String title, String borrowerName, int daysOverdue) {
        this.title = title;
        this.borrowerName = borrowerName;
        this.daysOverdue = daysOverdue;
    }

    // Instance method — needs THIS specific book's daysOverdue, so it can't be static
    public double fineAmount() {
        return daysOverdue > 0 ? daysOverdue * 5 : 0;
    }

    public boolean isSeverelyOverdue() {
        return daysOverdue > 14;
    }

    // Static — it operates on a whole ARRAY of BookIssue objects, not one instance.
    // It belongs to the class as a concept ("total across all books"), not to any single book.
    public static double totalFineCollected(BookIssue[] issues) {
        double total = 0;
        for (BookIssue b : issues) {
            total += b.fineAmount();
        }
        return total;
    }

    public String getTitle() { return title; }
    public int getDaysOverdue() { return daysOverdue; }

    public static void main(String[] args) {
        BookIssue[] issues = {
            new BookIssue("Clean Code", "Aman", 18),
            new BookIssue("Effective Java", "Riya", 5),
            new BookIssue("Refactoring", "Karthik", 0),
            new BookIssue("DSA Handbook", "Sneha", 21),
            new BookIssue("Design Patterns", "Vikram", 9)
        };

        for (BookIssue b : issues) {
            String status = b.isSeverelyOverdue() ? "Severely overdue" : "OK";
            System.out.println(b.getTitle() + " - " + b.getDaysOverdue() + " days - " + status);
        }

        System.out.println("Total fine collected: Rs " + BookIssue.totalFineCollected(issues));
    }
}