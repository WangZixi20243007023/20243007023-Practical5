package com.example.practicalfive;

/**
 * A plain Java helper class (not an Activity) that validates and normalises
 * municipality names entered by the user.
 */
public class TextProcessor {

    /**
     * Validates the given municipality name and returns the correctly
     * capitalised form, or null if the input is invalid.
     *
     * @param municipality the raw name entered by the user (may be null)
     * @return "Beijing", "Shanghai", "Tianjin" or "Chongqing", or null
     */
    public static String processMunicipality(String municipality) {
        // Reject null, empty or whitespace-only input.
        if (municipality == null || municipality.trim().isEmpty()) {
            return null;
        }

        String normalised = municipality.trim().toLowerCase();

        // Case-insensitive matching, e.g. "ChOnGqInG" -> "Chongqing".
        if (normalised.equals("beijing")) {
            return "Beijing";
        } else if (normalised.equals("shanghai")) {
            return "Shanghai";
        } else if (normalised.equals("tianjin")) {
            return "Tianjin";
        } else if (normalised.equals("chongqing")) {
            return "Chongqing";
        }

        // Unsupported name (e.g. "Guangzhou").
        return null;
    }
}
