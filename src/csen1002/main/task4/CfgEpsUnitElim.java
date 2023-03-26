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
    HashMap<Character, HashSet<String>> rules;
    LinkedList<Character> variables;
    LinkedList<Character> terminals;

    /**
     * Constructs a Context Free Grammar
     *
     * @param cfg A formatted string representation of the CFG. The string
     *            representation follows the one in the task description
     */
    public CfgEpsUnitElim(String cfg) {
        rules = new HashMap<Character, HashSet<String>>();
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
            HashSet<String> variableRulesList = new HashSet<String>();

            Collections.addAll(variableRulesList, variableRules);
            rules.put(variable, variableRulesList);
        }

    }

    public static void main(String[] args) {
        String cfg = "S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e";
        String cfg2 = "S;A;B#a;b#S/ASA,aB;A/B,S;B/b,e";
        String cfg3 = "S;A;B#a;b#S/AS,ASA,S,SA,a,aB;A/B,S;B/b";

        String test1 = "S;O;T;A;K;V#c;g;h;s;t#S/gAVA,tOKh;O/A,SsOAO,T,V,e,tVVO;T/A,KTVK,e;A/AThO,K,OSc,S,SOKt,TtTOs;K/A,AgTSs,V,e,gOOO;V/KVKK,cTA";
        String test2 = "S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e";

        CfgEpsUnitElim instance = new CfgEpsUnitElim(test2);
        System.out.println(instance.rules);

        instance.eliminateEpsilonRules();
//        instance.eliminateUnitRules();
        System.out.println(instance);

    }



    /**
     * @return Returns a formatted string representation of the CFG. The string
     * representation follows the one in the task description
     */
    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (char v : variables) {
            sb.append(v).append(";");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("#");

        for (char t : terminals) {
            sb.append(t).append(";");
        }

        sb.deleteCharAt(sb.length() - 1);
        sb.append("#");

        for (char v : variables) {
            sb.append(v).append("/");
            ArrayList<String> arr = new ArrayList<>(rules.get(v));
            Collections.sort(arr);

            for (String rule : arr) {
                sb.append(rule).append(",");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb.append(";");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public static List<String> getPossiblePermutations(String input, char variable) {
        List<String> permutations = new ArrayList<>();

        permutations.add(input);


        for (int i = 0; i < permutations.size(); i++) {
            String permutation = permutations.get(i);
            for (int j = 0; j < permutation.length(); j++) {
                if (permutation.charAt(j) == variable) {
                    String s =  permutation.substring(0, j) + permutation.substring(j + 1);
                    if (s.length() == 0)
                        s = "e";
                    permutations.add(s);
                }
            }
        }

        return permutations;
    }

    /**
     * Eliminates Epsilon Rules from the grammar
     */
    public void eliminateEpsilonRules() {
        ArrayList<Character> epsilonVariables = new ArrayList<Character>();
        HashSet<Character> eliminatedVariables = new HashSet<Character>();

        for (char v : variables) {
            if (rules.get(v).contains("e")) {
                epsilonVariables.add(v);
            }
        }

        while (epsilonVariables.size() > 0) {
            for (char epsVar : epsilonVariables) {
                eliminatedVariables.add(epsVar);
                rules.get(epsVar).remove("e");

                for (Character var : variables) {
                    HashSet<String> newRules = new HashSet<String>();

                    for (String rule : rules.get(var)) {
                        List<String> permutations = getPossiblePermutations(rule, epsVar);
                        newRules.addAll(permutations);
                    }

                    if (eliminatedVariables.contains(var)) {
                        newRules.remove("e");
                    }

                    rules.put(var, newRules);
                }
            }

            epsilonVariables.clear();

            for (char v : variables) {
                if (rules.get(v).contains("e")) {
                    epsilonVariables.add(v);
                }
            }
        }
    }

    /**
     * Eliminates Unit Rules from the grammar
     */

    public void eliminateUnitRules() {
        HashMap<Character, HashSet<String>> eliminatedVariables = new HashMap<>();
        LinkedList<Character> variableProductions = new LinkedList<Character>();

        for (char variable : variables) {
            HashSet<String> temp = new HashSet<String>();
            temp.add(variable + "");
            eliminatedVariables.put(variable, temp);
        }


        for (char variable : variables) {
            HashSet<String> rulesList = rules.get(variable);

            for (String rule : rulesList) {
                if (rule.length() == 1 && variables.contains(rule.charAt(0))) {
                    variableProductions.add(variable);
                    break;
                }
            }
        }

        while (variableProductions.size() > 0) {
            for (char variable : variableProductions) {
                HashSet<String> combinedRules = new HashSet<String>();
                ;
                HashSet<String> rulesList = rules.get(variable);

                for (String rule : rulesList) {
                    if (rule.length() == 1 && variables.contains(rule.charAt(0))) {


                        combinedRules.addAll(rulesList);
                        combinedRules.addAll(rules.get(rule.charAt(0)));

                        eliminatedVariables.get(variable).add(rule);
                        break;
                    }
                }

                HashSet<String> tempSet = new HashSet<>(combinedRules);
                tempSet.removeAll(eliminatedVariables.get(variable));

                rules.put(variable, tempSet);
            }

            variableProductions.clear();
            for (char variable : variables) {
                HashSet<String> rulesList = rules.get(variable);

                for (String rule : rulesList) {
                    if (rule.length() == 1 && variables.contains(rule.charAt(0))) {
                        variableProductions.add(variable);
                        break;
                    }
                }
            }
        }
    }

}
