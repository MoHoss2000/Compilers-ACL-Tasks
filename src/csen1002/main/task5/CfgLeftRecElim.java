package csen1002.main.task5;

import java.util.*;

/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

public class CfgLeftRecElim {
    HashMap<String, LinkedHashSet<String>> rules;
    LinkedHashSet<String> variables;
    LinkedList<Character> terminals;

    /**
     * Constructs a Context Free Grammar
     *
     * @param cfg A formatted string representation of the CFG. The string
     *            representation follows the one in the task description
     */
    public CfgLeftRecElim(String cfg) {
        rules = new HashMap<>();
        String[] parts = cfg.split("#");

        String[] variables = parts[0].split(";");
        this.variables = new LinkedHashSet<>(Arrays.asList(variables));

        String[] terminals = parts[1].split(";");
        this.terminals = new LinkedList<>();
        for (String t : terminals) {
            this.terminals.add(t.charAt(0));
        }

        String[] rulesForEachVariable = parts[2].split(";");
        for (String rule : rulesForEachVariable) {
            String variable = rule.charAt(0) + "";
            System.out.println(variable);
            String[] variableRules = rule.substring(2).split(",");
            LinkedHashSet<String> variableRulesList = new LinkedHashSet<>();

            Collections.addAll(variableRulesList, variableRules);
            rules.put(variable, variableRulesList);
        }
    }

    public static void main(String[] args) {
        String test = "S;T;L#a;b;c;d;i#S/ScTi,La,Ti,b;T/aSb,LabS,i;L/SdL,Si";
//        String test2 = "S;T#a;b#S/Sab,Sb;T/ab,Tab";

        CfgLeftRecElim cfg = new CfgLeftRecElim(test);
        cfg.eliminateLeftRecursion();
        System.out.println(cfg);
    }

    /**
     * @return Returns a formatted string representation of the CFG. The string
     * representation follows the one in the task description
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (String v : variables) {
            sb.append(v).append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("#");

        for (char t : terminals) {
            sb.append(t).append(";");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("#");

        for (String v : variables) {
            sb.append(v).append("/");
            ArrayList<String> arr = new ArrayList<>(rules.get(v));
//            Collections.sort(arr);

            for (String rule : arr) {
                sb.append(rule).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(";");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * Eliminates Left Recursion from the grammar
     */
    public void eliminateLeftRecursion() {
        for (int i = 0; i < rules.keySet().size(); i++) {
            String variable = (String) variables.toArray()[i];

            if (variable.contains("'"))
                break;

//            eliminate indirect left recursion
            for (int j = 0; j < i; j++) {
                LinkedHashSet<String> variableRules = rules.get(variable);

                char variableFirstChar = ((String) variables.toArray()[j]).charAt(0);
                LinkedHashSet<String> newVariableRules = new LinkedHashSet<>();
                for (String rule : variableRules) {
                    char ruleFirstChar = rule.charAt(0);
                    if (ruleFirstChar == variableFirstChar) {
                        LinkedHashSet<String> variableRules2 = rules.get((String) variables.toArray()[j]);
                        for (String rule2 : variableRules2) {
                            String newRule = rule2 + rule.substring(1);
                            newVariableRules.add(newRule);
                        }
                    } else {
                        newVariableRules.add(rule);
                    }
                }

                rules.put(variable, newVariableRules);
            }

            //        eliminate immediate left recursion
            LinkedHashSet<String> variableRules = rules.get(variable);
            LinkedList<String> allAlphas = new LinkedList<>();
            LinkedList<String> allBetas = new LinkedList<>();

            for (String rule : variableRules) {
                char firstChar = rule.charAt(0);
                if (firstChar == variable.charAt(0) && rule.length() > 1) {
                    String alpha = rule.substring(1);
                    allAlphas.add(alpha);
                } else {
                    allBetas.add(rule);
                }
            }


            String primeVariable = variable + "'";


            if (!allAlphas.isEmpty()) {
                variableRules.clear();
                for (String rule : allBetas) {
                    variableRules.add(rule + primeVariable);
                }

                variables.add(primeVariable);
                LinkedHashSet<String> primeVariableRules = new LinkedHashSet<>();

                for (String rule : allAlphas) {
                    primeVariableRules.add(rule + primeVariable);
                }
                primeVariableRules.add("e");
                System.out.println(primeVariableRules);
                rules.put(primeVariable, primeVariableRules);

                variables.add(primeVariable);

            }

        }
    }
}
