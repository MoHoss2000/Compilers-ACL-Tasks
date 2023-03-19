package csen1002.main.task4;

import java.util.*;

/**
 * Write your info here
 *
 * @name Mohamed Hossam
 * @id 46-9261
 * @labNumber 16
 */

public class CfgEpsUnitElim {
//	variables
//	terminals
//	rules


    LinkedHashMap<Character, LinkedList<String>> rules;
    LinkedList<Character> variables;
    LinkedList<Character> terminals;

    /**
     * Constructs a Context Free Grammar
     *
     * @param cfg A formatted string representation of the CFG. The string
     *            representation follows the one in the task description
     */
    public CfgEpsUnitElim(String cfg) {
        rules = new LinkedHashMap<Character, LinkedList<String>>();
        String[] parts = cfg.split("#");

        String[] variables = parts[0].split(";");
        this.variables = new LinkedList<Character>();
        for (String v : variables) {
            this.variables.add(v.charAt(0));
        }

        String[] terminals = parts[1].split(";");
        this.terminals = new LinkedList<Character>();
        for (String t : terminals) {
            this.terminals.add(t.charAt(0));
        }

        String[] rulesForEachVariable = parts[2].split(";");
        for (String rule : rulesForEachVariable) {
            char variable = rule.charAt(0);
            System.out.println(variable);
            String[] variableRules = rule.substring(2).split(",");
            LinkedList<String> variableRulesList = new LinkedList<String>();

            Collections.addAll(variableRulesList, variableRules);
            rules.put(variable, variableRulesList);
        }

    }

    public static void main(String[] args) {
        String cfg = "S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e";
        String cfg2 = "S;A;B#a;b#S/ASA,aB;A/B,S;B/b,e";
        String cfg3 = "S;A;B#a;b#S/AS,ASA,S,SA,a,aB;A/B,S;B/b";

        CfgEpsUnitElim instance = new CfgEpsUnitElim(cfg2);
        System.out.println(instance.rules);

        instance.eliminateEpsilonRules();
//        instance.eliminateUnitRules();
        System.out.println(instance.rules);
    }

    /**
     * @return Returns a formatted string representation of the CFG. The string
     * representation follows the one in the task description
     */
    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * Eliminates Epsilon Rules from the grammar
     */
    /**
     * Eliminates Epsilon Rules from the grammar
     */
    /**
     * Eliminates Epsilon Rules from the grammar
     */
    public void eliminateEpsilonRules() {
        // For each variable A
        for (char variable : variables) {
            LinkedList<String> rulesList = rules.get(variable);
            LinkedList<String> newRulesList = new LinkedList<String>(rulesList);

            // For each rule of the form A -> epsilon
            for (String rule : rulesList) {
                if (rule.equals("e")) {
                    // For each rule of the form B -> u
                    for (char otherVariable : variables) {
                        LinkedList<String> otherRulesList = rules.get(otherVariable);
                        for (String otherRule : otherRulesList) {
                            // If the rule is not an epsilon rule, add it to A's rules
                            if (!otherRule.equals("e")) {
                                if (!newRulesList.contains(otherRule)) {
                                    newRulesList.add(otherRule);
                                }
                            }
                        }
                    }

                    // Remove the epsilon rule A -> epsilon
                    newRulesList.remove(rule);
                }
            }

            // Update A's rules
            rules.put(variable, newRulesList);
        }
    }



    /**
     * Eliminates Unit Rules from the grammar
     */
    /**
     * Eliminates Unit Rules from the grammar
     */
//    For each rule, r ∈ R, of the form A −→ B (where B ∈ V) do
//1 Let R = R − {r}
//2 For every rule of the form B −→ u ∈ R (where u ∈ (V1 ∪ Σ)+ and
//u ̸∈ V), let R = R ∪ {A −→ u}.
//3 For every rule of the form B −→ C ∈ R (where C ∈ V), then unless
//A −→ C has already been removed, let R = R ∪ {A −→ C}.
    public void eliminateUnitRules() {
        // For each variable A
        for (char variable : variables) {
            LinkedList<String> rulesList = rules.get(variable);
            LinkedList<String> newRulesList = new LinkedList<String>(rulesList);

            // For each rule of the form A -> B
            for (String rule : rulesList) {
                if (rule.length() == 1 && variables.contains(rule.charAt(0))) {
                    char unitVariable = rule.charAt(0);
                    LinkedList<String> unitRulesList = rules.get(unitVariable);

                    // For each rule of the form B -> u
                    for (String unitRule : unitRulesList) {
                        // If the rule is not a unit rule, add it to A's rules
                        if (!unitRule.equals(variable + "")) {
                            if (!newRulesList.contains(unitRule)) {
                                newRulesList.add(unitRule);
                            }
                        }
                    }

                    // Remove the unit rule A -> B
                    newRulesList.remove(rule);
                }
            }

            // Update A's rules
            rules.put(variable, newRulesList);
        }
    }



}
