class BrokenLibraryMember {
    // WRONG: making these static means there's only ONE shared copy across every
    // BrokenLibraryMember object. Each field should belong to one specific member,
    // but static erases that — the second object's constructor call overwrites
    // the first object's data instead of getting its own.
    static String name;
    static String memberId;
    static int booksIssued;

    public BrokenLibraryMember(String name, String memberId, int booksIssued) {
        BrokenLibraryMember.name = name;
        BrokenLibraryMember.memberId = memberId;
        BrokenLibraryMember.booksIssued = booksIssued;
    }
}

class LibraryMember {
    // Instance fields — each member needs independent data
    private String name;
    private String memberId;
    private int booksIssued;

    // Static fields — genuinely shared across ALL members
    private static String libraryName = "SRM Central Library";
    private static int memberCount = 0;

    public LibraryMember(String name, int booksIssued) {
        this.name = name;
        memberCount++;
        this.memberId = "LM-" + (1000 + memberCount);
        this.booksIssued = booksIssued;
    }

    public void printMemberCard() {
        System.out.println(name + " | " + memberId);
    }

    public static void printTotalMembers() {
        System.out.println("Total members: " + memberCount);
    }

    public static void main(String[] args) {
        System.out.println("Broken version:");
        new BrokenLibraryMember("Aditi", "LM-1001", 2);
        new BrokenLibraryMember("Rohan", "LM-1002", 1);
        System.out.println(BrokenLibraryMember.name);
        System.out.println(BrokenLibraryMember.name);
        System.out.println("(Aditi's data was overwritten — both members now show \"Rohan\")");

        System.out.println();
        System.out.println("Fixed version:");
        LibraryMember m1 = new LibraryMember("Aditi", 2);
        LibraryMember m2 = new LibraryMember("Rohan", 1);
        m1.printMemberCard();
        m2.printMemberCard();
        LibraryMember.printTotalMembers();
    }
}