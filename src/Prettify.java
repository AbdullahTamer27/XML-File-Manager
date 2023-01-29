public class Prettify {
    private String[] f;
    private static final String TAB = "    ";

    public Prettify(String[] file) {
        this.f = file;
    }

    public String[] pretty() {
        int indent = 0;
        for (int i = 0; i < f.length; i++) {
            String line = f[i];
            if (line.startsWith("<")) {
                if (line.startsWith("</")) {
                    indent--;
                }
                f[i] = getIndentation(indent) + line;
                if (!line.endsWith("/>") && !line.startsWith("</")) {
                    indent++;
                }
            }
        }
        return f;
    }

    private String getIndentation(int indent) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            sb.append(TAB);
        }
        return sb.toString();
    }
}