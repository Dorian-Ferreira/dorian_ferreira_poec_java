package org.dorianferreira.service;

public class Slug {

    public static String slugify(String toSlug) {
        String slug = toSlug.toLowerCase();
        slug = slug.replaceAll("^([a-zA-Z]|[à-ü]|[À-Ü]|[0-9])", " ");
        slug = slug.replaceAll("\\s+", " ");
        slug = slug.replaceAll(" ", "-");
        return slug;
    }
}
