package csen1002.main.task7;

import java.util.*;

/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

public class CfgLl1Parser {
    HashMap<String, LinkedHashSet<String>> rules;
    LinkedHashSet<String> variables;
    LinkedList<Character> terminals;

    HashMap<String, LinkedHashSet<String>> first = new HashMap<>();
    HashMap<String, LinkedHashSet<String>> follow = new HashMap<>();

    /**
     * Constructs a Context Free Grammar
     *
     * @param input A formatted string representation of the CFG, the First sets of
     *              each right-hand side, and the Follow sets of each variable. The
     *              string representation follows the one in the task description
     **/
    public CfgLl1Parser(String input) {
        rules = new HashMap<>();
        String[] parts = input.split("#");

        String[] variables = parts[0].split(";");
        this.variables = new LinkedHashSet<>(Arrays.asList(variables));

        String[] terminals = parts[1].split(";");
        this.terminals = new LinkedList<>();
        for (String t : terminals) {
            this.terminals.add(t.charAt(0));
        }

        String[] rulesForEachVariable = parts[2].split(";");
        for (String rule : rulesForEachVariable) {
            String[] splitted = rule.split("/");

            String LHS = splitted[0];
            String RHS = splitted[1];

            String[] variableRules = RHS.split(",");
            LinkedHashSet<String> variableRulesList = new LinkedHashSet<>();

            Collections.addAll(variableRulesList, variableRules);
            rules.put(LHS, variableRulesList);
        }

        String[] firstForEachVariable = parts[3].split(";");
        for (String first : firstForEachVariable) {
            String[] splitted = first.split("/");

            String LHS = splitted[0];
            String RHS = splitted[1];

            String[] variableFirst = RHS.split(",");
            LinkedHashSet<String> variableFirstList = new LinkedHashSet<>();

            Collections.addAll(variableFirstList, variableFirst);
            this.first.put(LHS, variableFirstList);
        }

        String[] followForEachVariable = parts[4].split(";");

        for (String follow : followForEachVariable) {
            String[] splitted = follow.split("/");

            String LHS = splitted[0];
            String RHS = splitted[1];

            String[] variableFollow = RHS.split("(?!^)");
            LinkedHashSet<String> variableFollowList = new LinkedHashSet<>();

            Collections.addAll(variableFollowList, variableFollow);
            this.follow.put(LHS, variableFollowList);
        }
    }

    public static void main(String[] args) {
        String cfg = "S;T#a;c;i#S/iST,e;T/cS,a#S/i,e;T/c,a#S/$ca;T/$ca";
//        "E;E';T;T';F#i;+;*;(;)#E/TE';E'/+TE',e;T/FT';T'/*FT',e;F/(E),i#E/(,i;E'/+,e;T/(,i;T'/*,e;F/(,i#E/)$;E'/)$;T/+)$;T'/+)$;F/+*)$";
//= "S;T#a;b#S/aTb,b;T/Ta,e";
        CfgLl1Parser parser = new CfgLl1Parser(cfg);
        parser.parse("iia");


    }

    /**
     * @param input The string to be parsed by the LL(1) CFG.
     * @return A string encoding a left-most derivation.
     */

    public ArrayList<String> getDerivations(String input, ParsingTable table) {
        Stack<String> stack = new Stack<>();
        int headIndex = 0;

        String currentDerivation = "S";
        ArrayList<String> derivations = new ArrayList<>();
        derivations.add(currentDerivation);

        stack.push("$");
        stack.push("S");

        while (headIndex <= input.length()) {

            String read;

            if (headIndex == input.length()) {
                read = "$";
                headIndex++;
            } else {
                read = input.charAt(headIndex) + "";
            }

            String stackTop = stack.peek();

//                if stackTop is terminal
            if (terminals.contains(stackTop.charAt(0))) {
                if (stackTop.equals(read)) {
                    stack.pop();
                    headIndex++;
                } else {
                    derivations.add("ERROR");
                    return derivations;
                }
            } else if (variables.contains(stackTop)) {
                String transition = table.getCellValue(stackTop, read);

                if (transition.equals("")) {
                    derivations.add("ERROR");
                    return derivations;
                }


                String[] transitionSplitted = transition.split("");
                Collections.reverse(Arrays.asList(transitionSplitted));

                stack.pop();

//                    replace left most variable of current derivation with the right side of the transition
                currentDerivation = currentDerivation.replaceFirst(stackTop, transition);

//                    remove any e from current derivation
                currentDerivation = currentDerivation.replace("e", "");
                derivations.add(currentDerivation);


                if (transitionSplitted[0].equals("e")) {
                    continue;
                }

                for (String symbol : transitionSplitted) {
                    stack.push(symbol);
                }


            }
        }

        return derivations;
    }

    public String parse(String input) {
        // TODO Auto-generated method stub
        ParsingTable table = new ParsingTable(this);

        ArrayList<String> derivations = getDerivations(input, table);
        System.out.println(derivations);

        return String.join(";", derivations);
    }

    public static class ParsingTable {
        private final Hashtable<Key, String> table;
        private final CfgLl1Parser parser;

        public ParsingTable(CfgLl1Parser parser) {
            this.parser = parser;
            table = new Hashtable<>();

            for (String variable : parser.variables) {
                for (Character terminal : parser.terminals) {

                    Key key = new Key(variable, terminal + "");
                    table.put(key, "");
                }

                table.put(new Key(variable, "$"), "");
            }

            table.keySet().forEach((key) -> {
                String A = key.variable;
                String a = key.terminal;
                HashMap<String, LinkedHashSet<String>> follow = parser.follow;


                for (String rule : parser.rules.get(A)) {
                    LinkedHashSet<String> firstAlpha = getFirstAlpha(rule);
                    LinkedHashSet<String> followA = follow.get(A);


                    String ruleToPut = null;
                    if (firstAlpha.contains(a)) {
                        ruleToPut = rule;
                    } else if (firstAlpha.contains("e") && followA.contains(a)) {
                        ruleToPut = rule;
                    }

                    if (ruleToPut != null) {
                        table.put(key, ruleToPut);
                    }

                }
            });
        }

        public LinkedHashSet<String> getFirstAlpha(String alpha) {
            for (int i = 0; i < alpha.length(); i++) {
                String A = alpha.charAt(i) + "";

                LinkedHashSet<String> firstA;

                if (parser.terminals.contains(A.charAt(0))) {
                    firstA = new LinkedHashSet<>();
                    firstA.add(alpha.charAt(i) + "");
                } else if (A.equals("e")) {
                    firstA = new LinkedHashSet<>();
                    firstA.add("e");
                } else {
                    firstA = parser.first.get(A);
                }

                if (!firstA.contains("e")) {
                    return firstA;
                }
            }

            LinkedHashSet<String> result = new LinkedHashSet<>();
            result.add("e");
            return result;
        }

        public String getCellValue(String variable, String terminal) {
            return table.get(new Key(variable, terminal));
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();

            // Print the header row
            sb.append(String.format("%-10s", ""));
            for (Character terminal : parser.terminals) {
                sb.append(String.format("%-10s", terminal));
            }
            sb.append(String.format("%-10s", "$"));
            sb.append("\n");
//             Print the rows
            for (String variable : parser.variables) {
                sb.append(String.format("%-10s", variable));
                for (Character terminal : parser.terminals) {
                    String value = table.get(new Key(variable, terminal + ""));
                    sb.append(String.format("%-10s", value));
                }
                String value = table.get(new Key(variable, "$"));
                sb.append(String.format("%-10s", value));
                sb.append("\n");
            }

            return sb.toString();
        }

        public static class Key {

            private final String variable;
            private final String terminal;

            public Key(String x, String y) {
                this.variable = x;
                this.terminal = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Key key = (Key) o;
                return Objects.equals(variable, key.variable) &&
                        Objects.equals(terminal, key.terminal);
            }


            @Override
            public int hashCode() {
                return Objects.hash(variable, terminal);
            }


        }
    }
}
