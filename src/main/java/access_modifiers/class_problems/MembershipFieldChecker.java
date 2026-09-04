package access_modifiers.class_problems;

public class MembershipFieldChecker {

    
    // Method 1: Check whether access is allowed
    
    static String classifyAccess(String fieldModifier, String accessorContext) {

        fieldModifier = fieldModifier.trim().toLowerCase();
        accessorContext = accessorContext.trim().toUpperCase();

        // private -> only same class
        if (fieldModifier.equals("private")) {

            if (accessorContext.equals("SAME_CLASS")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // default -> same class or same package
        if (fieldModifier.equals("default")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // protected -> same class, same package,
        // or appropriate inherited access from another package
        if (fieldModifier.equals("protected")) {

            if (accessorContext.equals("SAME_CLASS")
                    || accessorContext.equals("SAME_PACKAGE")
                    || accessorContext.equals("DIFFERENT_PACKAGE")) {
                return "ALLOWED";
            }

            return "DENIED";
        }

        // public -> accessible everywhere
        if (fieldModifier.equals("public")) {
            return "ALLOWED";
        }

        // Unknown modifier
        return "DENIED";
    }


    
    // Method 2: Summarize all access attempts
    
    static String summarizeByModifier(String[][] attempts) {

        int privateAllowed = 0;
        int privateDenied = 0;

        int defaultAllowed = 0;
        int defaultDenied = 0;

        int protectedAllowed = 0;
        int protectedDenied = 0;

        int publicAllowed = 0;
        int publicDenied = 0;

        // Go through every access attempt
        for (String[] attempt : attempts) {

            String modifier = attempt[0];
            String context = attempt[1];

            // Ask method 1 to determine the result
            String result = classifyAccess(modifier, context);

            // Count the result according to the modifier
            if (modifier.equalsIgnoreCase("private")) {

                if (result.equals("ALLOWED")) {
                    privateAllowed++;
                } else {
                    privateDenied++;
                }

            } else if (modifier.equalsIgnoreCase("default")) {

                if (result.equals("ALLOWED")) {
                    defaultAllowed++;
                } else {
                    defaultDenied++;
                }

            } else if (modifier.equalsIgnoreCase("protected")) {

                if (result.equals("ALLOWED")) {
                    protectedAllowed++;
                } else {
                    protectedDenied++;
                }

            } else if (modifier.equalsIgnoreCase("public")) {

                if (result.equals("ALLOWED")) {
                    publicAllowed++;
                } else {
                    publicDenied++;
                }
            }
        }

        return "private: " + privateAllowed + " allowed / "
                + privateDenied + " denied | "
                + "default: " + defaultAllowed + " allowed / "
                + defaultDenied + " denied | "
                + "protected: " + protectedAllowed + " allowed / "
                + protectedDenied + " denied | "
                + "public: " + publicAllowed + " allowed / "
                + publicDenied + " denied";
    }


    
    // LibraryMember class
    
    static class LibraryMember {

        private String membershipId;
        String branchCode;
        protected double fineAmount;
        public String displayName;


        // Parameterized constructor
        public LibraryMember(String membershipId,
                             String branchCode,
                             double fineAmount,
                             String displayName) {

            // Handle null safely
            String trimmedId;

            if (membershipId == null) {
                trimmedId = "";
            } else {
                trimmedId = membershipId.trim();
            }

            // Validate membership ID
            if (trimmedId.isEmpty() || trimmedId.length() < 4) {
                throw new IllegalArgumentException(
                        "Membership ID must contain at least 4 characters."
                );
            }

            this.membershipId = trimmedId;
            this.branchCode = branchCode;
            this.fineAmount = fineAmount;
            this.displayName = displayName;
        }


        // Getter for private membershipId
        public String getMembershipId() {
            return membershipId;
        }
    }


    // --------------------------------------------------
    // Main method - testing the program
    // --------------------------------------------------
    public static void main(String[] args) {

        // Example 1
        System.out.println(
                classifyAccess("private", "SAME_CLASS")
        );


        // Example 2
        System.out.println(
                classifyAccess("protected", "DIFFERENT_PACKAGE")
        );


        // Example 3
        String[][] attempts = {
                {"private", "SAME_CLASS"},
                {"private", "SAME_PACKAGE"},
                {"default", "SAME_PACKAGE"},
                {"default", "DIFFERENT_PACKAGE"},
                {"protected", "SAME_CLASS"},
                {"protected", "SAME_PACKAGE"},
                {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(
                summarizeByModifier(attempts)
        );


        // Example 4: Valid LibraryMember
        LibraryMember member = new LibraryMember(
                "LIB9",
                "BR1",
                0,
                "Priya Nair"
        );

        System.out.println(
                "Member created: " + member.getMembershipId()
        );


        // Example 5: Invalid LibraryMember
        try {

            LibraryMember invalidMember = new LibraryMember(
                    "LB9",
                    "BR1",
                    0,
                    "Priya Nair"
            );

        } catch (IllegalArgumentException e) {

            System.out.println("Construction rejected");
        }
    }
}
