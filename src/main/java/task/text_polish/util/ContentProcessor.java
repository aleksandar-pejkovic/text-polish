package task.text_polish.util;

public final class ContentProcessor {

    private ContentProcessor() {
    }

    /**
     * Removes all meta tags from the provided content.
     * Meta tags are defined as any text enclosed within angle brackets (&lt; and &gt;).
     *
     * @param content the input text from which meta tags should be removed.
     *                If the content is null, the method returns null.
     * @return the text with meta tags removed, or null if the input is null.
     */
    public static String removeMetaTags(String content) {
        if (content == null) {
            return null;
        }
        return content.replaceAll("<[^>]+>", "");
    }
}
