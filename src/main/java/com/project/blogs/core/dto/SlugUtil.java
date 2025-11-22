package com.project.blogs.core.dto;



public class SlugUtil {

    public static String toSlug(String input) {
        return input.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-") // replace spaces & special chars with dash
                .replaceAll("-+$", "")         // remove ending dashes
                .replaceAll("^-+", "");        // remove starting dashes
    }

}
