package com.jmp0x00;

public class Main {

    private static String serialize(Node root) {
        if (root == null) {
            return "{}";
        }
        return "{" +
                root.val.replace("{", "\\{").replace("}", "\\}") +
                serialize(root.left) + serialize(root.right) +
                "}";
    }

    private static Node deserialize(String s, int[] i) {
        if (s == null)
            return null;

        Integer valStart = null;
        Integer valEnd = null;
        Node left = null;
        Node right = null;
        boolean inBrackets = false;
        boolean leftIsTaken = false;
        boolean escaped = false;
        for (; i[0] < s.length(); i[0]++) {
            if (escaped) {
                escaped = false;
                continue;
            }
            switch (s.charAt(i[0])) {
                case '{':
                    if (!inBrackets) {
                        valStart = i[0] + 1;
                        inBrackets = true;
                    } else {
                        if (!leftIsTaken) {
                            valEnd = i[0];
                            left = deserialize(s, i);
                            leftIsTaken = true;
                        } else if (right == null) {
                            right = deserialize(s, i);
                        } else {
                            throw new IllegalStateException("Found unexpected open bracket");
                        }
                    }
                    break;
                case '}':
                    if (!inBrackets) {
                        throw new IllegalStateException("Found unexpected close bracket");
                    }
                    if (valEnd == null)
                        return null;
                    return new Node(s.substring(valStart, valEnd), left, right);
                case '\\':
                    escaped = true;
                    break;
                default:
                    break;
            }
        }
        return null;
    }

    private static Node deserialize(String s) {
        int[] i = {0};
        return deserialize(s, i);
    }

    public static void main(String[] args) {
        Node node = new Node("root", new Node("left", new Node("left.left")), new Node("right"));
        assert deserialize(serialize(node)).left.left.val.equals("left.left");
        System.out.println("SUCCEEDED");
    }
}
